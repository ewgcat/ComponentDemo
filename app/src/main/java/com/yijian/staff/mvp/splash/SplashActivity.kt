package com.yijian.staff.mvp.splash

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.text.TextUtils
import android.view.ContextThemeWrapper
import android.view.KeyEvent
import android.widget.Toast
import com.tbruyelle.rxpermissions2.Permission

import com.tbruyelle.rxpermissions2.RxPermissions
import com.yijian.staff.R
import com.yijian.staff.application.CustomApplication
import com.yijan.commonlib.db.ClubDBManager
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity
import com.yijian.staff.mvp.login.LoginActivity
import com.yijian.staff.mvp.main.MainActivity
import com.yijian.staff.net.httpmanager.HttpManager
import com.yijan.commonlib.net.response.ResultStringObserver
import com.yijian.staff.rx.RxUtil
import com.yijian.staff.util.NotificationsUtil

import java.util.concurrent.TimeUnit

import io.reactivex.Observable

import com.yijian.staff.net.httpmanager.HttpManager.GET_NEW_TOKEN_URL


class SplashActivity : MvcBaseActivity() {

    internal var permissions = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CHANGE_NETWORK_STATE, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    private var index = 0

    protected var mExitTime: Long = 0



    override fun getLayoutID(): Int {
        return R.layout.activity_splash
    }

    override fun initView(savedInstanceState: Bundle?) {
        setImmersionBar()
        initRxPermissions(index, permissions)
    }


    fun jumpToNext() {
        Handler().postDelayed({
            val user = ClubDBManager.getInstance().queryUser()
            if (user != null) {
                val token = user.token.trim { it <= ' ' }
                if (TextUtils.isEmpty(token)) {
                    val intent = Intent()
                    intent.setClass(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {

                    getNewToken()
                }
            } else {
                val intent = Intent()
                intent.setClass(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, (1000 * 2).toLong())


    }

    fun getNewToken() {
        HttpManager.getHasHeaderNoParam(GET_NEW_TOKEN_URL, object : ResultStringObserver(lifecycle) {
            override fun onSuccess(result: String) {
                val user = ClubDBManager.getInstance().queryUser()
                user.token = result
                ClubDBManager.getInstance().insertOrReplaceUser(user)
                val intent = Intent()
                intent.setClass(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onFail(msg: String) {
                val intent = Intent()
                intent.setClass(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    /**
     * 运行时请求权限
     */
    private fun initRxPermissions(i: Int, permissions: Array<String>) {
        val rxPermissions = RxPermissions(this)
        Observable.timer(200, TimeUnit.MILLISECONDS)
                .compose<Permission>(rxPermissions.ensureEach(
                        permissions[i]
                ))
                .compose<Permission>(RxUtil.rxObservableSchedulerHelper<Permission>())
                .subscribe { _ ->
                    index = i + 1
                    if (permissions.size > index) {
                        initRxPermissions(index, permissions)
                    } else {
                        val msg = checkPermissions()
                        if (!TextUtils.isEmpty(msg) && msg.contains("【通知与状态栏权限】")) {
                            requestPermissions("    【通知与状态栏权限】\n")
                        } else {
                            jumpToNext()
                        }
                    }
                }
    }

    /**
     * 请求通知权限  100
     */
    private fun requestPermissions(msg: String) {
        val alertDialog = AlertDialog.Builder(ContextThemeWrapper(this@SplashActivity, R.style.AlertDialogCustom))

                .setTitle("获取必要权限")
                .setMessage(msg)
                .setPositiveButton("立即获取") { _, _ ->
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivityForResult(intent, 100)
                }
                .setNegativeButton("拒绝") { _, _ -> jumpToNext() }.create()

        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    private fun checkPermissions(): String {
        var msg = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !NotificationsUtil.isNotificationEnabled(this)) {
            msg = "    【通知与状态栏权限】\n"
        }
        return msg
    }


    /**
     * 请求权限回调
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            100 -> {
                val msg = checkPermissions()
                if (!TextUtils.isEmpty(msg)) {
                   showToast( "缺少$msg")
                }
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    /**
     * 监听按键的点击
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val secondTime = System.currentTimeMillis()
            if (secondTime - mExitTime > 2000) {
                Toast.makeText(applicationContext, "再按一次返回键退出程序!", Toast.LENGTH_SHORT).show()
                mExitTime = secondTime
                return true
            } else {
                CustomApplication.instance.exitApp()
            }
        }
        return true
    }


}
