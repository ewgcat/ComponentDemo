package com.yijian.staff.net.httpmanager;


import com.yijian.staff.BuildConfig;
import com.yijian.staff.net.api.ApiService;
import com.yijian.staff.net.requestbody.savemenu.MenuRequestBody;
import com.yijian.staff.net.requestbody.login.LoginRequestBody;
import com.yijian.staff.net.requestbody.savemenu.MenuBean;
import com.yijian.staff.net.response.ResultObserver;


import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.HeaderMap;

public class HttpManager {

    private static ApiService apiService = RetrofitClient.mRetrofit.create(ApiService.class);

    public static ApiService getApiService() {
        return apiService;
    }


    public static String LOGIN_URL = BuildConfig.HOST + "user/login";
    public static String GET_CODE_URL = BuildConfig.HOST + "user/verificationCode/send";
    public static String RESET_PASSWORD_URL = BuildConfig.HOST + "user/password/reset";

    //会籍（客服） 获取全部会员列表
    public static String GET_ALL_VIPER_LIST_URL = BuildConfig.HOST + "customer-service/member/list";

    //会籍 会员信息 今日来访列表
    public static String GET_TODAY_VIPER_LIST_URL = BuildConfig.HOST + "customer-service/member/today/visit/list";
    //会籍  过期向会员列表
    public static String GET_OUTDATE_VIPER_LIST_URL = BuildConfig.HOST + "customer-service/member/expire/list";

    //会籍  意向会员列表
    public static String GET_INTENT_VIPER_LIST_URL = BuildConfig.HOST + "/customer-service/member/intention/list";

    //会籍  潜在会员列表
    public static String GET_POTENTIAL_VIPER_LIST_URL = BuildConfig.HOST + "/customer-service/member/potential/list";



    //工作台 首页图标
    public static String GET_WORK_INDEX_URL = BuildConfig.HOST + "homepage/data";

    //保存 图标位置
    public static String SAVE_MENU_CHANGE_URL = BuildConfig.HOST + "menu/common/item/save";

    //首页搜索 会籍
    public static String INDEX_HUI_JI_QUERY_URL = BuildConfig.HOST + "customer-service/member/fuzzy/query/list";

    //首页搜索 教练
    public static String INDEX_COACH_QUERY_URL = BuildConfig.HOST + "coach/member/fuzzy/query/list";


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

    // 首页图标
    public static void getIndexMenuList(Map<String, String> headers, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getIndexMenuList(GET_WORK_INDEX_URL, headers);
        execute(observable, observer);
    }

    //保存menu编辑状态
    public static void saveMenuChange(@HeaderMap Map<String, String> headers, MenuRequestBody menuRequestBody, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.saveMenuChange(SAVE_MENU_CHANGE_URL, headers, menuRequestBody);
        execute(observable, observer);
    }

    //教练模糊搜索会员
    public static void searchViperByCoach( Map<String, String> header, Map<String, String> params, Observer<JSONObject> observer) {
        getHasHeaderHasParam(INDEX_COACH_QUERY_URL,header,params,observer);
    }


    //会籍模糊搜索会员
    public static void searchViperByHuiJi( Map<String, String> header, Map<String, String> params, Observer<JSONObject> observer) {
        getHasHeaderHasParam(INDEX_HUI_JI_QUERY_URL,header,params,observer);
    }


    //公共
    // post没请求头没有参数
    public static void postNoHeaderNoParam(String url, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.postNoHeaderNoParam(url);
        execute(observable, observer);
    }

    // post有头无参
    public static void postHasHeaderNoParam(String url, Map<String, String> header, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.postHasHeaderNoParam(url, header);
        execute(observable, observer);
    }

    // post无头有参
    public static void postNoHeaderHasParam(String url, Map<String, String> param, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.postNoHeaderHasParam(url, param);
        execute(observable, observer);
    }

    // post有头有参
    public static void postHasHeaderHasParam(String url, Map<String, String> header, Map<String, String> param, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.postHasHeaderHasParam(url, header, param);
        execute(observable, observer);
    }

    // get没请求头没有参数
    public static void getNoHeaderNoParam(String url, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getNoHeaderNoParam(url);
        execute(observable, observer);
    }

    // get有头无参
    public static void getHasHeaderNoParam(String url, Map<String, String> header, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getHasHeaderNoParam(url, header);
        execute(observable, observer);
    }

    // get无头有参
    public static void getNoHeaderHasParam(String url, Map<String, String> param, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getNoHeaderHasParam(url, param);
        execute(observable, observer);
    }

    // get有头有参
    public static void getHasHeaderHasParam(String url, Map<String, String> header, Map<String, String> param, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getHasHeaderHasParam(url, header, param);
        execute(observable, observer);
    }


}