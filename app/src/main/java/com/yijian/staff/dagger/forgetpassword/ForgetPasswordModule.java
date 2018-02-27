package com.yijian.staff.dagger.forgetpassword;

import com.yijian.staff.mvp.base.AppRepositoryManager;
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordModel;
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordPresenter;
import com.yijian.staff.mvp.login.LoginActivity;
import com.yijian.staff.mvp.login.LoginModel;
import com.yijian.staff.mvp.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ForgetPasswordModule {
    private ForgetPasswordActivity forgetPasswordActivity;

    public ForgetPasswordModule(ForgetPasswordActivity forgetPasswordActivity) {
        this.forgetPasswordActivity = forgetPasswordActivity;
    }


    @Provides
    public ForgetPasswordPresenter provideForgetPasswordPresenter(ForgetPasswordModel forgetPasswordModel) {
        return new ForgetPasswordPresenter(forgetPasswordActivity, forgetPasswordModel);
    }

    @Provides
    public ForgetPasswordModel provideForgetPasswordModel(AppRepositoryManager appRepositoryManager) {
        return new ForgetPasswordModel(appRepositoryManager);
    }
}
