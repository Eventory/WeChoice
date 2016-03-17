package com.fuxuemingzhu.wechoice.app;

import android.app.Application;

import com.fuxuemingzhu.wechoice.BuildConfig;
import com.fuxuemingzhu.wechoice.model.MyActivityLifeCycleDelegate;
import com.jude.beam.Beam;
import com.jude.http.RequestManager;
import com.jude.utils.JUtils;

/**
 * Created by Mr.Jude on 2015/8/20.
 */
public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JUtils.initialize(this);
        JUtils.setDebug(BuildConfig.DEBUG, "ChoiceLog");
        RequestManager.getInstance().init(this);
        RequestManager.getInstance().setDebugMode(BuildConfig.DEBUG, "ChoiceNet");
        Beam.init(this);
        Beam.setActivityLifeCycleDelegateProvider(MyActivityLifeCycleDelegate::new);
    }
}
