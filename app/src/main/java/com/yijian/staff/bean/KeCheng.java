package com.yijian.staff.bean;


import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/2 10:07:46
 */
@SmartTable(name="课程表")
public class KeCheng {

    @SmartColumn(id = 1, name = "时间段")
    private String num1;
    @SmartColumn(id = 2, name = "周一")
    private String num2;
    @SmartColumn(id = 3, name = "周二")
    private String num3;
    @SmartColumn(id = 4, name = "周三")
    private String num4;
    @SmartColumn(id = 5, name = "周四")
    private String num5;
    @SmartColumn(id = 6, name = "周五")
    private String num6;
    @SmartColumn(id = 7, name = "周六")
    private String num7;
    @SmartColumn(id = 8, name = "周日")
    private String num8;

    public KeCheng(String num1, String num2, String num3, String num4, String num5, String num6, String num7, String num8) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.num7 = num7;
        this.num8 = num8;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getNum3() {
        return num3;
    }

    public void setNum3(String num3) {
        this.num3 = num3;
    }

    public String getNum4() {
        return num4;
    }

    public void setNum4(String num4) {
        this.num4 = num4;
    }

    public String getNum5() {
        return num5;
    }

    public void setNum5(String num5) {
        this.num5 = num5;
    }

    public String getNum6() {
        return num6;
    }

    public void setNum6(String num6) {
        this.num6 = num6;
    }

    public String getNum7() {
        return num7;
    }

    public void setNum7(String num7) {
        this.num7 = num7;
    }

    public String getNum8() {
        return num8;
    }

    public void setNum8(String num8) {
        this.num8 = num8;
    }
}

