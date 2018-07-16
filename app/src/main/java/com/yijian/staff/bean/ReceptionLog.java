package com.yijian.staff.bean;

/**
 * Created by The_P on 2018/4/22.
 */

public class ReceptionLog {
//    createTime (string, optional),
//    handleId (string, optional): 处理人ID ,
//    memberId (string, optional): 会员id ,
//    operatorType (integer, optional): 操作类型 ,
//    operatorTypeDesc (string, optional): 操作类型描述 ,
//    receptionId (string, optional): 流程id ,
//    receptionLogId (string, optional): 日志id ,
//    submitId (string, optional): 提交人id

    public String createTime;
    public String handleId;
    public String memberId;
    public Integer operatorType;
    public String operatorTypeDesc;
    public String receptionId;
    public String receptionLogId;
    public String submitId;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperatorTypeDesc() {
        return operatorTypeDesc;
    }

    public void setOperatorTypeDesc(String operatorTypeDesc) {
        this.operatorTypeDesc = operatorTypeDesc;
    }

    public String getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(String receptionId) {
        this.receptionId = receptionId;
    }

    public String getReceptionLogId() {
        return receptionLogId;
    }

    public void setReceptionLogId(String receptionLogId) {
        this.receptionLogId = receptionLogId;
    }

    public String getSubmitId() {
        return submitId;
    }

    public void setSubmitId(String submitId) {
        this.submitId = submitId;
    }
}
