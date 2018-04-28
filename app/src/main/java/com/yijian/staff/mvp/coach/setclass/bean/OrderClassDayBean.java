package com.yijian.staff.mvp.setclass.bean;

public class OrderClassDayBean {


    /**
     * endDatetime : 2018-04-10T05:34:29.093Z
     * endTimeActual : 2018-04-10T05:34:29.093Z
     * id : string
     * isExperience  0：私教课，1：体验课
     * isPrepare : 0
     * lessonName : string
     * lessonPlace : string
     * memberName : string
     * punchStatus : 0
     * startDate : 2018-04-10T05:34:29.093Z
     * startDatetime : 2018-04-10T05:34:29.093Z
     * startTimeActual : 2018-04-10T05:34:29.093Z
     */

    private String endDatetime;
    private String endTimeActual;
    private String id;
    private String isExperience;
    private int isPrepare;
    private String isUseTemplate;
    private String lessonName;
    private String lessonPlace;
    private String memberName;
    private int punchStatus;
    private String startDate;
    private String startDatetime;
    private String startTimeActual;
    private String experienceRecordId;

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getEndTimeActual() {
        return endTimeActual;
    }

    public void setEndTimeActual(String endTimeActual) {
        this.endTimeActual = endTimeActual;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsPrepare() {
        return isPrepare;
    }

    public void setIsPrepare(int isPrepare) {
        this.isPrepare = isPrepare;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonPlace() {
        return lessonPlace;
    }

    public void setLessonPlace(String lessonPlace) {
        this.lessonPlace = lessonPlace;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(int punchStatus) {
        this.punchStatus = punchStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getStartTimeActual() {
        return startTimeActual;
    }

    public void setStartTimeActual(String startTimeActual) {
        this.startTimeActual = startTimeActual;
    }

    public String getExperienceRecordId() {
        return experienceRecordId;
    }

    public void setExperienceRecordId(String experienceRecordId) {
        this.experienceRecordId = experienceRecordId;
    }

    public String getIsUseTemplate() {
        return isUseTemplate;
    }

    public void setIsUseTemplate(String isUseTemplate) {
        this.isUseTemplate = isUseTemplate;
    }

    public String getIsExperience() {
        return isExperience;
    }

    public void setIsExperience(String isExperience) {
        this.isExperience = isExperience;
    }
}
