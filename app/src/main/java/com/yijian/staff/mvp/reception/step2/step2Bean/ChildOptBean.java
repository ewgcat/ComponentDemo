package com.yijian.staff.mvp.reception.step2.step2Bean;

import java.util.List;

/**
 * Created by The_P on 2018/3/15.
 */

public  class ChildOptBean {


    /**
     * qustion : 身高(cm)
     * qusType : normal
     * valueIsNum : true
     * minValue : 120
     * maxValue : 300
     * valueArray : []
     * hasSymbol : false
     * hasDecimal : false
     * defaultValue : 170
     * isSelected: false
     */

    private String qustion;
    private String qusType;
    private boolean valueIsNum;
    private String minValue;
    private String maxValue;
    private boolean hasSymbol;
    private boolean hasDecimal;
    private String defaultValue;
    private List<String> valueArray;
    private boolean isSelected;
    private List<MultiOptBean> multiOpt;
    private String userValue;

    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }

    public List<MultiOptBean> getMultiOptBeans() {
        return multiOpt;
    }

    public void setMultiOptBeans(List<MultiOptBean> multiOptBeans) {
        this.multiOpt = multiOptBeans;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getQustion() {
        return qustion;
    }

    public void setQustion(String qustion) {
        this.qustion = qustion;
    }

    public String getQusType() {
        return qusType;
    }

    public void setQusType(String qusType) {
        this.qusType = qusType;
    }

    public boolean isValueIsNum() {
        return valueIsNum;
    }

    public void setValueIsNum(boolean valueIsNum) {
        this.valueIsNum = valueIsNum;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public boolean isHasSymbol() {
        return hasSymbol;
    }

    public void setHasSymbol(boolean hasSymbol) {
        this.hasSymbol = hasSymbol;
    }

    public boolean isHasDecimal() {
        return hasDecimal;
    }

    public void setHasDecimal(boolean hasDecimal) {
        this.hasDecimal = hasDecimal;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<String> getValueArray() {
        return valueArray;
    }

    public void setValueArray(List<String> valueArray) {
        this.valueArray = valueArray;
    }

    @Override
    public String toString() {
        return "ChildOptBean{" +
                "qustion='" + qustion + '\'' +
                ", qusType='" + qusType + '\'' +
                ", valueIsNum=" + valueIsNum +
                ", minValue='" + minValue + '\'' +
                ", maxValue='" + maxValue + '\'' +
                ", hasSymbol=" + hasSymbol +
                ", hasDecimal=" + hasDecimal +
                ", defaultValue='" + defaultValue + '\'' +
                ", valueArray=" + valueArray +
                ", isSelected=" + isSelected +
                ", multiOpt=" + multiOpt +
                ", userValue='" + userValue + '\'' +
                '}';
    }
}
