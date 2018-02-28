package com.yijian.staff.mvp.splash;

import android.Manifest;
import android.app.Activity;

import com.yijian.staff.rx.RxUtil;
import com.yijian.staff.mvp.base.RxPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;



public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter{

    String[] permissions = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private int index = 0;

    private static final int COUNT_DOWN_TIME = 2200;

    Activity activity;
    @Inject
    public SplashPresenter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void attachView(SplashContract.View view) {
        super.attachView(view);
    }


    @Override
    public void getSplashInfo() {

    }



    private void startCountDown() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxFlowableSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        mView.checkPremession();
                    }
                })
        );
    }

}
