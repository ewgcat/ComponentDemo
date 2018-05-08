package com.yijian.staff.bean;

import com.alibaba.fastjson.JSONArray;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/22 20:04:14
 */
public class CoachViperBean implements Serializable {

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
    private String name;
    private String sex;
    private int dictItemKey;
    private String headImg;
    private String viperRole;
    private boolean underProtected;

    public CoachViperBean(JSONObject jsonObject) {
        this.name = JsonUtil.getString(jsonObject, "name");
        this.viperRole = JsonUtil.getString(jsonObject, "viperRole");
        this.sex = JsonUtil.getString(jsonObject, "sex");
        this.memberId = JsonUtil.getString(jsonObject, "memberId");
        this.headImg = JsonUtil.getString(jsonObject, "headImg");
        this.subclassName = JsonUtil.getString(jsonObject, "subclassName");
        this.underProtected = JsonUtil.getBoolean(jsonObject, "underProtected");

    }


}
