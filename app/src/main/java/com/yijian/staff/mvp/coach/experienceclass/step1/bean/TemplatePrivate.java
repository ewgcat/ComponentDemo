package com.yijian.staff.mvp.coach.experienceclass.step1.bean;

import java.util.List;

/**
 * Created by The_P on 2018/4/25.
 */

public class TemplatePrivate {

    public String templateId;//备课模板id ,
    public String templateName;//模板名称
    public List<TemplatePrivateContent> templateContextList;//(Array[备课模板内容返回对象], optional): 模板内容集合 ,

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<TemplatePrivateContent> getTemplateContextList() {
        return templateContextList;
    }

    public void setTemplateContextList(List<TemplatePrivateContent> templateContextList) {
        this.templateContextList = templateContextList;
    }
}
