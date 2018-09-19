package com.yijian.staff.net.api;

import com.yijian.staff.net.httpmanager.login.LoginRequestBody;


import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import retrofit2.http.Url;

/**
 * 所有的接口定义在这里写
 */
public interface ApiService {



    //登录
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> login(@Url String url, @Body LoginRequestBody loginRequest);


    @GET
    @Headers({"Content-type: application/json", "Accept: */*"})
    Observable<JSONObject> getHasHeaderNoParam(@Url String url, @HeaderMap Map<String, String> headers);



}
