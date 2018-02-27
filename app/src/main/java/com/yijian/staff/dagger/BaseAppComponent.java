package com.yijian.staff.dagger;

import com.example.commonlibrary.dagger.component.AppComponent;
import com.example.commonlibrary.dagger.scope.PerApplication;
import com.yijian.staff.mvp.base.AppRepositoryManager;

import dagger.Component;


@PerApplication
@Component(dependencies = AppComponent.class,modules =BaseAppModule.class)
public interface BaseAppComponent {
    public AppRepositoryManager getDataManager();
}
