package com.yijian.staff.mvp.complaint.handling;

import com.yijian.staff.R;
import com.yijian.staff.util.JsonUtil;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by yangk on 2018/3/9.
 */

public class HandTaskInfo {

    private String headerUrl;
    private String name;
    private int gender; //这里返回图片的路径
    private String status; //处理状态
    private String taskContent; //任务内容
    private String dispatchTaskTime; //任务分配的时间
    private List<String> taskImgs; //任务分配图片集合
    private HandTaskResultInfo handTaskResultInfo; //任务处理结果信息

    private List<String> evaluateContentList; //客户评价内容集合
    private String evaluateGrade; //客户评分星级

    public HandTaskInfo(JSONObject jsonObject) {
        this.headerUrl = JsonUtil.getString(jsonObject, "headerUrl");
        this.name = JsonUtil.getString(jsonObject, "name");
        this.gender = "0".equals(JsonUtil.getString(jsonObject, "gender")) ? R.mipmap.lg_women : R.mipmap.lg_man;
        this.status = "0".equals(JsonUtil.getString(jsonObject, "status")) ? "待处理" : "已处理";
        this.taskContent = JsonUtil.getString(jsonObject, "taskContent");
        this.dispatchTaskTime = JsonUtil.getString(jsonObject, "dispatchTaskTime");
        this.taskImgs = com.alibaba.fastjson.JSONArray.parseArray(JsonUtil.getJsonArray(jsonObject, "taskImgs").toString(), String.class);
        this.handTaskResultInfo = new HandTaskResultInfo(JsonUtil.getJsonObject(jsonObject, "handTaskResultInfo"));
        this.evaluateContentList = com.alibaba.fastjson.JSONArray.parseArray(JsonUtil.getJsonArray(jsonObject, "evaluateContentList").toString(), String.class);
        this.evaluateGrade = JsonUtil.getString(jsonObject, "evaluateGrade");
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getDispatchTaskTime() {
        return dispatchTaskTime;
    }

    public void setDispatchTaskTime(String dispatchTaskTime) {
        this.dispatchTaskTime = dispatchTaskTime;
    }

    public List<String> getTaskImgs() {
        return taskImgs;
    }

    public void setTaskImgs(List<String> taskImgs) {
        this.taskImgs = taskImgs;
    }

    public HandTaskResultInfo getHandTaskResultInfo() {
        return handTaskResultInfo;
    }

    public void setHandTaskResultInfo(HandTaskResultInfo handTaskResultInfo) {
        this.handTaskResultInfo = handTaskResultInfo;
    }

    public List<String> getEvaluateContentList() {
        return evaluateContentList;
    }

    public void setEvaluateContentList(List<String> evaluateContentList) {
        this.evaluateContentList = evaluateContentList;
    }

    public String getEvaluateGrade() {
        return evaluateGrade;
    }

    public void setEvaluateGrade(String evaluateGrade) {
        this.evaluateGrade = evaluateGrade;
    }

    class HandTaskResultInfo {
        private String headerUrl;
        private String name;
        private String taskResult; //处理任务的结果
        private String handTaskTime;//任务处理的时间

        public HandTaskResultInfo(JSONObject jsonObject) {
            this.headerUrl = JsonUtil.getString(jsonObject, "headerUrl");
            this.name = JsonUtil.getString(jsonObject, "name");
            this.taskResult = JsonUtil.getString(jsonObject, "taskResult");
            this.handTaskTime = JsonUtil.getString(jsonObject, "handTaskTime");
        }

        public String getHeaderUrl() {
            return headerUrl;
        }

        public void setHeaderUrl(String headerUrl) {
            this.headerUrl = headerUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTaskResult() {
            return taskResult;
        }

        public void setTaskResult(String taskResult) {
            this.taskResult = taskResult;
        }

        public String getHandTaskTime() {
            return handTaskTime;
        }

        public void setHandTaskTime(String handTaskTime) {
            this.handTaskTime = handTaskTime;
        }
    }

}
