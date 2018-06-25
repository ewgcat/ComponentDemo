package com.yijian.staff.mvp.reception.step1.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by The_P on 2018/4/9.
 */

public class QuestionnaireAnswerWrap implements Parcelable {
    List<QuestionnaireAnswer> abc;

    public List<QuestionnaireAnswer> getQuestionnaireSurveySaveDtos() {
        return abc;
    }

    public void setQuestionnaireSurveySaveDtos(List<QuestionnaireAnswer> questionnaireSurveySaveDtos) {
        this.abc = questionnaireSurveySaveDtos;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.abc);
    }

    public QuestionnaireAnswerWrap() {
    }

    protected QuestionnaireAnswerWrap(Parcel in) {
        this.abc = in.createTypedArrayList(QuestionnaireAnswer.CREATOR);
    }

    public static final Parcelable.Creator<QuestionnaireAnswerWrap> CREATOR = new Parcelable.Creator<QuestionnaireAnswerWrap>() {
        @Override
        public QuestionnaireAnswerWrap createFromParcel(Parcel source) {
            return new QuestionnaireAnswerWrap(source);
        }

        @Override
        public QuestionnaireAnswerWrap[] newArray(int size) {
            return new QuestionnaireAnswerWrap[size];
        }
    };
}
