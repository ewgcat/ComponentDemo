package com.yijian.staff.mvp.questionnaire;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/18 12:00:19
 */
public class QuestionNaireVipBean {


    /**
     * memberId : 01052a5565c64ed3b826dda2df3d31f7
     * memberName : 回访快过期会员23-0
     * createTime : null
     * sellerName : aa
     */

    private String memberId;
    private String memberName;
    private Long createTime;
    private String sellerName;

    public QuestionNaireVipBean(JSONObject jsonObject){
        this. memberId = JsonUtil.getString(jsonObject, "memberId");
        this. memberName = JsonUtil.getString(jsonObject, "memberName");
        this. sellerName = JsonUtil.getString(jsonObject, "sellerName");
        this. createTime = JsonUtil.getLong(jsonObject, "createTime");
    }


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
