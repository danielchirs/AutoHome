package com.reggie.au.autohome.view;

/**
 * 房子基础配置信息
 * Created by daniel on 2015/4/11.
 */
public class SmtechHouseInfoView {

    private String houseName;//房子名称
    private String houseAddress;//房子躲在地址
    private String houseUser;//房子户主名称
    private String houseTel;//户主电话
    private String houseMobile;//户主手机
    private String ip;//内部连接IP
    private String domain;//外部连接域名
    private int port;//端口号
    private String loginName;//登录用户名
    private String password;//登录密码


    public SmtechHouseInfoView() {
        super();
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseUser() {
        return houseUser;
    }

    public void setHouseUser(String houseUser) {
        this.houseUser = houseUser;
    }

    public String getHouseTel() {
        return houseTel;
    }

    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel;
    }

    public String getHouseMobile() {
        return houseMobile;
    }

    public void setHouseMobile(String houseMobile) {
        this.houseMobile = houseMobile;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
