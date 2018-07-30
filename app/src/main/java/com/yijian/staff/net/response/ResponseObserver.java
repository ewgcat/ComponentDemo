package com.yijian.staff.net.response;


import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class ResponseObserver<T> implements Observer<JSONObject>, ResultCallBack<T>, LifecycleObserver {
    protected Type dataClassType;
    private Disposable disposable;
    private Lifecycle lifecycle;


    public ResponseObserver(Lifecycle lifecycle) {
        this.lifecycle=lifecycle;
        this. lifecycle.addObserver(this);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        destroy();
    }

    private void destroy() {
        if (lifecycle != null) {
            lifecycle.removeObserver(this);
        }
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }






    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;

    }

    @Override
    public void onComplete() {

        destroy();
    }

    @Override
    public void onNext(JSONObject jsonObject) {
        try {
            ResponseBean responseBean = new ResponseBean();
            responseBean.setCode(JsonUtil.getInt(jsonObject,"code"));
            responseBean.setMsg(JsonUtil.getString(jsonObject,"msg"));
            switch (responseBean.getCode()) {
                case ResponseCode.SUCCESS:
                    responData(jsonObject);
                    break;
                case ResponseCode.TOKEN_TIME_OUT:
                    onFail(responseBean.getMsg());
                    ARouter.getInstance().build("/test/login").navigation();
                    break;
                default:
                    onFail(responseBean.getMsg());
                    break;
            }
        } catch (Exception e) {
            onFail(getErrorMsg(e));
        }

    }

    @SuppressWarnings("unchecked")
    protected void responData(JSONObject jsonObject) throws Exception {
        String dataJson = jsonObject.get("data").toString();
        try {
            if (dataClassType == JSONObject.class) {
                onSuccess((T) new JSONObject(dataJson));
                return;
            } else if (dataClassType == JSONArray.class) {
                onSuccess((T) new JSONArray(dataJson));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (TextUtils.isEmpty(dataJson) || (dataJson.startsWith("{") && dataJson.length() <= 2)) {
            onSuccess(null);
        } else {
            T data = new Gson().fromJson(dataJson, dataClassType);
            onSuccess(data);
        }
    }

    @Override
    public void onError(Throwable e) {
        onFail(getErrorMsg(e));
        onComplete();
    }

    private String getErrorMsg(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            return "连接超时";
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            return "网络错误";
        } else if (e instanceof JsonSyntaxException) {
            return "解析数据出错了";
        } else {
            return "出错了";
        }
    }


}
