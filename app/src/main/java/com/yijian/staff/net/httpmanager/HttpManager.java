package com.yijian.staff.net.httpmanager;


import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.db.bean.User;
import com.yijian.commonlib.net.retrofit.RetrofitClient;
import com.yijian.staff.net.api.ApiService;

import com.yijian.staff.net.httpmanager.login.LoginRequestBody;
import com.yijian.commonlib.prefs.SharePreferenceUtil;

import org.json.JSONObject;


import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HttpManager {


    private static ApiService apiService = RetrofitClient.mRetrofit.create(ApiService.class);

    public static ApiService getApiService() {
        return apiService;
    }


    //登录
    public static String LOGIN_URL = "user/login";

    //获取新Token
    public static String GET_NEW_TOKEN_URL = "user/getToken_n";






    //公用方法
    private static <T> void execute(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //登陆
    public static void postLogin(LoginRequestBody loginRequestBody, Observer<JSONObject> observer) {
        Observable<JSONObject> loginObservable = apiService.login(SharePreferenceUtil.getHostUrl() + LOGIN_URL, loginRequestBody);
        execute(loginObservable, observer);
    }
    // get有头无参
    public static void getHasHeaderNoParam(String url, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.getHasHeaderNoParam(SharePreferenceUtil.getHostUrl() + url, headers);
            execute(observable, observer);
        }
    }



}
