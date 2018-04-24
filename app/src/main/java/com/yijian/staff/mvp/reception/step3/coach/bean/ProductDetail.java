package com.yijian.staff.mvp.reception.step3.coach.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by The_P on 2018/4/18.
 */

public class ProductDetail implements Parcelable {
//    cardId (string, optional): 卡产品id ,
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
//    switchPoundageFee (number, optional): 卡品转换手续费费用，当转换手续费类型是1的时候值是百分比 ,
//    switchPoundageType (number, optional): 卡品转换手续费类型：0固定金额，1百分比 ,
//    transferPoundageFee (number, optional): 转让手续费费用，当转让手续费类型是1的时候值是百分比 ,
//    transferPoundageType (number, optional): 转让手续费类型：0固定金额，1百分比 ,
//    validDay (integer, optional): 有效时间 ,
//    validTime (integer, optional): 有效次数 ,
//    venueNames (Array[string], optional): 场馆名称

    String cardId;
    String cardTypeName;//卡类型名称 ,
    Integer cardType;// 卡类型:0期限卡,1次数卡,2储值卡,3会员制卡 ,
    List<String> venueNames;//场馆名称
    Integer validDay;//有效时间 ,
    Integer validTime;// 有效次数 ,
    BigDecimal rechargeGivePercent;//充值赠送 ,
    BigDecimal salePrice;//售价 ,

//    Object gift; //赠品
    List<String> rightsInterestsList;//权益

    BigDecimal changeShopPoundageFee;//转店手续费费用，当转店手续费类型是1的时候值是百分比 ,
    BigDecimal changeShopPoundageType;// 转店手续费类型：0固定金额，1百分比 ,

    BigDecimal  renewPoundageFee ;//续费折扣费用，当续费类型是1的时候值是百分比 ,
    BigDecimal  renewPoundageType ;//续费折扣类型：0固定金额，1百分比 ,

    BigDecimal   returnPoundageFee ;//退卡手续费费用，当退卡手续费类型是1的时候值是百分比 ,
    BigDecimal   returnPoundageType ;//退卡手续费类型：0固定金额，1百分比 ,

    BigDecimal   stopPoundageFee ;// 停卡手续费费用，当停卡手续费类型是1的时候值是百分比 ,
    BigDecimal    stopPoundageType;//停卡手续费类型：0固定金额，1百分比 ,

    BigDecimal  switchPoundageFee ;// 卡品转换手续费费用，当转换手续费类型是1的时候值是百分比 ,
    BigDecimal   switchPoundageType;// 卡品转换手续费类型：0固定金额，1百分比 ,

    BigDecimal  transferPoundageFee ;// 转让手续费费用，当转让手续费类型是1的时候值是百分比 ,
    BigDecimal  transferPoundageType;// 转让手续费类型：0固定金额，1百分比 ,

    String createDate;//创建时间
    String expirationDate;//结束时间

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

    public BigDecimal getRechargeGivePercent() {
        return rechargeGivePercent;
    }

