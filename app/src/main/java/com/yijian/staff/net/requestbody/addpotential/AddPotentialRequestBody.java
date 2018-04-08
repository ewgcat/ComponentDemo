package com.yijian.staff.net.requestbody.addpotential;

import com.yijian.staff.util.DateUtil;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/8 10:51:38
 */
public class AddPotentialRequestBody {


    /**
     * addTime : 2018-04-08T01:22:44.790Z
     * mobile : string
     * name : string
     * sex : 0
     */

    private String addTime;
    private String mobile;
    private String name;
    private int sex;

    public AddPotentialRequestBody(String mobile, String name, int sex) {
        this.addTime =  DateUtil.getCurrentDate();
        this.mobile = mobile;
        this.name = name;
        this.sex = sex;
    }


}
