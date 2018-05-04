package com.yijian.staff.mvp.coach.experienceclass.invate_ycm;

import com.yijian.staff.mvp.coach.experienceclass.step1.bean.InviterBean;

/**
 * Created by The_P on 2018/4/24.
 */

public interface ExperienceStatusLisenter {
    void step1ToStep2();
    void step2ToStep3();
    void step3ToStep4();
    void step4ToStep5();

    void toInvateAgainActivity(InviterBean prepareRecord);
}
