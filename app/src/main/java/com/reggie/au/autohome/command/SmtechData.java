package com.reggie.au.autohome.command;

import com.reggie.au.autohome.view.SmtechHouseInfoView;
import com.reggie.au.autohome.view.SmtechHouseView;
import com.reggie.au.autohome.view.SmtechSceneModeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daniel on 2015/3/29.
 */
public class SmtechData {
    public static SmtechHouseInfoView houseInfo=new SmtechHouseInfoView();//房子基础信息
    public static Map<String,SmtechHouseView> houseMap=new HashMap<String,SmtechHouseView>();//家居配置数据列表
    public static Map<String,SmtechSceneModeView> sceneMap=new HashMap<String,SmtechSceneModeView>();//情景数据列表
    public SmtechData() {
        super();
    }

}
