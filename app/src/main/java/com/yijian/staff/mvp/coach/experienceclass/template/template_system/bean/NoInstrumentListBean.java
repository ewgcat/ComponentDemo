package com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by The_P on 2018/4/13.
 */

public class NoInstrumentListBean extends BaseGroupBean implements Parcelable {
    @Override
    public String toString() {
        return "NoInstrumentListBean{" +
                "templateContextId='" + templateContextId + '\'' +
                ", contextType=" + contextType +
                ", sort=" + sort +
                ", name='" + name + '\'' +
                ", groupNum=" + groupNum +
                ", groupTime=" + groupTime +
                '}';
    }

    public NoInstrumentListBean(String templateContextId, Integer contextType, Integer sort, String name, Integer groupNum, Integer groupTime) {
        this.templateContextId = templateContextId;
        this.contextType = contextType;
        this.sort = sort;
        this.name = name;
        this.groupNum = groupNum;
        this.groupTime = groupTime;
    }

    /**
     * templateContextId : 1
     * contextType : 1
     * sort : 1
     * name : 台阶测试
     * groupNum : 2
     * groupTime : 60
     */

    private String templateContextId;
    private Integer contextType;
    private Integer sort;
    private String name;
    private Integer groupNum;
    private Integer groupTime;

    public String getTemplateContextId() {
        return templateContextId;
    }

    public void setTemplateContextId(String templateContextId) {
        this.templateContextId = templateContextId;
    }

    public Integer getContextType() {
        return contextType;
    }

    public void setContextType(Integer contextType) {
        this.contextType = contextType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public Integer getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(Integer groupTime) {
        this.groupTime = groupTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.templateContextId);
        dest.writeValue(this.contextType);
        dest.writeValue(this.sort);
        dest.writeString(this.name);
        dest.writeValue(this.groupNum);
        dest.writeValue(this.groupTime);
        dest.writeByte(this.isHead ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isLastItem ? (byte) 1 : (byte) 0);
    }

    protected NoInstrumentListBean(Parcel in) {
        this.templateContextId = in.readString();
        this.contextType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sort = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.groupNum = (Integer) in.readValue(Integer.class.getClassLoader());
        this.groupTime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isHead = in.readByte() != 0;
        this.isLastItem = in.readByte() != 0;
    }

    public static final Parcelable.Creator<NoInstrumentListBean> CREATOR = new Parcelable.Creator<NoInstrumentListBean>() {
        @Override
        public NoInstrumentListBean createFromParcel(Parcel source) {
            return new NoInstrumentListBean(source);
        }

        @Override
        public NoInstrumentListBean[] newArray(int size) {
            return new NoInstrumentListBean[size];
        }
    };
}