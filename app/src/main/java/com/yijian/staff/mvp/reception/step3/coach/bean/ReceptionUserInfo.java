package com.yijian.staff.mvp.reception.step3.coach.bean;

/**
 * Created by The_P on 2018/4/12.
 */

public class ReceptionUserInfo {


    /**
     * receptionId : 01aae16feb2a4963a4413e1aa251c096
     * memberId : 1
     * merchantId : 333
     * shopId : 11
     * cardId : 7a99b42260e644af89cd69c92fa5309b
     * coachId :
     * saleId : 1
     * leaderId :
     * createTime : 1523329366000
     * createBy : 1
     * updateTime : 1523968455000
     * updateBy :
     * isDelete : 0
     * isFinish : 0
     * smallStatus : 33
     * smallStatusDesc : 会员没购买意愿，会籍TO教练
     * bigStatus : 50
     * bigStatusDesc : 成交
     * memberBcRejectReason :
     * toLeaderReason :
     * qsCompletionRate : 0
     * bodyCheckCompletionRate : 0
     * memberMobile :
     * memberName :
     * coachName :
     * saleName :
     * cardName :
     */

//    private String receptionId;
//    private String memberId;
//    private String merchantId;
//    private String shopId;
//    private String cardId;
//    private String coachId;
//    private String saleId;
//    private String leaderId;
//    private long createTime;
//    private String createBy;
//    private long updateTime;
//    private String updateBy;
//    private int isDelete;
//    private int isFinish;
//    private int smallStatus;
//    private String smallStatusDesc;
//    private int bigStatus;
//    private String bigStatusDesc;
    private String memberBcRejectReason;
    //    private String toLeaderReason;
//    private int qsCompletionRate;
//    private int bodyCheckCompletionRate;
    private String memberMobile;
    private String memberName;
    private String coachName;
    private String saleName;
    private String sex;
//    private String cardName;


    public String getMemberBcRejectReason() {
        return memberBcRejectReason;
    }

    public void setMemberBcRejectReason(String memberBcRejectReason) {
        this.memberBcRejectReason = memberBcRejectReason;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
