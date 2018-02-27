package com.yijian.staff.mvp.base;

import com.example.commonlibrary.bean.music.DaoSession;
import com.example.commonlibrary.data.DataManager;

import retrofit2.Retrofit;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/26 14:54:58
 */

public class AppRepositoryManager extends DataManager<DaoSession> {


    public AppRepositoryManager(Retrofit retrofit, DaoSession abstractDaoSession) {
        super(retrofit, abstractDaoSession);
    }


    @Override
    public <T> T getApi(Class<T> retrofitClass) {
        return super.getApi(retrofitClass);
    }
}
