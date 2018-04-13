package com.yijian.staff.mvp.coach.preparelessons.createlession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/21.
 */

public class ActionBean {

    //第几个动作
    private int sort;
    //训练难度
    private String moDifficulty;
    //动作名称
    private String moName;
    //训练次数(x次/x组)
    private String buildTime;
    //器械名称
    private String moApplianceName;
    //是否选中标志位
    private boolean isCheck = false;
    private boolean isShowHeader = false;

    public ActionBean(int sort, String moDifficulty, String moName, String buildTime, String moApplianceName) {
        this.sort = sort;
        this.moDifficulty = moDifficulty;
        this.moName = moName;
        this.buildTime = buildTime;
        this.moApplianceName = moApplianceName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getMoDifficulty() {
        return moDifficulty;
    }

    public void setMoDifficulty(String moDifficulty) {
        this.moDifficulty = moDifficulty;
    }

    public String getMoName() {
        return moName;
    }

    public void setMoName(String moName) {
        this.moName = moName;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getMoApplianceName() {
        return moApplianceName;
    }

    public void setMoApplianceName(String moApplianceName) {
        this.moApplianceName = moApplianceName;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isShowHeader() {
        return isShowHeader;
    }

    public void setShowHeader(boolean showHeader) {
        isShowHeader = showHeader;
    }
}
