package com.yijian.staff.mvp.resourceallocation.huijileader.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

public class ResourceInfo {


    /**
     * memberId : bc79d4833dc64eae8bf93cd9881ae6a1
     * name : 库鸟
     * sex : 1
     * dictItemKey : 16
     * headImg :
     * viperRole : 教练-意向会员
     * underProtected : false
     * subclassName : CoachIntentionVO
     */

    private String memberId;
    private String name;
    private int sex;
    private int dictItemKey;
    private String headImg;
    private String viperRole;
    private boolean underProtected;
    private String subclassName;


    public ResourceInfo(JSONObject jsonObject){
        this. memberId = JsonUtil.getString(jsonObject, "memberId");
        this. name = JsonUtil.getString(jsonObject, "name");
        this. sex = JsonUtil.getInt(jsonObject, "sex");
        this. headImg = JsonUtil.getString(jsonObject, "headImg");
        this. viperRole = JsonUtil.getString(jsonObject, "viperRole");
        this. memberId = JsonUtil.getString(jsonObject, "memberId");
        this. subclassName = JsonUtil.getString(jsonObject, "subclassName");
        this. dictItemKey = JsonUtil.getInt(jsonObject, "dictItemKey");
        this. underProtected = JsonUtil.getBoolean(jsonObject, "underProtected");
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getDictItemKey() {
        return dictItemKey;
    }

    public void setDictItemKey(int dictItemKey) {
        this.dictItemKey = dictItemKey;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getViperRole() {
        return viperRole;
    }

    public void setViperRole(String viperRole) {
        this.viperRole = viperRole;
    }

    public boolean isUnderProtected() {
        return underProtected;
    }

    public void setUnderProtected(boolean underProtected) {
        this.underProtected = underProtected;
    }

    public String getSubclassName() {
        return subclassName;
    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }
}
