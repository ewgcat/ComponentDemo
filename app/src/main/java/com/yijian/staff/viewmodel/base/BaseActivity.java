package com.yijian.staff.viewmodel.base;

import android.support.v7.app.AppCompatDelegate;
import android.view.ViewGroup;

import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.dagger.component.ActivityComponent;
import com.yijian.staff.dagger.component.DaggerActivityComponent;
import com.yijian.staff.dagger.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by codeest on 2016/8/2.
 * MVP activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(CustomApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }



    protected abstract void initInject();
}