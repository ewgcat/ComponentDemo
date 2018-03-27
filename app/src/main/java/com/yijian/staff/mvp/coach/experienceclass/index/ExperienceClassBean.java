package com.yijian.staff.mvp.coach.experienceclass.index;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/19 10:21:41
 */
public class ExperienceClassBean implements Serializable{
    private String headerUrl;
    private String name;
    private int sex;

    private String cardName;
    private String cardType;
    private int ExperiencedCuont;
    private String currentOperation;

    public ExperienceClassBean(JSONObject jsonObject){
        this.headerUrl= JsonUtil.getString(jsonObject,"headerUrl");
        this.name= JsonUtil.getString(jsonObject,"name");
        this.sex= JsonUtil.getInt(jsonObject,"sex");
        this.cardName= JsonUtil.getString(jsonObject,"cardName");
        this.cardType= JsonUtil.getString(jsonObject,"cardType");
        this.ExperiencedCuont= JsonUtil.getInt(jsonObject,"ExperiencedCuont");
        this.currentOperation= JsonUtil.getString(jsonObject,"currentOperation");
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getExperiencedCuont() {
        return ExperiencedCuont;
    }

    public void setExperiencedCuont(int experiencedCuont) {
        ExperiencedCuont = experiencedCuont;
    }

    public String getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(String currentOperation) {
        this.currentOperation = currentOperation;
    }

}
