package com.yijian.staff.net.httpmanager;


import android.content.Context;

import com.yijian.staff.net.api.ApiService;

import org.json.JSONObject;



import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HttpManager {

    private static ApiService apiService = RetrofitClient.mRetrofit.create(ApiService.class);

    public static ApiService getApiService() {
        return apiService;
    }




    //公用方法
    private static <T> void execute(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void postLogin(JSONObject jsonObject, Observer<JSONObject> observer) {
        Observable<JSONObject> loginObservable = apiService.login(jsonObject.toString());
    }


}