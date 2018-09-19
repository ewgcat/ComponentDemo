package com.yijian.workspace.module

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.yijan.commonlib.application.BaseApplication

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/9/18 19:28:10
 */
class WorkSpaceApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        //初始化屏幕宽高
        getScreenSize()
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

    companion object {
        @get:Synchronized
        lateinit var instance: WorkSpaceApplication
        var SCREEN_WIDTH = -1
        var SCREEN_HEIGHT = -1
        var DIMEN_RATE = -1.0f
        var DIMEN_DPI = -1
    }


}

