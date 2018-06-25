package com.yijian.staff.mvp.coach.experienceclass.step2;

import com.yijian.staff.mvp.coach.experienceclass.step2.bean.AccessRecordBean;

/**
 * Created by The_P on 2018/4/16.
 */

public interface ExperienceClassProcess2Contract {
    interface View {
        void showAccessRecord(AccessRecordBean accessRecordBean);

        void showSavaSucceed();
    }

    interface Presenter {
        void getAccessRecord(String processId);

        void postCoachAccessRecord(AccessRecordBean bean);
    }
}
