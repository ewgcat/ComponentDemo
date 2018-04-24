package com.yijian.staff.mvp.reception.reception_step_ycm;

/**
 * Created by The_P on 2018/4/22.
 */

public interface ReceptionStatusChange {
    void ReceptionStep1RequestionSaved();//问卷保存完成
    void ReceptionStep1ToStep2();//step1 下一步

    void ReceptionStep2SaleNoticeCoached();//通知教练录入体测
    void ReceptionStep2SaleSkipCoach();//跳过录入体测
    void ReceptionStep2ToStep3();//step2 下一步

    void ReceptionStep3SaleNoticeCoached();//产品报价TO给教练来完成
    void ReceptionStep3CoachMakeADeal();//教练完成产品报价
    void ReceptionStep3CoachNoticeLeader();//产品报价教练TO给领导完成
    void ReceptionStep3LeaderMakeADeal();//leader完成产品报价
    void ReceptionStep3SaleToStep4(Integer operatorType);//step3 进入step4

    void ReceptionStep4SaleToStep5(Integer operatorType);//step4 进入step5

    void ReceptionCompleted(Integer operatorType);//整个接待流程完成


    void ReceptionStep5Back();
    void ReceptionStep4Back();
    void ReceptionStep3Back();
    void ReceptionStep2Back();
    void ReceptionStep1Back();
}
