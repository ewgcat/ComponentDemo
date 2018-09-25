package com.yijian.staff.ui.splash

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.Settings
import android.text.TextUtils
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.tbruyelle.rxpermissions2.Permission

import com.tbruyelle.rxpermissions2.RxPermissions
import com.yijian.clubmodule.ui.course.schedule.day.LockTimePopuwindow
import com.yijian.clubmodule.ui.face.FaceDetail
import com.yijian.commonlib.application.BaseApplication

import com.yijian.staff.application.CustomApplication
import com.yijian.commonlib.db.DBManager
import com.yijian.commonlib.base.mvc.MvcBaseActivity
import com.yijian.staff.ui.login.LoginActivity
import com.yijian.staff.net.httpmanager.HttpManager
import com.yijian.commonlib.net.response.ResultStringObserver
import com.yijian.commonlib.rx.RxUtil
import com.yijian.commonlib.util.LoadingProgressDialog
import com.yijian.commonlib.util.NotificationsUtil
import com.yijian.staff.R

import java.util.concurrent.TimeUnit

import io.reactivex.Observable

import com.yijian.staff.net.httpmanager.HttpManager.GET_NEW_TOKEN_URL
import com.yijian.staff.ui.widget.NotificationPopuwindow


class SplashActivity : MvcBaseActivity() {

    internal var permissions = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CHANGE_NETWORK_STATE, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    private var index = 0
    protected var mExitTime: Long = 0
    lateinit var iv: View;
    lateinit var notificationPopuwindow: NotificationPopuwindow;


    override fun getLayoutID(): Int {
        return R.layout.activity_splash
    }


    override fun initView(savedInstanceState: Bundle?) {
        setImmersionBar()
        iv = findViewById<View>(R.id.iv)


        notificationPopuwindow = NotificationPopuwindow(this)
        notificationPopuwindow.setAnimationStyle(com.yijian.clubmodule.R.style.locktime_popwin_anim_style)
        notificationPopuwindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        notificationPopuwindow.setOnSelectLisener(object : NotificationPopuwindow.OnSelectLisener {
            override fun onConfirm() {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivityForResult(intent, 100)
            }
            override fun onCancel() {
                jumpToNext()
            }
        })
        notificationPopuwindow.setOnDismissListener(PopupWindow.OnDismissListener { notificationPopuwindow.setBackgroundAlpha(this, 1f) })
    }

    override fun lazzyLoad() {
        initRxPermissions(index, permissions)
    }


    fun jumpToNext() {
        Handler().postDelayed({
            val user = DBManager.getInstance().queryUser()
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
                val user = DBManager.getInstance().queryUser()
                user.token = result
                DBManager.getInstance().insertOrReplaceUser(user)
                ARouter.getInstance().build("/test/main").navigation()

                finish()
            }

            override fun onFail(msg: String) {
                ARouter.getInstance().build("/test/main").navigation()
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
                        val manager = NotificationManagerCompat.from(applicationContext);
                        val isOpened = manager.areNotificationsEnabled();
                        if (isOpened) {
                            jumpToNext()
                        } else {
                            requestNotificationPermission();
                        }
                    }
                }
    }

    /**
     * 请求通知权限  100
     */
    private fun requestNotificationPermission() {
        notificationPopuwindow.setBackgroundAlpha(this, 0.3f)
        notificationPopuwindow.showAtLocation(iv, Gravity.CENTER, 0, 0)
    }


    /**
     * 请求权限回调
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            100 -> {
                val msg = NotificationsUtil.isNotificationEnabled(this)
                if (msg) {
                    showToast("缺少通知权限,推送消息接收不到哦！")
                }
                jumpToNext()
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
                notificationPopuwindow.dismiss()
                BaseApplication.instance.exitApp()

            }
        }
        return true
    }


}
