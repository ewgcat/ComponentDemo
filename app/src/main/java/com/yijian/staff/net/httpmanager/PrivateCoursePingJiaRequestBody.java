package com.yijian.staff.net.httpmanager;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/15 15:41:37
 */
public class PrivateCoursePingJiaRequestBody {


    /**
     * actionComplete : 0
     * actionEvaluate : 0
     * adaptStrength : 0
     * privateApplyId : string
     */

    private float actionComplete;
    private float actionEvaluate;
    private float adaptStrength;
    private String privateApplyId;

    public float getActionComplete() {
        return actionComplete;
    }

    public void setActionComplete(float actionComplete) {
        this.actionComplete = actionComplete;
    }

    public float getActionEvaluate() {
        return actionEvaluate;
    }

    public void setActionEvaluate(float actionEvaluate) {
        this.actionEvaluate = actionEvaluate;
    }

    public float getAdaptStrength() {
        return adaptStrength;
    }

    public void setAdaptStrength(float adaptStrength) {
        this.adaptStrength = adaptStrength;
    }

    public String getPrivateApplyId() {
        return privateApplyId;
    }

    public void setPrivateApplyId(String privateApplyId) {
        this.privateApplyId = privateApplyId;
    }
}
