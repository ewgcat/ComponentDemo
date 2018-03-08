package com.yijian.staff.mvp.invitation;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * Created by yangk on 2018/3/8.
 */

public class InvitationInfo {

    private String headerUrl;
    private String name;
    private String cardName;
    private String rightsAndInterests;
    private String overTime;
    private String overReason;
    private String invitationTime;
    private String invitationContent;
    private String invitationType;
    private String invitationResult;


    public InvitationInfo(){}
    public InvitationInfo(JSONObject jsonObject){
        this.headerUrl = JsonUtil.getString(jsonObject,"headerUrl");
        this.name = JsonUtil.getString(jsonObject,"name");
        this.cardName = JsonUtil.getString(jsonObject,"cardName");
        this.rightsAndInterests = JsonUtil.getString(jsonObject,"rightsAndInterests");
        this.overTime = JsonUtil.getString(jsonObject,"overTime");
        this.overReason = JsonUtil.getString(jsonObject,"overReason");
        this.invitationTime = JsonUtil.getString(jsonObject,"invitationTime");
        this.invitationContent = JsonUtil.getString(jsonObject,"invitationContent");
        this.invitationType = JsonUtil.getString(jsonObject,"invitationType");
        this.invitationResult = JsonUtil.getString(jsonObject,"invitationResult");

    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getRightsAndInterests() {
        return rightsAndInterests;
    }

    public void setRightsAndInterests(String rightsAndInterests) {
        this.rightsAndInterests = rightsAndInterests;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public String getOverReason() {
        return overReason;
    }

    public void setOverReason(String overReason) {
        this.overReason = overReason;
    }

    public String getInvitationTime() {
        return invitationTime;
    }

    public void setInvitationTime(String invitationTime) {
        this.invitationTime = invitationTime;
    }

    public String getInvitationContent() {
        return invitationContent;
    }

    public void setInvitationContent(String invitationContent) {
        this.invitationContent = invitationContent;
    }

    public String getInvitationType() {
        return invitationType;
    }

    public void setInvitationType(String invitationType) {
        this.invitationType = invitationType;
    }

    public String getInvitationResult() {
        return invitationResult;
    }

    public void setInvitationResult(String invitationResult) {
        this.invitationResult = invitationResult;
    }
}
