package com.yijian.staff.mvp.huifang.huiji.invitation.list.bean;

/**
 * Created by yangk on 2018/3/8.
 */

public class InvitationRecordBean {


    /**
     * birthday : 2018-04-26T07:16:47.092Z
     * content : string
     * emali : string
     * memberId : string
     * memberName : string
     * memberType : 0
     * memberTypeName : string
     * mobile : string
     * visitResult : 0
     * visitResultName : string
     * visitTime : 2018-04-26T07:16:47.092Z
     * wechatCccount : string
     */


    private String birthday;
    private String content;
    private String email;
    private String memberId;
    private String memberName;
    /**
     * 0：潜在会员 1：意向会员 2：正式会员 3：过期会员 4：潜在会员
     */
    private int memberType;
    private String memberTypeName;
    private String mobile;
    private String visitResult;
    private String visitResultName;
    private Long visitTime;
    private String wechatCccount;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }

    public String getMemberTypeName() {
        return memberTypeName;
    }

    public void setMemberTypeName(String memberTypeName) {
        this.memberTypeName = memberTypeName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVisitResult() {
        return visitResult;
    }

    public void setVisitResult(String visitResult) {
        this.visitResult = visitResult;
    }

    public String getVisitResultName() {
        return visitResultName;
    }

    public void setVisitResultName(String visitResultName) {
        this.visitResultName = visitResultName;
    }

    public Long getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Long visitTime) {
        this.visitTime = visitTime;
    }

    public String getWechatCccount() {
        return wechatCccount;
    }

    public void setWechatCccount(String wechatCccount) {
        this.wechatCccount = wechatCccount;
    }
}
