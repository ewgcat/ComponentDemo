package com.yijian.staff.mvp.price.courseprice.filter;

import java.io.Serializable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/26 21:05:32
 */
public class CoachClassFilterBean implements Serializable {
    private String lcourseNum;
    private String rcourseNum;

    private String lconsumingMinute;
    private String rconsumingMinute;

    private String ltotalPrice;
    private String rtotalPrice;

    private String indate;

    public CoachClassFilterBean() {
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

    @Override
    public String toString() {
        return "CoachClassFilterBean{" +
                "lcourseNum='" + lcourseNum + '\'' +
                ", rcourseNum='" + rcourseNum + '\'' +
                ", lconsumingMinute='" + lconsumingMinute + '\'' +
                ", rconsumingMinute='" + rconsumingMinute + '\'' +
                ", ltotalPrice='" + ltotalPrice + '\'' +
                ", rtotalPrice='" + rtotalPrice + '\'' +
                ", indate='" + indate + '\'' +
                '}';
    }
}
