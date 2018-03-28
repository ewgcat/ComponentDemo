package com.yijian.staff.net.httpmanager;


import com.yijian.staff.BuildConfig;
import com.yijian.staff.net.api.ApiService;
import com.yijian.staff.net.requestbody.login.LoginRequestBody;
import com.yijian.staff.net.response.ResultObserver;


import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HttpManager {

    private static ApiService apiService = RetrofitClient.mRetrofit.create(ApiService.class);

    public static ApiService getApiService() {
        return apiService;
    }


    public static String LOGIN_URL = BuildConfig.HOST + "user/login";
    public static String GET_CODE_URL = BuildConfig.HOST + "user/verificationCode/send";
    public static String RESET_PASSWORD_URL = BuildConfig.HOST + "user/password/reset";
    public static String GET_ALL_VIPER_LIST_URL = BuildConfig.HOST + "customer-service/member/list";

    //会籍 会员信息 今日来访列表
    public static String GET_TODAY_VIPER_LIST_URL = BuildConfig.HOST + "customer-service/member/today/visit/list";


    //公用方法
    private static <T> void execute(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //登陆
    public static void postLogin(LoginRequestBody loginRequestBody, Observer<JSONObject> observer) {
        Observable<JSONObject> loginObservable = apiService.login(LOGIN_URL, loginRequestBody);
        execute(loginObservable, observer);
    }

    //获取验证码
    public static void getCode(String username, String telephone, Observer<JSONObject> observer) {
        Observable<JSONObject> getCodeObservable = apiService.getCode(GET_CODE_URL, username, telephone);
        execute(getCodeObservable, observer);
    }


    //重置密码
    public static void resetPassword(String username, String telephone, String verificationCode, String newPwd, String confirmPwd, ResultObserver observer) {
        Observable<JSONObject> getCodeObservable = apiService.resetPassword(RESET_PASSWORD_URL, username, telephone, verificationCode, newPwd, confirmPwd);
        execute(getCodeObservable, observer);
    }

    //会籍（客服） 获取全部会员列表
    public static void getAllViperList(Map<String, String> headers, Map<String, String> params, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getDataList(GET_ALL_VIPER_LIST_URL, headers, params);
        execute(observable, observer);
    }

    //会籍 会员信息 今日来访列表
    public static void getTodayViperList(Map<String, String> headers, Map<String, String> params, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getDataList(GET_TODAY_VIPER_LIST_URL, headers, params);
        execute(observable, observer);
    }





    //接待人信息
    public static void getReceptionInfo(Map<String, String> headers, Observer<JSONObject> observer){
        Observable<JSONObject> receptionInfo = apiService.getDataOnlyToken(GET_RECEPTION_INFO, headers);
        execute(receptionInfo,observer);
    }

    public static void getReceptionRecordList(Map<String, String> headers, Map<String, String> params, Observer<JSONObject> observer){
        Observable<JSONObject> dataList = apiService.getDataList(GET_RECEPTION_RECORD, headers, params);
        execute(dataList,observer);
    }


    //接待人的信息
    public static final String GET_RECEPTION_INFO=BuildConfig.HOST+"/reception/person";


    //接待记录
    public static final String GET_RECEPTION_RECORD=BuildConfig.HOST+"/reception/record";


    // //接待---问卷调查
    public static String GET_RECEPTION_QUESTION = BuildConfig.HOST + "/qs/edit";



}