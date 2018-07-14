package com.yijian.staff.mvp.course.experienceclass.invate.bean;

import java.util.List;

/**
 * Created by The_P on 2018/5/4.
 */

public class InvateBean {
//    保存邀约相关信息传参对象 {
//        chief (boolean, optional),
//        courseTime (integer, optional): 课程时长 ,
//                experienceDefinedPrepareList (Array[体验课自定义备课内容传参对象], optional): 自定义备课内容集合，如果选择自定义模板，请填这个 ,
//                memberId (string): 会员id ,
//                privateTemplateId (string, optional): 如果选择私教课备课模板，请填这个 ,
//                processId (string): 体验课流程id ,
//                remark (string, optional): 备注 ,
//                startTime (string, optional): 上课时间 ,
//                templateId (string, optional): 如果选择体测备课模板，请填这个
//    }
//    体验课自定义备课内容传参对象 {
//        buildDesc (string, optional): 训练组数描述（组数×次数或秒数） ,
//        moApplianceName (string, optional): 器械名称 ,
//                moDifficulty (string, optional): 训练难度(1：高，2：中，3：低) ,
//                moName (string, optional): 动作名称 ,
//                moParts (string, optional): 训练部位
//    }

    boolean chief;
    Integer courseTime;//课程时长
    List<ExperienceDefinedPrepareBean> experienceDefinedPrepareList;//(Array[体验课自定义备课内容传参对象], optional): 自定义备课内容集合，如果选择自定义模板，请填这个 ,
    String memberId;
    String privateTemplateId;
    String processId;
    String remark;
    String startTime;
    String templateId;

    public boolean isChief() {
        return chief;
    }

    public void setChief(boolean chief) {
        this.chief = chief;
    }

    public Integer getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Integer courseTime) {
        this.courseTime = courseTime;
    }

    public List<ExperienceDefinedPrepareBean> getExperienceDefinedPrepareList() {
        return experienceDefinedPrepareList;
    }

    public void setExperienceDefinedPrepareList(List<ExperienceDefinedPrepareBean> experienceDefinedPrepareList) {
        this.experienceDefinedPrepareList = experienceDefinedPrepareList;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPrivateTemplateId() {
        return privateTemplateId;
    }

    public void setPrivateTemplateId(String privateTemplateId) {
        this.privateTemplateId = privateTemplateId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
