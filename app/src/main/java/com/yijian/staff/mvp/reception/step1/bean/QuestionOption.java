package com.yijian.staff.mvp.reception.step1.bean;

public class QuestionOption {
    public static final int TYPE_SINGLECHECK = 0;
    public static final int TYPE_MULTICHECK = 1;
    public static final int TYPE_WRITE = 2;

    public QuestionOption(String mName, int type, boolean isSelected) {
        this.mName = mName;
        this.type = type;
        this.isSelected = isSelected;
    }

    private String mName;
    private int type;
    private boolean isSelected;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
