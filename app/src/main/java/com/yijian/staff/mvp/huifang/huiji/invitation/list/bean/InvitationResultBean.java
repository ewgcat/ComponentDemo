package com.yijian.staff.mvp.huifang.huiji.invitation.list.bean;

/**
 * Created by yangk on 2018/3/8.
 */

public class InvitationResultBean {

    /**
     * memberId : 85fec0bef849466fb95697d4bb7f2901
     * memberName : 正式会员
     * memberType : 2
     * memberTypeName : null
     * mobile : 15615715588
     * wechatCccount : 值0
     * birthday : 1523721600000
     * content : 实际上计算机
     * visitTime : 1523808000000
     * visitResult : 1
     * visitResultName : 爽约
     * emaiL : 455123231@qq.com
     */

    private String memberId;
    private String memberName;
    private Long memberType;
    private String memberTypeName;
    private String mobile;
    private String wechatCccount;
    private Long birthday;
    private String content;
    private Long visitTime;
    private String visitResult;
    private String visitResultName;
    private String emaiL;

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

    public Long getMemberType() {
        return memberType;
    }

    public void setMemberType(Long memberType) {
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

    public String getWechatCccount() {
        return wechatCccount;
    }

    public void setWechatCccount(String wechatCccount) {
        this.wechatCccount = wechatCccount;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(long visitTime) {
        this.visitTime = visitTime;
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

    public String getEmaiL() {
        return emaiL;
    }

    public void setEmaiL(String emaiL) {
        this.emaiL = emaiL;
    }
}
