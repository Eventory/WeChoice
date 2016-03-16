package com.fuxuemingzhu.wechoice.model.callback;

import com.fuxuemingzhu.wechoice.config.API;
import com.jude.utils.JUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mr.Jude on 2015/5/25.
 */
public abstract class StatusCallback extends LinkCallback {
    @Override
    public void onRequest() {
        super.onRequest();
    }

    @Override
    public void onSuccess(String s) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(s);
            String status = jsonObject.getString(API.KEY.STATUS);
            if (status.equals(API.CODE.SUCCEED)) {
                success(status);
            } else {
                error(status);
            }
        } catch (JSONException e) {
            error("数据解析错误");
        }
        super.onSuccess(s);
    }

    @Override
    public void onError(String s) {
        result(-1, "网络错误");
        error("网络错误");
        super.onError(s);
    }

    public void result(int status, String info) {
    }

    public abstract void success(String info);

    public void error(String errorInfo) {
        JUtils.Toast(errorInfo);
    }
}
