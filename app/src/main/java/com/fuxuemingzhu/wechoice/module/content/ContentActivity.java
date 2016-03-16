package com.fuxuemingzhu.wechoice.module.content;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.webkit.*;

import com.fuxuemingzhu.wechoice.R;
import com.fuxuemingzhu.wechoice.utils.Logcat;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ContentPresenter.class)
public class ContentActivity extends BeamBaseActivity {

    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Bind(R.id.wb_common)
    WebView webview = null;


    private Bundle data;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        // ////////////////////////////////
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        ButterKnife.bind(this);

        // ///////////////////////////////
        // 获取启动该Result的Intent
        Intent intent = getIntent();
        // 获取该intent所携带的数据

        data = intent.getExtras();
        // 从Bundle包中取出数据
        url = data.getString("url");


        loadWeb();
    }


    @SuppressLint("SetJavaScriptEnabled")
    protected void initWeb() {
        webview.setWebViewClient(new myWebViewClient());
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress >= 80) {
                    getExpansion().dismissErrorPage();
                    getExpansion().dismissProgressPage();
                } else {
                    getExpansion().showProgressPage();
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
            getExpansion().showErrorPage();
        }
    }

    private class myWebViewClient extends WebViewClient {
        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            getExpansion().showErrorPage();
            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            getExpansion().showErrorPage();
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            Logcat.i("WEB_VIEW_TEST", "error code:" + error);
            getExpansion().showErrorPage();
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            getExpansion().dismissProgressDialog();
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

}
