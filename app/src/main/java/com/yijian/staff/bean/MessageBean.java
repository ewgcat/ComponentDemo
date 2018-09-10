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
    private Double cost;
    private Integer costType;
    private Long createTime;
    private int gender;
    private String id;
    private String headPortrait;
    private String userId;
    private String userName;
    private String title;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
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
