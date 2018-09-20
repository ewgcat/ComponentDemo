package com.yijian.staff.ui.login

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.alibaba.fastjson.JSONArray
import com.jaeger.library.StatusBarUtil
import com.yijian.clubmodule.bean.PermissionBean
import com.yijian.clubmodule.ui.forgetpassword.ForgetPasswordActivity
import com.yijian.clubmodule.ui.permission.PermissionUtils
import com.yijian.commonlib.db.DBManager
import com.yijian.commonlib.db.bean.User
import com.yijian.commonlib.net.response.ResultJSONObjectObserver
import com.yijian.staff.BuildConfig
import com.yijian.staff.R

import com.yijian.clubmodule.db.ClubDBManager
import com.yijian.clubmodule.db.bean.OthermodelVo
import com.yijian.clubmodule.db.bean.RoleVoBean
import com.yijian.commonlib.base.mvc.MvcBaseActivity

import com.yijian.staff.net.httpmanager.HttpManager
import com.yijian.staff.net.httpmanager.login.LoginRequestBody
import com.yijian.commonlib.prefs.SharePreferenceUtil
import com.yijian.commonlib.util.AndroidAdjustResizeBugFix
import com.yijian.commonlib.util.CommonUtil
import com.yijian.commonlib.util.DensityUtil
import com.yijian.commonlib.util.JsonUtil
import com.yijian.commonlib.util.Logger
import com.yijian.commonlib.widget.TableView

import org.json.JSONObject


@Route(path = "/app/login")
class LoginActivity : MvcBaseActivity(), AndroidAdjustResizeBugFix.CallKeyBoardStatu, View.OnClickListener {
    internal lateinit var etAccount: EditText
    internal lateinit var etPassword: EditText
    internal lateinit var ll_content: LinearLayout
    internal lateinit var tab_view: TableView
    internal lateinit var rel_container: RelativeLayout
    private var containerHeight: Float = 0F
    private var difference: Float = 0F
    private val TAG =LoginActivity::class.java.simpleName

    override fun getLayoutID(): Int {
        return R.layout.activity_login
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun initView(savedInstanceState: Bundle?) {
        etAccount = findViewById(R.id.et_account)
        etPassword = findViewById(R.id.et_password)
        ll_content = findViewById(R.id.ll_content)
        tab_view = findViewById(R.id.tab_view)
        rel_container = findViewById(R.id.rel_container)
        findViewById<View>(R.id.ll_login).setOnClickListener(this)
        findViewById<View>(R.id.forget_password).setOnClickListener(this)
        val vto = ll_content.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                ll_content.viewTreeObserver.removeOnGlobalLayoutListener(this)
                containerHeight = ll_content.measuredHeight.toFloat()
            }
        })
        AndroidAdjustResizeBugFix.assistActivity(this, this)
        StatusBarUtil.setTranslucentForImageView(this, 0, null)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
        rel_container.setOnTouchListener { v, _ ->
            if (v !is EditText) {
                var imm: InputMethodManager
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
                    imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }

            }
            false
        }

        etAccount.setText(SharePreferenceUtil.getUserName())
        tab_view.createButton("俱乐部", "工作室")
        tab_view.setListener { index ->
            when (index) {
                0 //俱乐部
                -> {
                    SharePreferenceUtil.setHostUrl(BuildConfig.HOST)
                    SharePreferenceUtil.setImageUrl(BuildConfig.FILE_HOST)
                    SharePreferenceUtil.setH5Url(BuildConfig.H5_HOST)
                }
                1 //工作室
                -> {
                    SharePreferenceUtil.setWorkSpaceHost(false)
                    SharePreferenceUtil.setHostUrl(BuildConfig.WORKSPACE_HOST)
                    SharePreferenceUtil.setImageUrl(BuildConfig.WORKSPACE_FILE_HOST)
                    SharePreferenceUtil.setH5Url(BuildConfig.H5_HOST)
                }
            }
        }
        tab_view.setCurrentPosition(if (SharePreferenceUtil.isWorkSpaceVersion()) 1 else 0)
    }


    private fun jumpToForgetPassword() {
        val account = etAccount.text.toString()
        val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
        intent.putExtra("account", account)
        startActivity(intent)
    }

    private fun login() {
        val account = etAccount.text.toString()
        val password = etPassword.text.toString()
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            showToast("账号和密码不能为空")
        } else {
            if (CommonUtil.isPassWordFormat(password)) {
                showLoading()
                val loginRequest = LoginRequestBody(account, password)
                HttpManager.postLogin(loginRequest, object : ResultJSONObjectObserver(lifecycle) {
                    override fun onSuccess(result: JSONObject) {
                        hideLoading()
                        val user = User(result)
                        SharePreferenceUtil.setUserName(account)
                        SharePreferenceUtil.setUserId(user.userId)
                        SharePreferenceUtil.setUserRole(user.role)
                        DBManager.getInstance().insertOrReplaceUser(user)
                        val roleVo = JsonUtil.getJsonObject(result, "roleVo")
                        ClubDBManager.getInstance().insertOrReplaceRoleVoBean(RoleVoBean(roleVo))
                        val homePageModelVO = JsonUtil.getJsonObject(result, "homePageModelVO")
                        val othermodelVo = JsonUtil.getJsonObject(homePageModelVO, "othermodelVo")

                        ClubDBManager.getInstance().insertOrReplaceOthermodelVo(OthermodelVo(othermodelVo))

                        try {
                            //存储菜单子选项
                            val permissionBeanList = JSONArray.parseArray(homePageModelVO.getJSONArray("menuModelList").toString(), PermissionBean::class.java)
                            PermissionUtils.getInstance().savePermissionMenu(this@LoginActivity, permissionBeanList)
                        } catch (e: Exception) {
                            Logger.i(TAG, e.message)
                        }

                        ARouter.getInstance().build("/test/main").navigation()

                        finish()
                    }

                    override fun onFail(msg: String) {
                        hideLoading()
                        Logger.i(TAG, msg)
                        showToast(msg)
                    }
                })
            } else {
                showToast("密码格式错误，密码是数字和字母的6-20位组合！")
            }
        }
    }


    override fun callkeyboardstatu(showFlag: Boolean, heightDifference: Int) {
        if (showFlag) {
            difference = heightDifference - (DensityUtil.getScreenHeight(this) - containerHeight)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                ObjectAnimator.ofFloat(ll_content, "translationY", -difference).setDuration(300).start()
            }
        } else {
            if (difference > 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    ObjectAnimator.ofFloat(ll_content, "translationY", 0F).setDuration(300).start()
                }
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ll_login -> login()
            R.id.forget_password -> jumpToForgetPassword()
        }
    }

}