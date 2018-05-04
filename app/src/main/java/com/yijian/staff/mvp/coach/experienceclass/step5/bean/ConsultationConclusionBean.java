package com.yijian.staff.mvp.coach.experienceclass.step5.bean;

import com.yijian.staff.mvp.coach.experienceclass.step4.bean.ExperienceClassRecordTable;

import java.util.List;

/**
 * Created by The_P on 2018/5/4.
 */

public class ConsultationConclusionBean {
    String conclusionContext;
    List<ExperienceClassRecordTable> recordList;

    public String getConclusionContext() {
        return conclusionContext;
    }

    public void setConclusionContext(String conclusionContext) {
        this.conclusionContext = conclusionContext;
    }

    public List<ExperienceClassRecordTable> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<ExperienceClassRecordTable> recordList) {
        this.recordList = recordList;
    }
}
