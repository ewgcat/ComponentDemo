package com.yijian.staff.dagger.module;

import com.yijian.staff.application.CustomApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;



@Module
public class AppModule {
    private final CustomApplication application;

    public AppModule(CustomApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    CustomApplication provideApplicationContext() {
        return application;
    }


}
