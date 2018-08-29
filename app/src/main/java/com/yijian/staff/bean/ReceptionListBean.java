package com.yijian.staff.bean;

import java.util.List;

/**
 * Created by The_P on 2018/5/14.
 */

public class ReceptionListBean {
    private int pageSize;
    private int pages;
    private int total;
    private int pageNum;
    private boolean hasPermissionEdit;
    private boolean hasPermissionDel;
    private List<ReceptionRecordBean> records;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public boolean isHasPermissionEdit() {
        return hasPermissionEdit;
    }

    public void setHasPermissionEdit(boolean hasPermissionEdit) {
        this.hasPermissionEdit = hasPermissionEdit;
    }

    public boolean isHasPermissionDel() {
        return hasPermissionDel;
    }

    public void setHasPermissionDel(boolean hasPermissionDel) {
        this.hasPermissionDel = hasPermissionDel;
    }

    public List<ReceptionRecordBean> getRecords() {
        return records;
    }

    public void setRecords(List<ReceptionRecordBean> records) {
        this.records = records;
    }
}
