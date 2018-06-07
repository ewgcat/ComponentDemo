package com.yijian.staff.mvp.huiji.goodsbaojia.bean;

import java.util.List;

/**
 * Created by The_P on 2018/4/11.
 */

public class RecptionCards {
//    "offset": 0,integer
//            "limit": 2147483647,integer
//            "total": 9,integer
//            "size": 10,integer
//            "pages": 1,integer
//            "current": 1,integer
//            "searchCount": true,boolean
//            "openSort": true,boolean
//            "orderByField": null,string
//            "records":


    public Integer offset;
    public Integer limit;
    public Integer total;
    public Integer size;
    public Integer pages;
    public Integer current;
    public Boolean searchCount;
    public Boolean openSort;
    public String orderByField;
    public List<CardInfo> records;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Boolean getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
    }

    public Boolean getOpenSort() {
        return openSort;
    }

    public void setOpenSort(Boolean openSort) {
        this.openSort = openSort;
    }

    public String getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(String orderByField) {
        this.orderByField = orderByField;
    }

    public List<CardInfo> getRecords() {
        return records;
    }

    public void setRecords(List<CardInfo> records) {
        this.records = records;
    }
}
