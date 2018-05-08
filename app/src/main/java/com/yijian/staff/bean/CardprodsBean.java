package com.yijian.staff.bean;

import java.io.Serializable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/5/8 16:00:44
 */
public  class CardprodsBean implements Serializable {
    /**
     * cardName : string
     * cardType : string
     * cardprodId : string
     */

    private String cardName;
    private String cardType;
    private String cardprodId;

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

    public String getCardprodId() {
        return cardprodId;
    }

    public void setCardprodId(String cardprodId) {
        this.cardprodId = cardprodId;
    }
}
