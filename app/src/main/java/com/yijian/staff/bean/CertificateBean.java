package com.yijian.staff.bean;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/17 15:33:51
 */
public class CertificateBean {


    /**
     * userId : 0e8c24d724fe4a1595077910463a1455
     * career : 职业大佬
     * qualification : 资格认证,资格人事6
     * experience : 从业经历,这是教练吗？
     * skilled : 擅长领域、不测试
     * headUrl : 头像图片相对地址
     * merchantId : 333
     * shopId : 222
     * createTime : 1526462764000
     * createBy : 1
     * updateTime : 1526462772000
     * updateBy : 1
     * isDelete : 0
     * coach2picList : ["相册相对地址1","相册相对地址2"]
     * certificateList : ["/credential/4c6447c6f4a2414c88b5688568b2b511.jpg","证书相对路径01"]
     */

    private String userId;
    private String career;
    private String qualification;
    private String experience;
    private String skilled;
    private String headUrl;
    private String merchantId;
    private String shopId;
    private long createTime;
    private String createBy;
    private long updateTime;
    private String updateBy;
    private int isDelete;
    private List<String> coach2picList;
    private List<String> certificateList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkilled() {
        return skilled;
    }

    public void setSkilled(String skilled) {
        this.skilled = skilled;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public List<String> getCoach2picList() {
        return coach2picList;
    }

    public void setCoach2picList(List<String> coach2picList) {
        this.coach2picList = coach2picList;
    }

    public List<String> getCertificateList() {
        return certificateList;
    }

    public void setCertificateList(List<String> certificateList) {
        this.certificateList = certificateList;
    }
}
