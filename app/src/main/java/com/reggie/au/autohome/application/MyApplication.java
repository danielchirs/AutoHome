package com.reggie.au.autohome.application;

import com.tandong.sa.sql.ActiveAndroid;

/**
 * Created by daniel on 2015/3/22.
 */
public class MyApplication extends com.tandong.sa.sql.app.Application  {

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("=====1111111111======>");
        ActiveAndroid.initialize(this);
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        //ActiveAndroid.dispose();//清理
    }

}
