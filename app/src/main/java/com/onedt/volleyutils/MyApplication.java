package com.onedt.volleyutils;

import android.app.Application;

/**
 * Created by Administrator on 2015/8/9.
 */
public class MyApplication extends Application {
    public static Application app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
    public static Application getApp(){
        return  app;
    }
}
