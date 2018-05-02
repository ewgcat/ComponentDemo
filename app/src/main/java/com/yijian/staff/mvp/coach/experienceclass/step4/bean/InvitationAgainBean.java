package com.yijian.staff.mvp.coach.experienceclass.step4.bean;

import com.yijian.staff.mvp.coach.experienceclass.step1.bean.InviterBean;

import java.util.List;

/**
 * Created by The_P on 2018/4/25.
 */

public class InvitationAgainBean {

    public  InviterBean inviteVO;//(邀约节点返回对象, optional): 邀约相关信息 ,

    public List<ExperienceClassRecordTable> recordList;//(Array[体验课上课记录表返回对象], optional): 体验课上课记录表

    public InviterBean getInviteVO() {
        return inviteVO;
    }

    public void setInviteVO(InviterBean inviteVO) {
        this.inviteVO = inviteVO;
    }

    public List<ExperienceClassRecordTable> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<ExperienceClassRecordTable> recordList) {
        this.recordList = recordList;
    }
}
