package com.reggie.au.autohome.model;

import com.tandong.sa.sql.Model;
import com.tandong.sa.sql.annotation.Column;
import com.tandong.sa.sql.annotation.Table;

/**
 * Created by daniel on 2015/3/22.
 * 房屋信息数据表
 */
@Table(name="SmtechHouse")
public class SmtechHouse extends Model {
    @Column(name = "sname")
    public String sname;//房屋名称
    @Column(name = "saddress")
    public String saddress;//房屋地址
    @Column(name = "scode")
    public String scode;//房屋编码
    @Column(name = "spic")
    public String spic;//图标
    @Column(name = "srmark")
    public String srmark;//简介

    public SmtechHouse(){
        super();
    }


    public SmtechHouse(String sname, String saddress, String scode,String spic,String srmark){
        super();
        this.sname = sname;
        this.saddress = saddress;
        this.scode=scode;
        this.spic=spic;
        this.srmark=srmark;
    }

}
