package com.yijian.staff.mvp.reception.step1.bean;

/**
 * Created by The_P on 2018/4/9.
 */

public  class ItemsBean {
    /**
     * id : 3
     * item : 报纸
     * type : 0
     * order : 0
     * inputContent : null
     * select : false
     */

    private String id;
    private String item;
    private int type;
    private int order;
    private String inputContent;
    private boolean select;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    @Override
    public String toString() {
        return "ItemsBean{" +
                "id='" + id + '\'' +
                ", item='" + item + '\'' +
                ", type=" + type +
                ", order=" + order +
                ", inputContent='" + inputContent + '\'' +
                ", select=" + select +
                '}';
    }
}
