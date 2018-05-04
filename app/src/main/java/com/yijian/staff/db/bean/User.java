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
    public String headImg;
    public String sex;
    public String birthday;
    public String mobile;
    private int post;
    private boolean chief;

    public User(JSONObject jsonObject) {
        this.age = JsonUtil.getInt(jsonObject, "age");
        this.sex = JsonUtil.getString(jsonObject, "sex");
        this.role = JsonUtil.getInt(jsonObject, "role");
        this.name = JsonUtil.getString(jsonObject, "name");
        this.userId = JsonUtil.getString(jsonObject, "userId");
        this.shopId = JsonUtil.getString(jsonObject, "shopId");
        this.merchantId = JsonUtil.getString(jsonObject, "merchantId");
        this.token = JsonUtil.getString(jsonObject, "token");
        this.tokenAge = JsonUtil.getString(jsonObject, "tokenAge");
        this.headImg = JsonUtil.getString(jsonObject, "headImg");
        this.birthday = JsonUtil.getString(jsonObject, "birthday");
        this.tokenAge = JsonUtil.getString(jsonObject, "tokenAge");
        this.mobile = JsonUtil.getString(jsonObject, "mobile");
    }


    @Generated(hash = 602243532)
    public User(int age, int role, String name, String userId, String shopId,
            String merchantId, String token, String tokenAge, String headImg,
            String sex, String birthday, String mobile, int post, boolean chief) {
        this.age = age;
        this.role = role;
        this.name = name;
        this.userId = userId;
        this.shopId = shopId;
        this.merchantId = merchantId;
        this.token = token;
        this.tokenAge = tokenAge;
        this.headImg = headImg;
        this.sex = sex;
        this.birthday = birthday;
        this.mobile = mobile;
        this.post = post;
        this.chief = chief;
    }


    @Generated(hash = 586692638)
    public User() {
    }


    public int getAge() {
        return age;
    }

    public int getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getShopId() {
        return shopId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getToken() {
        return token;
    }

    public String getTokenAge() {
        return tokenAge;
    }

    public String getHeadImg() {
        return headImg;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenAge(String tokenAge) {
        this.tokenAge = tokenAge;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public int getPost() {
        return this.post;
    }


    public void setPost(int post) {
        this.post = post;
    }


    public boolean getChief() {
        return this.chief;
    }


    public void setChief(boolean chief) {
        this.chief = chief;
    }
}
