package com.yijian.staff.mvp.coach.experienceclass.index;

import java.io.Serializable;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/19 10:21:41
 */
public class ExperienceClassBean implements Serializable {


    /**
     * processId : 5
     * memberType : 1
     * headPath : 头像
     * memberId : 666
     * memberName : 何健林
     * gender : 1
     * cardprodVOs : [{"cardName":"储值卡","cardType":"储值卡","cardTypeId":2},{"cardName":"会员制卡","cardType":"会员制卡","cardTypeId":3},{"cardName":"时间卡","cardType":"时间卡","cardTypeId":0}]
     * birthDate : null
     * birthdayType : null
     * birthdayTypeDesc : null
     * healthStatusDesc : null
     * sportHobbys : null
     * hobbys : null
     * car : null
     * courseNum : 1
     * status : 40
     * statusDesc : 二次邀约
     * result : 无
     */

    private String processId;
    private int memberType;
    private String headPath;
    private String memberId;
    private String memberName;
    private int gender;
    private String birthDate;
    private Long birthdayType;
    private String birthdayTypeDesc;
    private String healthStatusDesc;
    private String sportHobbys;
    private String hobbys;
    private String car;
    private int courseNum;
    private int status;
    private String statusDesc;
    private String result;
    private List<CardprodVOsBean> cardprodVOs;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Long getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(Long birthdayType) {
        this.birthdayType = birthdayType;
    }

    public String getBirthdayTypeDesc() {
        return birthdayTypeDesc;
    }

    public void setBirthdayTypeDesc(String birthdayTypeDesc) {
        this.birthdayTypeDesc = birthdayTypeDesc;
    }

    public String getHealthStatusDesc() {
        return healthStatusDesc;
    }

    public void setHealthStatusDesc(String healthStatusDesc) {
        this.healthStatusDesc = healthStatusDesc;
    }

    public String getSportHobbys() {
        return sportHobbys;
    }

    public void setSportHobbys(String sportHobbys) {
        this.sportHobbys = sportHobbys;
    }

    public String getHobbys() {
        return hobbys;
    }

    public void setHobbys(String hobbys) {
        this.hobbys = hobbys;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<CardprodVOsBean> getCardprodVOs() {
        return cardprodVOs;
    }

    public void setCardprodVOs(List<CardprodVOsBean> cardprodVOs) {
        this.cardprodVOs = cardprodVOs;
    }


}
