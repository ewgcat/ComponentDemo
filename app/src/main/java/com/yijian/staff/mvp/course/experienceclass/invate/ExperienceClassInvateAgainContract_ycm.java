package com.yijian.staff.mvp.course.experienceclass.invate;

import com.yijian.staff.mvp.course.experienceclass.invate.bean.InvateBean;

/**
 * Created by The_P on 2018/4/16.
 */

public interface ExperienceClassInvateAgainContract_ycm {
    interface View {
        void showSendSucceed();
    }

    interface Presenter {
        void saveAndSendInvite(InvateBean bean);
    }

}
