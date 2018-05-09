package com.yijian.staff.mvp.resourceallocation.huijileader.selecthuiji;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;


public class HuiJiInfo {

    private String userId;
    private String userName;


    public HuiJiInfo(JSONObject jsonObject){
        this. userId = JsonUtil.getString(jsonObject, "userId");
        this. userName = JsonUtil.getString(jsonObject, "userName");
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
