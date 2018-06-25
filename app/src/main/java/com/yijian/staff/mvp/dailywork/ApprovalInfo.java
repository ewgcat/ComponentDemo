package com.yijian.staff.mvp.dailywork;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * Created by yangk on 2018/3/7.
 */

public class ApprovalInfo {

    private String headerUrl;
    private String name;
    private String applyType;
    private String applayTotalTime;
    private String applayStartTime;
    private String applayEndTime;


    public ApprovalInfo() {
    }

    public ApprovalInfo(JSONObject jsonObject) {
        this.headerUrl = JsonUtil.getString(jsonObject, "headerUrl");
        this.name = JsonUtil.getString(jsonObject, "name");
        this.applyType = JsonUtil.getString(jsonObject, "applyType");
        this.applayTotalTime = JsonUtil.getString(jsonObject, "applayTotalTime");
        this.applayStartTime = JsonUtil.getString(jsonObject, "applayStartTime");
        this.applayEndTime = JsonUtil.getString(jsonObject, "applayEndTime");
    }


    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getApplayTotalTime() {
        return applayTotalTime;
    }

    public void setApplayTotalTime(String applayTotalTime) {
        this.applayTotalTime = applayTotalTime;
    }

    public String getApplayStartTime() {
        return applayStartTime;
    }

    public void setApplayStartTime(String applayStartTime) {
        this.applayStartTime = applayStartTime;
    }

    public String getApplayEndTime() {
        return applayEndTime;
    }

    public void setApplayEndTime(String applayEndTime) {
        this.applayEndTime = applayEndTime;
    }
}
