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


}