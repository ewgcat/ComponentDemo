package com.yijian.staff.net.requestbody.huifang;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/10 11:00:55
 */
public class HuifangTaskRequestBody {


    /**
     * chief : true
     * menu : 0
     * pageNum : 0
     * pageSize : 0
     */

    private boolean chief;
    private int menu;
    private int pageNum;
    private int pageSize;

    public boolean isChief() {
        return chief;
    }

    public void setChief(boolean chief) {
        this.chief = chief;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
