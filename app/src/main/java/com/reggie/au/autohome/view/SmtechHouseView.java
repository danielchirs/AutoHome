package com.reggie.au.autohome.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 2015/3/29.
 * 家居模式配置
 */
public class SmtechHouseView {
    public int rid;
    public String name;
    public List<SmtechDeviceView> deviceList = new ArrayList<SmtechDeviceView>();//设备集合
}
