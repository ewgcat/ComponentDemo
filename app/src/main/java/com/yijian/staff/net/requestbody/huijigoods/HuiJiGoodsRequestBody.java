package com.yijian.staff.net.requestbody.huijigoods;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/30 13:40:38
 */
public class HuiJiGoodsRequestBody {

    /**
     * 筛选查询卡产品列表参数载体 {
     cardName (string, optional): 卡名字 ,
     cardType (integer, optional): 卡类型 ,
     endPrice (number, optional): 价格范围上限 ,
     isSortByPrice (integer, optional): 是否需要价格排序（0：升序，1：降序） ,
     pageNum (integer, optional),
     pageSize (integer, optional),
     startPrice (number, optional): 价格范围下限 ,
     venueName (string, optional): 场馆
     }
     */

    private String cardName;
    private int cardType;
    private String startPrice;
    private String endPrice;
    private int isSortByPrice;
    private int pageNum;
    private int pageSize;
    private String venueName;

    public HuiJiGoodsRequestBody() {
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
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

    public int getIsSortByPrice() {
        return isSortByPrice;
    }

    public void setIsSortByPrice(int isSortByPrice) {
        this.isSortByPrice = isSortByPrice;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
}
