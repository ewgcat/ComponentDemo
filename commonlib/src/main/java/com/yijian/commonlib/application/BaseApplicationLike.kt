package com.yijian.commonlib.application

import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.multidex.MultiDex
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta
import com.tencent.tinker.loader.app.DefaultApplicationLike
import com.yijian.commonlib.BuildConfig

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/9/18 13:43:35
 */


 class BaseApplicationLike(application: Application, tinkerFlags: Int, tinkerLoadVerifyFlag: Boolean, applicationStartElapsedTime: Long, applicationStartMillisTime: Long, tinkerResultIntent: Intent) : DefaultApplicationLike(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent) {


    override fun onCreate() {
        super.onCreate()
        Bugly.init(application, "9de22ca904", BuildConfig.DEBUG)
        Beta.checkUpgrade(false, false)

    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    override fun onBaseContextAttached(base: Context?) {
        super.onBaseContextAttached(base)
        MultiDex.install(base)
        Beta.installTinker(this)
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    fun registerActivityLifecycleCallback(callbacks: Application.ActivityLifecycleCallbacks) {
        application.registerActivityLifecycleCallbacks(callbacks)
    }

    companion object {

        val TAG = "Tinker.CustomApplicationLike"
    }

}


