package com.yijian.staff.net.response;

/**
 * desc:
 * <p>
 * date:2016/11/22
 * time:9:26
 */

public interface ResultCallBack<T> {
    void onSuccess(T result);

    void onFail(String msg);
}
