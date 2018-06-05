package com.hengte.retrofit.net.subsrciber;

/**
 * Created by Lenovo on 2017/4/7.
 */

public interface ResponseState<T> {
    void onFail(String error);

    void onSuccess(T t);
}
