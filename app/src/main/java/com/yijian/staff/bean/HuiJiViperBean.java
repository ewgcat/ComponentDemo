package com.yijian.staff.bean;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by yangk on 2018/3/29.
 */

public class HuiJiViperBean implements Serializable {

    /**
     * CoachExpireVO：教练过期
     * CoachInfoVO ：教练正式
     * CoachIntentionVO：教练意向
     * CoachTodayVisitVO：教练今日来访
     * CustomerInfoVO：会籍正式
     * CustomerTodayVisitVO：会籍今日来访
     * CustomerExpireVO：会籍过期
     * CustomerIntentionVO：会籍意向
     * PotentialVO：潜在（会籍教练共用）
     */
    private String subclassName;
    /**
     * memberId : 503634cd51734962a11463c87a0526a6
     * name : 回访过期会员0-0
     * sex : 1
     * dictItemKey : 17
     * headImg : 值0
     * viperRole : 教练-正式会员
     * underProtected : false
     */

    private String memberId;
    private String mobile;
    private String name;
    private int sex;
    private int protectedDay;
    private int medalType;
    private int dictItemKey;
    private String headImg;
    private String viperRole;
    private boolean underProtected;

    public HuiJiViperBean(JSONObject jsonObject) {
        this.name = JsonUtil.getString(jsonObject, "name");
        this.viperRole = JsonUtil.getString(jsonObject, "viperRole");
        this.sex = JsonUtil.getInt(jsonObject, "sex");
        this.medalType = JsonUtil.getInt(jsonObject, "medalType");
        this.protectedDay = JsonUtil.getInt(jsonObject, "protectedDay");
        this.dictItemKey = JsonUtil.getInt(jsonObject, "dictItemKey");
        this.memberId = JsonUtil.getString(jsonObject, "memberId");
        this.headImg = BuildConfig.FILE_HOST + JsonUtil.getString(jsonObject, "headImg");
        this.subclassName = JsonUtil.getString(jsonObject, "subclassName");
        this.underProtected = JsonUtil.getBoolean(jsonObject, "underProtected");
        this.mobile = JsonUtil.getString(jsonObject, "mobile");

    }

    public int getProtectedDay() {
        return protectedDay;
    }



    public int getMedalType() {
        return medalType;
    }

    public void setMedalType(int medalType) {
        this.medalType = medalType;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSubclassName() {
        return subclassName;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public int getDictItemKey() {
        return dictItemKey;
    }

    public String getHeadImg() {
        return headImg;
    }

    public String getViperRole() {
        return viperRole;
    }

    public boolean isUnderProtected() {
        return underProtected;
    }

}
