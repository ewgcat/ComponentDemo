package com.yijian.staff.mvp.reception.step2;

import com.yijian.staff.mvp.reception.step2.step2Bean.PhysicalExaminationBean;

/**
 * Created by The_P on 2018/4/3.
 */

public interface CoachReceptionStepTwoContract {
    interface View{
        void showSavaSucceed();

        void showUserData(PhysicalExaminationBean bean);
    }

    interface Presenter{
          void   saveTestData(PhysicalExaminationBean bean);

          void viewTestData();
    }
}
