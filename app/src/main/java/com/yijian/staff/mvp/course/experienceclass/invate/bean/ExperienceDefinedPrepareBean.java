package com.yijian.staff.mvp.course.experienceclass.invate.bean;

/**
 * Created by The_P on 2018/5/4.
 */

public class ExperienceDefinedPrepareBean {
    //    体验课自定义备课内容传参对象 {
//        buildDesc (string, optional): 训练组数描述（组数×次数或秒数） ,
//        moApplianceName (string, optional): 器械名称 ,
//                moDifficulty (string, optional): 训练难度(1：高，2：中，3：低) ,
//                moName (string, optional): 动作名称 ,
//                moParts (string, optional): 训练部位
//    }

    String buildDesc;//训练组数描述（组数×次数或秒数） ,
    String moApplianceName;//器械名称
    String moDifficulty;//训练难度(1：高，2：中，3：低) ,
    String moName;//动作名称 ,
    String moParts;//训练部位

    public String getBuildDesc() {
        return buildDesc;
    }

    public void setBuildDesc(String buildDesc) {
        this.buildDesc = buildDesc;
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
}
