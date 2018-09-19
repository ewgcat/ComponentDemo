package com.yijian.clubmodule.net.requestbody.message;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/9/19 21:52:57
 */
public class BusinessMessageRequestBody {
    private int pageNum;
    private int pageSize;

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
