package com.yijian.staff.bean;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/12 11:08:32
 */


public class MessageBean {


    /**
     * content : string
     * cost : 0
     * costType : 0
     * createTime : 2018-09-10T06:36:05.730Z
     * gender : 0
     * headPortrait : string
     * id : string
     * title : string
     * userId : string
     * userName : string
     */

    private String content;
    private Double cost;
    private Integer costType;
    private Long createTime;
    private int gender;
    private String headPortrait;
    private String id;
    private String title;
    private String userId;
    private String userName;

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

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
