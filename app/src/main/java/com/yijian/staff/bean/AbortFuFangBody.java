package com.yijian.staff.bean;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/23 11:07:27
 */
public class AbortFuFangBody {


    /**
     * chief : true
     * id : string
     * reviewReason : string
     * reviewTime : 2018-08-23T01:57:32.822Z
     */

    private boolean chief;
    private String id;
    private String reviewReason;
    private String reviewTime;

    public boolean isChief() {
        return chief;
    }

    public void setChief(boolean chief) {
        this.chief = chief;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }
}
