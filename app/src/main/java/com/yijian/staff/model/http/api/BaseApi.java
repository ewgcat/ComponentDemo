package com.yijian.staff.model.http.api;

import com.yijian.staff.model.bean.SplashBean;
import com.yijian.staff.model.bean.User;
import com.yijian.staff.model.http.response.BaseResponse;

import io.reactivex.Flowable;
import retrofit2.http.POST;

/**
 * 所有的接口定义在这里写
 */
public interface BaseApi {

    String HOST = "http://codeest.me/api/geeknews/";

    @POST("login")
    Flowable<BaseResponse<User>> login(String userName, String password);


    @POST("splash")
    Flowable<BaseResponse<SplashBean>> getSplashInfo();


}
