package com.yijian.staff.mvp.coach.cunke.bean;

/**
 * Created by yangk on 2018/3/29.
 * 私教课存课Body
 */

public class TypeOfCunKeBody {

    private String memberName; //姓名
    private String courseName; //课程名称
    private String totalCourseCount; //总课程
    private String stockCourseCount; //存课

    public TypeOfCunKeBody(){}

    public TypeOfCunKeBody(String userName, String lessonName, String totalLessonNum, String remainLessonNum) {
        this.memberName = memberName;
        this.courseName = courseName;
        this.totalCourseCount = totalCourseCount;
        this.stockCourseCount = stockCourseCount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTotalCourseCount() {
        return totalCourseCount;
    }

    public void setTotalCourseCount(String totalCourseCount) {
        this.totalCourseCount = totalCourseCount;
    }

    public String getStockCourseCount() {
        return stockCourseCount;
    }

    public void setStockCourseCount(String stockCourseCount) {
        this.stockCourseCount = stockCourseCount;
    }
}
