package com.yijian.staff.bean;

/**
 * Created by The_P on 2018/3/23.
 */

public class TypeOfActionTitle {
    String actionName; //动作名称
    String actionForm;  //动作形态
    String actionIntensity;//动作强度
    String actionTime; //动作时间
    String actionTimeInterval;//动作时间间隔

    public TypeOfActionTitle(String actionName, String actionForm, String actionIntensity, String actionTime, String actionTimeInterval) {
        this.actionName = actionName;
        this.actionForm = actionForm;
        this.actionIntensity = actionIntensity;
        this.actionTime = actionTime;
        this.actionTimeInterval = actionTimeInterval;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionForm() {
        return actionForm;
    }

    public void setActionForm(String actionForm) {
        this.actionForm = actionForm;
    }

    public String getActionIntensity() {
        return actionIntensity;
    }

    public void setActionIntensity(String actionIntensity) {
        this.actionIntensity = actionIntensity;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public String getActionTimeInterval() {
        return actionTimeInterval;
    }

    public void setActionTimeInterval(String actionTimeInterval) {
        this.actionTimeInterval = actionTimeInterval;
    }
}
