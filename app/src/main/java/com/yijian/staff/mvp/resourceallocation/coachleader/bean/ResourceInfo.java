package com.yijian.staff.mvp.resourceallocation.coachleader.bean;

import com.yijian.staff.R;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;



public class ResourceInfo {


    private String headImg;
    private String name;
    private int gender;
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
    private String memberId;


    public ResourceInfo(JSONObject jsonObject){
        this.headImg = JsonUtil.getString(jsonObject,"headImg");
        this.name = JsonUtil.getString(jsonObject,"name");
        this.gender = "1".equals(JsonUtil.getString(jsonObject,"gender")) ? R.mipmap.lg_man : R.mipmap.lg_women;
        this.subclassName = JsonUtil.getString(jsonObject,"subclassName");
        this.memberId = JsonUtil.getString(jsonObject,"memberId");
    }

    public String getMemberId() {
        return memberId;
    }

    public String getHeadImg() {
        return headImg;
    }



    public String getName() {
        return name;
    }


    public int getGender() {
        return gender;
    }



    public String getSubclassName() {
        return subclassName;
    }




}
