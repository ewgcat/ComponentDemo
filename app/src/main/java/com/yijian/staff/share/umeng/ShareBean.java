package com.yijian.staff.share.umeng;

/**
 * Created by GZLX on 2018/7/3.
 */

public class ShareBean {
    public static final int TYPE_BROWER = 520, TYPE_WEIXIN = 521, TYPE_WEIXIN_CIRCLE = 522, TYPE_SINA = 523;
    private int resID;
    private String name;
    private int type;

    public ShareBean(int type, int resID, String name) {
        this.resID = resID;
        this.name = name;
        this.type = type;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
