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


}