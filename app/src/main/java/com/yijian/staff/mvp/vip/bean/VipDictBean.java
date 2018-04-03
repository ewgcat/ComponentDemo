package com.yijian.staff.mvp.vip.bean;

/**
 * Created by yangk on 2018/4/3.
 */

public class VipDictBean {


    /**
     * dictItemId : 66
     * dictItemName : 中国
     * sort : 100
     */

    private int dictItemId;
    private String dictItemName;
    private int sort;

    public VipDictBean(){}

    public VipDictBean(String dictItemName) {
        this.dictItemName = dictItemName;
    }

    public int getDictItemId() {
        return dictItemId;
    }

    public void setDictItemId(int dictItemId) {
        this.dictItemId = dictItemId;
    }

    public String getDictItemName() {
        return dictItemName;
    }

    public void setDictItemName(String dictItemName) {
        this.dictItemName = dictItemName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
