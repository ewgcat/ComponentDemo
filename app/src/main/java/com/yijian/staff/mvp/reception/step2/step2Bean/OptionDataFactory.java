package com.yijian.staff.mvp.reception.step2.step2Bean;

import java.util.ArrayList;

/**
 * Created by The_P on 2018/3/14.
 */

public class OptionDataFactory {
    private static final String TAG = "OptionDataFactory";

    public OptionDataFactory() {
        initDecimal();//初始化小数
        initSymbol();//初始化符号
    }

    ArrayList<String> symbol = new ArrayList<>();

    private void initSymbol() {
        symbol.add("+");
        symbol.add("-");
    }

    ArrayList<String> decimal = new ArrayList<>();

    private void initDecimal() {
        decimal.add(".0");
        decimal.add(".1");
        decimal.add(".2");
        decimal.add(".3");
        decimal.add(".4");
        decimal.add(".5");
        decimal.add(".6");
        decimal.add(".7");
        decimal.add(".8");
        decimal.add(".9");
    }

    ArrayList<String> integer = new ArrayList<>();

    public ArrayList<String> initIntegerRange(int star, int end) {

        integer.clear();
        int range = end - star + 1;
        for (int i = 0; i < range; i++) {
            int j = star + i;
            integer.add(String.valueOf(j));
        }
        return integer;
    }

    public ArrayList<String> getSymbol() {
        return symbol;
    }

    public ArrayList<String> getDecimal() {
        return decimal;
    }
}
