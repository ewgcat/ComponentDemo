package com.yijian.staff.net.api;


import com.yijian.staff.mvp.coach.experienceclass.step2.bean.AccessRecordBean;
import com.yijian.staff.mvp.coach.preparelessons.PrivatePrepareLessonBody;
import com.yijian.staff.bean.EditHuiJiVipBody;
import com.yijian.staff.mvp.reception.step1.bean.QuestionnaireAnswer;
import com.yijian.staff.mvp.reception.step2.step2Bean.PhysicalExaminationBean;
import com.yijian.staff.mvp.reception.step3.bean.ConditionBody;
import com.yijian.staff.mvp.coach.setclass.bean.PrivateShangKeBean;
import com.yijian.staff.net.httpmanager.HuiJiInviteListRequestBody;
import com.yijian.staff.net.requestbody.addpotential.AddPotentialRequestBody;
import com.yijian.staff.net.requestbody.advice.AddAdviceBody;
import com.yijian.staff.net.requestbody.authcertificate.AuthCertificateRequestBody;
import com.yijian.staff.net.requestbody.huifang.AddHuiFangResultBody;
import com.yijian.staff.net.requestbody.huijigoods.HuiJiGoodsRequestBody;
import com.yijian.staff.net.requestbody.login.LoginRequestBody;
import com.yijian.staff.net.requestbody.message.BusinessMessageRequestBody;
import com.yijian.staff.net.requestbody.privatecourse.CoachPrivateCourseRequestBody;
import com.yijian.staff.net.requestbody.questionnaire.QuestionnaireRequestBody;
import com.yijian.staff.net.requestbody.savemenu.MenuRequestBody;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
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


    /*POST 请求 上传文件*/

    @Multipart
    @POST()
    Observable<JSONObject> uploadFiles(@Url String url, @HeaderMap Map<String, String> headers, @Query("fileType") String param,
                                       @Part() List<MultipartBody.Part> parts);

    //登录
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> login(@Url String url, @Body LoginRequestBody loginRequest);

    //体验课_回访——教练提交回访记录
    @POST
    Observable<JSONObject> postExperienceAccessRecord(@Url String url, @HeaderMap Map<String, String> headers, @Body AccessRecordBean body);

    //体验课_回访——教练提交回访记录
    @POST
    Observable<JSONObject> postAddHuiFangResult(@Url String url, @HeaderMap Map<String, String> headers, @Body AddHuiFangResultBody body);

    //获取问卷列表
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> getQuestionnaireList(@Url String url, @HeaderMap Map<String, String> headers, @Body QuestionnaireRequestBody body);


    //添加职业证书
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> addCertificate(@Url String url, @HeaderMap Map<String, String> headers, @Body AuthCertificateRequestBody body);


    //体测录入
    @POST
    Observable<JSONObject> saveReceptionTest(@Url String url, @HeaderMap Map<String, String> headers, @Query("memberId") String memberId, @Body PhysicalExaminationBean physicalExaminationBean);

    //问卷调查_保存
    @POST
    Observable<JSONObject> postObj(@Url String url, @HeaderMap Map<String, String> headers, @Query("memberId") String memberId, @Body List<QuestionnaireAnswer> requestBody);

    //添加潜在
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> postAddPotential(@Url String addPotentialUrl, @HeaderMap Map<String, String> headers, @Body AddPotentialRequestBody addPotentialRequestBody);


    //保存图标位置
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> saveMenuChange(@Url String url, @HeaderMap Map<String, String> headers, @Body MenuRequestBody menuRequestBody);

    //私教课
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> getCoachPrivateCourseList(@Url String url, @HeaderMap Map<String, String> headers, @Body CoachPrivateCourseRequestBody body);

    //会籍卡产品
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> getHuiJiCardGoodsList(@Url String url, @HeaderMap Map<String, String> headers, @Body HuiJiGoodsRequestBody body);

    //会籍卡产品
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> getHuiJiCardGoodsList_ycm(@Url String url, @HeaderMap Map<String, String> headers, @Body ConditionBody body);


    /**
     * 表单请求
     *
     * @param url
     * @param username
     * @param telephone
     * @return
     */

    //发送验证码
    @FormUrlEncoded
    @POST
    Observable<JSONObject> getCode(@Url String url, @Field("username") String username, @Field("telephone") String telephone);

    //找回密码
    @FormUrlEncoded
    @POST
    Observable<JSONObject> resetPassword(@Url String url, @Field("username") String username, @Field("telephone") String telephone, @Field("verificationCode") String verificationCode, @Field("newPwd") String newPwd, @Field("confirmPwd") String confirmPwd);


    //会籍（客服）获取会员列表
    @GET
    Observable<JSONObject> getDataList(@Url String url, @HeaderMap Map<String, String> headers, @QueryMap Map<String, Object> param);

    //首页图标
    @GET
    Observable<JSONObject> getIndexMenuList(@Url String url, @HeaderMap Map<String, String> headers);


    /**
     * 会籍会员详情编辑
     *
     * @param url
     * @param headers
     * @param editHuiJiVipBody
     * @return
     */
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> editHuiJiVipDetail(@Url String url, @HeaderMap Map<String, String> headers, @Body EditHuiJiVipBody editHuiJiVipBody);


    /**
     * 提交下课数据
     */
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> saveXiaKeRecord(@Url String url, @HeaderMap Map<String, String> headers, @Body PrivateShangKeBean privateShangKeBean, @Query("state") String state);

    /**
     *  保存私教课备课内容
     */
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> savePrivatePrepareLesson(@Url String url, @HeaderMap Map<String, String> headers, @Body PrivatePrepareLessonBody privatePrepareLessonBody);



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

    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> getBusinessMessage(@Url String loginUrl, @HeaderMap Map<String, String> headers, @Body BusinessMessageRequestBody businessMessageRequestBody);

    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> postAddAdvice(@Url String url, @HeaderMap HashMap<String, String> headers, @Body AddAdviceBody addAdviceBody);

    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST
    Observable<JSONObject> getHuiJiInviteRecord(@Url String indexHuiJiInvitationRecordUrl,  @HeaderMap HashMap<String, String> headers, @Body HuiJiInviteListRequestBody body);
}
