package com.yijian.commonlib.net.response;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.JsonSyntaxException;
import com.yijian.commonlib.util.JsonUtil;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class Response2Observer implements Observer<JSONObject>, ResultCallBack<JSONObject>, LifecycleObserver {
    private Disposable disposable;
    private Lifecycle lifecycle;




    public Response2Observer(Lifecycle lifecycle) {
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
                    onSuccess(jsonObject);
                    break;
                case ResponseCode.TOKEN_TIME_OUT:
                    onFail(responseBean.getMsg());
                    ARouter.getInstance().build("/app/login").navigation();
                    break;
                default:
                     onFail(responseBean.getMsg());
                    break;
            }
        } catch (Exception e) {
            onFail(getErrorMsg(e));
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
