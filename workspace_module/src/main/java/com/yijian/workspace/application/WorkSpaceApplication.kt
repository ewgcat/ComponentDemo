package com.yijian.workspace.application


import com.yijian.commonlib.application.BaseApplication

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/9/18 19:28:10
 */
class WorkSpaceApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this


    }



    companion object {
        @get:Synchronized
        lateinit var instance: WorkSpaceApplication

    }


}

