package com.yijian.staff.mvp.reception.step2.step2Bean;

/**
 * Created by The_P on 2018/3/13.
 */

public class Child_Demo {

    public static final int TYPE_NORMAL=3;
    public static final int TYPE_MULTIOPTION=4;
    public static final int TYPE_WRITE=5;
    public static final int TYPE_DISPLAY=6;


    private String key;
    private String value;
    private int type;
    private boolean isSelected;

    public Child_Demo(String key, String value, int type, boolean isSelected) {
        this.key = key;
        this.value = value;
        this.type = type;
        this.isSelected=isSelected;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
