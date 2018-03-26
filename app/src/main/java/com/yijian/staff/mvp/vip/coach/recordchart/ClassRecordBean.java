package com.yijian.staff.mvp.vip.coach.recordchart;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/26 14:48:27
 */
public class ClassRecordBean {
    private String className;
    private String classTime;
    private String classJieShu;
    private String hasShangClassCount;

    public ClassRecordBean() {
    }

    public ClassRecordBean(String className, String classTime, String classJieShu, String hasShangClassCount) {
        this.className = className;
        this.classTime = classTime;
        this.classJieShu = classJieShu;
        this.hasShangClassCount = hasShangClassCount;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getClassJieShu() {
        return classJieShu;
    }

    public void setClassJieShu(String classJieShu) {
        this.classJieShu = classJieShu;
    }

    public String getHasShangClassCount() {
        return hasShangClassCount;
    }

    public void setHasShangClassCount(String hasShangClassCount) {
        this.hasShangClassCount = hasShangClassCount;
    }
}
