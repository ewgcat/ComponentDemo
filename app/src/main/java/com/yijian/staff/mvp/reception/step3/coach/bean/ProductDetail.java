package com.yijian.staff.mvp.reception.step3.coach.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by The_P on 2018/4/18.
 */

public class ProductDetail implements Serializable {
//    cardId (string, optional): 卡产品id ,
//    cardName (string, optional): 卡名称 ,
//    cardType (integer, optional): 卡类型:0期限卡,1次数卡,2储值卡,3会员制卡 ,
//    cardTypeName (string, optional): 卡类型名称 ,
//    changeShopPoundageFee (number, optional): 转店手续费费用，当转店手续费类型是1的时候值是百分比 ,
//    changeShopPoundageType (number, optional): 转店手续费类型：0固定金额，1百分比 ,
//    createDate (string, optional): 创建时间 ,
//    expirationDate (string, optional): 结束时间 ,
//    gift (inline_model, optional): 赠品 ,
//    rechargeGivePercent (number, optional): 充值赠送 ,
//    renewPoundageFee (number, optional): 续费折扣费用，当续费类型是1的时候值是百分比 ,
//    renewPoundageType (integer, optional): 续费折扣类型：0固定金额，1百分比 ,
//    returnPoundageFee (number, optional): 退卡手续费费用，当退卡手续费类型是1的时候值是百分比 ,
//    returnPoundageType (integer, optional): 退卡手续费类型：0固定金额，1百分比 ,
//    rightsInterestsList (Array[string], optional): 权益 ,
//    salePrice (number, optional): 售价 ,
//    stopPoundageFee (number, optional): 停卡手续费费用，当停卡手续费类型是1的时候值是百分比 ,
//    stopPoundageType (integer, optional): 停卡手续费类型：0固定金额，1百分比 ,
//    strRestKey (string, optional): 剩下字符串KEY ,
//    strRestVal (string, optional): 剩下字符串value ,
//    switchPoundageFee (number, optional): 卡品转换手续费费用，当转换手续费类型是1的时候值是百分比 ,
//    switchPoundageType (number, optional): 卡品转换手续费类型：0固定金额，1百分比 ,
//    transferPoundageFee (number, optional): 转让手续费费用，当转让手续费类型是1的时候值是百分比 ,
//    transferPoundageType (number, optional): 转让手续费类型：0固定金额，1百分比 ,
//    validDay (integer, optional): 有效时间 ,
//    validTime (integer, optional): 有效次数 ,
//    venueNames (Array[string], optional): 场馆名称


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public List<String> getVenueNames() {
        return venueNames;
    }

    public void setVenueNames(List<String> venueNames) {
        this.venueNames = venueNames;
    }

    public Integer getValidDay() {
        return validDay;
    }

    public void setValidDay(Integer validDay) {
        this.validDay = validDay;
    }

    public Integer getValidTime() {
        return validTime;
    }

    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

    public String getRechargeGivePercent() {
        return rechargeGivePercent;
    }

