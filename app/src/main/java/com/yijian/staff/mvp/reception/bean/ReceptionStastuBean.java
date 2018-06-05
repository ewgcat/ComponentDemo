package com.yijian.staff.mvp.reception.bean;

import java.util.List;

/**
 * Created by The_P on 2018/4/22.
 */

public class ReceptionStastuBean {
//    isFinish (integer, optional): 是否完成流程:状态：0未完成，1已经完成 ,
//    operatorType (integer, optional): 刚刚执行的动作 ,
//    operatorTypeDesc (string, optional): 刚刚执行的动作描述 ,
//    receptionLogs (Array[接待流程日志], optional): 刚刚执行的动作描述 ,
//    status (integer, optional): 状态 ,
//    statusDesc (string, optional): 状态描述

    public Integer isFinish;
    public Integer operatorType;
    public String operatorTypeDesc;
    public List<ReceptionLog> receptionLogs;
    public Integer status;
    public String statusDesc;

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
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

    public List<ReceptionLog> getReceptionLogs() {
        return receptionLogs;
    }

    public void setReceptionLogs(List<ReceptionLog> receptionLogs) {
        this.receptionLogs = receptionLogs;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
