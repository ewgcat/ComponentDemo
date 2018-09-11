package com.yijian.staff.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.dagger.component.AppComponent;
import com.yijian.staff.dagger.component.DaggerAppComponent;
import com.yijian.staff.dagger.module.AppModule;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.share.umeng.UmengUtils;
import com.yijian.staff.net.httpmanager.RetrofitClient;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.tab.tools.ContextUtil;
import com.yijian.staff.util.ApplicationHolder;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;


public class CustomApplication extends TinkerApplication implements Application.ActivityLifecycleCallbacks {


    private static final String TAG = "CustomApplication";
    public static CustomApplication instance;
    public static AppComponent appComponent;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public CustomApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.yijian.staff.application.CustomApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

    public static synchronized CustomApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;


        //极光推送
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        // 初始化 JPush
        JPushInterface.init(this);
        String registrationID = JPushInterface.getRegistrationID(this);
        SharePreferenceUtil.setJpushRegistionId(registrationID);
        JPushInterface.stopPush(this);

        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        ApplicationHolder.setmApplication(this);

        //初始化屏幕宽高
        getScreenSize();

        RetrofitClient.init(this);
        SharePreferenceUtil.setWorkSpaceHost(SharePreferenceUtil.isWorkSpaceVersion());
        DBManager.init(this);
        //在子线程中完成其他初始化
        ContextUtil.init(getApplicationContext());
        registerActivityLifecycleCallbacks(this);

        UmengUtils.init(this);


    }


    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }

    private Map<String, Activity> activityMap = new HashMap<>();


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityMap.put(activity.hashCode() + "", activity);
        Log.e(TAG, "onActivityCreated: ");
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {


    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityMap.remove(activity.hashCode() + "");
    }

    public void exitApp() {
        for (Activity activity : activityMap.values()) {
            activity.finish();
        }
    }

}
