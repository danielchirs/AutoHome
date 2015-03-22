package com.reggie.au.autohome.model;

import com.tandong.sa.sql.Model;
import com.tandong.sa.sql.annotation.Column;
import com.tandong.sa.sql.annotation.Table;

/**
 * Created by daniel on 2015/3/22.
 * 用户对象
 */
@Table(name="SmtechHouse")
public class SmtechUser extends Model {

    @Column(name = "UserName")
    public String userName;
    @Column(name = "Password")
    public String password;
    @Column(name = "NickName")
    public String nickName;
    @Column(name = "CreateTime")
    public String createTime;
    @Column(name = "LastTime")
    public String lastTime;
    @Column(name = "Sex")
    public int sex;
    @Column(name = "LoginType")
    public int loginType;//(0是未登录，1是登录)

    public SmtechUser(){
        super();
    }
}
