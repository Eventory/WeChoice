package com.fuxuemingzhu.wechoice.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.*;
import android.widget.LinearLayout;

import com.fuxuemingzhu.wechoice.R;
import com.fuxuemingzhu.wechoice.app.BaseActivity;
import com.fuxuemingzhu.wechoice.utils.Logcat;

import java.util.Calendar;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

public class ContentActivity extends BaseActivity {
    private String title;
    private String number;

    WebView webview = null;

    String url;

    private LinearLayout ll_common_error_page = null;
    SmoothProgressBar progressBar = null;

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
        loadWeb();
    }

    @Override
    protected void initViews() {
        webview = (WebView) findViewById(R.id.wb_common);
        ll_common_error_page = (LinearLayout) findViewById(R.id.ll_common_error_page);
        progressBar = (SmoothProgressBar) findViewById(R.id.pb_content_above);

    }

    @Override
    protected void initEvents() {
        ll_common_error_page.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                loadWeb();
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    protected void initWeb() {
        webview.setWebViewClient(new myWebViewClient());
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    progressBar.setVisibility(View.INVISIBLE);
                } else if (ll_common_error_page.getVisibility() == View.VISIBLE
                        && progress >= 50) {
                    hideErrorPage();
                } else {

                    if (progressBar.getVisibility() == View.INVISIBLE) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }
            }

        });
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

    private void loadWeb() {
        if (isOnline()) {
            initWeb();
        } else {
            showErrorPage();
        }
    }

    private class myWebViewClient extends WebViewClient {
        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            showErrorPage();
            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            showErrorPage();
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            Logcat.i("WEB_VIEW_TEST", "error code:" + error);
            showErrorPage();
            super.onReceivedError(view, request, error);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getBaseContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = cm.getActiveNetworkInfo();
        if ((i == null) || (!i.isConnected())) {
            Logcat.i("network info", "Error: No connection to Internet");
            return false;
        }
        return true;
    }

    public abstract class NoDoubleClickListener implements View.OnClickListener {

        public static final int MIN_CLICK_DELAY_TIME = 500;
        private long lastClickTime = 0;

        public abstract void onNoDoubleClick(View v);

        @Override
        public void onClick(View v) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                onNoDoubleClick(v);
            } else {
                Logcat.i("click", "on double click.");
            }
        }
    }

    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    protected void showErrorPage() {

        ll_common_error_page.setVisibility(View.VISIBLE);

    }

    protected void hideErrorPage() {
        ll_common_error_page.setVisibility(View.INVISIBLE);
    }
}
