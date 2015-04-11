package com.reggie.au.autohome.model;

import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 * Created by michaelchen on 2015/3/30.
 */
public class AutohomeWidget {
    private String label;
    private String icon;
    private String type;
    private float minValue = 0;
    private float maxValue = 100;
    private float step = 1;
    private int refresh = 0;
    private int height = 0;
    private AutohomeWidget parent;
    private AutohomeItem item;
    private ArrayList<AutohomeWidget> children;
    private ArrayList<AutohomeWidgetMapping> mappings;

    public AutohomeWidget() {
        this.children = new ArrayList<AutohomeWidget>();
        this.mappings = new ArrayList<AutohomeWidgetMapping>();
    }

    public AutohomeWidget(AutohomeWidget parent, Node startNode) {

    }

    public void addChildWidget(AutohomeWidget child) {
        if (child != null) {
            this.children.add(child);
        }
    }

    public boolean hasChildren() {
        if (this.children.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<AutohomeWidget> getChildren() {
        return children;
    }

    public boolean hasItem() {
        if (this.getItem() != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AutohomeItem getItem() {
        return item;
    }

    public void setItem(AutohomeItem item) {
        this.item = item;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean hasMappings() {
        if (mappings.size() > 0) {
            return true;
        }
        return false;
    }

    public AutohomeWidgetMapping getMapping(int index) {
        return mappings.get(index);
    }

    public ArrayList<AutohomeWidgetMapping> getMappings() {
        return mappings;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }

    public int getRefresh() {
        return refresh;
    }

    public void setRefresh(int refresh) {
        this.refresh = refresh;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
