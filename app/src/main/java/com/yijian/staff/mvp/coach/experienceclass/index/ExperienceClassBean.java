package com.yijian.staff.mvp.coach.experienceclass.index;

import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/19 10:21:41
 */
public class ExperienceClassBean implements Serializable {


    /**
     * cardprodVOs : [{"cardName":"string","cardType":"string","cardprodId":"string"}]卡名-类型
     courseNum (integer, optional): 上课次数 ,
     gender (integer, optional): 性别（0:未知 1:男 2:女） ,
     headPath (string, optional): 头像 ,
     memberName (string, optional): 会员名字 ,
     processId (string, optional): 体验课流程id ,
     status (integer, optional): 当前操作状态 ,
     statusDesc (string, optional): 当前操作状态描述
     */

    private int courseNum;
    private int gender;
    private String headPath;
    private String memberName;
    private String memberId;
    private String processId;
    private int status;
    private String statusDesc;
    private List<CardprodVOsBean> cardprodVOs;

    public String getMemberId() {
        return memberId;
    }

    public  ExperienceClassBean(JSONObject jsonObject) {
        try {

            this.headPath = jsonObject.getString("headPath");
            this.memberName = jsonObject.getString("memberName");
            this.memberId = jsonObject.getString("memberId");
            this.statusDesc = jsonObject.getString("statusDesc");
            this.processId = jsonObject.getString("processId");
            this.courseNum = jsonObject.getInt("courseNum");
            this.gender = jsonObject.getInt("gender");
            this.status = jsonObject.getInt("status");

            if (jsonObject.has("cardprodVOs")) {
                this.cardprodVOs = com.alibaba.fastjson.JSONObject.parseArray(jsonObject.getJSONArray("cardprodVOs").toString(), ExperienceClassBean.CardprodVOsBean.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public List<CardprodVOsBean> getCardprodVOs() {
        return cardprodVOs;
    }

    public void setCardprodVOs(List<CardprodVOsBean> cardprodVOs) {
        this.cardprodVOs = cardprodVOs;
    }

    public static class CardprodVOsBean {
        /**
         * cardName : string
         * cardType : string
         * cardprodId : string
         */

        private String cardName;
        private String cardType;
        private String cardprodId;


        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCardprodId() {
            return cardprodId;
        }

        public void setCardprodId(String cardprodId) {
            this.cardprodId = cardprodId;
        }
    }
}
