package com.yijian.staff.mvp.coach.huifang.bean;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/19 15:53:35
 */
public class CoachHuiFangTypeBean {


    /**
     * id : 6
     * configType : 1
     * postId : 2
     * configName : 生日
     * triggerDay : 0
     * order : 100
     */

    private int id;
    private int configType;
    private int postId;
    private String configName;
    private int triggerDay;
    private int order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConfigType() {
        return configType;
    }

    public void setConfigType(int configType) {
        this.configType = configType;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public int getTriggerDay() {
        return triggerDay;
    }

    public void setTriggerDay(int triggerDay) {
        this.triggerDay = triggerDay;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
