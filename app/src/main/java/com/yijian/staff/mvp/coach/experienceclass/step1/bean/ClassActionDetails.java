package com.yijian.staff.mvp.coach.experienceclass.step1.bean;

/**
 * Created by The_P on 2018/4/25.
 */

public class ClassActionDetails {
    public Integer actionForm;//动作形态(1:标准，2：不标准) ,
    public Integer actionStrength;// 动作强度(1:高，2：中，3：低) ,
    public String buildDesc;//训练次数(x次/x组) ,
    public String id;//每一项记录内容的id ,
    public Integer interval;//间隔时间(单位：秒) ,
    public Integer isFinish;//是否已填√(0:未填写，1:已填写) ,
    public String moApplianceName;// 器械名称 ,
    public String moDifficulty;//训练难度 ,
    public String moName;//动作名称 ,
    public String moParts;//训练部位 ,
    public String moPartsName;//训练部位名称 ,
    public String prepareId;// 备课内容id ,
    public Integer sort;//第几个动作 ,
    public String time;//计时时间（00:00:00）

    public Integer getActionForm() {
        return actionForm;
    }

    public void setActionForm(Integer actionForm) {
        this.actionForm = actionForm;
    }

    public Integer getActionStrength() {
        return actionStrength;
    }

    public void setActionStrength(Integer actionStrength) {
        this.actionStrength = actionStrength;
    }

    public String getBuildDesc() {
        return buildDesc;
    }

    public void setBuildDesc(String buildDesc) {
        this.buildDesc = buildDesc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    public String getMoApplianceName() {
        return moApplianceName;
    }

    public void setMoApplianceName(String moApplianceName) {
        this.moApplianceName = moApplianceName;
    }

    public String getMoDifficulty() {
        return moDifficulty;
    }

    public void setMoDifficulty(String moDifficulty) {
        this.moDifficulty = moDifficulty;
    }

    public String getMoName() {
        return moName;
    }

    public void setMoName(String moName) {
        this.moName = moName;
    }

    public String getMoParts() {
        return moParts;
    }

    public void setMoParts(String moParts) {
        this.moParts = moParts;
    }

    public String getMoPartsName() {
        return moPartsName;
    }

    public void setMoPartsName(String moPartsName) {
        this.moPartsName = moPartsName;
    }

    public String getPrepareId() {
        return prepareId;
    }

    public void setPrepareId(String prepareId) {
        this.prepareId = prepareId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
