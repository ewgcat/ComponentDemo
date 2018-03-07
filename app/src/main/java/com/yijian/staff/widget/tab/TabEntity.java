package com.yijian.staff.widget.tab;

import android.app.Activity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/7 11:18:31
 */
@Entity
public class TabEntity {
    private String tabName;
    private int  resId;

    @Generated(hash = 1753271375)
    public TabEntity(String tabName, int resId) {
        this.tabName = tabName;
        this.resId = resId;
    }

    @Generated(hash = 1747464178)
    public TabEntity() {
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }


   
}
