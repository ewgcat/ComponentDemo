package com.yijian.staff.mvp.reception.step3.coach.bean;

import java.io.Serializable;

/**
 * Created by The_P on 2018/5/5.
 */

public class CardGiftDto implements Serializable {
    String name;//赠品名称
    String num;//节数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
