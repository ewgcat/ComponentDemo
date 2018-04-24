package com.yijian.staff.mvp.reception.step3;

/**
 * Created by The_P on 2018/4/12.
 */

public interface ReceptionStepThreeContract {
    interface View{
        void leaderToSaleSecceed();
        void coachToSaleSecceed();
    }

    interface Presenter{
        void leaderToSale(String memberId);
        void coachToSale(String memberId);

//        void searchStatus();
    }
}
