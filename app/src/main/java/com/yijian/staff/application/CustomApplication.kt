package com.yijian.staff.application



import com.yijian.commonlib.application.BaseApplication
import com.yijian.clubmodule.db.ClubDBManager
import com.yijian.commonlib.net.retrofit.RetrofitClient


class CustomApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.init(this)
        ClubDBManager.init(this)
    }





}
