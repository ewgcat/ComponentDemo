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




    @Generated(hash = 1518238289)
    public User(int age, int role, String name, String userId, String shopId,
            String merchantId, String token, String tokenAge) {
        this.age = age;
        this.role = role;
        this.name = name;
        this.userId = userId;
        this.shopId = shopId;
        this.merchantId = merchantId;
        this.token = token;
        this.tokenAge = tokenAge;
    }
    @Generated(hash = 586692638)
    public User() {
    }



    public User(JSONObject jsonObject){
        this.age= JsonUtil.getInt(jsonObject,"age");
        this.role= JsonUtil.getInt(jsonObject,"role");
        this.name= JsonUtil.getString(jsonObject,"name");
        this.userId= JsonUtil.getString(jsonObject,"userId");
        this.shopId= JsonUtil.getString(jsonObject,"shopId");
        this.merchantId= JsonUtil.getString(jsonObject,"merchantId");
        this.token= JsonUtil.getString(jsonObject,"token");
        this.tokenAge= JsonUtil.getString(jsonObject,"tokenAge");
    }



    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRole() {
        return this.role;
    }
    public void setRole(int role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenAge() {
        return tokenAge;
    }

    public void setTokenAge(String tokenAge) {
        this.tokenAge = tokenAge;
    }
}
