package com.yijian.staff.mvp.reception.step1.bean;

import java.util.List;

/**
 * Created by The_P on 2018/4/9.
 */

public class TemplateBean {
    @Override
    public String toString() {
        return "TemplateBean{" +
                "dataList=" + dataList +
                '}';
    }

    public List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }


}
