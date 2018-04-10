package com.yijian.staff.mvp.reception.step1.bean;

import com.yijian.staff.mvp.reception.step1.recyclerView.ParentImp;

import java.util.List;

/**
 * Created by The_P on 2018/4/9.
 */

public  class DataListBean implements ParentImp<ItemsBean> {
    /**
     * id : 1
     * desc : 信息来源
     * selectType : 1
     * items : [{"id":"3","item":"报纸","type":0,"order":0,"inputContent":null,"select":false},{"id":"4","item":"电视","type":0,"order":0,"inputContent":null,"select":false},{"id":"5","item":"传单","type":0,"order":0,"inputContent":null,"select":false}]
     * order : 0
     */

    private String id;
    private String desc;
    private int selectType;
    private int order;
    private List<ItemsBean> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = (desc!=null)?desc:"";
    }

    public int getSelectType() {
        return selectType;
    }

    public void setSelectType(int selectType) {
        this.selectType = selectType;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }


    @Override
    public List<ItemsBean> getChildList() {
        return items;
    }

    @Override
    public String toString() {
        return "DataListBean{" +
                "id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", selectType=" + selectType +
                ", order=" + order +
                ", items=" + items +
                '}';
    }
}