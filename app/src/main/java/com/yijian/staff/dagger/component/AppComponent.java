package com.yijian.staff.dagger.component;

import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.dagger.module.AppModule;
import com.yijian.staff.dagger.module.HttpModule;
import com.yijian.staff.model.DataManager;
import com.yijian.staff.model.http.httphelper.RetrofitHelper;
import com.yijian.staff.model.prefs.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    CustomApplication getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
