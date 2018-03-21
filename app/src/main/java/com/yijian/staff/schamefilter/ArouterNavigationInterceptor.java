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
        String path = postcard.getPath();

        if (path.equals("/test/19")){
            if (SharePreferenceUtil.getUserRole()==1){
                callback.onContinue(postcard);  // 处理完成，交还控制权
            }else {
                Logger.i("ArouterNavigationInterceptor","只有教练才有该功能");
                ARouter.getInstance().build("/test/empty").navigation();
            }
        }else {
            callback.onContinue(postcard);  // 处理完成，交还控制权
        }

        // 以上两种至少需要调用其中一种，否则不会继续路由
    }

    @Override
    public void init(Context context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
    }
}
