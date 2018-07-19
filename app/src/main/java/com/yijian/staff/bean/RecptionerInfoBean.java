package com.yijian.staff.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/3/28.
 */

public class RecptionerInfoBean implements Parcelable {

    @Override
    public String toString() {
        return "RecptionerInfoBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", historyNode=" + historyNode +
                '}';
    }

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
    private Integer status;//节点位置
    private List<Integer> historyNode;//历史节点

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Integer> getHistoryNode() {
        return historyNode;
    }

    public void setHistoryNode(List<Integer> historyNode) {
        this.historyNode = historyNode;
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
        dest.writeValue(this.status);
        dest.writeList(this.historyNode);
    }

    public RecptionerInfoBean() {
    }

    protected RecptionerInfoBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.sex = in.readString();
        this.mobile = in.readString();
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.historyNode = new ArrayList<Integer>();
        in.readList(this.historyNode, Integer.class.getClassLoader());
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
