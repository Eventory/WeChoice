package com.fuxuemingzhu.wechoice.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.fuxuemingzhu.wechoice.R;
import com.fuxuemingzhu.wechoice.module.choice.ChoiceFragment;
import com.fuxuemingzhu.wechoice.module.choice.ChoicePresenter;
import com.fuxuemingzhu.wechoice.module.setting.AboutUsActivity;
import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BeamBaseActivity<MainPresenter> {

    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragmentManager = getSupportFragmentManager();


        addFragment(new ChoiceFragment());

    }

    private void addFragment(BeamFragment<ChoicePresenter> fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //是rl 不是ll
        //好可恶啊，整了好久  就是因为这个搞错了
        fragmentTransaction.replace(R.id.rl_main, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            startActivity(new Intent(this, AboutUsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //private String url = "http://v.juhe.cn/weixin/query";
    //private static String APPKEY = "d7bbe8531dc5a69516334aaafd698d98";
    //private List<Choice> listChoice = new ArrayList<>();
    //
    //private RelativeLayout ll_load_more;
    //
    //private ListView lv_choices;
    //private ChoiceAdapter listAdapter;
    //
    //private MaterialRefreshLayout materialRefreshLayout;
    //private int refreshPages = 1;
    //private int morePages = 1;
    //final int QUEUE_SIZE = 10;//队列大小
    ////手写队列用来存储已经加载过的文章页数
    //Queue<Integer> refreshQueue = new LinkedList<>();
    //Queue<Integer> moreQueue = new LinkedList<>();
    //
    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);
    //    initViews();
    //    initEvents();
    //    displayDefaultContent();
    //}
    //
    //@Override
    //protected void initViews() {
    //    lv_choices = (ListView) findViewById(R.id.lv_main_choices);
    //    materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh_main);
    //    ll_load_more = (RelativeLayout) ((LayoutInflater) this.getSystemService(Context
    //            .LAYOUT_INFLATER_SERVICE)).inflate(R
    //            .layout.foot_more, null, false);
    //    lv_choices.addFooterView(ll_load_more);
    //
    //}
    //
    //@Override
    //protected void initEvents() {
    //    materialRefreshLayout.setLoadMore(true);
    //    materialRefreshLayout.finishRefreshLoadMore();
    //    materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
    //        @Override
    //        public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
    //            while (refreshQueue.contains(refreshPages)) {
    //                refreshPages = (int) Math.ceil(Math.random() * 25);
    //            }
    //            getFreshContent();
    //        }
    //
    //        @Override
    //        public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
    //            super.onRefreshLoadMore(materialRefreshLayout);
    //
    //            while (moreQueue.contains(morePages)) {
    //                morePages = (int) Math.ceil(Math.random() * 25);
    //            }
    //            getMoreContent();
    //        }
    //
    //        @Override
    //        public void onfinish() {
    //            super.onfinish();
    //        }
    //    });
    //    materialRefreshLayout.autoRefresh();//drop-down refresh automatically
    //}
    //
    //private void getFreshContent() {
    //    if (refreshQueue.size() == QUEUE_SIZE) {
    //        refreshQueue.poll();
    //    }
    //    refreshQueue.offer(refreshPages);
    //    moreQueue.clear();
    //    moreQueue.offer(refreshPages);
    //    Logcat.i("refreshQueue", refreshQueue.toString());
    //    OkHttpUtils
    //            .get()
    //            .url(url)
    //            .addParams("pno", Integer.toString(refreshPages))
    //            .addParams("key", APPKEY)
    //            .build()
    //            .execute(new StringCallback() {
    //                @Override
    //                public void onError(Call call, Exception e) {
    //                    materialRefreshLayout.finishRefresh();
    //                    MainActivity.this.showCustomToast("网络异常，请稍后重试");
    //                }
    //
    //                @Override
    //                public void onResponse(String response) {
    //                    materialRefreshLayout.finishRefresh();
    //                    if (response == null) {
    //                        MainActivity.this.showCustomToast("网络异常，请稍后重试");
    //                        return;
    //                    }
    //                    AppData.getInstance(MainActivity.this).setDefaultContent(response);
    //                    displayContent(response);
    //                }
    //            });
    //}
    //
    //private void displayContent(String response) {
    //    JSONObject responseJson = JSON.parseObject(response);
    //    JSONObject result = responseJson.getJSONObject("result");
    //    JSONArray jsonList = result.getJSONArray("list");
    //    listChoice.clear();
    //    for (int i = 0; i < jsonList.size(); i++) {
    //        JSONObject choiceJson = jsonList.getJSONObject(i);
    //        Choice choice = JSON.parseObject(choiceJson.toJSONString(), Choice.class);
    //        listChoice.add(choice);
    //    }
    //    String listString = "";
    //    for (int i = 0; i < listChoice.size(); i++) {
    //        listString += listChoice.get(i).toString();
    //    }
    //    listAdapter = new ChoiceAdapter(MainActivity.this, listChoice);
    //    lv_choices.setAdapter(listAdapter);
    //    lv_choices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    //        @Override
    //        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    //            Bundle data = new Bundle();
    //            data.putString("url", listChoice.get(i).getUrl());
    //            // 创建一个Intent
    //            Intent intent = new Intent(MainActivity.this,
    //                    ContentActivity.class);
    //            intent.putExtras(data);
    //            // 启动intent对应的Activity
    //            startActivity(intent);
    //        }
    //    });
    //    Logcat.i("response", listString);
    //}
    //
    //private void displayDefaultContent() {
    //    String defaultResponse = AppData.getInstance(this).getDefaultContent();
    //    if (defaultResponse != null && !defaultResponse
    //            .equals("")) {
    //        displayContent(defaultResponse);
    //    }
    //}
    //
    //private void getMoreContent() {
    //    if (moreQueue.size() == 25) {
    //        moreQueue.clear();
    //        moreQueue.offer(refreshPages);
    //    }
    //    moreQueue.offer(morePages);
    //    Logcat.i("moreQueue", moreQueue.toString());
    //    OkHttpUtils
    //            .get()
    //            .url(url)
    //            .addParams("pno", Integer.toString(morePages))
    //            .addParams("key", APPKEY)
    //            .build()
    //            .execute(new StringCallback() {
    //                @Override
    //                public void onError(Call call, Exception e) {
    //                    materialRefreshLayout.finishRefreshLoadMore();
    //                    MainActivity.this.showCustomToast("网络异常，请稍后重试");
    //                }
    //
    //                @Override
    //                public void onResponse(String response) {
    //                    materialRefreshLayout.finishRefreshLoadMore();
    //                    if (response == null) {
    //                        MainActivity.this.showCustomToast("网络异常，请稍后重试");
    //                        return;
    //                    }
    //                    JSONObject responseJson = JSON.parseObject(response);
    //                    JSONObject result = responseJson.getJSONObject("result");
    //                    JSONArray jsonList = result.getJSONArray("list");
    //                    for (int i = 0; i < jsonList.size(); i++) {
    //                        JSONObject choiceJson = jsonList.getJSONObject(i);
    //                        Choice choice = JSON.parseObject(choiceJson.toJSONString(), Choice.class);
    //                        listChoice.add(choice);
    //                    }
    //                    String listString = "";
    //                    for (int i = 0; i < listChoice.size(); i++) {
    //                        listString += listChoice.get(i).toString();
    //                    }
    //                    listAdapter.notifyDataSetChanged();
    //                    Logcat.i("response", listString);
    //                }
    //            });
    //}

}
