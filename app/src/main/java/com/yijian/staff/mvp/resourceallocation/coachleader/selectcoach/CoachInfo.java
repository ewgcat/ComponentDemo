package com.yijian.staff.mvp.resourceallocation.coachleader.selectcoach;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * Created by yangk on 2018/3/12.
 * 会籍信息
 */
public class CoachInfo {


    /**
     * userId : 0e8c24d724fe4a1595077910463a1455
     * userName : 教练测试账号
     */

    private String userId;
    private String userName;


    public CoachInfo(JSONObject jsonObject){
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
