package com.yijian.staff.mvp.coach.cunke.bean;

/**
 * Created by yangk on 2018/3/29.
 * 私教课存课Body
 */

public class TypeOfCunKeBody {

    private String userName; //姓名
    private String lessonName; //课程名称
    private String totalLessonNum; //总课程
    private String remainLessonNum; //存课

    public String getUserName() {
        return userName;
    }

    public TypeOfCunKeBody(String userName, String lessonName, String totalLessonNum, String remainLessonNum) {
        this.userName = userName;
        this.lessonName = lessonName;
        this.totalLessonNum = totalLessonNum;
        this.remainLessonNum = remainLessonNum;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getTotalLessonNum() {
        return totalLessonNum;
    }

    public void setTotalLessonNum(String totalLessonNum) {
        this.totalLessonNum = totalLessonNum;
    }

    public String getRemainLessonNum() {
        return remainLessonNum;
    }

    public void setRemainLessonNum(String remainLessonNum) {
        this.remainLessonNum = remainLessonNum;
    }

}
