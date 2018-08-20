package com.yijian.staff.mvp.course.timetable.schedule.week.addstudent.step2;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/17 17:08:38
 */
public class CoursePlanBean {
    private String weekDay;

    private List<CourseTimeBean> courseTimeBeanList;

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public List<CourseTimeBean> getCourseTimeBeanList() {
        return courseTimeBeanList;
    }

    public void setCourseTimeBeanList(List<CourseTimeBean> courseTimeBeanList) {
        this.courseTimeBeanList = courseTimeBeanList;
    }



}