    public void setRechargeGivePercent(String rechargeGivePercent) {
        this.rechargeGivePercent = rechargeGivePercent;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getStrRestKey() {
        return strRestKey;
    }

    public void setStrRestKey(String strRestKey) {
        this.strRestKey = strRestKey;
    }

    public String getStrRestVal() {
        return strRestVal;
    }

    public void setStrRestVal(String strRestVal) {
        this.strRestVal = strRestVal;
    }

    public Map<Integer, List<CardGiftDto>> getGift() {
        return gift;
    }

    public void setGift(Map<Integer, List<CardGiftDto>> gift) {
        this.gift = gift;
    }

    public List<String> getRightsInterestsList() {
        return rightsInterestsList;
    }

    public void setRightsInterestsList(List<String> rightsInterestsList) {
        this.rightsInterestsList = rightsInterestsList;
    }

    public String getChangeShopPoundageFee() {
        return changeShopPoundageFee;
    }

    public void setChangeShopPoundageFee(String changeShopPoundageFee) {
        this.changeShopPoundageFee = changeShopPoundageFee;
    }

    public String getChangeShopPoundageType() {
        return changeShopPoundageType;
    }

    public void setChangeShopPoundageType(String changeShopPoundageType) {
        this.changeShopPoundageType = changeShopPoundageType;
    }

    public String getRenewPoundageFee() {
        return renewPoundageFee;
    }

    public void setRenewPoundageFee(String renewPoundageFee) {
        this.renewPoundageFee = renewPoundageFee;
    }

    public Integer getRenewPoundageType() {
        return renewPoundageType;
    }

    public void setRenewPoundageType(Integer renewPoundageType) {
        this.renewPoundageType = renewPoundageType;
    }

    public String getReturnPoundageFee() {
        return returnPoundageFee;
    }

    public void setReturnPoundageFee(String returnPoundageFee) {
        this.returnPoundageFee = returnPoundageFee;
    }

    public Integer getReturnPoundageType() {
        return returnPoundageType;
    }

    public void setReturnPoundageType(Integer returnPoundageType) {
        this.returnPoundageType = returnPoundageType;
    }

    public String getStopPoundageFee() {
        return stopPoundageFee;
    }

    public void setStopPoundageFee(String stopPoundageFee) {
        this.stopPoundageFee = stopPoundageFee;
    }

    public Integer getStopPoundageType() {
        return stopPoundageType;
    }

    public void setStopPoundageType(Integer stopPoundageType) {
        this.stopPoundageType = stopPoundageType;
    }

    public String getSwitchPoundageFee() {
        return switchPoundageFee;
    }

    public void setSwitchPoundageFee(String switchPoundageFee) {
        this.switchPoundageFee = switchPoundageFee;
    }

    public String getSwitchPoundageType() {
        return switchPoundageType;
    }

    public void setSwitchPoundageType(String switchPoundageType) {
        this.switchPoundageType = switchPoundageType;
    }

    public String getTransferPoundageFee() {
        return transferPoundageFee;
    }

    public void setTransferPoundageFee(String transferPoundageFee) {
        this.transferPoundageFee = transferPoundageFee;
    }

    public String getTransferPoundageType() {
        return transferPoundageType;
    }

    public void setTransferPoundageType(String transferPoundageType) {
        this.transferPoundageType = transferPoundageType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    String cardName;
    String cardId;
    String cardTypeName;//卡类型名称 ,
    Integer cardType;// 卡类型:0期限卡,1次数卡,2储值卡,3会员制卡 ,
    List<String> venueNames;//场馆名称
    Integer validDay;//有效时间 ,
    Integer validTime;// 有效次数 ,
    String rechargeGivePercent;//充值赠送 ,
    String salePrice;//售价 ,

    String strRestKey;
    String strRestVal;
    //    Object gift; //品
    Map<Integer, List<CardGiftDto>> gift; //赠品类型（0：会员卡  1：团课  2：私课）
    List<String> rightsInterestsList;//权益

    String changeShopPoundageFee;//转店手续费费用，当转店手续费类型是1的时候值是百分比 ,
    String changeShopPoundageType;// 转店手续费类型：0固定金额，1百分比 ,

    String renewPoundageFee;//续费折扣费用，当续费类型是1的时候值是百分比 ,
    Integer renewPoundageType;//续费折扣类型：0固定金额，1百分比 ,

    String returnPoundageFee;//退卡手续费费用，当退卡手续费类型是1的时候值是百分比 ,
    Integer returnPoundageType;//退卡手续费类型：0固定金额，1百分比 ,

    String stopPoundageFee;// 停卡手续费费用，当停卡手续费类型是1的时候值是百分比 ,
    Integer stopPoundageType;//停卡手续费类型：0固定金额，1百分比 ,

    String switchPoundageFee;// 卡品转换手续费费用，当转换手续费类型是1的时候值是百分比 ,
    String switchPoundageType;// 卡品转换手续费类型：0固定金额，1百分比 ,

    String transferPoundageFee;// 转让手续费费用，当转让手续费类型是1的时候值是百分比 ,
    String transferPoundageType;// 转让手续费类型：0固定金额，1百分比 ,

    String createDate;//创建时间
    String expirationDate;//结束时间


}
