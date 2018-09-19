package com.yijian.clubmodule.net.requestbody.huifang;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/10 11:00:55
 */
public class HuifangTaskRequestBody {


    /**
     * chief : true
     * menu : 0
     * offset : 0
     * size : 0
     */

    private boolean chief;
    private int menu;
    private int offset;
    private int size;


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

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
