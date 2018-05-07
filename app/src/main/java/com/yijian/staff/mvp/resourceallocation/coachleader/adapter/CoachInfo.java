package com.yijian.staff.mvp.resourceallocation.coachleader.adapter;

import com.yijian.staff.R;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * Created by yangk on 2018/3/12.
 * 会籍信息
 */
public class CoachInfo {

    private String headerUrl;
    private String name;
    private int gender; //这里返回图片的路径
    private String commentGrade; //评分等级
    private String businessProgress; //业务完成度
    private String actionTargetProgress; //行为指标完成度
    private String vipServiceNum; //会员服务数
    private String potentialServiceNum; //潜在服务数


    public CoachInfo(JSONObject jsonObject){
        this.headerUrl = JsonUtil.getString(jsonObject,"headerUrl");
        this.name = JsonUtil.getString(jsonObject,"name");
        this.gender = "0".equals(JsonUtil.getString(jsonObject,"gender")) ? R.mipmap.lg_women : R.mipmap.lg_man;
        this.commentGrade = JsonUtil.getString(jsonObject,"commentGrade");
        this.businessProgress = JsonUtil.getString(jsonObject,"businessProgress");
        this.actionTargetProgress = JsonUtil.getString(jsonObject,"actionTargetProgress");
        this.vipServiceNum = JsonUtil.getString(jsonObject,"vipServiceNum");
        this.potentialServiceNum = JsonUtil.getString(jsonObject,"potentialServiceNum");
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCommentGrade() {
        return commentGrade;
    }

    public void setCommentGrade(String commentGrade) {
        this.commentGrade = commentGrade;
    }

    public String getBusinessProgress() {
        return businessProgress;
    }

    public void setBusinessProgress(String businessProgress) {
        this.businessProgress = businessProgress;
    }

    public String getActionTargetProgress() {
        return actionTargetProgress;
    }

    public void setActionTargetProgress(String actionTargetProgress) {
        this.actionTargetProgress = actionTargetProgress;
    }

    public String getVipServiceNum() {
        return vipServiceNum;
    }

    public void setVipServiceNum(String vipServiceNum) {
        this.vipServiceNum = vipServiceNum;
    }

    public String getPotentialServiceNum() {
        return potentialServiceNum;
    }

    public void setPotentialServiceNum(String potentialServiceNum) {
        this.potentialServiceNum = potentialServiceNum;
    }
}
