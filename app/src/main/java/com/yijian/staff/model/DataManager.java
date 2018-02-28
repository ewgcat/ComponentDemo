package com.yijian.staff.model;

import com.yijian.staff.model.bean.SplashBean;
import com.yijian.staff.model.bean.User;
import com.yijian.staff.model.http.httphelper.HttpHelper;

import com.yijian.staff.model.http.response.BaseResponse;
import com.yijian.staff.model.prefs.PreferencesHelper;

import io.reactivex.Flowable;


public class DataManager implements HttpHelper, PreferencesHelper {

    HttpHelper mHttpHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mPreferencesHelper = preferencesHelper;
    }

    //http 请求
    @Override
    public Flowable<BaseResponse<User>> login(String userName, String password) {
        return null;
    }



    @Override
    public Flowable<BaseResponse<SplashBean>> getSplashInfo() {
        return mHttpHelper.getSplashInfo();
    }


}
