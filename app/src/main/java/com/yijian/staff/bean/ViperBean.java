package com.yijian.staff.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/22 20:04:14
 */
public class ViperBean {




    /**
     birthday (string, optional): 生日 ,
     birthdayType (string, optional): 生日类型 ,
     name (string, optional): 名称 ,
     sex (string, optional): 性别
     memberId (string, optional): id ,
     headImg (string, optional): 头像图片 ,
     cardName (string, optional): 卡名 ,
     cardType (string, optional): 卡类型 ,
     contractBalance (string, optional): 合同余额 ,
     contractDeadline (string, optional): 合同到期日 ,
     contractId (string, optional): 合同ID ,
     favorCourse (string, optional): 喜欢课程 ,
     favorTeacher (string, optional): 喜欢老师 ,
     privateCoach (string, optional): 私教教练 ,
     purchaseCount (integer, optional): 购买次数 ,
     registerTime (string, optional): 注册时间 ,
     */

    private String birthday;
    private String birthdayType;
    private String name;
    private String sex;
    private String memberId;
    private String headImg;
    private String cardName;
    private String cardType;
    private String contractBalance;
    private String contractDeadline;
    private String contractId;
    private String privateCoach;
    private String favorCourse;
    private String favorTeacher;
    private int purchaseCount;
    private String registerTime;
    private String bePresentTime; //到场时间
    private String departureTime; //离场时间

    public ViperBean(JSONObject jsonObject){
         this.birthday = JsonUtil.getString(jsonObject, "birthday");
         this.birthdayType = JsonUtil.getString(jsonObject, "birthdayType");
         this.name = JsonUtil.getString(jsonObject, "name");
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
}
