package com.yijian.staff.schamefilter;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.Logger;

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
