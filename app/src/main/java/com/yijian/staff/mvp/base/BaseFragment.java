package com.yijian.staff.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.dagger.component.DaggerFragmentComponent;
import com.yijian.staff.dagger.component.FragmentComponent;
import com.yijian.staff.dagger.module.FragmentModule;

import javax.inject.Inject;

/**
 * Created by codeest on 2016/8/2.
 * MVP Fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(CustomApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }



    protected abstract void initInject();
}