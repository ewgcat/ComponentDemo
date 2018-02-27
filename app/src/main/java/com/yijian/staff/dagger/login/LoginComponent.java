package com.yijian.staff.dagger.login;

import com.example.commonlibrary.dagger.scope.PerActivity;
import com.yijian.staff.dagger.BaseAppComponent;
import com.yijian.staff.mvp.login.LoginActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = BaseAppComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    public void inject(LoginActivity loginActivity);


}
