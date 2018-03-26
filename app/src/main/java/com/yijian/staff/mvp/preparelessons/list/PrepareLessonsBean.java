package com.yijian.staff.mvp.preparelessons.list;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/26 17:26:53
 */
public class PrepareLessonsBean {


    private String headerUrl;
    private String sex;
    private String name;
    private String classType;
    private String className;
    private String classTime;
    private String classNum;
    private String hasShangClassCount;
    private String beikeStatus;//1 备课状态 2已备课状态

    public PrepareLessonsBean() {
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
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

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getHasShangClassCount() {
        return hasShangClassCount;
    }

    public void setHasShangClassCount(String hasShangClassCount) {
        this.hasShangClassCount = hasShangClassCount;
    }

    public String getBeikeStatus() {
        return beikeStatus;
    }

    public void setBeikeStatus(String beikeStatus) {
        this.beikeStatus = beikeStatus;
    }
}
