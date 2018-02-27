package com.yijian.staff.dagger.login;

import com.yijian.staff.mvp.base.AppRepositoryManager;
import com.yijian.staff.mvp.login.LoginModel;
import com.yijian.staff.mvp.login.LoginActivity;
import com.yijian.staff.mvp.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    private LoginActivity loginActivity;

    public LoginModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }


    @Provides
    public LoginPresenter provideLoginPresenter(LoginModel loginModel) {
        return new LoginPresenter(loginActivity, loginModel);
    }

    @Provides
    public LoginModel provideLoginModel(AppRepositoryManager appRepositoryManager) {
        return new LoginModel(appRepositoryManager);
    }
}
