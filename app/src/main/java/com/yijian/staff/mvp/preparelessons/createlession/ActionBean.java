package com.yijian.staff.mvp.preparelessons.createlession;

import android.text.TextUtils;

import com.yijian.staff.mvp.preparelessons.SubActionBean;

import org.jsoup.helper.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/21.
 */

public class ActionBean {

    private String degree;
    private List<List<SubActionBean>> subActionBeans = new ArrayList<>();

    public String getDegree() {
        return TextUtils.isEmpty(degree)?"":":"+degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<List<SubActionBean>> getSubActionBeans() {
        return subActionBeans;
    }

    public void setSubActionBeans(List<List<SubActionBean>> subActionBeans) {
        this.subActionBeans = subActionBeans;
    }
}
