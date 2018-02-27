package com.yijian.staff.dagger.forgetpassword;

import com.example.commonlibrary.dagger.scope.PerActivity;
import com.yijian.staff.dagger.BaseAppComponent;
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.login.LoginActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = BaseAppComponent.class, modules = ForgetPasswordModule.class)
public interface ForgetPasswordComponent {
    public void inject(ForgetPasswordActivity forgetPasswordActivity);


}
