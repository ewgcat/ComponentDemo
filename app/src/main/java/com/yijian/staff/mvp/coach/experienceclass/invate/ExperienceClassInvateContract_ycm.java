package com.yijian.staff.mvp.coach.experienceclass.invate;

import com.yijian.staff.mvp.coach.experienceclass.step1.bean.InviterBean;

/**
 * Created by The_P on 2018/4/16.
 */

public interface ExperienceClassInvateContract_ycm {
    interface View {
        void showInviterInfo(InviterBean inviterBean);
    }

    interface Presenter {
        void getInviterInfo(String memberId);
    }

}
