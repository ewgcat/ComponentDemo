package com.yijian.clubmodule.net.requestbody.huifang;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/20 10:39:32
 */
public class AddHuiFangResultBody {


    /**
     * chief : true
     * desc : string
     * id : string
     * visitTime : 2018-08-10T06:07:15.986Z
     */

    private boolean chief;
    private String desc;
    private String id;
    private String visitTime;

    public boolean isChief() {
        return chief;
    }

    public void setChief(boolean chief) {
        this.chief = chief;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
