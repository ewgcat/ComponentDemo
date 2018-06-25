package com.yijian.staff.db.bean;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.util.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.json.JSONObject;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 14:31:37
 */
@Entity
public class User {
    public int age;
    public int role;// 1 会籍客服 2教练  3会籍总监 4教练总监 5店长 6会籍经理 7教练经理
    public String name;
    /**
     * birthday : 1270656000000
     * userId : 0f1374f5025246a093e115ac165b5076
     * sex : 男
     * headImg : https://h5.qa.ejoyst.com/file/downloadFile?filename=ca079c485a4444cdbecf77ce8d78f320.jpg
     * mobile : 13412345678
     * departmentId : 75bbf06b4e6245d5be2766bb20845c23
     * shopId : eaf79bf34d274354890b7e55c3aeb250
     * merchantId : 4351681b3f0b11e89f9b00163e04d061
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjgxOTAzMTMzNjYsInBheWxvYWQiOiJ7XCJpZFwiOm51bGwsXCJ1c2VySWRcIjpcIjBmMTM3NGY1MDI1MjQ2YTA5M2UxMTVhYzE2NWI1MDc2XCIsXCJtZXJjaGFudElkXCI6XCI0MzUxNjgxYjNmMGIxMWU4OWY5YjAwMTYzZTA0ZDA2MVwiLFwicG9zdElkXCI6MixcInBvc3RDbGFzc2lmaWNhdGlvblwiOjIsXCJzaG9wSWRcIjpcImVhZjc5YmYzNGQyNzQzNTQ4OTBiN2U1NWMzYWViMjUwXCIsXCJkZXBhcnRtZW50SWRcIjpcIjc1YmJmMDZiNGU2MjQ1ZDViZTI3NjZiYjIwODQ1YzIzXCIsXCJjaGllZlwiOmZhbHNlfSJ9.1Rrfpj4tbHSARwOugGiE_8FqRFYJ6LpPs-egK52JjUg
     * tokenAge : 1209600000
     * post : 2
     * postName : 助理教练
     * chief : false
     */

    private long birthday;
    private String userId;
    private String sex;
    private String headImg;
    private String mobile;
    private String departmentId;
    private String shopId;
    private String merchantId;
    private String token;
    private String tokenAge;
    private int post;
    private String postName;
    private boolean chief;

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
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

    public User(JSONObject jsonObject) {
        this.age = JsonUtil.getInt(jsonObject, "age");
        this.sex = JsonUtil.getString(jsonObject, "sex");
        this.role = JsonUtil.getInt(jsonObject, "role");
        this.post = JsonUtil.getInt(jsonObject, "post");
        this.name = JsonUtil.getString(jsonObject, "name");
        this.userId = JsonUtil.getString(jsonObject, "userId");
        this.shopId = JsonUtil.getString(jsonObject, "shopId");
        this.merchantId = JsonUtil.getString(jsonObject, "merchantId");
        this.token = JsonUtil.getString(jsonObject, "token");
        this.tokenAge = JsonUtil.getString(jsonObject, "tokenAge");
        this.headImg = BuildConfig.FILE_HOST + JsonUtil.getString(jsonObject, "headImg");
        this.birthday = JsonUtil.getLong(jsonObject, "birthday");
        this.mobile = JsonUtil.getString(jsonObject, "mobile");
        this.postName = JsonUtil.getString(jsonObject, "postName");
    }

    @Generated(hash = 733498128)
    public User(int age, int role, String name, long birthday, String userId, String sex, String headImg, String mobile, String departmentId, String shopId, String merchantId, String token, String tokenAge, int post, String postName, boolean chief) {
        this.age = age;
        this.role = role;
        this.name = name;
        this.birthday = birthday;
        this.userId = userId;
        this.sex = sex;
        this.headImg = headImg;
        this.mobile = mobile;
        this.departmentId = departmentId;
        this.shopId = shopId;
        this.merchantId = merchantId;
        this.token = token;
        this.tokenAge = tokenAge;
        this.post = post;
        this.postName = postName;
        this.chief = chief;
    }

    @Generated(hash = 586692638)
    public User() {
    }


    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public boolean isChief() {
        return chief;
    }

    public void setChief(boolean chief) {
        this.chief = chief;
    }

    public boolean getChief() {
        return this.chief;
    }
}
