package com.yijian.staff.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/26 14:48:27
 */
public class ClassRecordBean {

    /**
     * courseName : string
     * courseNum : string
     * currentNum : string
     * endDateTime : 2018-04-19T02:01:53.602Z
     * id : string
     * startDateTime : 2018-04-19T02:01:53.602Z
     */

    private String courseName;
    private String courseNum;
    private String currentNum;
    private String startTime;
    private String endTime;
    private String id;
    private String startDate;


    public ClassRecordBean(JSONObject jsonObject) {
        this.courseName = JsonUtil.getString(jsonObject, "courseName");
        this.courseNum = JsonUtil.getString(jsonObject, "courseNum");
        this.currentNum = JsonUtil.getString(jsonObject, "currentNum");
        this.id = JsonUtil.getString(jsonObject, "id");
        this.startDate = JsonUtil.getString(jsonObject, "startDate");
        this.startTime = JsonUtil.getString(jsonObject, "startTime");
        this.endTime = JsonUtil.getString(jsonObject, "endTime");

    }


    public String getCourseName() {
        return courseName;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public String getCurrentNum() {
        return currentNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }
}
