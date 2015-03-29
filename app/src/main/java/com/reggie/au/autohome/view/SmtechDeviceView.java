package com.reggie.au.autohome.view;

/**
 * Created by daniel on 2015/3/29.
 */
public class SmtechDeviceView {
    public String name;//名称
    public String machinecode;//机器码
    public int layoutid;//样式数值


    public SmtechDeviceView() {
        super();
    }

    public SmtechDeviceView(String name, String machinecode, int layoutid) {
        this.name=name;
        this.machinecode=machinecode;
        this.layoutid=layoutid;
    }


}
