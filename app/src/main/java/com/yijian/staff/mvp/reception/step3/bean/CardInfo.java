package com.yijian.staff.mvp.reception.step3.bean;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/17 19:29:17
 */
public class CardInfo implements Comparable<CardInfo>{


    @Override
    public String toString() {
        return "CardInfo{" +
                "cardprodbaseId='" + cardprodbaseId + '\'' +
                ", cardType=" + cardType +
                ", validDay=" + validDay +
                ", amount=" + amount +
                ", rechargeGivePercent=" + rechargeGivePercent +
                ", cardName='" + cardName + '\'' +
                ", venusNames='" + venusNames + '\'' +
                ", rechargeGiveStr='" + rechargeGiveStr + '\'' +
                ", validTime=" + validTime +
                ", salePrice=" + salePrice +
                ", venueNameList=" + venueNameList +
                '}';
    }

    /**
     * cardprodbaseId : aa986bc873e84782a72979044ee72842
     * cardType : 2
     * validDay : 10
     * amount : 0
     * rechargeGivePercent : 10
     * venueNameList : ["瑜伽馆"]
     * cardName : 213213
     * venusNames : 瑜伽馆
     * rechargeGiveStr : 10%
     * validTime : 0
     * salePrice : 233
     */

    private String cardprodbaseId;
    private Integer cardType;
    private Integer validDay;
    private Integer amount;
    private Integer rechargeGivePercent;
    private String cardName;
    private String venusNames;
    private String rechargeGiveStr;
    private Integer validTime;
    private Integer salePrice;
    private List<String> venueNameList;

    public String getCardprodbaseId() {
        return cardprodbaseId;
    }

    public void setCardprodbaseId(String cardprodbaseId) {
        this.cardprodbaseId = cardprodbaseId;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getValidDay() {
        return validDay;
    }

    public void setValidDay(int validDay) {
        this.validDay = validDay;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRechargeGivePercent() {
        return rechargeGivePercent;
    }

    public void setRechargeGivePercent(int rechargeGivePercent) {
        this.rechargeGivePercent = rechargeGivePercent;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getVenusNames() {
        return venusNames;
    }

    public void setVenusNames(String venusNames) {
        this.venusNames = venusNames;
    }

    public String getRechargeGiveStr() {
        return rechargeGiveStr;
    }

    public void setRechargeGiveStr(String rechargeGiveStr) {
        this.rechargeGiveStr = rechargeGiveStr;
    }

    public int getValidTime() {
        return validTime;
    }

    public void setValidTime(int validTime) {
        this.validTime = validTime;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public List<String> getVenueNameList() {
        return venueNameList;
    }

    public void setVenueNameList(List<String> venueNameList) {
        this.venueNameList = venueNameList;
    }

    @Override
    public int compareTo(@NonNull CardInfo o) {
        int i = this.getSalePrice() - o.getSalePrice();//先按照年龄排序
        return i;
    }
}
