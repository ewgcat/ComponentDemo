package com.yijan.commonlib.application

import android.app.Activity
import android.app.Application
import android.os.Bundle

import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.tinker.loader.app.TinkerApplication
import com.tencent.tinker.loader.shareutil.ShareConstants

import java.util.HashMap

import cn.jpush.android.api.JPushInterface
import com.umeng.socialize.net.dplus.db.DBManager
import com.yijan.commonlib.BuildConfig
import com.yijan.commonlib.net.httpmanager.RetrofitClient
import com.yijan.commonlib.umeng.UmengUtils


open class BaseApplication : TinkerApplication(ShareConstants.TINKER_ENABLE_ALL, "com.yijan.commonlib.application.BaseApplicationLike", "com.tencent.tinker.loader.TinkerLoader", false), Application.ActivityLifecycleCallbacks {


    private val activityMap = HashMap<String, Activity>()


    override fun onCreate() {
        super.onCreate()

        instance = this
        RetrofitClient.init(this)


        //极光推送
        JPushInterface.setDebugMode(BuildConfig.DEBUG)
        // 初始化 JPush
        JPushInterface.init(this)
        JPushInterface.stopPush(this)

        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)

        registerActivityLifecycleCallbacks(this)

        UmengUtils.init(this)


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


        private val TAG = BaseApplication::class.java.simpleName

        @get:Synchronized
        lateinit var instance: BaseApplication


    }

}
