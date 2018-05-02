package com.yijian.staff.mvp.coach.experienceclass.step1.bean;

import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.TemplateListBean;

import java.util.List;

/**
 * Created by The_P on 2018/4/14.
 */

public class InviterBean {

  public   String coachName;//教练名字 ,
    public Integer courseCurrent;//上课次数 ,
    public  Integer courseNum;//课程数量 ,
    public Integer courseTime;//上课时长 ,
    public  List<TemplateListBean> experienceTemplateList;// (Array[体验课备课模板返回对象], optional): 体验课程模板 ,
    public  String memberId;// 会员id(受邀人) ,
    public String memberName;//会员名字(受邀人) ,
    public String processId;//体验课流程id，假如当前教练没有邀约过则无 ,
    public String recordId;//邀约记录ID ,
    public String remark;//备注 ,
    public String startTime;//上课时间

    List<TemplatePrivate> privateTemplateList;//(Array[私教课备课模板返回对象], optional): 私教课模板 ,

    LessonPreparation prepareVO;//(体验课备课内容, optional): 如果是已经邀约过了，这个里面会有内容邀约时的备课内容 ,

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Integer getCourseCurrent() {
        return courseCurrent;
    }

    public void setCourseCurrent(Integer courseCurrent) {
        this.courseCurrent = courseCurrent;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public Integer getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Integer courseTime) {
        this.courseTime = courseTime;
    }

    public List<TemplateListBean> getExperienceTemplateList() {
        return experienceTemplateList;
    }

    public void setExperienceTemplateList(List<TemplateListBean> experienceTemplateList) {
        this.experienceTemplateList = experienceTemplateList;
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

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
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

    public List<TemplatePrivate> getPrivateTemplateList() {
        return privateTemplateList;
    }

    public void setPrivateTemplateList(List<TemplatePrivate> privateTemplateList) {
        this.privateTemplateList = privateTemplateList;
    }

    public LessonPreparation getPrepareVO() {
        return prepareVO;
    }

    public void setPrepareVO(LessonPreparation prepareVO) {
        this.prepareVO = prepareVO;
    }
}
