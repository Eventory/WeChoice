package com.fuxuemingzhu.wechoice.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fuxuemingzhu.wechoice.R;


public class ToastUtil {
    public static void showToast(Context context, int resId) {
        View toastRoot = LayoutInflater.from(context).inflate(
                R.layout.toast_customer, null);
        ((TextView) toastRoot.findViewById(R.id.tv_toast_text)).setText(resId);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                BitmapHelper.dip2px(context, 58));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastRoot);
        toast.show();
    }

    public static void showToast(Context context, String text) {
        View toastRoot = LayoutInflater.from(context).inflate(
                R.layout.toast_customer, null);
        ((TextView) toastRoot.findViewById(R.id.tv_toast_text)).setText(text);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                BitmapHelper.dip2px(context, 58));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastRoot);
        toast.show();
    }
}
