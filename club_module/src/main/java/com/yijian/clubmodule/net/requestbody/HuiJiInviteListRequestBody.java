package com.yijian.clubmodule.net.requestbody;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/26 14:50:53
 */
public class HuiJiInviteListRequestBody {

    public HuiJiInviteListRequestBody(String currentTime, int pageNum, int pageSize) {
        this.currentTime = currentTime;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * currentTime : 2018-04-25T10:33:18.098Z
     * pageNum : 0
     * pageSize : 0
     */

    private String currentTime;
    private int pageNum;
    private int pageSize;

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
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
