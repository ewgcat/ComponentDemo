package com.yijian.staff.mvp.course.experienceclass.step4.bean;

import com.yijian.staff.bean.ClassRecordTable;
import com.yijian.staff.mvp.course.experienceclass.template.template_system.bean.TemplateListBean;

/**
 * Created by The_P on 2018/4/25.
 */

public class ExperienceClassRecordTable {

    public ClassRecordTable definedRecord;//(体验课上课记录表(使用自定义模板), optional): 上课记录表(采用私教课模板/自定义备课内容) ,
    public Integer num;//第几个记录表 ,
    public TemplateListBean prepareRecord;//(体验课上课记录表(采用备课模板), optional): 上课记录表(采用备课模板) ,
    public String recordId;// 记录表id ,
    public String templateName;// 模板名字

    public ClassRecordTable getDefinedRecord() {
        return definedRecord;
    }

    public void setDefinedRecord(ClassRecordTable definedRecord) {
        this.definedRecord = definedRecord;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public TemplateListBean getPrepareRecord() {
        return prepareRecord;
    }

    public void setPrepareRecord(TemplateListBean prepareRecord) {
        this.prepareRecord = prepareRecord;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
