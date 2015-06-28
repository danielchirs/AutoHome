package com.reggie.au.autohome.command;

import com.reggie.au.autohome.view.SmtechDeviceView;
import com.reggie.au.autohome.view.SmtechHouseInfoView;
import com.reggie.au.autohome.view.SmtechHouseView;
import com.reggie.au.autohome.view.SmtechSceneModeView;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daniel on 2015/3/29.
 */
public class SmtechData {
    public static SmtechHouseInfoView houseInfo=new SmtechHouseInfoView();//房子基础信息
    public static List<SmtechHouseView> houseList=new ArrayList<SmtechHouseView>();//房间列表
    public static Map<String,List<SmtechDeviceView>> dataMap=new HashMap<String,List<SmtechDeviceView>>();//家居配置数据列表
    public static List<SmtechSceneModeView> modList=new ArrayList<SmtechSceneModeView>();//情景数据列表
//    public static Map<String,SmtechSceneModeView> modMap=new HashMap<String,SmtechSceneModeView>();//情景数据列表
    public static Socket sever;
    public static Map<String,String> stratus=new HashMap<String,String>();//记录控件最后状态
    public SmtechData() {
        super();
    }

}
