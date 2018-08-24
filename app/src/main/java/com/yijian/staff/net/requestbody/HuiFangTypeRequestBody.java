package com.yijian.staff.net.requestbody;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/24 18:41:09
 */
public class HuiFangTypeRequestBody {


    /**
     * chief : true
     * type : 0
     */

    private boolean chief;
    private int type;

    public boolean isChief() {
        return chief;
    }

    public void setChief(boolean chief) {
        this.chief = chief;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
