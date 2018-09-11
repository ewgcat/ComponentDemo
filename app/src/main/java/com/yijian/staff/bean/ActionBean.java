package com.yijian.staff.bean;

/**
 * Created by yangk on 2018/3/21.
 */

public class ActionBean {

    //动作内容id
    private String motionId;
    //训练难度
    private String moDifficulty;
    //动作名称
    private String moName;
    //训练部位
    private String moParts;
    //训练次数(x次/x组)
    private String buildDesc;
    //器械名称
    private String moApplianceName;
    //动作难易程度描述
    private String moDifficultyDesc;
    //训练部位描述
    private String moPartsDesc;
    //是否选中标志位
    private boolean isCheck = false;
    private boolean isShowHeader = false;

    public ActionBean() {
    }

    public ActionBean(String motionId, String moDifficulty, String moName, String moParts, String buildDesc, String moApplianceName) {
        this.motionId = motionId;
        this.moDifficulty = moDifficulty;
        this.moName = moName;
        this.moParts = moParts;
        this.buildDesc = buildDesc;
        this.moApplianceName = moApplianceName;
    }

    public String getMotionId() {
        return motionId;
    }

    public void setMotionId(String motionId) {
        this.motionId = motionId;
    }

    public String getMoParts() {
        return moParts;
    }

    public void setMoParts(String moParts) {
        this.moParts = moParts;
    }

    public String getBuildDesc() {
        return buildDesc;
    }

    public void setBuildDesc(String buildDesc) {
        this.buildDesc = buildDesc;
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

    public String getMoApplianceName() {
        return moApplianceName;
    }

    public void setMoApplianceName(String moApplianceName) {
        this.moApplianceName = moApplianceName;
    }

    public String getMoDifficultyDesc() {
        return moDifficultyDesc;
    }

    public void setMoDifficultyDesc(String moDifficultyDesc) {
        this.moDifficultyDesc = moDifficultyDesc;
    }

    public String getMoPartsDesc() {
        return moPartsDesc;
    }

    public void setMoPartsDesc(String moPartsDesc) {
        this.moPartsDesc = moPartsDesc;
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
