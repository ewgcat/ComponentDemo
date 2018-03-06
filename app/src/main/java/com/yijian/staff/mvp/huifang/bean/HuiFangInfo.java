package com.yijian.staff.mvp.huifang.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 16:22:37
 */
public class HuiFangInfo {
    private String headUrl;
    private String name;
    private String sex;
    private String shentiStatus;
    private String jianshenAihao;
    private String xingquAihao;
    private String carName;
    private String quanyi;
    private String outdateTime;
    private String outdateReason;
    private String huifangType;
    private String huifangReason;




    public HuiFangInfo(JSONObject jsonObject) {
        this.headUrl = JsonUtil.getString(jsonObject, "headUrl");
        this.name = JsonUtil.getString(jsonObject, "name");
        this.sex = JsonUtil.getString(jsonObject, "sex");
        this.shentiStatus = JsonUtil.getString(jsonObject, "shentiStatus");
        this.jianshenAihao = JsonUtil.getString(jsonObject, "jianshenAihao");
        this.xingquAihao = JsonUtil.getString(jsonObject, "xingquAihao");
        this.carName = JsonUtil.getString(jsonObject, "carName");
        this.quanyi = JsonUtil.getString(jsonObject, "quanyi");
        this.outdateTime = JsonUtil.getString(jsonObject, "outdateTime");
        this.outdateReason = JsonUtil.getString(jsonObject, "outdateReason");
        this.huifangType = JsonUtil.getString(jsonObject, "huifangType");
        this.huifangReason = JsonUtil.getString(jsonObject, "huifangReason");
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getOutdateTime() {
        return outdateTime;
    }

    public String getOutdateReason() {
        return outdateReason;
    }

    public String getHuifangType() {
        return huifangType;
    }

    public String getHuifangReason() {
        return huifangReason;
    }

    public String getQuanyi() {
        return quanyi;
    }

    public String getShentiStatus() {
        return shentiStatus;
    }

    public String getJianshenAihao() {
        return jianshenAihao;
    }

    public String getXinquAihao() {
        return xingquAihao;
    }

    public String getCarName() {
        return carName;
    }
}
