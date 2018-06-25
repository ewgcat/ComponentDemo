package com.yijian.staff.mvp.reception.bean;

/**
 * Created by The_P on 2018/5/14.
 */

public class ReceptionRecordBean {
//    coachId (string, optional): 接待教练id ,
//    coachName (string, optional): 接待教练名称 ,
//    memberId (string, optional): 会员id ,
//    memberName (string, optional): 会员名称 ,
//    mobile (string, optional): 会员手机 ,
//    referrerUserId (string, optional): 推荐人名称 ,
//    referrerUserName (string, optional): 推荐人名称 ,
//    sellerId (string, optional): 销售会籍id ,
//    sellerName (string, optional): 销售会籍名称 ,
//    visitRecordId (string, optional): 来访记录id ,
//    visitTime (string, optional): 来访时间
//      sex（int,optional） 0：未知  1：男 2：女


    private String coachId;
    private String coachName;
    private String memberId;
    private String memberName;
    private String mobile;
    private String referrerUserId;
    private String referrerUserName;
    private String sellerId;
    private String sellerName;
    private String visitRecordId;
    private String visitTime;
    private int sex;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReferrerUserId() {
        return referrerUserId;
    }

    public void setReferrerUserId(String referrerUserId) {
        this.referrerUserId = referrerUserId;
    }

    public String getReferrerUserName() {
        return referrerUserName;
    }

    public void setReferrerUserName(String referrerUserName) {
        this.referrerUserName = referrerUserName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getVisitRecordId() {
        return visitRecordId;
    }

    public void setVisitRecordId(String visitRecordId) {
        this.visitRecordId = visitRecordId;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
