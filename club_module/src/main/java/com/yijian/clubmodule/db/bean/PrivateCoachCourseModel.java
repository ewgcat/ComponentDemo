package com.yijian.clubmodule.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/30 11:11:52
 */
@Entity
public  class PrivateCoachCourseModel {
    /**
     * consumingMinute : 0
     * memberCourseId : string
     * memberCourseName : string
     */

    @Id(autoincrement = true)
    private Long idx;
    private String id;
    private Integer consumingMinute;
    private String memberCourseId;
    private String memberCourseName;


}
