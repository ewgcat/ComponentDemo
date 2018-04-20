package com.yijian.staff.net.requestbody.huifang;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/20 10:39:32
 */
public class AddHuiFangResultBody {


    /**
     * dictItemId : 0
     * interviewRecordId : string
     * interviewResult : string
     * memberId : string
     * needReview : false
     * reviewTime : 2018-04-20T02:05:07.909Z
     */

    private String dictItemId;
    private String interviewRecordId;
    private String interviewResult;
    private String memberId;
    private boolean needReview;
    private String reviewTime;


    public AddHuiFangResultBody() {
    }

    public String getDictItemId() {
        return dictItemId;
    }

    public void setDictItemId(String dictItemId) {
        this.dictItemId = dictItemId;
    }

    public String getInterviewRecordId() {
        return interviewRecordId;
    }

    public void setInterviewRecordId(String interviewRecordId) {
        this.interviewRecordId = interviewRecordId;
    }

    public String getInterviewResult() {
        return interviewResult;
    }

    public void setInterviewResult(String interviewResult) {
        this.interviewResult = interviewResult;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public boolean isNeedReview() {
        return needReview;
    }

    public void setNeedReview(boolean needReview) {
        this.needReview = needReview;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }
}
