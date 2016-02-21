package com.fuxuemingzhu.wechoice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fuxuemingzhu.wechoice.R;
import com.fuxuemingzhu.wechoice.adapter.ChoiceAdapter;
import com.fuxuemingzhu.wechoice.app.BaseActivity;
import com.fuxuemingzhu.wechoice.entity.Choice;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    private String url = "http://v.juhe.cn/weixin/query";
    private static String APPKEY = "d7bbe8531dc5a69516334aaafd698d98";
    private List<Choice> listChoice = new ArrayList<>();

    private ListView lv_choices;
    private ListAdapter listAdapter;

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
    }

    @Override
    protected void initEvents() {
        getStringContent();
    }

    private void getStringContent() {
        OkHttpUtils
                .get()
                .url(url)
                .addParams("key", APPKEY)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response == null) {
                            Toast.makeText(MainActivity.this, "response is real null", Toast.LENGTH_LONG)
                                    .show();
                            return;
                        }
                        JSONObject responseJson = JSON.parseObject(response);
                        JSONObject result = responseJson.getJSONObject("result");
                        JSONArray jsonList = result.getJSONArray("list");
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
                        Log.i("response", listString);
                    }
                });
    }

    private void getContent() {
        OkHttpUtils
                .get()
                .url(url)
                .addParams("key", APPKEY)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        if (response != null) {
                            Log.i("parseNetworkResponse", response.toString());
                        }
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(Object response) {
                        if (response == null) {
                            Toast.makeText(MainActivity.this, "response is real null", Toast.LENGTH_LONG)
                                    .show();
                            return;
                        }
                        Log.i("response", response.toString());
                    }
                });
    }

}
