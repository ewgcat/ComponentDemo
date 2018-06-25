package com.yijian.staff.mvp.main.message.bean;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/8 16:43:29
 */
public class MessageInfo {

    private String content;
    private String time;
    private String icon;
    private int sex;
    private int type;


    public MessageInfo(JSONObject jsonObject) {
        this.content = JsonUtil.getString(jsonObject, "content");
        this.time = JsonUtil.getString(jsonObject, "time");
        this.icon = JsonUtil.getString(jsonObject, "icon");
        this.sex = JsonUtil.getInt(jsonObject, "sex");
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getIcon() {
        return icon;
    }

    public int getSex() {
        return sex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
