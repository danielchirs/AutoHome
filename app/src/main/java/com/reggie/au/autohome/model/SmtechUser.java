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
    public String userName;//用户名称
    @Column(name = "Password")
    public String password;//用户密码
    @Column(name = "NickName")
    public String nickName;//用户昵称
    @Column(name = "CreateTime")
    public String createTime;//创建时间
    @Column(name = "LastTime")
    public String lastTime;//最后一次登录时间
    @Column(name = "Sex")
    public int sex;//性别（0是女，1是男）
    @Column(name = "LoginType")
    public int loginType;//(0是未登录，1是登录)

    public SmtechUser(){
        super();
    }
}
