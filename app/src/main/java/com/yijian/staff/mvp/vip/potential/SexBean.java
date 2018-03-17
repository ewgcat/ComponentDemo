package com.yijian.staff.mvp.vip.potential;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/17 13:44:34
 */
public class SexBean {
    private int sex;
    private String sexDesc;

    public SexBean(int sex, String sexDesc) {
        this.sex = sex;
        this.sexDesc = sexDesc;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSexDesc() {
        return sexDesc;
    }

    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }
}
