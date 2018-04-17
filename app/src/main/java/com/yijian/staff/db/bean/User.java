package com.yijian.staff.db.bean;

import com.yijian.staff.util.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 14:31:37
 */
@Entity
public class User {
    public int age;
    public int role;// 1 会籍客服 2教练  3会籍总监 4教练总监 5操课教练 6行政  7店长
    public String name;
    public String userId;
    public String shopId;
    public String merchantId;
    public String token;
    public String tokenAge;
    public String headUrl;
    public int sex;
    public String birthday;


    public User(JSONObject jsonObject) {
        this.age = JsonUtil.getInt(jsonObject, "age");
        this.sex = JsonUtil.getInt(jsonObject, "sex");
        this.role = JsonUtil.getInt(jsonObject, "role");
        this.name = JsonUtil.getString(jsonObject, "name");
        this.userId = JsonUtil.getString(jsonObject, "userId");
        this.shopId = JsonUtil.getString(jsonObject, "shopId");
        this.merchantId = JsonUtil.getString(jsonObject, "merchantId");
        this.token = JsonUtil.getString(jsonObject, "token");
        this.tokenAge = JsonUtil.getString(jsonObject, "tokenAge");
        this.headUrl = JsonUtil.getString(jsonObject, "headUrl");
        this.birthday = JsonUtil.getString(jsonObject, "birthday");
        this.tokenAge = JsonUtil.getString(jsonObject, "tokenAge");
    }


    @Generated(hash = 660386454)
    public User(int age, int role, String name, String userId, String shopId,
                String merchantId, String token, String tokenAge, String headUrl,
                int sex, String birthday) {
        this.age = age;
        this.role = role;
        this.name = name;
        this.userId = userId;
        this.shopId = shopId;
        this.merchantId = merchantId;
        this.token = token;
        this.tokenAge = tokenAge;
        this.headUrl = headUrl;
        this.sex = sex;
        this.birthday = birthday;
    }


    @Generated(hash = 586692638)
    public User() {
    }


    public int getAge() {
        return this.age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public int getRole() {
        return this.role;
    }


    public void setRole(int role) {
        this.role = role;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getShopId() {
        return this.shopId;
    }


    public void setShopId(String shopId) {
        this.shopId = shopId;
    }


    public String getMerchantId() {
        return this.merchantId;
    }


    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }


    public String getToken() {
        return this.token;
    }


    public void setToken(String token) {
        this.token = token;
    }


    public String getTokenAge() {
        return this.tokenAge;
    }


    public void setTokenAge(String tokenAge) {
        this.tokenAge = tokenAge;
    }


    public String getHeadUrl() {
        return this.headUrl;
    }


    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }


    public int getSex() {
        return this.sex;
    }


    public void setSex(int sex) {
        this.sex = sex;
    }


    public String getBirthday() {
        return this.birthday;
    }


    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


}
