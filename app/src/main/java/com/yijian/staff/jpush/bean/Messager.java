package com.yijian.staff.jpush.bean;

import com.yijian.staff.mvp.reception.bean.ReceptionLog;

import java.util.List;

/**
 * Created by The_P on 2018/4/26.
 */

public class Messager {
    private Integer status;

    private String statusDesc;

    private Integer isFinish;

    private Integer operatorType;

    private String operatorTypeDesc;

    private String id;

    private String name;

    private Integer sex;

    private String mobile;

    private List<ReceptionLog> receptionLogs;

    private List<Integer> operatorTypes;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<ReceptionLog> getReceptionLogs() {
        return receptionLogs;
    }

    public void setReceptionLogs(List<ReceptionLog> receptionLogs) {
        this.receptionLogs = receptionLogs;
    }

    public List<Integer> getOperatorTypes() {
        return operatorTypes;
    }

    public void setOperatorTypes(List<Integer> operatorTypes) {
        this.operatorTypes = operatorTypes;
    }
}
