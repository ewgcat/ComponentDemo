package com.yijian.staff.db.bean;

import com.yijian.staff.util.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.json.JSONObject;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/19 15:53:35
 */
@Entity
public class CoachHuiFangTypeBean {

    /**
     * menu : 0
     * name : string
     */

    private int menu;
    private String name;

    public CoachHuiFangTypeBean(JSONObject jsonObject) {
        this.menu = JsonUtil.getInt(jsonObject, "menu");
        this.name = JsonUtil.getString(jsonObject, "name");
    }

    @Generated(hash = 1133135597)
    public CoachHuiFangTypeBean(int menu, String name) {
        this.menu = menu;
        this.name = name;
    }

    @Generated(hash = 660955335)
    public CoachHuiFangTypeBean() {
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
