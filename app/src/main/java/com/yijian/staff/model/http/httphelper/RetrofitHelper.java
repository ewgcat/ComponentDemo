package com.yijian.staff.model.http.httphelper;




import com.yijian.staff.model.bean.SplashBean;
import com.yijian.staff.model.bean.User;
import com.yijian.staff.model.http.api.BaseApi;
import com.yijian.staff.model.http.response.BaseResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;


public class RetrofitHelper implements HttpHelper {

    private BaseApi baseApi;

    @Inject
    public RetrofitHelper(BaseApi baseApi) {
        this.baseApi = baseApi;
    }

    @Override
    public Flowable<BaseResponse<SplashBean>> getSplashInfo() {
        return baseApi.getSplashInfo();
    }

    @Override
    public Flowable<BaseResponse<User> > login(String userName, String password) {
        return baseApi.login( userName,  password);
    }

}
