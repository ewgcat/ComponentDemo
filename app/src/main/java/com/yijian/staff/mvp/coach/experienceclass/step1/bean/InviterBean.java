package com.yijian.staff.mvp.coach.experienceclass.step1.bean;

import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.TemplateListBean;

import java.util.List;

/**
 * Created by The_P on 2018/4/14.
 */

public class InviterBean {

    /**
     * processId : 1
     * memberId : 6661
     * memberName : 何健林1
     * coachName : 教练测试账号
     * courseNum : 2
     * courseCurrent : 0
     * startTime : null
     * remark : null
     * templateList : [{"templateId":"1","templateName":"体验课测试模板","noInstrumentList":[{"templateContextId":"1","contextType":1,"sort":1,"name":"台阶测试","groupNum":2,"groupTime":60},{"templateContextId":"2","contextType":1,"sort":2,"name":"卷腹测试","groupNum":3,"groupTime":60},{"templateContextId":"3","contextType":1,"sort":3,"name":"俯卧撑测试","groupNum":1,"groupTime":30}],"aerobicsList":[{"templateContextId":"5","contextType":2,"sort":1,"name":"健身单车","grade":"S级","time":600},{"templateContextId":"4","contextType":2,"sort":2,"name":"跑步机","grade":"A级","time":600}],"powerList":[{"templateContextId":"7","contextType":3,"sort":1,"name":"史密斯平板推胸","groupNum":2,"groupTime":10,"weight":50},{"templateContextId":"8","contextType":3,"sort":2,"name":"推荐训练机","groupNum":3,"groupTime":10,"weight":30},{"templateContextId":"6","contextType":3,"sort":3,"name":"推胸训练机","groupNum":2,"groupTime":20,"weight":100}]},{"templateId":"2","templateName":"体验课测试模板2","noInstrumentList":[{"templateContextId":"9","contextType":1,"sort":1,"name":"坐姿前屈","groupNum":3,"groupTime":20}],"aerobicsList":[],"powerList":[]}]
     */

    private String processId;
    private String memberId;
    private String memberName;
    private String coachName;
    private Integer courseNum;
    private Integer courseCurrent;
    private String startTime;
    private String remark;
    private List<TemplateListBean> templateList;
    private List<Object> customerTemplateList;//教练自定义模板

    public List<Object> getCustomerTemplateList() {
        return customerTemplateList;
    }

    public void setCustomerTemplateList(List<Object> customerTemplateList) {
        this.customerTemplateList = customerTemplateList;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
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

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public Integer getCourseCurrent() {
        return courseCurrent;
    }

    public void setCourseCurrent(Integer courseCurrent) {
        this.courseCurrent = courseCurrent;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<TemplateListBean> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<TemplateListBean> templateList) {
        this.templateList = templateList;
    }
}
