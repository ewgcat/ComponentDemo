package com.yijian.staff.bean;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/5/8 16:24:54
 */
public class TodayVisitStudentBean implements Serializable {
    /**
     * CoachExpireVO：教练过期
     * CoachInfoVO ：教练正式
     * CoachIntentionVO：教练意向
     * CoachTodayVisitVO：教练今日来访
     * CustomerInfoVO：会籍正式
     * CustomerTodayVisitVO：会籍今日来访
     * CustomerExpireVO：会籍过期
     * CustomerIntentionVO：会籍意向
     * PotentialVO：潜在（会籍教练共用）
     */
    private String subclassName;


    /**
     * memberId : 503634cd51734962a11463c87a0526a6
     * name : 回访过期会员0-0
     * sex : 1
     * mobile : 值0
     * dictItemKey : 18
     * headImg : 值0
     * clockedCount : 1
     * visitTime : 1525770818000
     * leaveTime : 1523271882000
     * viperRole : 教练-今日会员
     * underProtected : false
     */

    private String memberId;
    private String name;
    private int sex;
    private String mobile;
    private int dictItemKey;
    private String headImg;
    private int clockedCount;
    private Long visitTime;
    private Long leaveTime;
    private String viperRole;
    private boolean underProtected;


    public TodayVisitStudentBean(JSONObject jsonObject) {
        this.name = JsonUtil.getString(jsonObject, "name");
        this.viperRole = JsonUtil.getString(jsonObject, "viperRole");
        this.sex = JsonUtil.getInt(jsonObject, "sex");
        this.dictItemKey = JsonUtil.getInt(jsonObject, "dictItemKey");
        this.memberId = JsonUtil.getString(jsonObject, "memberId");
        this.headImg = BuildConfig.FILE_HOST + JsonUtil.getString(jsonObject, "headImg");
        this.subclassName = JsonUtil.getString(jsonObject, "subclassName");
        this.underProtected = JsonUtil.getBoolean(jsonObject, "underProtected");
        this.mobile = JsonUtil.getString(jsonObject, "mobile");
        this.visitTime = JsonUtil.getLong(jsonObject, "visitTime");
        this.leaveTime = JsonUtil.getLong(jsonObject, "leaveTime");
        this.clockedCount = JsonUtil.getInt(jsonObject, "clockedCount");

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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getClockedCount() {
        return clockedCount;
    }

    public void setClockedCount(int clockedCount) {
        this.clockedCount = clockedCount;
    }

    public long getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(long visitTime) {
        this.visitTime = visitTime;
    }

    public long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(long leaveTime) {
        this.leaveTime = leaveTime;
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
}

