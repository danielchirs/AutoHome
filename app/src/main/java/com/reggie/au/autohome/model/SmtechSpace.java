package com.reggie.au.autohome.model;

import com.tandong.sa.sql.annotation.Column;
import com.tandong.sa.sql.annotation.Table;

/**
 * Created by daniel on 2015/3/25.
 * 空间对象
 */
@Table(name="SmtechSpace")
public class SmtechSpace {

    @Column(name = "hid")
    public int hid;//房屋ID
    @Column(name = "sname")
    public String sname;//空间名称
    @Column(name = "scode")
    public String scode;//空间编码
    @Column(name = "spic")
    public String spic;//空间图标
    @Column(name = "srmark")
    public String srmark;//空间说明

    public SmtechSpace(){

    }

    public SmtechSpace(int hid,String sname,String scode,String spic,String srmark){
        this.hid=hid;
        this.sname=sname;
        this.scode=scode;
        this.spic=spic;
        this.srmark=srmark;

    }

}
