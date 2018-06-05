package com.yijian.staff.mvp.huiji.huifang.bean;

import com.yijian.staff.R;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 16:22:37
 */
public class HuiFangInfo implements Serializable {


    /**
     * interviewRecordId : 045f7cefb7074035855b62a8d29e9080
     * id : 0774378fb91e4a2db4fc5932e404ecaa
     * name : 测试会员18-18
     * sex : 男
     * mobile : 值18
     * headImg : 值18
     * healthStatus : 正常
     * fitnessHobby : 健身,跑步,爬山
     * hobby : 现金
     * carBrand : 无
     * interviewType : 潜在会员回访
     * interviewResult : null
     * birthday : 1523721600000
     * birthdayType : 农历
     * subclassName : PotentialVO
     */

    private String id;
    private String headImg;
    private String name;
    private String sex;
    private String mobile;
    private String healthStatus;
    private String fitnessHobby;
    private String hobby;
    private String carBrand;
    private String interviewRecordId;
    private String interviewType;
    private String interviewResult;
    private Long birthday;
    private String birthdayType;
    private String subclassName;

    private String reinterviewReason;
    private Long lastVisitTime;

    private Long deadline;
    private String expiryReason;

    private String contractBalance;
    /**
     * interviewResult : null
     * visitTime : 1524367655000
     * leaveTime : 1524454055000
     */

    private Long visitTime;
    private Long leaveTime;
    /**
     * interviewResult : null
     * lastFitTime : 2018-04-16
     * breakDay : -7
     */

    private String cardName;
    private String cardType;

    private String lastFitTime;
    private Long breakDay;
    private Long sinkDay;
    /**
     * interviewResult : null
     * openCardTime : 1524459244000
     * recentlyFitTime : 1523854444000
     * sinkDay : 7
     */

    private Long openCardTime;
    private Long recentlyFitTime;


    public String getMobile() {
        return mobile;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public String getInterviewResult() {
        return interviewResult;
    }

    public HuiFangInfo(JSONObject jsonObject) {
        this.id = JsonUtil.getString(jsonObject, "id");
        this.headImg = JsonUtil.getString(jsonObject, "headImg");
        this.name = JsonUtil.getString(jsonObject, "name");
        this.sex = JsonUtil.getString(jsonObject, "sex");
        this.mobile = JsonUtil.getString(jsonObject, "mobile");
        this.healthStatus = JsonUtil.getString(jsonObject, "healthStatus");
        this.fitnessHobby = JsonUtil.getString(jsonObject, "fitnessHobby");
        this.hobby = JsonUtil.getString(jsonObject, "hobby");
        this.carBrand = JsonUtil.getString(jsonObject, "carBrand");
        this.interviewRecordId = JsonUtil.getString(jsonObject, "interviewRecordId");
        this.interviewType = JsonUtil.getString(jsonObject, "interviewType");
        this.interviewResult = JsonUtil.getString(jsonObject, "interviewResult");
        this.birthday = JsonUtil.getLong(jsonObject, "birthday");
        this.birthdayType = JsonUtil.getString(jsonObject, "birthdayType");
        this.subclassName = JsonUtil.getString(jsonObject, "subclassName");
        this.reinterviewReason = JsonUtil.getString(jsonObject, "reinterviewReason");
        this.expiryReason = JsonUtil.getString(jsonObject, "expiryReason");
        this.lastVisitTime = JsonUtil.getLong(jsonObject, "lastVisitTime");
        this.deadline = JsonUtil.getLong(jsonObject, "deadline");
        this.visitTime = JsonUtil.getLong(jsonObject, "visitTime");
        this.leaveTime = JsonUtil.getLong(jsonObject, "leaveTime");
        this.breakDay = JsonUtil.getLong(jsonObject, "breakDay");
        this.sinkDay = JsonUtil.getLong(jsonObject, "sinkDay");
        this.lastVisitTime = JsonUtil.getLong(jsonObject, "lastVisitTime");
        this.openCardTime = JsonUtil.getLong(jsonObject, "openCardTime");
        this.recentlyFitTime = JsonUtil.getLong(jsonObject, "recentlyFitTime");
        this.contractBalance = JsonUtil.getString(jsonObject, "contractBalance");
        this.lastFitTime = JsonUtil.getString(jsonObject, "lastFitTime");
        this.cardName = JsonUtil.getString(jsonObject, "cardName");
        this.cardType = JsonUtil.getString(jsonObject, "cardType");


    }

    public String getCardName() {
        return cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public Long getSinkDay() {
        return sinkDay;
    }

    public String getContractBalance() {
        return contractBalance;
    }

    public Long getDeadline() {
        return deadline;
    }

    public String getExpiryReason() {
        return expiryReason;
    }

    public String getReinterviewReason() {
        return reinterviewReason;
    }

    public Long getLastVisitTime() {
        return lastVisitTime;
    }

    public String getInterviewRecordId() {
        return interviewRecordId;
    }

    public void setInterviewRecordId(String interviewRecordId) {
        this.interviewRecordId = interviewRecordId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getFitnessHobby() {
        return fitnessHobby;
    }

    public void setFitnessHobby(String fitnessHobby) {
        this.fitnessHobby = fitnessHobby;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }


    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }


    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(String birthdayType) {
        this.birthdayType = birthdayType;
    }

    public String getSubclassName() {
        return subclassName;
    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }

    public Long getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Long visitTime) {
        this.visitTime = visitTime;
    }

    public Long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getLastFitTime() {
        return lastFitTime;
    }

    public void setLastFitTime(String lastFitTime) {
        this.lastFitTime = lastFitTime;
    }

    public Long getBreakDay() {
        return breakDay;
    }

    public void setBreakDay(Long breakDay) {
        this.breakDay = breakDay;
    }

    public Long getOpenCardTime() {
        return openCardTime;
    }

    public void setOpenCardTime(Long openCardTime) {
        this.openCardTime = openCardTime;
    }

    public Long getRecentlyFitTime() {
        return recentlyFitTime;
    }

    public void setRecentlyFitTime(Long recentlyFitTime) {
        this.recentlyFitTime = recentlyFitTime;
    }

    public int getGenderImg() {
        return "0".equals(sex) ? R.mipmap.lg_women : R.mipmap.lg_man;
    }

}
