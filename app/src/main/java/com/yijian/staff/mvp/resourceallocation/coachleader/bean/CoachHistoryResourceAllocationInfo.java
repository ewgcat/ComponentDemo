package com.yijian.staff.mvp.resourceallocation.coachleader.bean;

import com.yijian.staff.R;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * Created by yangk on 2018/3/12.
 */

public class CoachHistoryResourceAllocationInfo {


    private String headerUrl;
    private String name;
    private int gender; //这里返回图片的路径
    private String birthDay; //生日
    private String wxIdentification; //微信号
    private String email; //邮箱
    private String serviceHuiJi; //服务会籍
    private String receptionCoach; //接待教练
    private String serviceCoach; //接待教练


    public CoachHistoryResourceAllocationInfo(JSONObject jsonObject){
        this.headerUrl = JsonUtil.getString(jsonObject,"headerUrl");
        this.name = JsonUtil.getString(jsonObject,"name");
        this.gender = "0".equals(JsonUtil.getString(jsonObject,"gender")) ? R.mipmap.lg_women : R.mipmap.lg_man;
        this.birthDay = JsonUtil.getString(jsonObject,"birthDay");
        this.wxIdentification = JsonUtil.getString(jsonObject,"wxIdentification");
        this.email = JsonUtil.getString(jsonObject,"email");
        this.serviceHuiJi = JsonUtil.getString(jsonObject,"serviceHuiJi");
        this.receptionCoach = JsonUtil.getString(jsonObject,"receptionCoach");
        this.serviceCoach = JsonUtil.getString(jsonObject,"serviceCoach");
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getWxIdentification() {
        return wxIdentification;
    }

    public void setWxIdentification(String wxIdentification) {
        this.wxIdentification = wxIdentification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServiceHuiJi() {
        return serviceHuiJi;
    }

    public void setServiceHuiJi(String serviceHuiJi) {
        this.serviceHuiJi = serviceHuiJi;
    }

    public String getReceptionCoach() {
        return receptionCoach;
    }

    public void setReceptionCoach(String receptionCoach) {
        this.receptionCoach = receptionCoach;
    }

    public String getServiceCoach() {
        return serviceCoach;
    }

    public void setServiceCoach(String serviceCoach) {
        this.serviceCoach = serviceCoach;
    }
}
