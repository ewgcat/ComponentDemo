package com.yijian.staff.mvp.course.experienceclass.template.template_system.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by The_P on 2018/4/13.
 */


public class AerobicsListBean extends BaseGroupBean implements Parcelable {
    @Override
    public String toString() {
        return "AerobicsListBean{" +
                "templateContextId='" + templateContextId + '\'' +
                ", contextType=" + contextType +
                ", sort=" + sort +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", time=" + time +
                '}';
    }

    /**
     * templateContextId : 5
     * contextType : 2
     * sort : 1
     * name : 健身单车
     * grade : S级
     * time : 600
     */

    private String templateContextId;
    private Integer contextType;
    private Integer sort;
    private String name;
    private String grade;
    private Integer time;

    public AerobicsListBean(String templateContextId, Integer contextType, Integer sort, String name, String grade, Integer time) {
        this.templateContextId = templateContextId;
        this.contextType = contextType;
        this.sort = sort;
        this.name = name;
        this.grade = grade;
        this.time = time;
    }

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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
        dest.writeString(this.grade);
        dest.writeValue(this.time);
        dest.writeByte(this.isHead ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isLastItem ? (byte) 1 : (byte) 0);
    }

    protected AerobicsListBean(Parcel in) {
        this.templateContextId = in.readString();
        this.contextType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sort = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.grade = in.readString();
        this.time = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isHead = in.readByte() != 0;
        this.isLastItem = in.readByte() != 0;
    }

    public static final Parcelable.Creator<AerobicsListBean> CREATOR = new Parcelable.Creator<AerobicsListBean>() {
        @Override
        public AerobicsListBean createFromParcel(Parcel source) {
            return new AerobicsListBean(source);
        }

        @Override
        public AerobicsListBean[] newArray(int size) {
            return new AerobicsListBean[size];
        }
    };
}
