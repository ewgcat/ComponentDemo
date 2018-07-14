package com.yijian.staff.mvp.price.cardprice.bean;

import android.os.Parcel;
import android.os.Parcelable;


public class CardRequestBody implements Parcelable {
    @Override
    public String toString() {
        return "CardRequestBody{" +
                "cardName='" + cardName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", startPrice='" + startPrice + '\'' +
                ", endPrice='" + endPrice + '\'' +
                ", isSortByPrice=" + isSortByPrice +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", venueId='" + venueId + '\'' +
                '}';
    }

    /**
     * 筛选查询卡产品列表参数载体 {
     * cardName (string, optional): 卡名字 ,
     * cardType (integer, optional): 卡类型 ,卡类型:1时间卡,2次数卡,3储值卡,4会员制卡 ,
     * endPrice (number, optional): 价格范围上限 ,
     * isSortByPrice (integer, optional): 是否需要价格排序（0：升序，1：降序） ,
     * pageNum (integer, optional),
     * pageSize (integer, optional),
     * startPrice (number, optional): 价格范围下限 ,
     * venueName (string, optional): 场馆
     * }
     */

    private String cardName;
    private String cardType;
    private String startPrice;
    private String endPrice;
    private Integer isSortByPrice;
    private Integer pageNum;
    private Integer pageSize;
    private String venueId;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(String endPrice) {
        this.endPrice = endPrice;
    }

    public Integer getIsSortByPrice() {
        return isSortByPrice;
    }

    public void setIsSortByPrice(Integer isSortByPrice) {
        this.isSortByPrice = isSortByPrice;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cardName);
        dest.writeString(this.cardType);
        dest.writeString(this.startPrice);
        dest.writeString(this.endPrice);
        dest.writeValue(this.isSortByPrice);
        dest.writeValue(this.pageNum);
        dest.writeValue(this.pageSize);
        dest.writeString(this.venueId);
    }

    public CardRequestBody() {
    }

    protected CardRequestBody(Parcel in) {
        this.cardName = in.readString();
        this.cardType = in.readString();
        this.startPrice = in.readString();
        this.endPrice = in.readString();
        this.isSortByPrice = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pageNum = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pageSize = (Integer) in.readValue(Integer.class.getClassLoader());
        this.venueId = in.readString();
    }

    public static final Creator<CardRequestBody> CREATOR = new Creator<CardRequestBody>() {
        @Override
        public CardRequestBody createFromParcel(Parcel source) {
            return new CardRequestBody(source);
        }

        @Override
        public CardRequestBody[] newArray(int size) {
            return new CardRequestBody[size];
        }
    };
}
