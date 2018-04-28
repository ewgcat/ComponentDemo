package com.yijian.staff.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangk on 2018/3/21.
 */

public class SubActionBean {

    private boolean isCheckChild; //扩展字段，是否选择了组字段
    private List<SubChildBean> subChildBeanList = new ArrayList<SubChildBean>();

    public boolean isCheckChild() {
        return isCheckChild;
    }

    public void setCheckChild(boolean checkChild) {
        isCheckChild = checkChild;
    }

    public List<SubChildBean> getSubChildBeanList() {
        return subChildBeanList;
    }

    public void setSubChildBeanList(List<SubChildBean> subChildBeanList) {
        this.subChildBeanList = subChildBeanList;
    }

    public static class SubChildBean{
        private String key; //动作名称
        private String value; //动作限制

        public SubChildBean(String key, String value) {
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

}
