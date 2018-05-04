package com.yijian.staff.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/28 18:30:01
 */
public class UserInfo {

    /**
     * userId : 0e8c24d724fe4a1595077910463a1455
     * name : 教练测试账号
     * mobile : 18986170640
     * sex : 男
     * headImg :
     * age : 0
     * jobNo : 123
     * shop : test门店2
     * department : 测试部门2
     * post : 助理教练
     */

    private String userId;
    private String name;
    private String mobile;
    private String sex;
    private String headImg;
    private int age;
    private String jobNo;
    private String shop;
    private String department;
    private String post;

    public UserInfo(JSONObject jsonObject){
       this.userId= JsonUtil.getString(jsonObject,"userId");
       this.name= JsonUtil.getString(jsonObject,"name");
       this.mobile= JsonUtil.getString(jsonObject,"mobile");
       this.sex= JsonUtil.getString(jsonObject,"sex");
       this.headImg= JsonUtil.getString(jsonObject,"headImg");
       this.jobNo= JsonUtil.getString(jsonObject,"jobNo");
       this.shop= JsonUtil.getString(jsonObject,"shop");
       this.department= JsonUtil.getString(jsonObject,"department");
       this.post= JsonUtil.getString(jsonObject,"post");
       this.age= JsonUtil.getInt(jsonObject,"age");

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
