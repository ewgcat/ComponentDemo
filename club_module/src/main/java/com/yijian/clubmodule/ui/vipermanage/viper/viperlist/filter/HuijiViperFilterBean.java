package com.yijian.clubmodule.ui.vipermanage.viper.viperlist.filter;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/24 11:20:56
 */
public class HuijiViperFilterBean {

    private int joinTimeType = -1;//入籍时间类型：【0:今日，7:最近七天，30:最近30天，-1:可编辑日期】
    private int expiringDay = -1;//快过期天数:【7:7天，14:14天，30:30天】
    private int sex = -1;//性别：【0:未知 1:男 2:女】
    private int cardType = -1;//卡类型：【0:时间卡，1:次卡，2:储值卡，3:会员制卡，4:员工卡】
    private int privateCourseState = -1;//私教课购买情况：【1.未购买，2.已购买，3.已购买的私课，且私课为体验课】
    private int source = -1;//来源（推广渠道)
    private String startTime = null;//开始时间
    private String endTime = null;//结束时间

    public HuijiViperFilterBean() {
    }

    public HuijiViperFilterBean(int joinTimeType, int expiringDay, int sex, int cardType, int privateCourseState, int source, String startTime, String endTime) {
        this.joinTimeType = joinTimeType;
        this.expiringDay = expiringDay;
        this.sex = sex;
        this.cardType = cardType;
        this.privateCourseState = privateCourseState;
        this.source = source;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getJoinTimeType() {
        return joinTimeType;
    }

    public void setJoinTimeType(int joinTimeType) {
        this.joinTimeType = joinTimeType;
    }

    public int getExpiringDay() {
        return expiringDay;
    }

    public void setExpiringDay(int expiringDay) {
        this.expiringDay = expiringDay;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getPrivateCourseState() {
        return privateCourseState;
    }

    public void setPrivateCourseState(int privateCourseState) {
        this.privateCourseState = privateCourseState;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
