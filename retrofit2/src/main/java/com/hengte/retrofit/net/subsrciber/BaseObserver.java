package com.hengte.retrofit.net.subsrciber;


import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public abstract class BaseObserver<T> implements Observer<T> ,ResponseState<T> {



    private Disposable mDisposable;


    public BaseObserver() {

    }

    @Override
    public void onSubscribe(@NonNull Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void onNext(T t) {
        Log.i("test", "onNext");
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e.getMessage());
        Log.i("test", "onError "+e.toString());
    }

    @Override
    public void onComplete() {
        Log.i("test", "onCompleted==请求结束");
    }

    public Disposable getmDisposable() {
        return mDisposable;
    }
}
