package com.yijian.staff.mvp.huiji.bean;

import com.yijian.staff.R;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * Created by yangk on 2018/3/26.
 */

public class VipOutdateInfo {

    private String headerUrl;
    private String name;
    private int gender; //这里返回图片的路径
    private String cardName;
    private String cardType;
    private String privateCoach; //私教教练
    private String likeLesson;
    private String likeTeacher;
    private String registTime;
    private String contractOutDate; //合同到期时间
    private String outDateDay; //已过期的天数

    private String historyLesson; //历史课程
    private String outDate; //过期时间
    private String outDateReason; //过期原因

    public VipOutdateInfo(){}
    public VipOutdateInfo(JSONObject jsonObject){
        this.headerUrl = JsonUtil.getString(jsonObject,"headerUrl");
        this.name = JsonUtil.getString(jsonObject,"name");
        this.gender = "0".equals(JsonUtil.getString(jsonObject,"gender")) ? R.mipmap.lg_women : R.mipmap.lg_man;
        this.cardName = JsonUtil.getString(jsonObject,"cardName");
        this.cardType = JsonUtil.getString(jsonObject,"cardType");
        this.privateCoach = JsonUtil.getString(jsonObject,"privateCoach");
        this.likeLesson = JsonUtil.getString(jsonObject,"likeLesson");
        this.likeTeacher = JsonUtil.getString(jsonObject,"likeTeacher");
        this.registTime = JsonUtil.getString(jsonObject,"registTime");
        this.contractOutDate = JsonUtil.getString(jsonObject,"contractOutDate");
        this.outDateDay = JsonUtil.getString(jsonObject,"outDateDay");

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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
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

    public String getPrivateCoach() {
        return privateCoach;
    }

    public void setPrivateCoach(String privateCoach) {
        this.privateCoach = privateCoach;
    }

    public String getLikeLesson() {
        return likeLesson;
    }

    public void setLikeLesson(String likeLesson) {
        this.likeLesson = likeLesson;
    }

    public String getLikeTeacher() {
        return likeTeacher;
    }

    public void setLikeTeacher(String likeTeacher) {
        this.likeTeacher = likeTeacher;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getContractOutDate() {
        return contractOutDate;
    }

    public void setContractOutDate(String contractOutDate) {
        this.contractOutDate = contractOutDate;
    }

    public String getOutDateDay() {
        return outDateDay;
    }

    public void setOutDateDay(String outDateDay) {
        this.outDateDay = outDateDay;
    }

    public String getHistoryLesson() {
        return historyLesson;
    }

    public void setHistoryLesson(String historyLesson) {
        this.historyLesson = historyLesson;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getOutDateReason() {
        return outDateReason;
    }

    public void setOutDateReason(String outDateReason) {
        this.outDateReason = outDateReason;
    }
}
