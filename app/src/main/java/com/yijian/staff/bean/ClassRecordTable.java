package com.yijian.staff.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/4/25.
 */

public class ClassRecordTable implements Parcelable {

    public List<ClassActionDetails> definedRecordContentList;// (Array[上课记录表具体动作详细内容], optional)

    public List<ClassActionDetails> getDefinedRecordContentList() {
        return definedRecordContentList;
    }

    public void setDefinedRecordContentList(List<ClassActionDetails> definedRecordContentList) {
        this.definedRecordContentList = definedRecordContentList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.definedRecordContentList);
    }

    public ClassRecordTable() {
    }

    protected ClassRecordTable(Parcel in) {
        this.definedRecordContentList = new ArrayList<ClassActionDetails>();
        in.readList(this.definedRecordContentList, ClassActionDetails.class.getClassLoader());
    }

    public static final Parcelable.Creator<ClassRecordTable> CREATOR = new Parcelable.Creator<ClassRecordTable>() {
        @Override
        public ClassRecordTable createFromParcel(Parcel source) {
            return new ClassRecordTable(source);
        }

        @Override
        public ClassRecordTable[] newArray(int size) {
            return new ClassRecordTable[size];
        }
    };
}
