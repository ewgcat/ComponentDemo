package com.yijian.staff.bean;

import com.yijian.staff.util.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.json.JSONObject;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/19 15:53:35
 */
public class HuiFangTypeBean {


    /**
     * menu : 0
     * name : string
     */

    private int menu;
    private String name;

    public HuiFangTypeBean(JSONObject jsonObject) {
        this.menu = JsonUtil.getInt(jsonObject, "menu");
        this.name = JsonUtil.getString(jsonObject, "name");
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
