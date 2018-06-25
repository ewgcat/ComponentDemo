package com.yijian.staff.mvp.reception.step1.bean;

/**
 * Created by The_P on 2018/3/16.
 */

public class QuestOptBean {
    /**
     * name : 报纸
     * type : normal
     * selected : false
     */

    private String name;
    private String type;
    private boolean selected;

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
