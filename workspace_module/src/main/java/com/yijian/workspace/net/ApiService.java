package com.yijian.workspace.net;


import com.yijian.workspace.bean.DynamicRequestBody;
import com.yijian.workspace.bean.LoginRequestBody;
import com.yijian.workspace.bean.PerfectRequestBody;
import com.yijian.workspace.bean.SportStepRequedtBody;
import com.yijian.workspace.bean.StaticRequestBody;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


/**
 * 所有的接口定义在这里写
 */
public interface ApiService {


    /*GET 请求 下载*/
    @Streaming
    @GET
    Observable<ResponseBody> downloadFileGET(
            @Url String fileUrl
    );

    /*POST 请求 下载*/
    @Streaming
    @POST
    Observable<ResponseBody> downloadFilePOST(
            @Url String fileUrl
    );


    /*POST 请求 上传单个文件*/
    @Multipart
    @POST()
    Observable<JSONObject> upLoadImage(
            @Url String url,
            @Part() MultipartBody.Part file
    );

    /*POST 请求 上传单个文件*/
    @Multipart
    @POST()
    Observable<JSONObject> upLoadImageHasParam(
            @Url String url,
            @HeaderMap Map<String, String> headers,
            @Query("fileType") Integer param,
            @Part() List<MultipartBody.Part> parts
    );

    /*POST 请求 上传文件*/

    @Multipart
    @POST()
    Observable<JSONObject> uploadFiles(@Url String url, @HeaderMap Map<String, String> headers, @Query("fileType") String param, @Part() List<MultipartBody.Part> parts);



    //post 表单
    @POST
    Observable<JSONObject> postNoHeaderNoParam(@Url String url);

    @POST
    Observable<JSONObject> postHasHeaderNoParam(@Url String url, @HeaderMap Map<String, String> headers);

    @POST
    Observable<JSONObject> postNoHeaderHasParam(@Url String url, @QueryMap Map<String, String> param);

    @FormUrlEncoded
    @POST
    Observable<JSONObject> postHasHeaderHasParam(@Url String url, @HeaderMap Map<String, String> headers, @FieldMap Map<String, String> param);

    @FormUrlEncoded
    @POST
    Observable<JSONObject> postHasHeaderHasParamOfObject(@Url String url, @HeaderMap Map<String, String> headers, @FieldMap Map<String, Object> param);

    @FormUrlEncoded
    @POST
    Observable<JSONObject> postHasHeaderHasParamOfInteger(@Url String url, @HeaderMap Map<String, String> headers, @FieldMap Map<String, Integer> param);


    //get 有请求头
    @GET
    Observable<JSONObject> getNoHeaderNoParam(@Url String url);

    @GET
    Observable<JSONObject> getHasHeaderNoParam(@Url String url, @HeaderMap Map<String, String> headers);

    @GET
    Observable<JSONObject> getNoHeaderHasParam(@Url String url, @QueryMap Map<String, String> param);

    @GET
    Observable<JSONObject> getHasHeaderHasParam(@Url String url, @HeaderMap Map<String, String> headers, @QueryMap Map<String, String> param);



    //登录
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> login(@Url String url, @Body LoginRequestBody loginRequest);

    /** 完美围度 **/
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> postPerfectInfo(@Url String url, @HeaderMap Map<String, String> headers, @Body PerfectRequestBody perfectRequestBody);

    /** 运动表现 **/
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> postSportInfo(@Url String url, @HeaderMap Map<String, String> headers, @Body SportStepRequedtBody sportStepRequedtBody);

    /** 静态评估 **/
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> postStaticInfo(@Url String url, @HeaderMap Map<String, String> headers, @Body StaticRequestBody staticRequestBody);

    /** 动态评估 **/
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> postDynamicInfo(@Url String url, @HeaderMap Map<String, String> headers, @Body DynamicRequestBody dynamicRequestBody);



}
