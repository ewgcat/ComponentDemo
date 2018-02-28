package com.yijian.staff.dagger.module;

import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.model.DataManager;
import com.yijian.staff.model.http.httphelper.HttpHelper;
import com.yijian.staff.model.http.httphelper.RetrofitHelper;
import com.yijian.staff.model.prefs.ImplPreferencesHelper;
import com.yijian.staff.model.prefs.PreferencesHelper;

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

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper,  preferencesHelper);
    }
}
