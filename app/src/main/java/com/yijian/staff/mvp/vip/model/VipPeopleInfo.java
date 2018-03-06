package com.yijian.staff.mvp.vip.model;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * Created by yangk on 2018/3/5.
 */

public class VipPeopleInfo {

    private String headerUrl; //头像路径
    private String name; //名字
    private String gender; //0 女，1 男
    private String birth; //生日时间
    private String birthType; // 生日类型
    private String bodyStatus; //身体状态
    private String bodybuildingHobby; //健身爱好
    private String interestHobby; //兴趣爱好
    private String useCar; //使用车辆
    private String isIntentVip; // 0 意向会员  ，1  潜在会员
    private String cardName; //卡名
    private String cardType; //卡类型
    private String privateCoach; //私教教练
    private String likeLesson; //喜欢课程
    private String likeTeacher; //喜欢老师
    private String registTime; //注册时间
    private String contractOverTime; //合同到期日
    private String contractBalance; //合同余额
    private String buyCount; //购买次数
    private String bePresentTime; //到场时间
    private String departureTime; //离场时间





    public VipPeopleInfo(){}
    public VipPeopleInfo(JSONObject jsonObject){
        this.headerUrl = JsonUtil.getString(jsonObject,"headerUrl");
        this.name = JsonUtil.getString(jsonObject,"name");
        this.gender = JsonUtil.getString(jsonObject,"gender");
        this.birth = JsonUtil.getString(jsonObject,"birth");
        this.birthType = JsonUtil.getString(jsonObject,"birthType");
        this.bodyStatus = JsonUtil.getString(jsonObject,"bodyStatus");
        this.bodybuildingHobby = JsonUtil.getString(jsonObject,"bodybuildingHobby");
        this.interestHobby = JsonUtil.getString(jsonObject,"interestHobby");
        this.useCar = JsonUtil.getString(jsonObject,"useCar");
        this.isIntentVip = JsonUtil.getString(jsonObject,"isIntentVip");

        this.cardName = JsonUtil.getString(jsonObject,"cardName");
        this.cardType = JsonUtil.getString(jsonObject,"cardType");
        this.privateCoach = JsonUtil.getString(jsonObject,"privateCoach");
        this.likeLesson = JsonUtil.getString(jsonObject,"likeLesson");
        this.likeTeacher = JsonUtil.getString(jsonObject,"likeTeacher");
        this.registTime = JsonUtil.getString(jsonObject,"registTime");
        this.contractOverTime = JsonUtil.getString(jsonObject,"contractOverTime");
        this.contractBalance = JsonUtil.getString(jsonObject,"contractBalance");
        this.buyCount = JsonUtil.getString(jsonObject,"buyCount");
        this.bePresentTime = JsonUtil.getString(jsonObject,"bePresentTime");
        this.departureTime = JsonUtil.getString(jsonObject,"departureTime");

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getBirthType() {
        return birthType;
    }

    public void setBirthType(String birthType) {
        this.birthType = birthType;
    }

    public String getBodyStatus() {
        return bodyStatus;
    }

    public void setBodyStatus(String bodyStatus) {
        this.bodyStatus = bodyStatus;
    }

    public String getBodybuildingHobby() {
        return bodybuildingHobby;
    }

    public void setBodybuildingHobby(String bodybuildingHobby) {
        this.bodybuildingHobby = bodybuildingHobby;
    }

    public String getInterestHobby() {
        return interestHobby;
    }

    public void setInterestHobby(String interestHobby) {
        this.interestHobby = interestHobby;
    }

    public String getUseCar() {
        return useCar;
    }

    public void setUseCar(String useCar) {
        this.useCar = useCar;
    }

    public String getIsIntentVip() {
        return isIntentVip;
    }

    public void setIsIntentVip(String isIntentVip) {
        this.isIntentVip = isIntentVip;
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

    public String getContractOverTime() {
        return contractOverTime;
    }

    public void setContractOverTime(String contractOverTime) {
        this.contractOverTime = contractOverTime;
    }

    public String getContractBalance() {
        return contractBalance;
    }

    public void setContractBalance(String contractBalance) {
        this.contractBalance = contractBalance;
    }

    public String getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount;
    }

    public String getBePresentTime() {
        return bePresentTime;
    }

    public void setBePresentTime(String bePresentTime) {
        this.bePresentTime = bePresentTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}
