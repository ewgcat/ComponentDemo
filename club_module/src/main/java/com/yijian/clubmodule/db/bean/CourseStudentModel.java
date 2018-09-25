package com.yijian.clubmodule.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/30 11:09:57
 */
@Entity
public class CourseStudentModel {
    /**
     * day : string
     * dayAlias : string
     * localDate : string
     * privateCoachCurriculumArrangementPlanVOS : [{"coachId":"string","colour":"string","dataType":0,"duration":0,"eTime":"string","id":"string","privateCoachCourseVO":{"consumingMinute":0,"memberCourseId":"string","memberCourseName":"string"},"privateCourseMemberVO":{"headPath":"string","memberId":"string","memberName":"string"},"sTime":"string","week":0}]
     * weekCode : 0
     * weekName : string
     */
    @Id(autoincrement = true)
    private Long idx;
    private String day;
    private String dayAlias;
    private String localDate;
    private Integer weekCode;
    private String weekName;
    @Generated(hash = 767421336)
    public CourseStudentModel(Long idx, String day, String dayAlias, String localDate, Integer weekCode, String weekName) {
        this.idx = idx;
        this.day = day;
        this.dayAlias = dayAlias;
        this.localDate = localDate;
        this.weekCode = weekCode;
        this.weekName = weekName;
    }
    @Generated(hash = 876313228)
    public CourseStudentModel() {
    }
    public Long getIdx() {
        return this.idx;
    }
    public void setIdx(Long idx) {
        this.idx = idx;
    }
    public String getDay() {
        return this.day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getDayAlias() {
        return this.dayAlias;
    }
    public void setDayAlias(String dayAlias) {
        this.dayAlias = dayAlias;
    }
    public String getLocalDate() {
        return this.localDate;
    }
    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }
    public Integer getWeekCode() {
        return this.weekCode;
    }
    public void setWeekCode(Integer weekCode) {
        this.weekCode = weekCode;
    }
    public String getWeekName() {
        return this.weekName;
    }
    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }


}