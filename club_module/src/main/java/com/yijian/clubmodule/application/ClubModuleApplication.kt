package com.yijian.clubmodule.application

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager


import com.yijian.commonlib.application.BaseApplication
import com.yijian.clubmodule.db.ClubDBManager
import com.yijian.commonlib.net.retrofit.RetrofitClient


class ClubModuleApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        RetrofitClient.init(this)
        ClubDBManager.init(this)
    }



    companion object {
        @get:Synchronized
        lateinit var instance: ClubModuleApplication

    }


}
