package com.yijian.staff.mvp.coach.classbaojia.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/17 19:29:17
 */
public class ClassInfo {
    private String className;
    private String classNum;
    private String classLongTime;
    private String price;
    public ClassInfo(JSONObject jsonObject){
        this.className=  JsonUtil.getString(jsonObject,"className");
        this.classNum=  JsonUtil.getString(jsonObject,"classNum");
        this.classLongTime=  JsonUtil.getString(jsonObject,"classLongTime");
        this.price=  JsonUtil.getString(jsonObject,"price");
    }


    public String getClassName() {
        return className;
    }

    public String getClassNum() {
        return classNum;
    }

    public String getClassLongTime() {
        return classLongTime;
    }

    public String getPrice() {
        return price;
    }
}
