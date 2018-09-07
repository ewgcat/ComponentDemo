package com.yijian.staff.net.requestbody;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/15 15:41:37
 */
public class PrivateCoursePingJiaRequestBody {


    /**
     * 约课--教练评价 {
     actionComplete (number, optional): 动作完成度百分比值 (取值0~1) ,
     actionEvaluate (number, optional): 动作评估百分比值 (取值0~1) ,
     adaptStrength (number, optional): 适应强度百分比值 (取值0~1) ,
     privateApplyId (string, optional): 约课id
     }
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
