package com.fuxuemingzhu.wechoice.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.fuxuemingzhu.wechoice.utils.IntentUtil;
import com.fuxuemingzhu.wechoice.utils.ToastUtil;


/**
 * BaseFragmentActivity
 *
 * @version 1.0.0
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    public BaseFragmentActivity() {
        // TODO Auto-generated constructor stub
    }

    protected abstract void initViews();

    protected abstract void initEvents();

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
    }

    protected void showCustomToast(int resId) {
        ToastUtil.showToast(BaseFragmentActivity.this, resId);
    }

    protected void showCustomToast(String text) {
        ToastUtil.showToast(BaseFragmentActivity.this, text);
    }

    protected void processHandler(Handler handler, int what) {
        Message msg = handler.obtainMessage();
        msg.what = what;
        handler.sendMessage(msg);
    }

    protected void showLogDebug(String tag, String msg) {
        Log.d(tag, msg);
    }

    protected void showLogError(String tag, String msg) {
        Log.e(tag, msg);
    }

    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    protected void startActivity(Class<?> cls, Bundle bundle) {
        IntentUtil.startActivity(this, cls, bundle);
    }

    protected void startActivity(String action) {
        startActivity(action, null);
    }

    protected void startActivity(String action, Bundle bundle) {
        IntentUtil.startActivity(this, action, bundle);
    }

    @Override
    public void finish() {
        super.finish();
    }

    protected void defaultFinish() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
