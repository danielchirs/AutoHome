package com.reggie.au.autohome.view;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daniel on 2015/3/29.
 */
public class SmtechDeviceView {

    private String widgetId;//编码
    private String widgetname;//名称
    private String type;//设备类型(switch开关,slider滑动,conditioning空调类型)
    private String icon;//图标
    private String machinecode;//机器码
    private String function;//功能码
    private String address;//地址码

    //在conditioning 条件下使用
    private String minvalue;//最小值
    private String maxvalue;//最大值
    private String state;//默认值

    private Map<String,String> item;




    public SmtechDeviceView() {
        item=new HashMap<String,String>();
    }


    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public String getWidgetname() {
        return widgetname;
    }

    public void setWidgetname(String widgetname) {
        this.widgetname = widgetname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMachinecode() {
        return machinecode;
    }

    public void setMachinecode(String machinecode) {
        this.machinecode = machinecode;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(String minvalue) {
        this.minvalue = minvalue;
    }

    public String getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(String maxvalue) {
        this.maxvalue = maxvalue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, String> getItem() {
        return item;
    }

    public void setItem(Map<String, String> item) {
        this.item = item;
    }
}
