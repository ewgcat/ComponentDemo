package com.yijian.staff.application;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


import com.tencent.bugly.Bugly;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.net.httpmanager.RetrofitClient;
import com.yijian.staff.tab.MenuHelper;
import com.yijian.staff.tab.tools.ContextUtil;
import com.yijian.staff.util.InitializeService;
import com.yijian.staff.dagger.component.AppComponent;
import com.yijian.staff.dagger.component.DaggerAppComponent;
import com.yijian.staff.dagger.module.AppModule;

import java.util.HashSet;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class CustomApplication extends TinkerApplication {



    private static CustomApplication instance;
    public static AppComponent appComponent;
    private Set<Activity> allActivities;

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

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Bugly.init(this, "9de22ca904", BuildConfig.DEBUG);

        //初始化屏幕宽高
        getScreenSize();

        RetrofitClient.init(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        //在子线程中完成其他初始化
        InitializeService.start(this);
        ContextUtil.init(getApplicationContext());
        if(!MenuHelper.hasEverInit()){
            MenuHelper.init();
        }

    }


    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }

}
