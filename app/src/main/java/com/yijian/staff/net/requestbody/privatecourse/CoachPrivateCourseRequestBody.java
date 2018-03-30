package com.yijian.staff.net.requestbody.privatecourse;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/29 19:38:39
 */
public class CoachPrivateCourseRequestBody {

    /**
     私课产品查询列表传参对象 {
     courseName (string, optional): 私课名称 ,
     indate (integer, optional): 有效期 ,
     isSortByPrice (integer, optional): 是否需要价格排序（0：升序，1：降序） ,
     lconsumingMinute (integer, optional): 单节时长-左区间(分钟) ,
     lcourseNum (integer, optional): 课程节数-左区间 ,
     ltotalPrice (number, optional): 价格-左区间 ,
     pageNum (integer, optional),
     pageSize (integer, optional),
     rconsumingMinute (integer, optional): 单节时长-右区间(分钟) ,
     rcourseNum (integer, optional): 课程节数-右区间 ,
     rtotalPrice (number, optional): 价格-右区间

     }
     */
    private String lcourseNum;
    private String rcourseNum;

    private String lconsumingMinute;
    private String rconsumingMinute;

    private String ltotalPrice;
    private String rtotalPrice;

    private String indate;

    private String courseName;
    private String isSortByPrice;
    private int pageNum;
    private int pageSize;

    public CoachPrivateCourseRequestBody() {
    }

    public String getLcourseNum() {
        return lcourseNum;
    }

    public void setLcourseNum(String lcourseNum) {
        this.lcourseNum = lcourseNum;
    }

    public String getRcourseNum() {
        return rcourseNum;
    }

    public void setRcourseNum(String rcourseNum) {
        this.rcourseNum = rcourseNum;
    }

    public String getLconsumingMinute() {
        return lconsumingMinute;
    }

    public void setLconsumingMinute(String lconsumingMinute) {
        this.lconsumingMinute = lconsumingMinute;
    }

    public String getRconsumingMinute() {
        return rconsumingMinute;
    }

    public void setRconsumingMinute(String rconsumingMinute) {
        this.rconsumingMinute = rconsumingMinute;
    }

    public String getLtotalPrice() {
        return ltotalPrice;
    }

    public void setLtotalPrice(String ltotalPrice) {
        this.ltotalPrice = ltotalPrice;
    }

    public String getRtotalPrice() {
        return rtotalPrice;
    }

    public void setRtotalPrice(String rtotalPrice) {
        this.rtotalPrice = rtotalPrice;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIsSortByPrice() {
        return isSortByPrice;
    }

    public void setIsSortByPrice(String isSortByPrice) {
        this.isSortByPrice = isSortByPrice;
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
