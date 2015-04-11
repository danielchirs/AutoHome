package com.reggie.au.autohome.model;

/**
 * Created by michaelchen on 2015/3/30.
 */
public class AutohomeWidgetMapping {
    private String command;
    private String label;

    public AutohomeWidgetMapping(String command, String label) {
        this.command = command;
        this.label = label;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
