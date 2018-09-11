package com.yijian.staff.db.bean;

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

    @Generated(hash = 2115229308)
    public PrivateCoachCourseModel(Long idx, String id, Integer consumingMinute,
            String memberCourseId, String memberCourseName) {
        this.idx = idx;
        this.id = id;
        this.consumingMinute = consumingMinute;
        this.memberCourseId = memberCourseId;
        this.memberCourseName = memberCourseName;
    }

    @Generated(hash = 979390038)
    public PrivateCoachCourseModel() {
    }

    @Override
    public String toString() {
        return "PrivateCoachCourseModel{" +
                "id='" + id + '\'' +
                ", consumingMinute=" + consumingMinute +
                ", memberCourseId='" + memberCourseId + '\'' +
                ", memberCourseName='" + memberCourseName + '\'' +
                '}';
    }

    public Long getIdx() {
        return this.idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getConsumingMinute() {
        return this.consumingMinute;
    }

    public void setConsumingMinute(Integer consumingMinute) {
        this.consumingMinute = consumingMinute;
    }

    public String getMemberCourseId() {
        return this.memberCourseId;
    }

    public void setMemberCourseId(String memberCourseId) {
        this.memberCourseId = memberCourseId;
    }

    public String getMemberCourseName() {
        return this.memberCourseName;
    }

    public void setMemberCourseName(String memberCourseName) {
        this.memberCourseName = memberCourseName;
    }
}
