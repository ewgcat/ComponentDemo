package com.yijian.staff.net.httpmanager.url;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/16 14:40:13
 */
public class CourseUrls {

    //上课打卡
    public static String COACH_PRIVATE_COURSE_STOCK_RECORD_SHANGKE_URL = "privatecourse/classBeginPunchCard";

    //下课打卡
    public static String COACH_PRIVATE_COURSE_STOCK_RECORD_XIAKE_URL = "privatecourse/classOverPunchCard";

    //课程实时信息
    public static String PRIVATE_COURSE_INFO_URL = "privatecourse/courseInfo";

    //课程评价
    public static String PRIVATE_COURSE_PINGJIA_URL = "privatecourse/addCourseSummary";

    //查私教排课周计划（新接口）
    public static String PRIVATE_COURSE_WEEK_PLAN_URL = "privatecourse/py/findPrivateCoachCAP_week";

    //查私教排课日计划（新接口）
    public static String PRIVATE_COURSE_DAY_PLAN_URL = "privatecourse/py/findPrivateCoachCAP_day";

    //查找可排课的学员（新接口）
    public static String PRIVATE_COURSE_STUDENT_LIST_URL = "privatecourse/py/findStudents";

    //校验排期是否可排（新接口）
    public static String PRIVATE_COURSE_PLAN_IS_ABLE_URL = "privatecourse/py/checkoutSchedule";


    //私教排课锁时间（新接口）
    public static String PRIVATE_COURSE_PLAN_LOCK_URL = "privatecourse/py/lockTime";

    //保存排课计划（新接口)
    public static String SAVE_PRIVATE_COURSE_PLAN_URL = "privatecourse/py/savePrivateCoachCAP";

    //Bapp端删除排课计划（新接口）
    public static String DELETE_PRIVATE_COURSE_PLAN_URL = "privatecourse/py/delPrivateCoachCAP";

    //Bapp端教练查看约课表（新接口）
    public static String PRIVATE_COURSE_DAY_TABLE_URL = "privatecourse/py/findPrivateapply2memberToBapp";

    //生成约课表（新接口）
    public static String ABORT_APPOINT_COURSE_TABLE_URL = "privatecourse/py/createPrivateapply2member";


}