    public void setRechargeGivePercent(BigDecimal rechargeGivePercent) {
        this.rechargeGivePercent = rechargeGivePercent;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public List<String> getRightsInterestsList() {
        return rightsInterestsList;
    }

    public void setRightsInterestsList(List<String> rightsInterestsList) {
        this.rightsInterestsList = rightsInterestsList;
    }

    public BigDecimal getChangeShopPoundageFee() {
        return changeShopPoundageFee;
    }

    public void setChangeShopPoundageFee(BigDecimal changeShopPoundageFee) {
        this.changeShopPoundageFee = changeShopPoundageFee;
    }

    public BigDecimal getChangeShopPoundageType() {
        return changeShopPoundageType;
    }

    public void setChangeShopPoundageType(BigDecimal changeShopPoundageType) {
        this.changeShopPoundageType = changeShopPoundageType;
    }

    public BigDecimal getRenewPoundageFee() {
        return renewPoundageFee;
    }

    public void setRenewPoundageFee(BigDecimal renewPoundageFee) {
        this.renewPoundageFee = renewPoundageFee;
    }

    public BigDecimal getRenewPoundageType() {
        return renewPoundageType;
    }

    public void setRenewPoundageType(BigDecimal renewPoundageType) {
        this.renewPoundageType = renewPoundageType;
    }

    public BigDecimal getReturnPoundageFee() {
        return returnPoundageFee;
    }

    public void setReturnPoundageFee(BigDecimal returnPoundageFee) {
        this.returnPoundageFee = returnPoundageFee;
    }

    public BigDecimal getReturnPoundageType() {
        return returnPoundageType;
    }

    public void setReturnPoundageType(BigDecimal returnPoundageType) {
        this.returnPoundageType = returnPoundageType;
    }

    public BigDecimal getStopPoundageFee() {
        return stopPoundageFee;
    }

    public void setStopPoundageFee(BigDecimal stopPoundageFee) {
        this.stopPoundageFee = stopPoundageFee;
    }

    public BigDecimal getStopPoundageType() {
        return stopPoundageType;
    }

    public void setStopPoundageType(BigDecimal stopPoundageType) {
        this.stopPoundageType = stopPoundageType;
    }

    public BigDecimal getSwitchPoundageFee() {
        return switchPoundageFee;
    }

    public void setSwitchPoundageFee(BigDecimal switchPoundageFee) {
        this.switchPoundageFee = switchPoundageFee;
    }

    public BigDecimal getSwitchPoundageType() {
        return switchPoundageType;
    }

    public void setSwitchPoundageType(BigDecimal switchPoundageType) {
        this.switchPoundageType = switchPoundageType;
    }

    public BigDecimal getTransferPoundageFee() {
        return transferPoundageFee;
    }

    public void setTransferPoundageFee(BigDecimal transferPoundageFee) {
        this.transferPoundageFee = transferPoundageFee;
    }

    public BigDecimal getTransferPoundageType() {
        return transferPoundageType;
    }

    public void setTransferPoundageType(BigDecimal transferPoundageType) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cardId);
        dest.writeString(this.cardTypeName);
        dest.writeValue(this.cardType);
        dest.writeStringList(this.venueNames);
        dest.writeValue(this.validDay);
        dest.writeValue(this.validTime);
        dest.writeSerializable(this.rechargeGivePercent);
        dest.writeSerializable(this.salePrice);
        dest.writeStringList(this.rightsInterestsList);
        dest.writeSerializable(this.changeShopPoundageFee);
        dest.writeSerializable(this.changeShopPoundageType);
        dest.writeSerializable(this.renewPoundageFee);
        dest.writeSerializable(this.renewPoundageType);
        dest.writeSerializable(this.returnPoundageFee);
        dest.writeSerializable(this.returnPoundageType);
        dest.writeSerializable(this.stopPoundageFee);
        dest.writeSerializable(this.stopPoundageType);
        dest.writeSerializable(this.switchPoundageFee);
        dest.writeSerializable(this.switchPoundageType);
        dest.writeSerializable(this.transferPoundageFee);
        dest.writeSerializable(this.transferPoundageType);
        dest.writeString(this.createDate);
        dest.writeString(this.expirationDate);
    }

    public ProductDetail() {
    }

    protected ProductDetail(Parcel in) {
        this.cardId = in.readString();
        this.cardTypeName = in.readString();
        this.cardType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.venueNames = in.createStringArrayList();
        this.validDay = (Integer) in.readValue(Integer.class.getClassLoader());
        this.validTime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.rechargeGivePercent = (BigDecimal) in.readSerializable();
        this.salePrice = (BigDecimal) in.readSerializable();
        this.rightsInterestsList = in.createStringArrayList();
        this.changeShopPoundageFee = (BigDecimal) in.readSerializable();
        this.changeShopPoundageType = (BigDecimal) in.readSerializable();
        this.renewPoundageFee = (BigDecimal) in.readSerializable();
        this.renewPoundageType = (BigDecimal) in.readSerializable();
        this.returnPoundageFee = (BigDecimal) in.readSerializable();
        this.returnPoundageType = (BigDecimal) in.readSerializable();
        this.stopPoundageFee = (BigDecimal) in.readSerializable();
        this.stopPoundageType = (BigDecimal) in.readSerializable();
        this.switchPoundageFee = (BigDecimal) in.readSerializable();
        this.switchPoundageType = (BigDecimal) in.readSerializable();
        this.transferPoundageFee = (BigDecimal) in.readSerializable();
        this.transferPoundageType = (BigDecimal) in.readSerializable();
        this.createDate = in.readString();
        this.expirationDate = in.readString();
    }

    public static final Parcelable.Creator<ProductDetail> CREATOR = new Parcelable.Creator<ProductDetail>() {
        @Override
        public ProductDetail createFromParcel(Parcel source) {
            return new ProductDetail(source);
        }

        @Override
        public ProductDetail[] newArray(int size) {
            return new ProductDetail[size];
        }
    };
}
