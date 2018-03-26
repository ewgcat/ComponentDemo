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

        if (path.equals("/test/19")) {
            if (SharePreferenceUtil.getUserRole() == 2) {
                callback.onContinue(postcard);  // 处理完成，交还控制权
            } else if (SharePreferenceUtil.getUserRole()==3||SharePreferenceUtil.getUserRole()==4) {
                ARouter.getInstance().build("/test/leader_experience_class").navigation();

            } else {
                ARouter.getInstance().build("/test/empty").navigation();
            }
        } else if (path.equals("/test/1")){
            if (SharePreferenceUtil.getUserRole() == 1) {
                callback.onContinue(postcard);  // 处理完成，交还控制权
            } else if (SharePreferenceUtil.getUserRole()==2) {
                ARouter.getInstance().build("/test/1.1").navigation();
            } else {
                ARouter.getInstance().build("/test/empty").navigation();
            }
        }
        else if (path.equals("/test/2")){
            if (SharePreferenceUtil.getUserRole() == 1) {
                callback.onContinue(postcard);  // 处理完成，交还控制权
            } else if (SharePreferenceUtil.getUserRole()==2) {
                ARouter.getInstance().build("/test/2.1").navigation();
            } else {
                ARouter.getInstance().build("/test/empty").navigation();
            }
        }else if (path.equals("/test/4")){
            if (SharePreferenceUtil.getUserRole() == 1) {//会籍
                callback.onContinue(postcard);  // 处理完成，交还控制权
            } else if (SharePreferenceUtil.getUserRole()==2) {//教练
                ARouter.getInstance().build("/test/4.1").navigation();
            } else {
                ARouter.getInstance().build("/test/empty").navigation();
            }
        }


        else {
            callback.onContinue(postcard);  // 处理完成，交还控制权
        }

        // 以上两种至少需要调用其中一种，否则不会继续路由
    }

    @Override
    public void init(Context context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
    }
}
