package com.reggie.au.autohome.model;

import java.util.Map;

/**
 * Created by michaelchen on 2015/3/30.
 */
public class AutohomeItem {
    private String name;
    private String type;
    private String state;
    private String link;

    /**
     * 获得对应的item节点
     *
     * @param itemMap
     */
    public AutohomeItem(Map<String, String> itemMap) {
        this.setType(itemMap.get("type"));
        this.setName(itemMap.get("name"));
        this.setState(itemMap.get("state"));
        this.setLink(itemMap.get("command"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
