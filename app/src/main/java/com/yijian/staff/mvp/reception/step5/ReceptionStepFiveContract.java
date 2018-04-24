package com.yijian.staff.mvp.reception.step5;

import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;

/**
 * Created by The_P on 2018/4/23.
 */

public interface ReceptionStepFiveContract {
    interface View{

        void showStatus(ReceptionStastuBean receptionStastuBean);

        void ShowEndProcess();
    }

    interface Presenter{

        void getStatus(boolean isFirst);

        void endProcess(String memberId);
    }
}
