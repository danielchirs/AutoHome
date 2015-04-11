package com.reggie.au.autohome.model;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
     * @param startNode
     */
    public AutohomeItem(Node startNode) {
        if (startNode.hasChildNodes()) {
            NodeList childNodes = startNode.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                if (childNode.getNodeName().equals("type")) {
                    this.setType(childNode.getTextContent());
                } else if (childNode.getNodeName().equals("name")) {
                    this.setName(childNode.getTextContent());
                } else if (childNode.getNodeName().equals("state")) {
                    if (childNode.getTextContent().equals("Uninitialized")) {
                        this.setState("0");
                    } else {
                        this.setState(childNode.getTextContent());
                    }
                } else if (childNode.getNodeName().equals("link")) {
                    this.setLink(childNode.getTextContent());
                }
            }
        }
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
