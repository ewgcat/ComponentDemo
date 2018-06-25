package com.yijian.staff.mvp.coach.preparelessons.list;

import java.io.Serializable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/26 17:26:53
 */
public class PrepareLessonsBean implements Serializable {


    /**
     * id : 1
     * lessonName : 测试课程咯
     * isExperience : null
     * isUseTemplate : null
     * experienceRecordId : null
     * lessonPlace : test门店2
     * startDatetime : 16:00
     * endDatetime : 16:00
     * startDate : 2018-04-20
     * startTimeActual : null
     * endTimeActual : null
     * punchStatus : 0
     * isPrepare : 0
     * memberName : 就是那个名字咯
     * courseNum : 100
     * gender : 2   //0 未知 ， 1  男， 2 女
     * headPath : http://h5.dev.ejoyst.com/file/downloadFile?filename=e8c096e46c77458ba2aa10c517085f78.png
     * currentNum : 3
     * mobile : 13444444444
     */

    private String id;
    private String lessonName;
    private Object isExperience;
    private Object isUseTemplate;
    private Object experienceRecordId;
    private String lessonPlace;
    private String startDatetime;
    private String endDatetime;
    private String startDate;
    private Object startTimeActual;
    private Object endTimeActual;
    private int punchStatus;
    private int isPrepare;
    private String memberName;
    private String memberId;
    private int courseNum;
    private int gender;
    private String headPath;
    private int currentNum;
    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Object getIsExperience() {
        return isExperience;
    }

    public void setIsExperience(Object isExperience) {
        this.isExperience = isExperience;
    }

    public Object getIsUseTemplate() {
        return isUseTemplate;
    }

    public void setIsUseTemplate(Object isUseTemplate) {
        this.isUseTemplate = isUseTemplate;
    }

    public Object getExperienceRecordId() {
        return experienceRecordId;
    }

    public void setExperienceRecordId(Object experienceRecordId) {
        this.experienceRecordId = experienceRecordId;
    }

    public String getLessonPlace() {
        return lessonPlace;
    }

    public void setLessonPlace(String lessonPlace) {
        this.lessonPlace = lessonPlace;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Object getStartTimeActual() {
        return startTimeActual;
    }

    public void setStartTimeActual(Object startTimeActual) {
        this.startTimeActual = startTimeActual;
    }

    public Object getEndTimeActual() {
        return endTimeActual;
    }

    public void setEndTimeActual(Object endTimeActual) {
        this.endTimeActual = endTimeActual;
    }

    public int getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(int punchStatus) {
        this.punchStatus = punchStatus;
    }

    public int getIsPrepare() {
        return isPrepare;
    }

    public void setIsPrepare(int isPrepare) {
        this.isPrepare = isPrepare;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
