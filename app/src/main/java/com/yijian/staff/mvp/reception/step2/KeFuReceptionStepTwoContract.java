package com.yijian.staff.mvp.reception.step2;

/**
 * Created by The_P on 2018/4/4.
 */

public interface KeFuReceptionStepTwoContract {
    interface View {

        void showJumpBodyCheck();

        void showCoachBodyCheck();
    }

    interface Presenter {
        //        sale-jump-body-check
        void jumpBodyCheck(String memberId);

        void coachBodyCheck(String memberId);

    }
}
