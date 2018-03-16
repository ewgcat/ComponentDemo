package com.yijian.staff.mvp.reception.step2.step2Bean;


import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.Parent;

import java.util.List;

/**
 * Created by The_P on 2018/3/15.
 */

public  class ParentQuestionBean implements Parent<ChildOptBean> {

    /**
     * title : 体测数据
     * childObj : [{"qustion":"身高(cm)","qusType":"normal","valueIsNum":true,"minValue":"120","maxValue":"300","valueArray":[],"hasSymbol":false,"hasDecimal":false,"defaultValue":"170"},{"qustion":"年龄(岁)","qusType":"normal","valueIsNum":true,"minValue":"18","maxValue":"100","valueArray":[],"hasSymbol":false,"hasDecimal":false,"defaultValue":"20"}]
     */

    private String title;
    private List<ChildOptBean> childObj;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setChildObj(List<ChildOptBean> childObj) {
        this.childObj = childObj;
    }


    @Override
    public List<ChildOptBean> getChildList() {
        return childObj;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return true;
    }
}