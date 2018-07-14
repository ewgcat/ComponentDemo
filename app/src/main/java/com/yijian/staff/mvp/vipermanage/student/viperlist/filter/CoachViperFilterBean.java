package com.yijian.staff.mvp.vipermanage.student.viperlist.filter;

import java.io.Serializable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/24 11:20:56
 */
public class CoachViperFilterBean implements Serializable {

    private int sex = -1;//性别：【0:男 1:女】
    private String courseType = null;//课程类型：【1:私教课，2:体验课】

    private int joinTimeType = -2;//入籍时间类型：【0:今日，7:最近七天，30:最近30天，-1:可编辑日期】
    private String startTime = null;//开始时间
    private String endTime = null;//结束时间


    private int buyTime = -1;//购买时间：【0:0天，7:7天，30:30天】

    private int expiringDay = -1;//快过期天数:【7:7天，14:14天，30:30天】

    public CoachViperFilterBean() {
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
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

    public int getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(int buyTime) {
        this.buyTime = buyTime;
    }

    public int getExpiringDay() {
        return expiringDay;
    }

    public void setExpiringDay(int expiringDay) {
        this.expiringDay = expiringDay;
    }
}
