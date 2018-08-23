package com.yijian.staff.net.httpmanager.url;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/23 17:18:04
 */
public class HuiFangUrls {

    //回访类型
    public static String GET_HUI_FANG_TYPE_LIST_URL = "interviewV2/bapp/getInterviewMenus";


    //回访任务列表
    public static String HUI_FANG_TASK_URL = "interviewV2/bapp/interviewTaskListLimit";

    //回访记录列表
    public static String HUI_FANG_RECORD_URL = "interviewV2/bapp/interviewRecordList";




    //发送回访结果
    public static String POST_HUI_FANG_RESULT_URL = "interviewV2/bapp/interviewDone";

    //发送复访请求
    public static String POST_ABORT_FU_FANG_URL = "interviewV2/bapp/reInterview";
}
