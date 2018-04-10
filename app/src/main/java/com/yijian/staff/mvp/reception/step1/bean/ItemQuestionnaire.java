package com.yijian.staff.mvp.reception.step1.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by The_P on 2018/4/9.
 */

public class ItemQuestionnaire implements Parcelable {
    @Override
    public String toString() {
        return "ItemQuestionnaire{" +
                "id='" + id + '\'' +
                ", inputContent='" + inputContent + '\'' +
                '}';
    }

    String id;
    String inputContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.inputContent);
    }

    public ItemQuestionnaire() {
    }

    protected ItemQuestionnaire(Parcel in) {
        this.id = in.readString();
        this.inputContent = in.readString();
    }

    public static final Parcelable.Creator<ItemQuestionnaire> CREATOR = new Parcelable.Creator<ItemQuestionnaire>() {
        @Override
        public ItemQuestionnaire createFromParcel(Parcel source) {
            return new ItemQuestionnaire(source);
        }

        @Override
        public ItemQuestionnaire[] newArray(int size) {
            return new ItemQuestionnaire[size];
        }
    };
}
