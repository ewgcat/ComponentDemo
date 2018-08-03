package com.yijian.staff.mvp.workspace.bean;

import java.io.Serializable;

public class WorkSpaceVipBean implements Serializable {


    /**
     * age : 0
     * birthday : 2018-08-03T01:32:47.665Z
     * firstCardTime : 2018-08-03T01:32:47.665Z
     * headPath : string
     * memberId : string
     * name : string
     * typeName : string
     */

    private int age;
    private String birthday;
    private String firstCardTime;
    private String headPath;
    private String memberId;
    private String name;
    private String gender;
    private String typeName;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFirstCardTime() {
        return firstCardTime;
    }

    public void setFirstCardTime(String firstCardTime) {
        this.firstCardTime = firstCardTime;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
