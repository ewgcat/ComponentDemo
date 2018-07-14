package com.yijian.staff.mvp.course.experienceclass.step4;

import com.yijian.staff.mvp.course.experienceclass.step4.bean.InvitationAgainBean;

/**
 * Created by The_P on 2018/4/16.
 */

public interface ExperienceClassProcess4Contract {
    interface View {

        void showClassRecordList(InvitationAgainBean bean);
    }

    interface Presenter {
        void getClassRecordList(String processId);


    }
}
