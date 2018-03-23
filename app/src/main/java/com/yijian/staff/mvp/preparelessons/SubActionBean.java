package com.yijian.staff.mvp.preparelessons;

/**
 * Created by yangk on 2018/3/21.
 */

public class SubActionBean {

    private String key; //动作名称
    private String value; //动作限制

    public SubActionBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
