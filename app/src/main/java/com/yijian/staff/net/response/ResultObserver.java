package com.yijian.staff.net.response;


import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public abstract  class ResultObserver implements Observer<JSONObject>   , ResultCallBack<JSONObject>{

    private Disposable mDisposable;


    public ResultObserver() {

    }

    @Override
    public void onSubscribe(@NonNull Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void onNext(JSONObject jsonObject) {
        Logger.i("ResultObserver",jsonObject.toString());
        try {
            int code = jsonObject.getInt("code");
            if (code==0){
                JSONObject data = JsonUtil.getJsonObject(jsonObject,"data");
                if (data!=null){
                    onSuccess(data);
                }
            }else if (code==3){
                String msg = jsonObject.getString("msg");
                onFail(msg);
                ARouter.getInstance().build("/test/login").navigation();
            }else {
                String msg = jsonObject.getString("msg");
                onFail(msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        onFail(e.getMessage());
        Log.i("ResultObserver", "onError "+e.toString());
    }

    @Override
    public void onComplete() {
        Log.i("ResultObserver", "onCompleted==请求结束");
    }

    public Disposable getmDisposable() {
        return mDisposable;
    }
}
