package com.yijian.staff.dagger.component;

import android.app.Activity;

import com.yijian.staff.dagger.scope.ActivityScope;
import com.yijian.staff.dagger.module.ActivityModule;
import com.yijian.staff.mvp.user.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.user.login.LoginActivity;
import com.yijian.staff.mvp.main.MainActivity;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.splash.SplashActivity;


import dagger.Component;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();


    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(ForgetPasswordActivity forgetPasswordActivity);

    void inject(ReceptionActivity receptionActivity);

}
