package com.yijian.staff.model.http.httphelper;


import com.yijian.staff.model.bean.SplashBean;
import com.yijian.staff.model.bean.User;
import com.yijian.staff.model.http.response.BaseResponse;

import io.reactivex.Flowable;

public interface HttpHelper {

    //启动
    Flowable<BaseResponse<SplashBean>> getSplashInfo();

    //登录
    Flowable<BaseResponse<User>> login(String userName, String password);



}
