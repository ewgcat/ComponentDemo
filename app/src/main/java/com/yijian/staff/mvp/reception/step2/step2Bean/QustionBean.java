package com.yijian.staff.mvp.reception.step2.step2Bean;

import java.util.List;

/**
 * Created by The_P on 2018/3/15.
 */

public class QustionBean {
    @Override
    public String toString() {
        return "Bean{" +
                "parentObj=" + parentObj.toString() +
                '}';
    }

    private List<ParentQuestionBean> parentObj;

    public List<ParentQuestionBean> getParentObj() {
        return parentObj;
    }

    public void setParentObj(List<ParentQuestionBean> parentObj) {
        this.parentObj = parentObj;
    }


}
