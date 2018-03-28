package com.yijian.staff.mvp.coach.preparelessons.createlession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/21.
 */

public class ActionBean {

    private String degree;
    private List<SubActionBean> subActionBeans = new ArrayList<>();
    private boolean isCheckGroup; //扩展字段，是否选择了组字段

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<SubActionBean> getSubActionBeans() {
        return subActionBeans;
    }

    public void setSubActionBeans(List<SubActionBean> subActionBeans) {
        this.subActionBeans = subActionBeans;
    }

    public boolean isCheckGroup() {
        return isCheckGroup;
    }

    public void setCheckGroup(boolean checkGroup) {
        isCheckGroup = checkGroup;
    }
}
