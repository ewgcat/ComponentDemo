package com.yijian.staff.application

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.WindowManager

import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.tinker.loader.app.TinkerApplication
import com.tencent.tinker.loader.shareutil.ShareConstants
import com.yijian.staff.BuildConfig
import com.yijian.staff.db.DBManager
import com.yijian.staff.share.umeng.UmengUtils
import com.yijian.staff.net.httpmanager.RetrofitClient
import com.yijian.staff.prefs.SharePreferenceUtil
import com.yijian.staff.tab.tools.ContextUtil
import com.yijian.staff.util.ApplicationHolder
import java.util.HashMap

import cn.jpush.android.api.JPushInterface


class CustomApplication : TinkerApplication(ShareConstants.TINKER_ENABLE_ALL, "com.yijian.staff.application.CustomApplicationLike", "com.tencent.tinker.loader.TinkerLoader", false), Application.ActivityLifecycleCallbacks {


    private val activityMap = HashMap<String, Activity>()


    override fun onCreate() {
        super.onCreate()

        instance = this


        //极光推送
        JPushInterface.setDebugMode(BuildConfig.DEBUG)
        // 初始化 JPush
        JPushInterface.init(this)
        val registrationID = JPushInterface.getRegistrationID(this)
        SharePreferenceUtil.setJpushRegistionId(registrationID)
        JPushInterface.stopPush(this)

        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
        ApplicationHolder.setmApplication(this)

        //初始化屏幕宽高
        getScreenSize()

        RetrofitClient.init(this)
        SharePreferenceUtil.setWorkSpaceHost(SharePreferenceUtil.isWorkSpaceVersion())
        DBManager.init(this)
        //在子线程中完成其他初始化
        ContextUtil.init(applicationContext)
        registerActivityLifecycleCallbacks(this)

        UmengUtils.init(this)


    }


    fun getScreenSize() {
        val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        val display = windowManager.defaultDisplay
        display.getMetrics(dm)
        DIMEN_RATE = dm.density / 1.0f
        DIMEN_DPI = dm.densityDpi
        SCREEN_WIDTH = dm.widthPixels
        SCREEN_HEIGHT = dm.heightPixels
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            val t = SCREEN_HEIGHT
            SCREEN_HEIGHT = SCREEN_WIDTH
            SCREEN_WIDTH = t
        }
    }


    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle?) {}
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activityMap[activity.hashCode().toString() + ""] = activity
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {


    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }


    override fun onActivityDestroyed(activity: Activity) {
        activityMap.remove(activity.hashCode().toString() + "")
    }

    fun exitApp() {
        for (activity in activityMap.values) {
            activity.finish()
        }
    }

    companion object {


        private val TAG = "CustomApplication"
        @get:Synchronized
        lateinit var instance: CustomApplication

        var SCREEN_WIDTH = -1
        var SCREEN_HEIGHT = -1
        var DIMEN_RATE = -1.0f
        var DIMEN_DPI = -1
    }

}
