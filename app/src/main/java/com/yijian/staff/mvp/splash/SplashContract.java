package com.yijian.staff.mvp.splash;


import com.yijian.staff.mvp.base.BasePresenter;
import com.yijian.staff.mvp.base.BaseView;

public interface SplashContract {

    interface View extends BaseView {


        void checkPremession();

    }

    interface Presenter extends BasePresenter<SplashContract.View> {


        void getSplashInfo();



    }
}
