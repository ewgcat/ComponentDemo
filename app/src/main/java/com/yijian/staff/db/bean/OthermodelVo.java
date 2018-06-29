package com.yijian.staff.db.bean;

import com.yijian.staff.util.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.json.JSONObject;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/6/27 14:30:01
 */
@Entity
public class OthermodelVo {


    /**
     * faceRecognition : false
     * schedule : false
     * reception : false
     */

    private boolean faceRecognition;
    private boolean schedule;
    private boolean reception;



    public OthermodelVo(JSONObject jsonObject) {
        this.faceRecognition = JsonUtil.getBoolean(jsonObject, "faceRecognition");
        this.schedule = JsonUtil.getBoolean(jsonObject, "schedule");
        this.reception = JsonUtil.getBoolean(jsonObject, "reception");
    }
    @Generated(hash = 701914135)
    public OthermodelVo(boolean faceRecognition, boolean schedule,
            boolean reception) {
        this.faceRecognition = faceRecognition;
        this.schedule = schedule;
        this.reception = reception;
    }
    @Generated(hash = 806863264)
    public OthermodelVo() {
    }
    public boolean isFaceRecognition() {
        return faceRecognition;
    }

    public void setFaceRecognition(boolean faceRecognition) {
        this.faceRecognition = faceRecognition;
    }

    public boolean isSchedule() {
        return schedule;
    }

    public void setSchedule(boolean schedule) {
        this.schedule = schedule;
    }

    public boolean isReception() {
        return reception;
    }

    public void setReception(boolean reception) {
        this.reception = reception;
    }
    public boolean getFaceRecognition() {
        return this.faceRecognition;
    }
    public boolean getSchedule() {
        return this.schedule;
    }
    public boolean getReception() {
        return this.reception;
    }

    @Override
    public String toString() {
        return "OthermodelVo{" +
                "faceRecognition=" + faceRecognition +
                ", schedule=" + schedule +
                ", reception=" + reception +
                '}';
    }
}
