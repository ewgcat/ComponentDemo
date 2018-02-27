package com.example.news;


import com.example.commonlibrary.bean.music.DaoSession;
import com.example.commonlibrary.data.DataManager;
import com.example.commonlibrary.utils.SharePreferenceManager;

import retrofit2.Retrofit;

/**
 * Created by COOTEK on 2017/8/11.
 */

public class MainRepositoryManager extends DataManager<DaoSession> {


    public MainRepositoryManager(Retrofit retrofit, DaoSession abstractDaoSession) {
        super(retrofit, abstractDaoSession);
    }


    @Override
    public <T> T getApi(Class<T> retrofitClass) {
        return super.getApi(retrofitClass);
    }
}
