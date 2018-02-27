package com.example.commonlibrary.data;

import android.preference.PreferenceManager;

import com.example.commonlibrary.utils.SharePreferenceManager;

import org.greenrobot.greendao.AbstractDaoSession;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;



public class DataManager<D extends AbstractDaoSession> implements IDataManager<D> {
    protected Retrofit retrofit;
    private D daoSession;
    private Map<String, Object> stringRetrofitMap;

    public DataManager(Retrofit retrofit, D abstractDaoSession  ) {
        this.retrofit = retrofit;
        this.daoSession = abstractDaoSession;
        stringRetrofitMap = new HashMap<>();
    }

    @Override
    public SharePreferenceManager getSharePreferenceManager(){
        return SharePreferenceManager.getInstance();
    }


    @Override
    public D getDaoSession() {
        return daoSession;
    }



    @Override
    public <T> T getApi(Class<T> retrofitClass) {
        T result = null;
        if (stringRetrofitMap != null) {
            synchronized (stringRetrofitMap) {
                result = (T) stringRetrofitMap.get(retrofitClass.getName());
                if (result == null) {
                    result = retrofit.create(retrofitClass);
                    stringRetrofitMap.put(retrofitClass.getName(), result);
                }
            }
        }
        return result;
    }

    @Override
    public <T> void clearApi(Class<T> retrofitClass) {
        if (stringRetrofitMap != null) {
            synchronized (stringRetrofitMap) {
                if (stringRetrofitMap.containsKey(retrofitClass.getName())) {
                    stringRetrofitMap.remove(retrofitClass.getName());
                }
            }
        }
    }
    @Override
    public void clearAllCache() {
        if (stringRetrofitMap != null) {
            synchronized (stringRetrofitMap) {
                stringRetrofitMap.clear();
            }
        }

    }
}
