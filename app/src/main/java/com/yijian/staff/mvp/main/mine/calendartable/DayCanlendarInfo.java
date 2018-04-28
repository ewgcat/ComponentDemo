package com.yijian.staff.mvp.main.mine.calendartable;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by yangk on 2018/3/15.
 */

public class DayCanlendarInfo {

    private String className; //课程名称
    private String venue;  //场馆
    private List<String> stuList; //学员集合
    private String intervalTime; //约课间隔时间
    private String startOrderTime;  //约课开始时间
    private String endOrderTime;  //约课结束时间
    private String status;  //是否上课标志位


    public DayCanlendarInfo(){}

    public DayCanlendarInfo(JSONObject jsonObject){
        this.className = JsonUtil.getString(jsonObject,"className");
        this.venue = JsonUtil.getString(jsonObject,"venue");
        this.stuList = com.alibaba.fastjson.JSONArray.parseArray(JsonUtil.getJsonArray(jsonObject,"stuList").toString(),String.class);
        this.intervalTime = JsonUtil.getString(jsonObject,"intervalTime");
        this.startOrderTime = JsonUtil.getString(jsonObject,"startOrderTime");
        this.endOrderTime = JsonUtil.getString(jsonObject,"endOrderTime");
        this.status = JsonUtil.getString(jsonObject,"status");
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public List<String> getStuList() {
        return stuList;
    }

    public void setStuList(List<String> stuList) {
        this.stuList = stuList;
    }

    public String getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(String intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getStartOrderTime() {
        return startOrderTime;
    }

    public void setStartOrderTime(String startOrderTime) {
        this.startOrderTime = startOrderTime;
    }

    public String getEndOrderTime() {
        return endOrderTime;
    }

    public void setEndOrderTime(String endOrderTime) {
        this.endOrderTime = endOrderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
