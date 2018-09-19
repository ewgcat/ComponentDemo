package com.yijian.commonlib.db.bean;

import com.yijian.commonlib.util.JsonUtil;

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

    /**
     * age : 0
     * appModuleFlagVOS : [{"data":"string","moduleCode":"string"}]
     * birthday : 2018-06-27T02:27:22.097Z
     * chief : true
     * departmentId : string
     * faceRecognitionType : 0
     * headImg : string
     * homePageModelVO : {"completePercent":"string","menuModelList":[{"className":"string","count":0,"icon":"string","level":0,"menuActionList":[{"check":false,"id":0,"name":"string","tag":"string"}],"menuId":0,"path":"string","subMeneModelList":[{}],"title":"string"}],"monthRank":0,"todayScore":"string"}
     * merchantId : string
     * mobile : string
     * name : string
     * post : 0
     * postName : string
     * role : 0
     * roleVo : {"roleCode":"string","roleId":"string","roleName":"string"}
     * sex : string
     * shopId : string
     * token : string
     * tokenAge : 0
     * userId : string
     */

    private int age;
    private Long birthday;
    private boolean chief;
    private String departmentId;
    private int faceRecognitionType;
    private String headImg;
    private String merchantId;
    private String mobile;
    private String name;
    private int post;
    private String postName;
    private int role;
    private String sex;
    private String shopId;
    private String token;
    private String tokenAge;
    private String userId;

    public User(JSONObject jsonObject) {
        this.age = JsonUtil.getInt(jsonObject, "age");
        this.sex = JsonUtil.getString(jsonObject, "sex");
        this.role = JsonUtil.getInt(jsonObject, "role");
        this.post = JsonUtil.getInt(jsonObject, "post");
        this.name = JsonUtil.getString(jsonObject, "name");
        this.userId = JsonUtil.getString(jsonObject, "userId");
        this.shopId = JsonUtil.getString(jsonObject, "shopId");
        this.merchantId = JsonUtil.getString(jsonObject, "merchantId");
        this.departmentId = JsonUtil.getString(jsonObject, "departmentId");
        this.token = JsonUtil.getString(jsonObject, "token");
        this.tokenAge = JsonUtil.getString(jsonObject, "tokenAge");
        this.headImg = JsonUtil.getString(jsonObject, "headImg");
        this.birthday = JsonUtil.getLong(jsonObject, "birthday");
        this.mobile = JsonUtil.getString(jsonObject, "mobile");
        this.postName = JsonUtil.getString(jsonObject, "postName");

        this.chief = JsonUtil.getBoolean(jsonObject, "chief");
    }
    @Generated(hash = 2031216056)
    public User(int age, Long birthday, boolean chief, String departmentId, int faceRecognitionType, String headImg, String merchantId, String mobile, String name, int post, String postName, int role, String sex, String shopId, String token, String tokenAge, String userId) {
        this.age = age;
        this.birthday = birthday;
        this.chief = chief;
        this.departmentId = departmentId;
        this.faceRecognitionType = faceRecognitionType;
        this.headImg = headImg;
        this.merchantId = merchantId;
        this.mobile = mobile;
        this.name = name;
        this.post = post;
        this.postName = postName;
        this.role = role;
        this.sex = sex;
        this.shopId = shopId;
        this.token = token;
        this.tokenAge = tokenAge;
        this.userId = userId;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public boolean isChief() {
        return chief;
    }

    public void setChief(boolean chief) {
        this.chief = chief;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public int getFaceRecognitionType() {
        return faceRecognitionType;
    }

    public void setFaceRecognitionType(int faceRecognitionType) {
        this.faceRecognitionType = faceRecognitionType;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }



    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

  

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public boolean getChief() {
        return this.chief;
    }





}
