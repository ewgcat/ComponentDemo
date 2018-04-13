package com.yijian.staff.mvp.reception.step3.coach.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by The_P on 2018/4/12.
 */

public class ReceptionUserInfo {


//    private String receptionId;
    private String memberId;
//    private String merchantId;
//    private String shopId;
//    private String cardId;e
    private String coachId;
    private String saleId;
//    private String leaderId;
//    private Date createTime;
//    private String createBy;
//    private Date updateTime;
//    private String updateBy;
//    private Integer isDelete;
//    private Integer isFinish;
//    private Integer smallStatus;
//    private String smallStatusDesc;
//    private Integer bigStatus;
//    private String bigStatusDesc;
//    private String memberBcRejectReason;
//    private String toLeaderReason;
//    private BigDecimal qsCompletionRate;
//    private BigDecimal bodyCheckCompletionRate;
    private String memberMobile;
    private String memberName;
    private String coachName;
    private String saleName;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
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
    //    private String cardName;


    @Override
    public String toString() {
        return "ReceptionUserInfo{" +
                "memberId='" + memberId + '\'' +
                ", coachId='" + coachId + '\'' +
                ", saleId='" + saleId + '\'' +
                ", memberMobile='" + memberMobile + '\'' +
                ", memberName='" + memberName + '\'' +
                ", coachName='" + coachName + '\'' +
                ", saleName='" + saleName + '\'' +
                '}';
    }
}
