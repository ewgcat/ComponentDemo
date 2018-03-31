package com.yijian.staff.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/22 20:04:14
 */
public class ViperBean {

    private String bodyStatus; //身体状态
    private String bodybuildingHobby; //健身爱好
    private String interestHobby; //兴趣爱好
    private String useCar; //使用车辆
    private String isIntentVip; // 0 意向会员  ，1  潜在会员

    private String birthday;//生日
    private String birthdayType;//生日类型
    private String name;//会员姓名
    private String viperRole;//会员角色 普通会员

    private String sex;//性别
    private String memberId;//会员id
    private String headImg;//头像图片路径
    private String cardName;//卡名称
    private String cardType;//卡类别
    private String contractBalance;//合同余额
    private String contractDeadline;//合同到期日
    private String contractId;//合同ID
    private String privateCoach;//私教
    private String favorCourse;//喜欢的课程
    private String favorTeacher;//喜欢的教练
    private int purchaseCount;//购买次数
    private String registerTime;//入籍时间
    private String bePresentTime; //到场时间
    private String departureTime; //离场时间
    private String privateClass;//私教课
    private String outDateDay; //已过期的天数
    private String historyLesson; //历史课程
    private String outDate; //过期时间
    private String outDateReason; //过期原因

    public ViperBean(JSONObject jsonObject){
         this.birthday = JsonUtil.getString(jsonObject, "birthday");
         this.birthdayType = JsonUtil.getString(jsonObject, "birthdayType");
         this.name = JsonUtil.getString(jsonObject, "name");
         this.viperRole = JsonUtil.getString(jsonObject, "viperRole");
         this.sex = JsonUtil.getString(jsonObject, "sex");
         this.memberId = JsonUtil.getString(jsonObject, "memberId");
         this.headImg = JsonUtil.getString(jsonObject, "headImg");
         this.cardName = JsonUtil.getString(jsonObject, "cardName");
         this.cardType = JsonUtil.getString(jsonObject, "cardType");
         this.contractBalance = JsonUtil.getString(jsonObject, "contractBalance");
         this.contractDeadline = JsonUtil.getString(jsonObject, "contractDeadline");
         this.contractId = JsonUtil.getString(jsonObject, "contractId");
         this.contractDeadline = JsonUtil.getString(jsonObject, "contractDeadline");
         this.privateCoach = JsonUtil.getString(jsonObject, "privateCoach");
         this.favorCourse = JsonUtil.getString(jsonObject, "favorCourse");
         this.favorTeacher = JsonUtil.getString(jsonObject, "favorTeacher");
         this.purchaseCount = JsonUtil.getInt(jsonObject, "purchaseCount");
         this.registerTime = JsonUtil.getString(jsonObject, "registerTime");
         this.bePresentTime = JsonUtil.getString(jsonObject, "bePresentTime");
         this.departureTime = JsonUtil.getString(jsonObject, "departureTime");
         this.bodyStatus = JsonUtil.getString(jsonObject, "bodyStatus");
         this.bodybuildingHobby = JsonUtil.getString(jsonObject, "bodybuildingHobby");
         this.interestHobby = JsonUtil.getString(jsonObject, "interestHobby");
         this.useCar = JsonUtil.getString(jsonObject, "useCar");
         this.isIntentVip = JsonUtil.getString(jsonObject, "isIntentVip");
         this.privateClass = JsonUtil.getString(jsonObject, "privateClass");
         this.outDateDay = JsonUtil.getString(jsonObject, "outDateDay");
         this.historyLesson = JsonUtil.getString(jsonObject, "historyLesson");
         this.outDate = JsonUtil.getString(jsonObject, "outDate");
         this.outDateReason = JsonUtil.getString(jsonObject, "outDateReason");

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(String birthdayType) {
        this.birthdayType = birthdayType;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
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

    public String getContractBalance() {
        return contractBalance;
    }

    public void setContractBalance(String contractBalance) {
        this.contractBalance = contractBalance;
    }

    public String getContractDeadline() {
        return contractDeadline;
    }

    public void setContractDeadline(String contractDeadline) {
        this.contractDeadline = contractDeadline;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getPrivateCoach() {
        return privateCoach;
    }

    public void setPrivateCoach(String privateCoach) {
        this.privateCoach = privateCoach;
    }

    public String getFavorCourse() {
        return favorCourse;
    }

    public void setFavorCourse(String favorCourse) {
        this.favorCourse = favorCourse;
    }

    public String getFavorTeacher() {
        return favorTeacher;
    }

    public void setFavorTeacher(String favorTeacher) {
        this.favorTeacher = favorTeacher;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getPrivateClass() {
        return privateClass;
    }

    public void setPrivateClass(String privateClass) {
        this.privateClass = privateClass;
    }

    public String getViperRole() {
        return viperRole;
    }

    public void setViperRole(String viperRole) {
        this.viperRole = viperRole;
    }
}
