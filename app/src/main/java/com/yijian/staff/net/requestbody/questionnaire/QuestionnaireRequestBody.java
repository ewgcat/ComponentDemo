package com.yijian.staff.net.requestbody.questionnaire;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/18 11:51:01
 */
public class QuestionnaireRequestBody {
    private int pageNum;
    private int pageSize;

    public QuestionnaireRequestBody(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
