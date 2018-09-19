package com.yijian.clubmodule.net.requestbody;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/10 16:08:29
 */
public class AddFuFangResultBody {


    /**
     * chief : true
     * id : string
     * reviewReason : string
     * reviewTime : 2018-08-10T06:07:15.995Z
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
