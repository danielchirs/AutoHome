package com.reggie.au.autohome.application;

import android.os.Environment;

import com.reggie.au.autohome.command.Configuration;
import com.tandong.sa.appInfo.AppInfo;
import com.tandong.sa.sql.ActiveAndroid;
import com.tandong.sa.tools.AssistTool;

/**
 * Created by daniel on 2015/3/22.
 * 该方法在初始时候执行一次
 * 用于初始的流程可以放入改方法内，包括升级更新的
 */
public class MyApplication extends com.tandong.sa.sql.app.Application  {

    private AssistTool assistTool;
    private AppInfo appInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        appInfo=new AppInfo(this);
        assistTool = new AssistTool(this);
        assistTool.userinfo= Configuration.userinfo;//设置配置文件名称
        ActiveAndroid.initialize(this);//加载初始数据库
        // 写入一个标识
        assistTool.savePreferenceBoolean(Configuration.app_state,true);
        //写入首次安装时间
        assistTool.savePreferenceLong(Configuration.app_fasttime,appInfo.getFirstInstallTime(this));

        System.out.println("========>"+Environment.getExternalStorageDirectory()+"/smtechcache/");
        //创建一个文件夹存放文件
        assistTool.AutoMakeDir(Environment.getExternalStorageDirectory()+"/smtechcache/");


//        if(!flag){
//            System.out.println("===执行1111====>");
//            assistTool.savePreferenceBoolean("msetch_init",true);
//        }else{
//            System.out.println("===执行2222====>");
//
//        }


        //boolean flag=assistTool.savePreferenceBoolean("msetch_init",false);


        System.out.println("===isLogin====>" + assistTool.userinfo);
//        System.out.println("===第一次安装时间====>"+firstInstallTime);
//        System.out.println("===最后使用时间====>"+lastUpdateTime);


    }

    //系统初始环境
    private void init(){



    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();//清理
    }

}
