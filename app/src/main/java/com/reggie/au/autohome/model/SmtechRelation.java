package com.reggie.au.autohome.model;

/**
 * Created by daniel on 2015/3/25.
 * 设备关系表
 */
public class SmtechRelation {

    public String scode;//关系编码(对应每个唯一的关系索引)
    public String name;//关系名称
    public int ssid;//关联空间主键
    public int sdid;//关联设备主键
    public int squence;//序号
    public String funcode;//功能码
    public String btnaddr;//按钮地址
    public String btnfuncode;//按钮功能

}
