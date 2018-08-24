package com.yijian.staff.net.requestbody;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/13 09:59:29
 */
public class HuifangRecordRequestBody {


    private boolean chief;
    private int pageNum;
    private int pageSize;

    public boolean isChief() {
        return chief;
    }

    public void setChief(boolean chief) {
        this.chief = chief;
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
