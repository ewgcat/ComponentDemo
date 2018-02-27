package com.example.commonlibrary.data;


import android.preference.PreferenceManager;

import com.example.commonlibrary.utils.SharePreferenceManager;

import org.greenrobot.greendao.AbstractDaoSession;

public interface IDataManager<D extends AbstractDaoSession> {



    D getDaoSession();
    SharePreferenceManager getSharePreferenceManager();
    <T> T getApi(Class<T> retrofitClass);
    <T> void clearApi(Class<T> retrofitClass);
    void clearAllCache();


}
