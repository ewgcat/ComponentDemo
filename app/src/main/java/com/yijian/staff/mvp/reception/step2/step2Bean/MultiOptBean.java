package com.yijian.staff.mvp.reception.step2.step2Bean;

/**
 * Created by The_P on 2018/3/15.
 */

public  class MultiOptBean {


    /**
     * optName : 减肥
     * isSelected : false
     * type : normal
     * userValue:
     */

    private String optName;
    private boolean isSelected;
    private String type;
    private String userValue;

    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
