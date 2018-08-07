package com.yijian.staff.mvp.workspace.bean;

public class WorkSpaceRecordBean {


    /**
     * day : string
     * grade : 0
     * id : string
     * time : string
     */

    private String day;
    private int grade;
    private String wdId;
    private String time;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getWdId() {
        return wdId;
    }

    public void setWdId(String wdId) {
        this.wdId = wdId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
