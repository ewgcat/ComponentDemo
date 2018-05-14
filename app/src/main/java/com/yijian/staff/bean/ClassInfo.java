package com.yijian.staff.bean;

import android.support.annotation.NonNull;

import com.yijian.staff.mvp.huiji.goodsbaojia.bean.CardInfo;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/17 19:29:17
 */
public class ClassInfo implements Comparable<ClassInfo>{

    @Override
    public String toString() {
        return "ClassInfo{" +
                "lessonId='" + lessonId + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", cleassNum='" + cleassNum + '\'' +
                ", lessonTime='" + lessonTime + '\'' +
                ", baseTotalPrice='" + baseTotalPrice + '\'' +
                '}';
    }

    /**
     * lessonId : 1
     * name : 瘦身课
     * img :
     * cleassNum : 10
     * lessonTime : 120
     * baseTotalPrice : 500
     */

    private String lessonId;
    private String name;
    private String img;
    private String cleassNum;
    private String lessonTime;
    private String baseTotalPrice;


    public ClassInfo(JSONObject jsonObject){
        this.name=  JsonUtil.getString(jsonObject,"name");
        this.cleassNum=  JsonUtil.getString(jsonObject,"cleassNum");
        this.lessonTime=  JsonUtil.getString(jsonObject,"lessonTime");
        this.lessonId=  JsonUtil.getString(jsonObject,"lessonId");
        this.img=  JsonUtil.getString(jsonObject,"img");
        this.baseTotalPrice=  JsonUtil.getInt(jsonObject,"baseTotalPrice")+"";
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCleassNum() {
        return cleassNum;
    }

    public void setCleassNum(String cleassNum) {
        this.cleassNum = cleassNum;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getBaseTotalPrice() {
        return baseTotalPrice;
    }

    public void setBaseTotalPrice(String baseTotalPrice) {
        this.baseTotalPrice = baseTotalPrice;
    }

    @Override
    public int compareTo(@NonNull ClassInfo o) {
        int i1 = Integer.parseInt(this.baseTotalPrice);
        int i2 = Integer.parseInt(o.baseTotalPrice);
        int i = i1-i2;//先按照年龄排序
        return i;
    }
}
