package com.yijian.staff.mvp.main.presenter;

import android.content.Context;

import com.yijian.staff.mvp.base.mvp.RxPresenter;
import com.yijian.staff.mvp.main.contract.MainContract;

import javax.inject.Inject;


public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    private Context context;

    @Inject
    public MainPresenter() {

    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
    }



}
