package com.yijian.staff.mvp.coach.experienceclass.step1.bean;

import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.TemplateListBean;

/**
 * Created by The_P on 2018/4/25.
 */

public class LessonPreparation {
//    definedRecordVO (体验课上课记录表(使用自定义模板), optional): 如果备课内容是私教课模板/自定义模板，请选择这个 ,
//    templateVO (体验课备课模板返回对象, optional): 如果备课内容是体侧模板的，请选择这个
    TemplateListBean templateVO;//(体验课备课模板返回对象, optional): 如果备课内容是体侧模板的，请选择这个

    ClassRecordTable definedRecordVO;//(体验课上课记录表(使用自定义模板), optional): 如果备课内容是私教课模板/自定义模板，请选择这个 ,
}
