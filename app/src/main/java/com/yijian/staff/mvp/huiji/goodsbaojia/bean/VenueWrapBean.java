package com.yijian.staff.mvp.huiji.goodsbaojia.bean;

import java.util.List;

/**
 * Created by The_P on 2018/4/11.
 */

public class VenueWrapBean {
//    id (string, optional): id ,
//    name (string, optional): 名称
    private List<VenueBean> dataList;

    public List<VenueBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<VenueBean> dataList) {
        this.dataList = dataList;
    }
}
