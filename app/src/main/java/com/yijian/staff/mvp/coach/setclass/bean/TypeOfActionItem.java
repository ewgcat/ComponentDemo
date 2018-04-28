package com.yijian.staff.mvp.coach.setclass.bean;

/**
 * Created by The_P on 2018/3/23.
 */

public class TypeOfActionItem {
    String actionName; //动作名称
    String actionForm;  //动作形态
    String actionIntensity;//动作强度
    String actionTime; //动作时间
    String actionTimeInterval;//动作时间间隔
    Long  startTime; //开始时间
    Long  endTime;  //结束时间

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
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
