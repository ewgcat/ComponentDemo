package com.yijian.clubmodule.bean;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/17 17:08:38
 */
public class CoursePlanBean implements Comparable<CoursePlanBean>{
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


    @Override
    public int compareTo(@NonNull CoursePlanBean o) {
        String weekDay = this.getWeekDay();
        String weekDay1 = o.getWeekDay();
        int i=0;
        if (weekDay.equals("周日")){
            i=0;
        }else  if (weekDay.equals("周一")){
            i=1;
        }else  if (weekDay.equals("周二")){
            i=2;
        }else  if (weekDay.equals("周三")){
            i=3;
        }else  if (weekDay.equals("周四")){
            i=4;
        }else  if (weekDay.equals("周五")){
            i=5;
        }else  if (weekDay.equals("周六")){
            i=6;
        }
        int i1=0;

        if (weekDay1.equals("周日")){
            i1=0;
        }else  if (weekDay1.equals("周一")){
            i1=1;
        }else  if (weekDay1.equals("周二")){
            i1=2;
        }else  if (weekDay1.equals("周三")){
            i1=3;
        }else  if (weekDay1.equals("周四")){
            i1=4;
        }else  if (weekDay1.equals("周五")){
            i1=5;
        }else  if (weekDay1.equals("周六")){
            i1=6;
        }

        return i - i1;
    }
}
