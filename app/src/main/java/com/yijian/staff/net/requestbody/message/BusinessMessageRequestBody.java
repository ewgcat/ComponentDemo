package com.yijian.staff.net.requestbody.message;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/12 10:45:24
 */
public class BusinessMessageRequestBody {
    private int pageSize;
    private int pageNum;
    private int businessType;

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
