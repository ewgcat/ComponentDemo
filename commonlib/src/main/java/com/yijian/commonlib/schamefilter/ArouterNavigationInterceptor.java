package com.yijian.commonlib.schamefilter;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/19 18:07:17
 */
@Interceptor(priority = 8, name = "测试用拦截器")
public class ArouterNavigationInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
    }
}
