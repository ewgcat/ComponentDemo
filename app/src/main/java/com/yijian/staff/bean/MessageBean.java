package com.yijian.staff.bean;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/12 11:08:32
 */


public class MessageBean {


    /**
     content (integer, optional): 消息内容 ,
     cost (number, optional): 费用 ,
     costType (integer, optional): 费用类型（0：收款，1：退款） ,
     createTime (string, optional): 创建时间 ,
     gender (integer, optional): 性别(0:未知 1:男 2:女) ,
     id (string, optional): id ,
     memberHeadPortrait (string, optional): 头像url ,
     userId (string, optional): 会员id ,
     userName (string, optional): 会员名称 ,
     title (string, optional): 消息标题
     *
     */

    private String content;
    private String cost;
    private int costType;
    private String createTime;
    private int gender;
    private String id;
    private String headPortrait;
    private String userId;
    private String userName;
    private String title;


    public MessageBean(JSONObject jsonObject) {

        this.content = JsonUtil.getString(jsonObject, "content");
        this.cost = JsonUtil.getString(jsonObject, "cost");
        this.costType = JsonUtil.getInt(jsonObject, "costType");
        this.gender = JsonUtil.getInt(jsonObject, "gender");
        this.id = JsonUtil.getString(jsonObject, "id");
        this.headPortrait = BuildConfig.FILE_HOST + JsonUtil.getString(jsonObject, "headPortrait");
        this.userId = JsonUtil.getString(jsonObject, "userId");
        this.userName = JsonUtil.getString(jsonObject, "userName");
        Long createTimeValue = JsonUtil.getLong(jsonObject, "createTime");
        if (createTimeValue != null && createTimeValue != -1) {
            this.createTime = DateUtil.parseLongDateToTimeString(createTimeValue);
        }

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getCostType() {
        return costType;
    }

    public void setCostType(int costType) {
        this.costType = costType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberHeadPortrait() {
        return headPortrait;
    }

    public void setMemberHeadPortrait(String memberHeadPortrait) {
        this.headPortrait = memberHeadPortrait;
    }

    public String getMemberId() {
        return userId;
    }

    public void setMemberId(String userId) {
        this.userId = userId;
    }

    public String getMemberName() {
        return userName;
    }

    public void setMemberName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
