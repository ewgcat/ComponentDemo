package com.yijian.clubmodule.db.bean;

import com.yijian.commonlib.util.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.json.JSONObject;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/19 15:53:35
 */
@Entity
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

}
