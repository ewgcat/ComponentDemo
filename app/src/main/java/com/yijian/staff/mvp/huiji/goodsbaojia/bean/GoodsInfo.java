package com.yijian.staff.mvp.huiji.goodsbaojia.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/17 19:29:17
 */
public class GoodsInfo {

    /**
     *
     * cardType (integer, optional): 卡类型:0期限卡,1次数卡,2储值卡,3会员制卡 ,
     cardName (string, optional): 卡类型名称 ,
     cardprodbaseId (string, optional): 卡产品id ,
     rechargeGivePercent (integer, optional): 充值赠送(%) ,
     salePrice (number, optional): 售价 ,
     validDay (integer, optional): 有效时间(时间卡额度和有效期) ,
     validTime (integer, optional): 有效次数(次卡) ,
     venueNames (Array[string], optional): 场馆名称
     */

    private String cardprodbaseId;
    private int cardType;
    private String cardName;
    private String validTime;
    private String validDay;
    private String salePrice;
    private String rechargeGivePercent;
    private String venueNames;

    public GoodsInfo(JSONObject jsonObject){
        this.cardName=  JsonUtil.getString(jsonObject,"cardName");
        this.venueNames=  JsonUtil.getString(jsonObject,"venueNames");
        this.cardType=  JsonUtil.getInt(jsonObject,"cardType");
        this.validDay=  JsonUtil.getString(jsonObject,"validDay");
        this.validTime=  JsonUtil.getString(jsonObject,"validTime");
        this.salePrice=  JsonUtil.getString(jsonObject,"salePrice");
        this.cardprodbaseId=  JsonUtil.getString(jsonObject,"cardprodbaseId");
        this.rechargeGivePercent=  JsonUtil.getString(jsonObject,"rechargeGivePercent");
    }

    public String getCardprodbaseId() {
        return cardprodbaseId;
    }

    public int getCardType() {
        return cardType;
    }

    public String getCardName() {
        return cardName;
    }

    public String getValidTime() {
        return validTime;
    }

    public String getValidDay() {
        return validDay;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getRechargeGivePercent() {
        return rechargeGivePercent;
    }

    public String getVenueNames() {
        return venueNames;
    }
}
