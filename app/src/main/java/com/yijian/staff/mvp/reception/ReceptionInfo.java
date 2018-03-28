package com.yijian.staff.mvp.reception;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:22:37
 */
public class ReceptionInfo {
    private String name;
    private String phone;
    private String sex;
    private String product;
    private String status;

    public ReceptionInfo(JSONObject jsonObject){
      this.name=  JsonUtil.getString(jsonObject,"name");
      this.phone=  JsonUtil.getString(jsonObject,"phone");
      this.sex=  JsonUtil.getString(jsonObject,"sex");
      this.product=  JsonUtil.getString(jsonObject,"product");
      this.status=  JsonUtil.getString(jsonObject,"status");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CoachHuiFangInfo{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", product='" + product + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
