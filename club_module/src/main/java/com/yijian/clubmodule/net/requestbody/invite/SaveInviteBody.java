package com.yijian.clubmodule.net.requestbody.invite;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/10 10:23:50
 */
public class SaveInviteBody {


    /**
     * chief : true
     * content : string
     * memberId : string
     * memberType : 0
     * visitTime : 2018-08-10T02:22:32.438Z
     */

    private boolean chief;
    private String content;
    private String memberId;
    private String memberType;
    private String visitTime;

    public boolean isChief() {
        return chief;
    }

    public void setChief(boolean chief) {
        this.chief = chief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
