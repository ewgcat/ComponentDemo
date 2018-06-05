package com.yijian.staff.mvp.resourceallocation.huijileader.selecthuiji;

import com.yijian.staff.R;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;


public class HuiJiInfo {

    /**
     * userId : 0e8c24d724fe4a1595077910463a1455
     * userName : 教练测试账号
     */

    private String userId;
    private String userName;
    /**
     * sex : 1
     * headImg :
     */

    private int sex;
    private String headImg;


    public HuiJiInfo(JSONObject jsonObject) {
        this.userId = JsonUtil.getString(jsonObject, "userId");
        this.userName = JsonUtil.getString(jsonObject, "userName");
        this.headImg = JsonUtil.getString(jsonObject, "headImg");
        this.sex = JsonUtil.getInt(jsonObject, "sex");
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getGenderImg() {
        return "0".equals(sex) ? R.mipmap.lg_women : R.mipmap.lg_man;
    }

}
