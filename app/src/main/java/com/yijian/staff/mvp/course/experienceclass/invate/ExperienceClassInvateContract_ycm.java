package com.yijian.staff.mvp.course.experienceclass.invate;

import com.yijian.staff.bean.InviterBean;

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
