package com.yijian.staff.mvp.login;

import com.example.commonlibrary.mvp.presenter.BasePresenter;
import com.example.commonlibrary.mvp.view.IView;
import com.yijian.staff.net.ApiService;
import com.yijian.staff.net.URLConstants;

import org.json.JSONException;
import org.json.JSONObject;



/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/25 13:56:04
 */

public class LoginPresenter extends BasePresenter<IView<Object>, LoginModel> {


    public LoginPresenter(IView<Object> iView, LoginModel baseModel) {
        super(iView, baseModel);
    }

    public void login(final String account,String password) {

        JSONObject jsonObject=new JSONObject() ;
        try {
            jsonObject.put("account",account);
            jsonObject.put("password",password);

            baseModel.getDataManager().getApi(ApiService.class).login(URLConstants.LOGIN_URL,jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
