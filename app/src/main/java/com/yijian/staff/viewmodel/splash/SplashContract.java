package com.yijian.staff.viewmodel.splash;


import com.yijian.staff.model.bean.SplashBean;
import com.yijian.staff.viewmodel.base.BasePresenter;
import com.yijian.staff.viewmodel.base.BaseView;

public interface SplashContract {

    interface View extends BaseView {

        void showSplashView(SplashBean splashBean);

        void checkPremession();

    }

    interface Presenter extends BasePresenter<SplashContract.View> {


        void getSplashInfo();



    }
}
