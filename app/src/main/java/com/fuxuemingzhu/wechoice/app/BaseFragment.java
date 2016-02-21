/**
 * com.hmammon.campuscalendar.app
 * BaseFragment.java
 */
package com.fuxuemingzhu.wechoice.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.fuxuemingzhu.wechoice.utils.IntentUtil;
import com.fuxuemingzhu.wechoice.utils.ToastUtil;


/**
 * BaseFragment
 *
 * @version 1.0.0
 */
public class BaseFragment extends Fragment {

    /**
     * 创建一个新的实例 BaseFragment.
     */
    public BaseFragment() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }

    /**
     * 显示自定义Toast提示(来自res) *
     */
    protected void showCustomToast(int resId) {
        ToastUtil.showToast(getActivity(), resId);
    }

    /**
     * 显示自定义Toast提示(来自String) *
     */
    protected void showCustomToast(String text) {
        ToastUtil.showToast(getActivity(), text);
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
        IntentUtil.startActivity(getActivity(), cls, bundle);
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
        IntentUtil.startActivity(getActivity(), action, bundle);
    }

}
