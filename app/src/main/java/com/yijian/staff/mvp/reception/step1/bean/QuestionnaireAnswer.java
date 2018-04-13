package com.yijian.staff.mvp.reception.step1.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by The_P on 2018/4/9.
 */

public class QuestionnaireAnswer implements Parcelable {
    @Override
    public String toString() {
        return "QuestionnaireAnswer{" +
                "id='" + id + '\'' +
                ", list=" + list +
                '}';
    }

    String id;
    List<ItemQuestionnaire> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ItemQuestionnaire> getList() {
        return list;
    }

    public void setList(List<ItemQuestionnaire> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeTypedList(this.list);
    }

    public QuestionnaireAnswer() {
    }

    protected QuestionnaireAnswer(Parcel in) {
        this.id = in.readString();
        this.list = in.createTypedArrayList(ItemQuestionnaire.CREATOR);
    }

    public static final Parcelable.Creator<QuestionnaireAnswer> CREATOR = new Parcelable.Creator<QuestionnaireAnswer>() {
        @Override
        public QuestionnaireAnswer createFromParcel(Parcel source) {
            return new QuestionnaireAnswer(source);
        }

        @Override
        public QuestionnaireAnswer[] newArray(int size) {
            return new QuestionnaireAnswer[size];
        }
    };
}
