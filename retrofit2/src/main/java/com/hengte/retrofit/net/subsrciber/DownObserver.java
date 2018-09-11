package com.hengte.retrofit.net.subsrciber;

import android.content.Context;

import com.hengte.retrofit.net.download.CallBack;
import com.hengte.retrofit.net.download.DownLoadManager;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class DownObserver<ResponseBody> implements Observer<ResponseBody> {
    CallBack callBack;
    Context mContext;

    public DownObserver(Context context, CallBack callBack) {
        this.mContext = context;
        this.callBack = callBack;
    }


    @Override
    public void onError(Throwable e) {
        if (callBack != null) {
            callBack.onError(e);
        }
    }

    @Override
    public void onComplete() {
        if (callBack != null) {
            callBack.onCompleted();
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable disposable) {

    }

    @Override
    public void onNext(ResponseBody responseBody) {
        DownLoadManager.getInstance(callBack).writeResponseBodyToDisk(mContext, (okhttp3.ResponseBody) responseBody);
    }
}

