package com.yijian.staff.application

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager


import com.yijian.commonlib.application.BaseApplication
import com.yijian.clubmodule.db.ClubDBManager
import com.yijian.commonlib.net.retrofit.RetrofitClient


class CustomApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.init(this)
    }





}
