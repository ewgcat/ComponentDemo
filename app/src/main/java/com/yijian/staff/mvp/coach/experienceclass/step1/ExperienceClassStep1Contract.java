package com.yijian.staff.mvp.coach.experienceclass.step1;

import com.yijian.staff.mvp.coach.experienceclass.step1.bean.InviterBean;

/**
 * Created by The_P on 2018/4/14.
 */

public interface ExperienceClassStep1Contract {
    interface View{

        void showInviterInfo(InviterBean inviterBean);
    }

    interface Presenter{
        void getInviterInfo(String processId);

    }
}
