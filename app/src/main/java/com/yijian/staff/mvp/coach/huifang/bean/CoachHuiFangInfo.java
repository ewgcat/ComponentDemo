package com.yijian.staff.mvp.coach.huifang.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 16:22:37
 */
public class CoachHuiFangInfo {
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
    private String shengri;
    private String shengriType;
    private String hetongYuEr;
    private String heTongDaoQiRi;
    private String cardName;
    private String cardType;
    private String zuijinJianShen;
    private String chenMoTianShu;
    private String preFangWenRiqi;
    private String fufangReason;
    private String preJianShenTime;
    private String weiJianShenTime;
    private String huifangReason;






    public CoachHuiFangInfo(JSONObject jsonObject) {
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
        this.shengri = JsonUtil.getString(jsonObject, "shengri");
        this.shengriType = JsonUtil.getString(jsonObject, "shengriType");
        this.huifangReason = JsonUtil.getString(jsonObject, "huifangReason");
        this.heTongDaoQiRi = JsonUtil.getString(jsonObject, "heTongDaoQiRi");
        this.cardName = JsonUtil.getString(jsonObject, "cardName");
        this.cardType = JsonUtil.getString(jsonObject, "cardType");
        this.zuijinJianShen = JsonUtil.getString(jsonObject, "zuijinJianShen");
        this.chenMoTianShu = JsonUtil.getString(jsonObject, "chenMoTianShu");
        this.preFangWenRiqi = JsonUtil.getString(jsonObject, "preFangWenRiqi");
        this.fufangReason  = JsonUtil.getString(jsonObject, "fufangReason");
        this.preJianShenTime  = JsonUtil.getString(jsonObject, "preJianShenTime");
        this.weiJianShenTime  = JsonUtil.getString(jsonObject, "weiJianShenTime");

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

    public String getXingquAihao() {
        return xingquAihao;
    }

    public String getShengri() {
        return shengri;
    }

    public String getShengriType() {
        return shengriType;
    }

    public String getHetongYuEr() {
        return hetongYuEr;
    }

    public String getHeTongDaoQiRi() {
        return heTongDaoQiRi;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public String getZuijinJianShen() {
        return zuijinJianShen;
    }

    public String getChenMoTianShu() {
        return chenMoTianShu;
    }

    public String getPreFangWenRiqi() {
        return preFangWenRiqi;
    }

    public String getFufangReason() {
        return fufangReason;
    }

    public String getPreJianShenTime() {
        return preJianShenTime;
    }

    public String getWeiJianShenTime() {
        return weiJianShenTime;
    }
}
