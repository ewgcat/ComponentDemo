package com.yijian.staff.net.api;


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

    /*POST 请求 上传文件*/
    @POST("{url}")
    Call<ResponseBody> uploadFiles(
            @Path("url") String url,
            @Part("filename") String description,
            @PartMap() Map<String, RequestBody> maps
    );


    @FormUrlEncoded
    @POST("http://wechat.kai-men.cn/pmsSrv/api/api!gateway.action")
    Observable<JSONObject> login(@Field("reqStr") String jsonStr);



    //发送验证码-2102
    @POST
    Observable<JSONObject> getCode(@Url String url, @Body JSONObject body);

    //验证验证码-2010
    @POST
    Observable<JSONObject> checkCode(@Url String url, @Body JSONObject body);


}
