package com.yijian.staff.mvp.resourceallocation.coachleader.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/5/8 12:24:38
 */
public class HistoryResourceInfo {


    /**
     * memberId : bc79d4833dc64eae8bf93cd9881ae6a1
     * memberName : 库鸟
     * sex : 男
     * headImg :
     * distributeTime : 1525753019000
     * historyUser : appcoach2
     * distributeUser : appcoach2
     */

    private String memberId;
    private String memberName;
    private String sex;
    private String headImg;
    private long distributeTime;
    private String historyUser;
    private String distributeUser;


    public HistoryResourceInfo(JSONObject jsonObject) {

        this.memberId = JsonUtil.getString(jsonObject, "memberId");
        this.memberName = JsonUtil.getString(jsonObject, "memberName");
        this.sex = JsonUtil.getString(jsonObject, "sex");
        this.headImg = JsonUtil.getString(jsonObject, "headImg");
        this.historyUser = JsonUtil.getString(jsonObject, "historyUser");
        this.distributeUser = JsonUtil.getString(jsonObject, "distributeUser");
        this.distributeTime = JsonUtil.getLong(jsonObject, "distributeTime");
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Long getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(Long distributeTime) {
        this.distributeTime = distributeTime;
    }

    public String getHistoryUser() {
        return historyUser;
    }

    public void setHistoryUser(String historyUser) {
        this.historyUser = historyUser;
    }

    public String getDistributeUser() {
        return distributeUser;
    }

    public void setDistributeUser(String distributeUser) {
        this.distributeUser = distributeUser;
    }
}
