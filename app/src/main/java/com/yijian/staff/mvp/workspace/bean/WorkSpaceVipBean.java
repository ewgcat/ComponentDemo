package com.yijian.staff.mvp.workspace.bean;

import java.io.Serializable;

public class WorkSpaceVipBean implements Serializable {


    /**
     * age : 0
     * memberId : yuanli123456789
     * name : 原力刷卡进场测试会员
     * sex : 1
     * mobile : 13318745112
     * dictItemKey : 0
     * underProtected : false
     * headImg : /url
     * subclassName : CoachPotentialStudentVO
     */

    private int age;
    private String memberId;
    private String name;
    private String sex;
    private String mobile;
    private int dictItemKey;
    private boolean underProtected;
    private String headImg;
    private String subclassName;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getDictItemKey() {
        return dictItemKey;
    }

    public void setDictItemKey(int dictItemKey) {
        this.dictItemKey = dictItemKey;
    }

    public boolean isUnderProtected() {
        return underProtected;
    }

    public void setUnderProtected(boolean underProtected) {
        this.underProtected = underProtected;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSubclassName() {
        return subclassName;
    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }
}
