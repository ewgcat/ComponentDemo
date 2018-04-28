package com.yijian.staff.mvp.mine.calendartable.bean;

import com.yijian.staff.mvp.mine.calendartable.bean.CourseInfo;

import java.util.List;

/**
 * Created by The_P on 2018/3/22.
 */

public class DayTask {
    private String time;
    private String name;
    private List<CourseInfo> courseInfos;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseInfo> getCourseInfos() {
        return courseInfos;
    }

    public void setCourseInfos(List<CourseInfo> courseInfos) {
        this.courseInfos = courseInfos;
    }
}
