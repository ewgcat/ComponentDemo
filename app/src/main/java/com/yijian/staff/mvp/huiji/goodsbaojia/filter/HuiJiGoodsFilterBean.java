package com.yijian.staff.mvp.huiji.goodsbaojia.filter;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/26 21:05:32
 */
public class HuiJiGoodsFilterBean {
    private int price;
    private int cardType;
    private int changguan;

    public HuiJiGoodsFilterBean() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getChangguan() {
        return changguan;
    }

    public void setChangguan(int changguan) {
        this.changguan = changguan;
    }
}
