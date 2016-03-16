package com.fuxuemingzhu.wechoice.model.callback;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fuxuemingzhu.wechoice.config.API;
import com.fuxuemingzhu.wechoice.model.bean.Choice;
import com.fuxuemingzhu.wechoice.model.bean.ChoicePage;
import com.jude.utils.JUtils;

import java.util.ArrayList;

/**
 * Created by fuxuemingzhu on 15/5/11.
 */
public abstract class DataCallback extends LinkCallback {

    @Override
    public void onRequest() {
        super.onRequest();
    }

    @Override
    public void onSuccess(String response) {


        com.alibaba.fastjson.JSONObject jsonObject;
        String status = null;
        com.alibaba.fastjson.JSONObject result = null;
        ChoicePage data = new ChoicePage();
        try {
            jsonObject = JSON.parseObject(response);
            status = jsonObject.getString(API.KEY.STATUS);

            JUtils.Log("status", status);

            if (status.equals(API.CODE.SUCCEED)) {
                result = jsonObject.getJSONObject(API.KEY.INFO);
                JUtils.Log("result", result.toJSONString());
                JUtils.Log("success", "init");
                JSONArray list = result.getJSONArray(API.KEY.DATA);
                ArrayList<Choice> listChoice = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    JSONObject choiceJson = list.getJSONObject(i);
                    Choice choice = JSON.parseObject(choiceJson.toJSONString(), Choice.class);
                    listChoice.add(choice);
                }
                JUtils.Log(listChoice.toString());
                data.setContentlist(listChoice);
            }
        } catch (Exception e) {
            JUtils.Log(e.getLocalizedMessage());
            error("数据解析错误");
            return;
        }
        result(status, result.toJSONString());
        if (status.equals(API.CODE.SUCCEED)) {
            success(status, data);
        } else {
            error(result.toJSONString());
        }
        super.onSuccess(response);
    }

    @Override
    public void onError(String s) {
        result("error", "网络错误");
        error("网络错误");
        super.onError(s);
    }

    public void result(String status, String info) {
    }

    public abstract void success(String info, ChoicePage data);

    public void error(String errorInfo) {
        JUtils.Toast(errorInfo);
    }

}
