package com.yijian.staff.net.requestbody.savemenu;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/28 18:07:58
 */
public class MenuBean {
    private long menuItemId;
    private int order;

    public MenuBean(long menuItemId, int order) {
        this.menuItemId = menuItemId;
        this.order = order;
    }
}
