package com.yijian.staff.mvp.message.business;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/12 11:08:32
 */
public class BusinessMessageBean {


    /**
     * id
     */
    private String id;
    /**
     * 消息名称
     */
    private String name;
    /**
     * 业务类型代码（如停课、延期）
     */
    private Integer type;
    /**
     * 业务类型名称（如停课、延期）
     */
    private String typeName;
    /**
     * 头像url
     */
    private String memberHeadPortrait;
    /**
     * 会员id
     */
    private String memberId;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 创建时间
     */
    private String createTime;


    public BusinessMessageBean(JSONObject jsonObject){

        this. id = JsonUtil.getString(jsonObject, "id");
        this. name = JsonUtil.getString(jsonObject, "name");
        this. typeName = JsonUtil.getString(jsonObject, "typeName");
        this. memberHeadPortrait = JsonUtil.getString(jsonObject, "memberHeadPortrait");
        this. memberId = JsonUtil.getString(jsonObject, "memberId");
        this. memberName = JsonUtil.getString(jsonObject, "memberName");
        this. createTime = JsonUtil.getString(jsonObject, "createTime");
        this. type = JsonUtil.getInt(jsonObject, "type");

    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getMemberHeadPortrait() {
        return memberHeadPortrait;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getCreateTime() {
        return createTime;
    }
}
