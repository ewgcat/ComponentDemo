package com.yijian.staff.mvp.coach.experienceclass.step1.bean;

import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.TemplateListBean;

import java.util.List;

/**
 * Created by The_P on 2018/4/14.
 */

public class InviterBean {


//    private String processId;
//    private String memberId;
//    private String memberName;
//    private String coachName;
//    private Integer courseNum;
//    private Integer courseCurrent;
//    private String startTime;
//    private String remark;
//    private List<TemplateListBean> templateList;
//    private List<Object> customerTemplateList;//教练自定义模板
//
//    public List<Object> getCustomerTemplateList() {
//        return customerTemplateList;
//    }


    String coachName;//教练名字 ,
    Integer courseCurrent;//上课次数 ,
    Integer courseNum;//课程数量 ,
    Integer courseTime;//上课时长 ,
    List<TemplateListBean> experienceTemplateList;// (Array[体验课备课模板返回对象], optional): 体验课程模板 ,
    String memberId;// 会员id(受邀人) ,
    String memberName;//会员名字(受邀人) ,
    String processId;//体验课流程id，假如当前教练没有邀约过则无 ,
    String recordId;//邀约记录ID ,
    String remark;//备注 ,
    String startTime;//上课时间

    List<TemplatePrivate> privateTemplateList;//(Array[私教课备课模板返回对象], optional): 私教课模板 ,

    LessonPreparation prepareVO;//(体验课备课内容, optional): 如果是已经邀约过了，这个里面会有内容邀约时的备课内容 ,




}
