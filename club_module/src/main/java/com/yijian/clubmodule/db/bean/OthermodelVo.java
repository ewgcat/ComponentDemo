package com.yijian.clubmodule.db.bean;

import com.yijian.commonlib.util.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.json.JSONObject;

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
    private boolean coachSchedule;
    private boolean sellerSchedule;
    private boolean reception;

    public OthermodelVo(JSONObject jsonObject) {
        this.faceRecognition = JsonUtil.getBoolean(jsonObject, "faceRecognition");
        this.coachSchedule = JsonUtil.getBoolean(jsonObject, "coachSchedule");
        this.sellerSchedule = JsonUtil.getBoolean(jsonObject, "sellerSchedule");
        this.reception = JsonUtil.getBoolean(jsonObject, "reception");
    }

    @Generated(hash = 1988909857)
    public OthermodelVo(boolean faceRecognition, boolean coachSchedule,
            boolean sellerSchedule, boolean reception) {
        this.faceRecognition = faceRecognition;
        this.coachSchedule = coachSchedule;
        this.sellerSchedule = sellerSchedule;
        this.reception = reception;
    }

    @Generated(hash = 806863264)
    public OthermodelVo() {
    }

    public boolean getFaceRecognition() {
        return this.faceRecognition;
    }

    public void setFaceRecognition(boolean faceRecognition) {
        this.faceRecognition = faceRecognition;
    }

    public boolean getCoachSchedule() {
        return this.coachSchedule;
    }

    public void setCoachSchedule(boolean coachSchedule) {
        this.coachSchedule = coachSchedule;
    }

    public boolean getSellerSchedule() {
        return this.sellerSchedule;
    }

    public void setSellerSchedule(boolean sellerSchedule) {
        this.sellerSchedule = sellerSchedule;
    }

    public boolean getReception() {
        return this.reception;
    }

    public void setReception(boolean reception) {
        this.reception = reception;
    }

}
