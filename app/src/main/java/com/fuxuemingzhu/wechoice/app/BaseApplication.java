/**
 * com.hmammon.accounting.app
 * SSApp.java
 * <p/>
 * 2014-5-22-����1:47:49
 */
package com.fuxuemingzhu.wechoice.app;

import android.app.Application;


/**
 * SSApp
 *
 * @version 1.0.0
 */
public class BaseApplication extends Application {

    /**
     * 创建一个新的实例 SSApp.
     */
    public BaseApplication() {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        AppData.initData(getApplicationContext());
        // LeanCLoud
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Application#onTerminate()
     */
    @Override
    public void onTerminate() {
        // TODO Auto-generated method stub
        super.onTerminate();
    }

}
