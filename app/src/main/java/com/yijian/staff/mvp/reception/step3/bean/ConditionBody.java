package com.yijian.staff.mvp.reception.step3.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by The_P on 2018/4/11.
 */

public class ConditionBody implements Parcelable {
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
                ", venueName='" + venueName + '\'' +
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
    private String venueName;

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

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
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
        dest.writeString(this.venueName);
    }

    public ConditionBody() {
    }

    protected ConditionBody(Parcel in) {
        this.cardName = in.readString();
        this.cardType = in.readString();
        this.startPrice = in.readString();
        this.endPrice = in.readString();
        this.isSortByPrice = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pageNum = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pageSize = (Integer) in.readValue(Integer.class.getClassLoader());
        this.venueName = in.readString();
    }

    public static final Parcelable.Creator<ConditionBody> CREATOR = new Parcelable.Creator<ConditionBody>() {
        @Override
        public ConditionBody createFromParcel(Parcel source) {
            return new ConditionBody(source);
        }

        @Override
        public ConditionBody[] newArray(int size) {
            return new ConditionBody[size];
        }
    };
}
