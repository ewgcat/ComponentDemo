package com.yijian.staff.mvp.main.mine.calendartable.bean;

/**
 * Created by The_P on 2018/3/22.
 */

public class CourseInfo {
    private String name;
    private String start;
    private String end;

    @Override
    public String toString() {
        return "CourseInfo{" +
                "name='" + name + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
