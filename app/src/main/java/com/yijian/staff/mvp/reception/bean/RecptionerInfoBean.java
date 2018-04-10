package com.yijian.staff.mvp.reception.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by The_P on 2018/3/28.
 */

public class RecptionerInfoBean implements Parcelable {

    /**
     * id : 55b2144ff5d145faa6247aa8964ea3e4
     * name : 值7
     * sex : 未知
     * mobile : 值7
     */

    private String id;
    private String name;
    private String sex;
    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.sex);
        dest.writeString(this.mobile);
    }

    public RecptionerInfoBean() {
    }

    protected RecptionerInfoBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.sex = in.readString();
        this.mobile = in.readString();
    }

    public static final Parcelable.Creator<RecptionerInfoBean> CREATOR = new Parcelable.Creator<RecptionerInfoBean>() {
        @Override
        public RecptionerInfoBean createFromParcel(Parcel source) {
            return new RecptionerInfoBean(source);
        }

        @Override
        public RecptionerInfoBean[] newArray(int size) {
            return new RecptionerInfoBean[size];
        }
    };
}
