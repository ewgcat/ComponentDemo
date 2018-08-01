package com.yijian.staff.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/30 15:17:19
 */
public class StudentBean implements Serializable{
    private Long time;
    private String name;
    private String headImg;
    private String courseName;
    private String courseTime;
    private int sex;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

}
