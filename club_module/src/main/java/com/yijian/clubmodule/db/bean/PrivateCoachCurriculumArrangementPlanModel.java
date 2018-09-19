package com.yijian.clubmodule.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/30 11:13:05
 */
@Entity
public  class PrivateCoachCurriculumArrangementPlanModel {
    /**
     * coachId : string
     * colour : string
     * dataType : 0
     * duration : 0
     * eTime : string
     * id : string
     * privateCoachCourseVO : {"consumingMinute":0,"memberCourseId":"string","memberCourseName":"string"}
     * privateCourseMemberVO : {"headPath":"string","memberId":"string","memberName":"string"}
     * sTime : string
     * week : 0
     */
    @Id(autoincrement = true)
    private Long idx;
    private String coachId;
    private String colour;
    private Integer dataType;
    private Integer duration;
    private String eTime;
    private String id;
    private String sTime;
    private Integer week;

    @Generated(hash = 833732481)
    public PrivateCoachCurriculumArrangementPlanModel(Long idx, String coachId, String colour, Integer dataType,
            Integer duration, String eTime, String id, String sTime, Integer week) {
        this.idx = idx;
        this.coachId = coachId;
        this.colour = colour;
        this.dataType = dataType;
        this.duration = duration;
        this.eTime = eTime;
        this.id = id;
        this.sTime = sTime;
        this.week = week;
    }

    @Generated(hash = 179403981)
    public PrivateCoachCurriculumArrangementPlanModel() {
    }

    @Override
    public String toString() {
        return "PrivateCoachCurriculumArrangementPlanModel{" +
                "coachId='" + coachId + '\'' +
                ", colour='" + colour + '\'' +
                ", dataType=" + dataType +
                ", duration=" + duration +
                ", eTime='" + eTime + '\'' +
                ", id='" + id + '\'' +
                ", sTime='" + sTime + '\'' +
                ", week=" + week +
                '}';
    }

    public Long getIdx() {
        return this.idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getCoachId() {
        return this.coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getColour() {
        return this.colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getDataType() {
        return this.dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getETime() {
        return this.eTime;
    }

    public void setETime(String eTime) {
        this.eTime = eTime;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSTime() {
        return this.sTime;
    }

    public void setSTime(String sTime) {
        this.sTime = sTime;
    }

    public Integer getWeek() {
        return this.week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}