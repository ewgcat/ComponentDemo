package com.yijian.staff.mvp.course.timetable.schedule.week.addstudent.step2;

import android.support.annotation.NonNull;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/17 19:21:46
 */
public class CourseTimeBean implements Comparable<CourseTimeBean> {
    private String startTime;
    private String endTime;

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

    @Override
    public int compareTo(@NonNull CourseTimeBean o) {
        return
                Integer.parseInt(this.getStartTime().replaceAll(":", "")) - Integer.parseInt(o.getStartTime().replaceAll(":", ""));


    }
}