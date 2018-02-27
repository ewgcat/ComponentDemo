package com.example.commonlibrary.net.okhttpconfig;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.module.LibraryGlideModule;
import com.example.commonlibrary.BaseApplication;
import com.example.commonlibrary.utils.CommonLogger;

import java.io.InputStream;

import okhttp3.OkHttpClient;


public class GlideConfig extends LibraryGlideModule {



    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(BaseApplication.getAppComponent().getOkHttpClient()));
    }
}
