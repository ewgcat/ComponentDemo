package com.yijian.staff.bean;

public class PrivateLessonRecordBean {


    /**
     * id : 1
     * sort : 1
     * templateContextId : 1
     * moName : 引体向上
     * moDifficulty : 1
     * moParts : 5
     * moApplianceName : 单杠
     * buildTime : 20次/1组
     * actionForm : 1
     * actionStrength : 3
     * time : 23076000
     * interval : 30
     */

    private String id;
    private int sort;
    private String prepareId;
    private String moName;
    private String moDifficulty;
    private String moParts;
    private String moApplianceName;
    private String buildTime;
    private Integer actionForm;
    private Integer actionStrength;
    private String time;
    private Integer interval;
    private boolean isStartClock = false;
    ;

 /*   public PrivateLessonRecordBean(JSONObject jsonObj) {
        try {
            this.id = jsonObj.getString("id");
            this.sort = jsonObj.getInt("sort");
            this.prepareId = jsonObj.getString("prepareId");
            this.moName = jsonObj.getString("moName");
            this.moDifficulty = jsonObj.getString("moDifficulty");
            this.moParts = jsonObj.getString("moParts");
            this.moApplianceName = jsonObj.getString("moApplianceName");
            this.buildTime = jsonObj.getString("buildTime");
            this.actionForm = jsonObj.getInt("actionForm");
            this.actionStrength = jsonObj.getInt("actionStrength");
            this.time = jsonObj.getString("time");
            this.interval = jsonObj.getInt("interval");
            this.isStartClock = false;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getPrepareId() {
        return prepareId;
    }

    public void setPrepareId(String prepareId) {
        this.prepareId = prepareId;
    }

    public String getMoName() {
        return moName;
    }

    public void setMoName(String moName) {
        this.moName = moName;
    }

    public String getMoDifficulty() {
        return moDifficulty;
    }

    public void setMoDifficulty(String moDifficulty) {
        this.moDifficulty = moDifficulty;
    }

    public String getMoParts() {
        return moParts;
    }

    public void setMoParts(String moParts) {
        this.moParts = moParts;
    }

    public String getMoApplianceName() {
        return moApplianceName;
    }

    public void setMoApplianceName(String moApplianceName) {
        this.moApplianceName = moApplianceName;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public Integer getActionForm() {
        return actionForm;
    }

    public void setActionForm(Integer actionForm) {
        this.actionForm = actionForm;
    }

    public Integer getActionStrength() {
        return actionStrength;
    }

    public void setActionStrength(Integer actionStrength) {
        this.actionStrength = actionStrength;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }


    public boolean isStartClock() {
        return isStartClock;
    }

    public void setStartClock(boolean startClock) {
        isStartClock = startClock;
    }
}
