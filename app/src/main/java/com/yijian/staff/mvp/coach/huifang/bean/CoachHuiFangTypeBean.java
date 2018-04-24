package com.yijian.staff.mvp.coach.huifang.bean;

import com.yijian.staff.util.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.json.JSONObject;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/19 15:53:35
 */
@Entity
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
    private int configType;//【0:全部，1:生日，2:昨日到访，3:昨日开卡，4:潜在会员，5:沉寂会员，6:恢复健身，7:复访，8:过期，9:快到期，10:易建平台，11:体验课，12:昨日上课，13:定时体测，14:私课上完，15:昨日买课】
    private int postId;
    private String configName;
    private int triggerDay;
    private int order;


    public CoachHuiFangTypeBean(JSONObject jsonObject){
        this.id= JsonUtil.getInt(jsonObject,"id");
        this.configType= JsonUtil.getInt(jsonObject,"configType");
        this.postId= JsonUtil.getInt(jsonObject,"postId");
        this.triggerDay= JsonUtil.getInt(jsonObject,"triggerDay");
        this.order= JsonUtil.getInt(jsonObject,"order");
        this.configName= JsonUtil.getString(jsonObject,"configName");
    }
    @Generated(hash = 506956304)
    public CoachHuiFangTypeBean(int id, int configType, int postId, String configName, int triggerDay, int order) {
        this.id = id;
        this.configType = configType;
        this.postId = postId;
        this.configName = configName;
        this.triggerDay = triggerDay;
        this.order = order;
    }
    @Generated(hash = 660955335)
    public CoachHuiFangTypeBean() {
    }
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
