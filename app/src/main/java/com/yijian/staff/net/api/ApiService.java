package com.yijian.staff.net.api;


import com.yijian.staff.net.requestbody.login.LoginRequestBody;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
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
    @POST("{url}")
    Observable<ResponseBody> upLoadFile(
            @Path("url") String url,
            @Part("image\"; filename=\"image.jpg") RequestBody requestBody
    );

//    @HeaderMap Map<String, String> headers,
    /*POST 请求 上传文件*/
    @POST("{url}")
    Call<ResponseBody> uploadFiles(
            @Path("url") String url,
            @Part("filename") String description,
            @PartMap() Map<String, RequestBody> maps
    );

    //登录
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> login(@Url String url, @Body LoginRequestBody loginRequest );

    /**
     * 表单请求
     * @param url
     * @param username
     * @param telephone
     * @return
     */

    //发送验证码
    @FormUrlEncoded
    @POST
    Observable<JSONObject> getCode(@Url String url, @Field("username") String username,@Field("telephone") String telephone);

    //找回密码
    @FormUrlEncoded
    @POST
    Observable<JSONObject> resetPassword(@Url String getCodeUrl, @Field("username") String username,@Field("telephone") String telephone,@Field("verificationCode") String verificationCode,@Field("newPwd") String newPwd,@Field("confirmPwd") String confirmPwd);
}
