package com.yijian.staff.mvp.course.setclass.bean;

public class NoInstrumentBean {


    /**
     * recordContextId : 1
     * contextType : 1
     * sort : 1
     * name : 台阶测试
     * groupNum : 99
     * groupTime : 99
     */

    private String recordContextId;
    private int contextType;
    private int sort;
    private String name;
    private int groupNum;
    private int groupTime;

    public String getRecordContextId() {
        return recordContextId;
    }

    public void setRecordContextId(String recordContextId) {
        this.recordContextId = recordContextId;
    }

    public int getContextType() {
        return contextType;
    }

    public void setContextType(int contextType) {
        this.contextType = contextType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public int getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(int groupTime) {
        this.groupTime = groupTime;
    }
}
