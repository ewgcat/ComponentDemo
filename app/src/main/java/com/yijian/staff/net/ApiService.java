package com.yijian.staff.net;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/25 14:11:10
 */
public interface ApiService {

    //登录
    @FormUrlEncoded
    @POST
    Observable<JSONObject> login(@Url String url, @Body JSONObject body);


    //
    @GET
    Observable<JSONObject> get(@Url String url, @Query("userId") String userId);
}
