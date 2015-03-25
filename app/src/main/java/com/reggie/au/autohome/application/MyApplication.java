package com.reggie.au.autohome.application;

import com.tandong.sa.sql.ActiveAndroid;
import com.tandong.sa.sql.Configuration;
import com.tandong.sa.system.SystemInfo;

/**
 * Created by daniel on 2015/3/22.
 */
public class MyApplication extends com.tandong.sa.sql.app.Application  {

    private SystemInfo systemInfo;
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();//清理
    }

}
