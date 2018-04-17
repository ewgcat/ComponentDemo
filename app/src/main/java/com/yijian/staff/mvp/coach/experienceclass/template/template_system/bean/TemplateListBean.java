package com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/4/13.
 */

public  class TemplateListBean implements Parcelable {
    @Override
    public String toString() {
        return "TemplateListBean{" +
                "templateId='" + templateId + '\'' +
                ", templateName='" + templateName + '\'' +
                ", noInstrumentList=" + noInstrumentList +
                ", aerobicsList=" + aerobicsList +
                ", powerList=" + powerList +
                '}';
    }

    /**
     * templateId : 1
     * templateName : 体验课测试模板
     * noInstrumentList : [{"templateContextId":"1","contextType":1,"sort":1,"name":"台阶测试","groupNum":2,"groupTime":60},{"templateContextId":"2","contextType":1,"sort":2,"name":"卷腹测试","groupNum":3,"groupTime":60},{"templateContextId":"3","contextType":1,"sort":3,"name":"俯卧撑测试","groupNum":1,"groupTime":30}]
     * aerobicsList : [{"templateContextId":"5","contextType":2,"sort":1,"name":"健身单车","grade":"S级","time":600},{"templateContextId":"4","contextType":2,"sort":2,"name":"跑步机","grade":"A级","time":600}]
     * powerList : [{"templateContextId":"7","contextType":3,"sort":1,"name":"史密斯平板推胸","groupNum":2,"groupTime":10,"weight":50},{"templateContextId":"8","contextType":3,"sort":2,"name":"推荐训练机","groupNum":3,"groupTime":10,"weight":30},{"templateContextId":"6","contextType":3,"sort":3,"name":"推胸训练机","groupNum":2,"groupTime":20,"weight":100}]
     */

    private String templateId;
    private String templateName;
    private List<NoInstrumentListBean> noInstrumentList;
    private List<AerobicsListBean> aerobicsList;
    private List<PowerListBean> powerList;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<NoInstrumentListBean> getNoInstrumentList() {
        return noInstrumentList;
    }

    public void setNoInstrumentList(List<NoInstrumentListBean> noInstrumentList) {
        this.noInstrumentList = noInstrumentList;
    }

    public List<AerobicsListBean> getAerobicsList() {
        return aerobicsList;
    }

    public void setAerobicsList(List<AerobicsListBean> aerobicsList) {
        this.aerobicsList = aerobicsList;
    }

    public List<PowerListBean> getPowerList() {
        return powerList;
    }

    public void setPowerList(List<PowerListBean> powerList) {
        this.powerList = powerList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.templateId);
        dest.writeString(this.templateName);
        dest.writeList(this.noInstrumentList);
        dest.writeList(this.aerobicsList);
        dest.writeList(this.powerList);
    }

    public TemplateListBean() {
    }

    protected TemplateListBean(Parcel in) {
        this.templateId = in.readString();
        this.templateName = in.readString();
        this.noInstrumentList = new ArrayList<NoInstrumentListBean>();
        in.readList(this.noInstrumentList, NoInstrumentListBean.class.getClassLoader());
        this.aerobicsList = new ArrayList<AerobicsListBean>();
        in.readList(this.aerobicsList, AerobicsListBean.class.getClassLoader());
        this.powerList = new ArrayList<PowerListBean>();
        in.readList(this.powerList, PowerListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TemplateListBean> CREATOR = new Parcelable.Creator<TemplateListBean>() {
        @Override
        public TemplateListBean createFromParcel(Parcel source) {
            return new TemplateListBean(source);
        }

        @Override
        public TemplateListBean[] newArray(int size) {
            return new TemplateListBean[size];
        }
    };
}