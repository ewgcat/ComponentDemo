package com.yijian.staff.bean;

/**
 * 体适能1 标题
 */
public class RecordTitleBean {

    private String title1;
    private String title2;
    private String title3;
    private String title4;

    public RecordTitleBean(String title1, String title2, String title3) {
        this.title1 = title1;
        this.title2 = title2;
        this.title3 = title3;
    }

    public RecordTitleBean(String title1, String title2, String title3, String title4) {
        this.title1 = title1;
        this.title2 = title2;
        this.title3 = title3;
        this.title4 = title4;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public String getTitle4() {
        return title4;
    }

    public void setTitle4(String title4) {
        this.title4 = title4;
    }
}
