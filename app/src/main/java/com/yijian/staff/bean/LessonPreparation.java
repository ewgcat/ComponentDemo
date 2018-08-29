package com.yijian.staff.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by The_P on 2018/4/25.
 */

public class LessonPreparation implements Parcelable {
    public TemplateListBean templateVO;//(体验课备课模板返回对象, optional): 如果备课内容是体侧模板的，请选择这个

    public ClassRecordTable definedRecordVO;//(体验课上课记录表(使用自定义模板), optional): 如果备课内容是私教课模板/自定义模板，请选择这个 ,

    public TemplateListBean getTemplateVO() {
        return templateVO;
    }

    public void setTemplateVO(TemplateListBean templateVO) {
        this.templateVO = templateVO;
    }

    public ClassRecordTable getDefinedRecordVO() {
        return definedRecordVO;
    }

    public void setDefinedRecordVO(ClassRecordTable definedRecordVO) {
        this.definedRecordVO = definedRecordVO;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.templateVO, flags);
        dest.writeParcelable(this.definedRecordVO, flags);
    }

    public LessonPreparation() {
    }

    protected LessonPreparation(Parcel in) {
        this.templateVO = in.readParcelable(TemplateListBean.class.getClassLoader());
        this.definedRecordVO = in.readParcelable(ClassRecordTable.class.getClassLoader());
    }

    public static final Parcelable.Creator<LessonPreparation> CREATOR = new Parcelable.Creator<LessonPreparation>() {
        @Override
        public LessonPreparation createFromParcel(Parcel source) {
            return new LessonPreparation(source);
        }

        @Override
        public LessonPreparation[] newArray(int size) {
            return new LessonPreparation[size];
        }
    };
}
