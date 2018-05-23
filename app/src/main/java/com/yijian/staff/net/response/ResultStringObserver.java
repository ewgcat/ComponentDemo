package com.yijian.staff.net.response;


import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public abstract class ResultStringObserver extends ResponseObserver<String> {

    public ResultStringObserver() {
        super();
    }

    @Override
    protected void initResultType() {
        dataClassType = String.class;
    }

    @Override
    protected void responData(JSONObject jsonObject) throws Exception {
        onSuccess(jsonObject.getString("data"));
    }
}