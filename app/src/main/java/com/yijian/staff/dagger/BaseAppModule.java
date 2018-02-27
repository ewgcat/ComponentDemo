package com.yijian.staff.dagger;


import android.support.annotation.Nullable;

import com.example.commonlibrary.bean.music.DaoSession;
import com.example.commonlibrary.dagger.scope.PerApplication;
import com.example.commonlibrary.utils.ConstantUtil;
import com.example.commonlibrary.utils.SharePreferenceManager;

import com.google.gson.Gson;
import com.yijian.staff.mvp.base.AppRepositoryManager;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


@Module
public class BaseAppModule {

    @Provides
    @PerApplication
    public AppRepositoryManager provideRepositoryManager( Retrofit retrofit, DaoSession daoSession) {
        return new AppRepositoryManager(retrofit, daoSession);
    }


}
