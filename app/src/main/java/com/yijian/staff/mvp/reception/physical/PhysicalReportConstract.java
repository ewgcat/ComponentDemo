package com.yijian.staff.mvp.reception.physical;

import com.yijian.staff.mvp.reception.step2.step2Bean.PhysicalExaminationBean;

/**
 * Created by The_P on 2018/4/4.
 */

public interface PhysicalReportConstract {
    interface View{
        void showUserData(PhysicalExaminationBean bean);
    }
    interface Presenter{
        void loadData(String memberId);
    }
}
