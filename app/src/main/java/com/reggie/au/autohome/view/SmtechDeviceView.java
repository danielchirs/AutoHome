package com.reggie.au.autohome.view;

/**
 * Created by daniel on 2015/3/29.
 */
public class SmtechDeviceView {
    public String name;//名称
    public int type;//设备类型(1,2,3属于灯,6空调)
    public String icon;//图标
    public String machinecode;//机器码
    public int layoutid;//样式数值


    public SmtechDeviceView() {
        super();
    }

    public SmtechDeviceView(String name,int type,String icon, String machinecode, int layoutid) {
        this.name=name;
        this.type=type;
        this.icon=icon;
        this.machinecode=machinecode;
        this.layoutid=layoutid;
    }


}
