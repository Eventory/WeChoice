package com.fuxuemingzhu.wechoice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.fuxuemingzhu.wechoice.R;
import com.fuxuemingzhu.wechoice.adapter.ChoiceAdapter;
import com.fuxuemingzhu.wechoice.app.BaseActivity;
import com.fuxuemingzhu.wechoice.entity.Choice;
import com.fuxuemingzhu.wechoice.utils.Logcat;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import okhttp3.Call;

public class MainActivity extends BaseActivity {

    private String url = "http://v.juhe.cn/weixin/query";
    private static String APPKEY = "d7bbe8531dc5a69516334aaafd698d98";
    private List<Choice> listChoice = new ArrayList<>();

    private ListView lv_choices;
    private ListAdapter listAdapter;

    private MaterialRefreshLayout materialRefreshLayout;
    private int pages = 1;
    final int QUEUE_SIZE = 10;//队列大小
    //手写队列用来存储已经加载过的文章页数
    Queue<Integer> autoQueue = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
    }

    @Override
    protected void initViews() {
        lv_choices = (ListView) findViewById(R.id.lv_main_choices);
        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh_main);
    }

    @Override
    protected void initEvents() {
        getStringContent();
        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.finishRefreshLoadMore();
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                while (autoQueue.contains(pages)) {
                    pages = (int) Math.ceil(Math.random() * 25);
                }
                getStringContent();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                showCustomToast("onRefreshLoadMore");
            }

            @Override
            public void onfinish() {
                super.onfinish();
            }
        });

    }

    private void getStringContent() {
        if (autoQueue.size() == QUEUE_SIZE) {
            autoQueue.poll();
        }
        autoQueue.offer(pages);
        Logcat.i("autoQueue", autoQueue.toString());
        OkHttpUtils
                .get()
                .url(url)
                .addParams("pno", Integer.toString(pages))
                .addParams("key", APPKEY)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        materialRefreshLayout.finishRefresh();
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        materialRefreshLayout.finishRefresh();
                        if (response == null) {
                            Toast.makeText(MainActivity.this, "response is null", Toast.LENGTH_LONG)
                                    .show();
                            return;
                        }
                        JSONObject responseJson = JSON.parseObject(response);
                        JSONObject result = responseJson.getJSONObject("result");
                        JSONArray jsonList = result.getJSONArray("list");
                        listChoice.clear();
                        for (int i = 0; i < jsonList.size(); i++) {
                            JSONObject choiceJson = jsonList.getJSONObject(i);
                            Choice choice = JSON.parseObject(choiceJson.toJSONString(), Choice.class);
                            listChoice.add(choice);
                        }
                        String listString = "";
                        for (int i = 0; i < listChoice.size(); i++) {
                            listString += listChoice.get(i).toString();
                        }
                        listAdapter = new ChoiceAdapter(MainActivity.this, listChoice);
                        lv_choices.setAdapter(listAdapter);
                        lv_choices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Bundle data = new Bundle();
                                data.putString("url", listChoice.get(i).getUrl());
                                // 创建一个Intent
                                Intent intent = new Intent(MainActivity.this,
                                        ContentActivity.class);
                                intent.putExtras(data);
                                // 启动intent对应的Activity
                                startActivity(intent);
                            }
                        });
                        Logcat.i("response", listString);
                    }
                });
    }

}
