package com.yijian.staff.mvp.base.mvp;

import android.content.Context;

import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.dagger.component.ActivityComponent;
import com.yijian.staff.dagger.component.DaggerActivityComponent;
import com.yijian.staff.dagger.module.ActivityModule;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public abstract class MvpBaseActivity<T extends BasePresenter> extends MvcBaseActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(CustomApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected abstract void initInject();
}