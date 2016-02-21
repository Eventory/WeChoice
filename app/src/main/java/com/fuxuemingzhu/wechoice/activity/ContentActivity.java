package com.fuxuemingzhu.wechoice.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.fuxuemingzhu.wechoice.R;
import com.fuxuemingzhu.wechoice.app.BaseActivity;

public class ContentActivity extends BaseActivity {
    private String title;
    private String number;

    WebView webview = null;
    RelativeLayout rl_common_bar = null;
    String url;


    private Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        // ////////////////////////////////
        initViews();
        // ///////////////////////////////
        // 获取启动该Result的Intent
        Intent intent = getIntent();
        // 获取该intent所携带的数据

        data = intent.getExtras();
        // 从Bundle包中取出数据
        url = data.getString("url");


        initEvents();
        initWeb();
    }

    @Override
    protected void initViews() {
        webview = (WebView) findViewById(R.id.wb_common);

    }

    @Override
    protected void initEvents() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    protected void initWeb() {
        webview.setWebViewClient(new myWebViewClient());
        // 支持缩放
        WebSettings websettings = webview.getSettings();
        websettings.setBuiltInZoomControls(true);
        websettings.setDisplayZoomControls(false);// 要求API大于11

        // 去掉滚动条
        // webview.setVerticalScrollBarEnabled(false);
        webview.setHorizontalScrollBarEnabled(false);

        // 设置webview自适应屏幕大小
        websettings.setUseWideViewPort(true);
        websettings.setLoadWithOverviewMode(true);

        // 支持与javascript交互
        websettings.setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }


    private class myWebViewClient extends WebViewClient {
        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            showErrorPage();
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    protected void showErrorPage() {

        //ll_common_error_page.setVisibility(View.VISIBLE);

    }

    protected void hideErrorPage() {
        //ll_common_error_page.setVisibility(View.INVISIBLE);
    }
}
