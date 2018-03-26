package com.yijian.staff.mvp.baojia.coach.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/17 19:29:17
 */
public class ClassInfo {
    private String goodsName;
    private String jianshenplace;
    private String yuer;
    private String chuzhiyouhui;
    private String price;
    public ClassInfo(JSONObject jsonObject){
        this.goodsName=  JsonUtil.getString(jsonObject,"goodsName");
        this.jianshenplace=  JsonUtil.getString(jsonObject,"jianshenplace");

        this.yuer=  JsonUtil.getString(jsonObject,"yuer");
        this.chuzhiyouhui=  JsonUtil.getString(jsonObject,"chuzhiyouhui");
        this.price=  JsonUtil.getString(jsonObject,"price");
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getJianshenplace() {
        return jianshenplace;
    }

    public String getYuer() {
        return yuer;
    }

    public String getChuzhiyouhui() {
        return chuzhiyouhui;
    }

    public String getPrice() {
        return price;
    }
}
