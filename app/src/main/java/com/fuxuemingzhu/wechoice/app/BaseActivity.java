package com.fuxuemingzhu.wechoice.app;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fuxuemingzhu.wechoice.utils.IntentUtil;
import com.fuxuemingzhu.wechoice.utils.ToastUtil;


/**
 * <p>
 * Title: BaseActivity
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author fuxuemingzhu
 * @email fuxuemingzhu@163.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected BaseApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化视图 *
     */
    protected abstract void initViews();

    /**
     * 初始化事件 *
     */
    protected abstract void initEvents();

    /**
     * 显示自定义Toast提示(来自res) *
     */
    protected void showCustomToast(int resId) {
        ToastUtil.showToast(BaseActivity.this, resId);
    }

    /**
     * 显示自定义Toast提示(来自String) *
     */
    protected void showCustomToast(String text) {
        ToastUtil.showToast(BaseActivity.this, text);
    }

    protected void processHandler(Handler handler, int what) {
        Message msg = handler.obtainMessage();
        msg.what = what;
        handler.sendMessage(msg);
    }

    /**
     * Debug输出Log日志 *
     */
    protected void showLogDebug(String tag, String msg) {
        Log.d(tag, msg);
    }

    /**
     * Error输出Log日志 *
     */
    protected void showLogError(String tag, String msg) {
        Log.e(tag, msg);
    }

    /**
     * 通过Class跳转界面 *
     */
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面 *
     */
    protected void startActivity(Class<?> cls, Bundle bundle) {
        IntentUtil.startActivity(this, cls, bundle);
    }

    /**
     * （非 Javadoc）
     * <p>
     * Title: getResources
     * </p>
     * <p>
     * Description:设置字体不随系统字体变化
     * </p>
     *
     * @return
     *
     * @see android.view.ContextThemeWrapper#getResources()
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * 通过Action跳转界面 *
     */
    protected void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面 *
     */
    protected void startActivity(String action, Bundle bundle) {
        IntentUtil.startActivity(this, action, bundle);
    }

    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 默认退出 *
     */
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
