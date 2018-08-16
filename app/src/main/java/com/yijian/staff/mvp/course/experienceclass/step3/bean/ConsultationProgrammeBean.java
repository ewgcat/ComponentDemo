package com.yijian.staff.mvp.course.experienceclass.step3.bean;

import com.yijian.staff.mvp.course.experienceclass.step2.bean.AccessRecordBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.PhysicalExaminationBean;

/**
 * Created by The_P on 2018/4/16.
 */

public class ConsultationProgrammeBean {

    /**
     * processId : 1
     * programmeContext : null
     * bodyCheck : null
     * visitRecord : {"processId":null,"mobile":null,"coachVisitRecord":null,"sellerVisitRecord":null}
     */

    private String processId;
    private String programmeContext;
    private Object bodyCheck;
    private AccessRecordBean visitRecord;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProgrammeContext() {
        return programmeContext;
    }

    public void setProgrammeContext(String programmeContext) {
        this.programmeContext = programmeContext;
    }

    public Object getBodyCheck() {
        return bodyCheck;
    }

    public void setBodyCheck(Object bodyCheck) {
        this.bodyCheck = bodyCheck;
    }

    public AccessRecordBean getVisitRecord() {
        return visitRecord;
    }

    public void setVisitRecord(AccessRecordBean visitRecord) {
        this.visitRecord = visitRecord;
    }
}
