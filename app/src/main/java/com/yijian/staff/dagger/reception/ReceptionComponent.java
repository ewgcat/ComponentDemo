package com.yijian.staff.dagger.reception;

import com.example.commonlibrary.dagger.scope.PerActivity;
import com.yijian.staff.dagger.BaseAppComponent;
import com.yijian.staff.mvp.login.LoginActivity;
import com.yijian.staff.mvp.reception.ReceptionActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = BaseAppComponent.class, modules = ReceptionModule.class)
public interface ReceptionComponent {
    public void inject(ReceptionActivity receptionActivity);


}
