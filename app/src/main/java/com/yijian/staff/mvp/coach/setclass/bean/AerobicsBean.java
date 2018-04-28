package com.yijian.staff.mvp.coach.setclass.bean;

public class AerobicsBean {


    /**
     * contextType : 2
     * sort : 1
     * name : 健身单车
     * grade : S级
     * time : 600
     */

    private int contextType;
    private int sort;
    private String name;
    private String grade;
    private int time;

    public int getContextType() {
        return contextType;
    }

    public void setContextType(int contextType) {
        this.contextType = contextType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
