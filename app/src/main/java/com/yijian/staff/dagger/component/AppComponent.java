package com.yijian.staff.dagger.component;

import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class,})
public interface AppComponent {

    CustomApplication getContext();  // 提供App的Context

}
