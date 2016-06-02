package com.example.zhuangqf.contacts;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by zhuangqf on 6/2/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
