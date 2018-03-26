package com.yijian.staff.mvp.vip.coach.viperlist.filter;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/24 11:20:56
 */
public class CoachViperFilterBean {

    private int sex = -1;//性别：【0:未知 1:男 2:女】
    private int classType = -1;//课程类型：【1:私教课，1:体验课】

    private int joinTimeType = -2;//入籍时间类型：【0:今日，7:最近七天，30:最近30天，-1:可编辑日期】
    private String startTime = null;//开始时间
    private String endTime = null;//结束时间


    private int buyClassTime = -1;//购买时间：【7:7天，14:14天，30:30天】

    private int expiringDay = -1;//快过期天数:【7:7天，14:14天，30:30天】

    public CoachViperFilterBean() {
    }

    public CoachViperFilterBean(int sex, int classType, int joinTimeType, String startTime, String endTime, int buyClassTime, int expiringDay) {
        this.sex = sex;
        this.classType = classType;
        this.joinTimeType = joinTimeType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.buyClassTime = buyClassTime;
        this.expiringDay = expiringDay;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getClassType() {
        return classType;
    }

    public void setClassType(int classType) {
        this.classType = classType;
    }

    public int getJoinTimeType() {
        return joinTimeType;
    }

    public void setJoinTimeType(int joinTimeType) {
        this.joinTimeType = joinTimeType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getBuyClassTime() {
        return buyClassTime;
    }

    public void setBuyClassTime(int buyClassTime) {
        this.buyClassTime = buyClassTime;
    }

    public int getExpiringDay() {
        return expiringDay;
    }

    public void setExpiringDay(int expiringDay) {
        this.expiringDay = expiringDay;
    }
}
