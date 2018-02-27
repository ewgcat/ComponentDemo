package com.yijian.staff.mvp.reception;

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

public class ReceptionPresenter extends BasePresenter<IView<Object>, ReceptionModel> {


    public ReceptionPresenter(IView<Object> iView, ReceptionModel baseModel) {
        super(iView, baseModel);
    }




}
