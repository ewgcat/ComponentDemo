package com.yijian.staff.mvp.resourceallocation.coach.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * Created by yangk on 2018/3/12.
 */

public class CoachResourceAllocationInfo {


    /**
     * memberId : 01052a5565c64ed3b826dda2df3d31f7
     * memberName : 回访快过期会员23-0
     * sex : 男
     * headImg : 值23
     * distributeTime : 1524738883000
     * historyUser : appsale2
     * distributeUser : appsale2
     */

    private String memberId;
    private String memberName;
    private String sex;
    private String headImg;
    private Long distributeTime;
    private String historyUser;
    private String distributeUser;

    public CoachResourceAllocationInfo(JSONObject jsonObject) {

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
