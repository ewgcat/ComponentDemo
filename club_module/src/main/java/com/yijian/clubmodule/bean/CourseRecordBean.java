package com.yijian.clubmodule.bean;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/20 10:07:51
 */
public class CourseRecordBean {
    private String weekDay;

    private CourseTimeBean courseTimeBean;

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public CourseTimeBean getCourseTimeBean() {
        return courseTimeBean;
    }

    public void setCourseTimeBean(CourseTimeBean courseTimeBean) {
        this.courseTimeBean = courseTimeBean;
    }
}
