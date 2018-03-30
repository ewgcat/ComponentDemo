package com.yijian.staff.mvp.huiji.goodsbaojia.filter;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/26 21:05:32
 */
public class HuiJiGoodsFilterBean {
    private String cardName;
    private String cardType;//1期限卡  2次数卡  3 储值卡   4会员卡
    private String startPrice;
    private String endPrice;
    private String venueName;

    public HuiJiGoodsFilterBean() {
    }

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

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
}
