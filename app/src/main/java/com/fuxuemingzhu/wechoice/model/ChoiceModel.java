package com.fuxuemingzhu.wechoice.model;

import android.content.Context;

import com.fuxuemingzhu.wechoice.config.API;
import com.fuxuemingzhu.wechoice.model.callback.DataCallback;
import com.jude.beam.model.AbsModel;
import com.jude.http.RequestManager;
import com.jude.http.RequestMap;

/**
 * Create by fuxuemingzhu
 */
public class ChoiceModel extends AbsModel {

    public static ChoiceModel getInstance() {
        return getInstance(ChoiceModel.class);
    }

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
    }

    public void getChoice(int page, DataCallback callback) {
        RequestMap map = new RequestMap();
        map.put("key", "d7bbe8531dc5a69516334aaafd698d98");
        map.put("pno", page + "");
        RequestManager.getInstance().post(API.URL.CHOICE_URL, map, callback);
    }

}
