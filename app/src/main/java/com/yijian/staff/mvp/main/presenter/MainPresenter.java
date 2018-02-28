package com.yijian.staff.mvp.main.presenter;

import com.yijian.staff.model.DataManager;
import com.yijian.staff.viewmodel.base.RxPresenter;
import com.yijian.staff.mvp.main.contract.MainContract;

import javax.inject.Inject;


public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
    }



}
