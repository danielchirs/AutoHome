package com.reggie.au.autohome.model;

import com.tandong.sa.sql.annotation.Column;
import com.tandong.sa.sql.annotation.Table;

/**
 * Created by daniel on 2015/3/25.
 * 设备表
 */
@Table(name="SmtechDevice")
public class SmtechDevice {

    @Column(name = "scode")
    public String scode;//机器码(FF553FBA)
    @Column(name = "sname")
    public String sname;//名称
    @Column(name = "title")
    public String title;//标题
    @Column(name = "srmark")
    public String srmark;//简介
    @Column(name = "spic")
    public String spic;//对应图标
    @Column(name = "sitem")
    public String sitem;//对应布局

    public SmtechDevice(){

    }

    public SmtechDevice(String scode,String sname,String srmark,String spic,String sitem){
        this.scode=scode;
        this.sname=sname;
        this.srmark=srmark;
        this.spic=spic;
        this.sitem=sitem;
    }


}
