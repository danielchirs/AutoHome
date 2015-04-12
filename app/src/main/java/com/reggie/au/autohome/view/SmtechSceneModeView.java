package com.reggie.au.autohome.view;

import android.provider.ContactsContract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daniel on 2015/3/29.
 * 情景模式配置
 */
public class SmtechSceneModeView {
    private int id;
    private String name;
    private String icon;
    private String command;
    private String machinecode;
    private String function;
    private String address;
    private Map<String,String> item;

    public SmtechSceneModeView() {
        item=new HashMap<String,String>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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

    public Map<String, String> getItem() {
        return item;
    }

    public void setItem(Map<String, String> item) {
        this.item = item;
    }

    /**
     * 获取字符串
     * @param itemCode
     * @return
     */
    public String getCode(String itemCode){
        String strCode=this.getMachinecode()+this.getFunction()+this.getAddress()+this.getItem().get(itemCode)+"01";
        return strCode;
    }
}
