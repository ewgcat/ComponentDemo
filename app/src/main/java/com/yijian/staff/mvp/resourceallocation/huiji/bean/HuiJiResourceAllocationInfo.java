package com.yijian.staff.mvp.resourceallocation.huiji.bean;

import com.yijian.staff.R;

/**
 * Created by yangk on 2018/3/12.
 */

public class HuiJiResourceAllocationInfo {


    /**
     * distributeTime : 2018-05-08T03:41:04.015Z
     * distributeUser : string
     * headImg : string
     * historyUser : string
     * memberId : string
     * memberName : string
     * sex : string
     */

    private String distributeTime;
    private String distributeUser;
    private String headImg;
    private String historyUser;
    private String memberId;
    private String memberName;
    private String sex;

    public String getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(String distributeTime) {
        this.distributeTime = distributeTime;
    }

    public String getDistributeUser() {
        return distributeUser;
    }

    public void setDistributeUser(String distributeUser) {
        this.distributeUser = distributeUser;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getHistoryUser() {
        return historyUser;
    }

    public void setHistoryUser(String historyUser) {
        this.historyUser = historyUser;
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

    public int getGenderImg() {
        return "0".equals(sex) ? R.mipmap.lg_women : R.mipmap.lg_man;
    }

}
