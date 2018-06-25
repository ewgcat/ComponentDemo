package com.yijian.staff.bean;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 16:22:37
 */
public class CoachHuiFangInfo {


    /**
     * interviewRecordId : a28e1c94b8c9446b9a6b64b9feb6bc21
     * id : 0211c816b10c4347a7f08084d47faa71
     * name : 测试会员29-9
     * sex : 男
     * mobile : 值29
     * headImg : 值29
     * healthStatus : 正常
     * fitnessHobby : 健身,跑步,爬山
     * hobby : 现金
     * carBrand : 无
     * interviewType : 生日回访
     * interviewResult : null
     * birthday : 1524067200000
     * birthdayType : 农历
     * subclassName : BirthdayVO
     */


    private String interviewRecordId;
    private String id;
    private String name;
    private String sex;
    private String mobile;
    private String headImg;
    private String healthStatus;
    private String fitnessHobby;
    private String hobby;
    private String carBrand;
    private String interviewType;
    private String interviewResult;
    private Long birthday;
    private String birthdayType;
    private String testTimes;
    private Long lastTestTime;
    private String subclassName;

    private Long inviteCourseTime;


    private Long deadline;
    private String expiryReason;

    private String courseName;
    private String courseId;
    private String reinterviewReason;
    private Long lastVisitTime;
    /**
     * interviewResult : null
     * contractBalance : 6天
     */

    private String contractBalance;


    public CoachHuiFangInfo(JSONObject jsonObject) {
        this.headImg = BuildConfig.FILE_HOST + JsonUtil.getString(jsonObject, "headImg");
        this.name = JsonUtil.getString(jsonObject, "name");
        this.sex = JsonUtil.getString(jsonObject, "sex");
        this.healthStatus = JsonUtil.getString(jsonObject, "healthStatus");
        this.fitnessHobby = JsonUtil.getString(jsonObject, "fitnessHobby");
        this.hobby = JsonUtil.getString(jsonObject, "hobby");
        this.carBrand = JsonUtil.getString(jsonObject, "carBrand");

        this.interviewType = JsonUtil.getString(jsonObject, "interviewType");
        this.interviewResult = JsonUtil.getString(jsonObject, "interviewResult");
        this.interviewRecordId = JsonUtil.getString(jsonObject, "interviewRecordId");

        this.birthday = JsonUtil.getLong(jsonObject, "birthday");
        this.lastTestTime = JsonUtil.getLong(jsonObject, "lastTestTime");
        this.deadline = JsonUtil.getLong(jsonObject, "deadline");
        this.inviteCourseTime = JsonUtil.getLong(jsonObject, "inviteCourseTime");
        this.lastVisitTime = JsonUtil.getLong(jsonObject, "lastVisitTime");
        this.birthdayType = JsonUtil.getString(jsonObject, "birthdayType");
        this.subclassName = JsonUtil.getString(jsonObject, "subclassName");
        this.mobile = JsonUtil.getString(jsonObject, "mobile");
        this.testTimes = JsonUtil.getString(jsonObject, "testTimes");
        this.id = JsonUtil.getString(jsonObject, "id");
        this.expiryReason = JsonUtil.getString(jsonObject, "expiryReason");
        this.courseName = JsonUtil.getString(jsonObject, "courseName");
        this.courseId = JsonUtil.getString(jsonObject, "courseId");
        this.contractBalance = JsonUtil.getString(jsonObject, "contractBalance");
        this.reinterviewReason = JsonUtil.getString(jsonObject, "reinterviewReason");


    }

    public String getReinterviewReason() {
        return reinterviewReason;
    }

    public Long getLastVisitTime() {
        return lastVisitTime;
    }

    public String getTestTimes() {
        return testTimes;
    }

    public Long getLastTestTime() {
        return lastTestTime;
    }

    public String getInterviewRecordId() {
        return interviewRecordId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHeadImg() {
        return headImg;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public String getFitnessHobby() {
        return fitnessHobby;
    }

    public String getHobby() {
        return hobby;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public String getInterviewResult() {
        return interviewResult;
    }

    public Long getBirthday() {
        return birthday;
    }

    public String getBirthdayType() {
        return birthdayType;
    }

    public String getSubclassName() {
        return subclassName;
    }

    public Long getInviteCourseTime() {
        return inviteCourseTime;
    }

    public void setInviteCourseTime(Long inviteCourseTime) {
        this.inviteCourseTime = inviteCourseTime;
    }


    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public String getExpiryReason() {
        return expiryReason;
    }

    public void setExpiryReason(String expiryReason) {
        this.expiryReason = expiryReason;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getContractBalance() {
        return contractBalance;
    }

    public void setContractBalance(String contractBalance) {
        this.contractBalance = contractBalance;
    }
}
