package com.yijian.commonlib.base.mvc

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

import com.tencent.bugly.beta.Beta
import com.yijian.commonlib.BuildConfig
import com.yijian.commonlib.R
import com.yijian.commonlib.widget.LoadingDialog
import android.view.ViewTreeObserver




/**
 * 无MVP的activity基类
 */

abstract class MvcBaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

    protected var loadingDialog: LoadingDialog? = null

    private val DEFAULT_STATUS_BAR_COLOR = Color.WHITE


    private var toast: Toast? = null
    protected abstract fun getLayoutID(): Int


    protected//获取status_bar_height资源的ID
    //根据资源ID获取响应的尺寸值
    val statusBarHeight: Int
        get() {
            var height = 0
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                height = resources.getDimensionPixelSize(resourceId)
            }
            return height
        }


    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        if (loadingDialog != null) {
            //防止弹出之前activity已经被销毁了
            if (!this.isFinishing) {
                loadingDialog!!.show()
                loadingDialog!!.setCancelable(false)
            }
        }

    }

    fun hideLoading() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            //防止显示期间activity已经被销毁了
            if (!this.isFinishing) {
                loadingDialog!!.dismiss()
                loadingDialog = null
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(DEFAULT_STATUS_BAR_COLOR)
        setContentView(getLayoutID())
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        mContext = this
        initView(savedInstanceState)
        val upgradeInfo = Beta.getUpgradeInfo()
        if (upgradeInfo != null) {
            val versionCode = upgradeInfo.versionCode
            if (versionCode > BuildConfig.VERSION_CODE) {
                Beta.checkUpgrade(false, false)
            }
        }
        window.decorView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                window.decorView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                lazzyLoad()
            }
        })
    }

    protected open fun lazzyLoad() {

    }

    protected fun onViewCreated() {

    }

    protected open fun initView(savedInstanceState: Bundle?) {}


    /**
     * 隐藏键盘
     */
    open fun hideKeyBoard(v: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    /**
     * 显示键盘
     */
    open fun showKeyBoard(v: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(v, 0)
    }


    fun showToast(id: Int) {
        showToast(getString(id))
    }

    fun showToast(msg: String) {
        if (toast != null) {
            toast!!.cancel()
        }
        toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        toast!!.show()
    }

    fun showToast(msg: String, time: Int) {
        Toast.makeText(this, msg, time).show()
    }



    /**
     * 设置沉浸状态栏
     */
    protected fun setImmersionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    protected fun setStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (ColorUtils.calculateLuminance(color) >= 0.5) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
            window.statusBarColor = color
        }
    }


}
