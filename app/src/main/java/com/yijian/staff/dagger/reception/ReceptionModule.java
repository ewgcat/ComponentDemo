package com.yijian.staff.dagger.reception;

import com.yijian.staff.mvp.base.AppRepositoryManager;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.ReceptionModel;
import com.yijian.staff.mvp.reception.ReceptionPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ReceptionModule {
    private ReceptionActivity receptionActivity;

    public ReceptionModule(ReceptionActivity receptionActivity) {
        this.receptionActivity = receptionActivity;
    }


    @Provides
    public ReceptionPresenter provideReceptionPresenter(ReceptionModel receptionModel) {
        return new ReceptionPresenter(receptionActivity, receptionModel);
    }

    @Provides
    public ReceptionModel provideReceptionModel(AppRepositoryManager appRepositoryManager) {
        return new ReceptionModel(appRepositoryManager);
    }
}
