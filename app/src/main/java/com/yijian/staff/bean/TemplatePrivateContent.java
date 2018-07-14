package com.yijian.staff.bean;

/**
 * Created by The_P on 2018/4/25.
 */

public class TemplatePrivateContent {

    public String buildDesc;// 训练描述 ,
    public String contentId;//模板内容id ,
    public Integer groupNum;//组数 ,
    public Integer groupTime;//次数/时长 ,
    public String moApplianceName;//器械名称 ,
    public String moDifficulty;//训练难度(1：高，2：中，3：低) ,
    public String moDifficultyDesc;//动作难度描述 ,
    public String moName;//动作名称 ,
    public String moParts;//训练部位 ,
    public String moPartsDesc;//训练部位描述 ,
    public Integer sort;//第几个动作 ,
    public String unit;//单位

    public String getBuildDesc() {
        return buildDesc;
    }

    public void setBuildDesc(String buildDesc) {
        this.buildDesc = buildDesc;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public Integer getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(Integer groupTime) {
        this.groupTime = groupTime;
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

    public String getMoDifficultyDesc() {
        return moDifficultyDesc;
    }

    public void setMoDifficultyDesc(String moDifficultyDesc) {
        this.moDifficultyDesc = moDifficultyDesc;
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

    public String getMoPartsDesc() {
        return moPartsDesc;
    }

    public void setMoPartsDesc(String moPartsDesc) {
        this.moPartsDesc = moPartsDesc;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
