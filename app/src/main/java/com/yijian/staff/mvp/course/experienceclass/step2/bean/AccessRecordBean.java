package com.yijian.staff.mvp.course.experienceclass.step2.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by The_P on 2018/4/16.
 */

public class AccessRecordBean implements Parcelable {
    public AccessRecordBean(String processId, String mobile, String coachVisitRecord, String sellerVisitRecord) {
        this.processId = processId;
        this.mobile = mobile;
        this.coachVisitRecord = coachVisitRecord;
        this.sellerVisitRecord = sellerVisitRecord;
    }

    /**
     * processId : 1792249a8d0442dfbb713337be249f4e
     * mobile :
     * coachVisitRecord : null
     * sellerVisitRecord : null
     */


    private String processId;
    private String mobile;
    private String coachVisitRecord;
    private String sellerVisitRecord;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCoachVisitRecord() {
        return coachVisitRecord;
    }

    public void setCoachVisitRecord(String coachVisitRecord) {
        this.coachVisitRecord = coachVisitRecord;
    }

    public String getSellerVisitRecord() {
        return sellerVisitRecord;
    }

    public void setSellerVisitRecord(String sellerVisitRecord) {
        this.sellerVisitRecord = sellerVisitRecord;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.processId);
        dest.writeString(this.mobile);
        dest.writeString(this.coachVisitRecord);
        dest.writeString(this.sellerVisitRecord);
    }

    public AccessRecordBean() {
    }

    protected AccessRecordBean(Parcel in) {
        this.processId = in.readString();
        this.mobile = in.readString();
        this.coachVisitRecord = in.readString();
        this.sellerVisitRecord = in.readString();
    }

    public static final Parcelable.Creator<AccessRecordBean> CREATOR = new Parcelable.Creator<AccessRecordBean>() {
        @Override
        public AccessRecordBean createFromParcel(Parcel source) {
            return new AccessRecordBean(source);
        }

        @Override
        public AccessRecordBean[] newArray(int size) {
            return new AccessRecordBean[size];
        }
    };
}
