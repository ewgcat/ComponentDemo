package com.yijian.staff.mvp.forgetpassword

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView

import com.yijian.staff.R
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity
import com.yijian.staff.net.httpmanager.HttpManager
import com.yijian.staff.net.response.ResultJSONObjectObserver
import com.yijian.staff.util.CommonUtil
import com.yijian.staff.util.CountDownTimerUtils
import com.yijian.staff.widget.NavigationBar

import org.json.JSONObject

import butterknife.BindView
import butterknife.OnClick

class ForgetPasswordActivity : MvcBaseActivity() {

    @BindView(R.id.et_account)
    internal var etAccount: EditText? = null
    @BindView(R.id.et_phonenum)
    internal var etPhonenum: EditText? = null

    @BindView(R.id.et_code)
    internal var etCode: EditText? = null
    @BindView(R.id.tv_getcode)
    internal var tvGetcode: TextView? = null
    @BindView(R.id.et_passwd)
    internal var etPasswd: EditText? = null
    @BindView(R.id.et_re_passwd)
    internal var etRePasswd: EditText? = null


    override fun getLayoutID(): Int {
        return R.layout.activity_forget_password
    }

    override fun initView(savedInstanceState: Bundle?) {
        val navigationBar = findViewById<View>(R.id.forget_password_activity_navigation_bar2) as NavigationBar
        navigationBar.setTitle("忘记密码")
        navigationBar.hideLeftSecondIv()
        navigationBar.setBackClickListener(this)
    }

    @OnClick(R.id.tv_getcode, R.id.btn_send)
    fun onViewClicked(view: View) {

        val account = etAccount!!.text.toString()
        val telephone = etPhonenum!!.text.toString().trim { it <= ' ' }
        val verificationCode = etCode!!.text.toString().trim { it <= ' ' }
        val newPwd = etPasswd!!.text.toString().trim { it <= ' ' }
        val confirmPwd = etRePasswd!!.text.toString().trim { it <= ' ' }
        when (view.id) {
            R.id.tv_getcode ->

                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(telephone)) {
                    showToast("账号和手机号不能为空!")
                    return
                } else {
                    if (CommonUtil.isPhoneFormat(telephone)) {
                        tvGetcode!!.isEnabled = false
                        val countDownTimerUtils = CountDownTimerUtils(tvGetcode, 30000, 1000)
                        countDownTimerUtils.start()
                        showLoading()
                        HttpManager.getCode(account, telephone, object : ResultJSONObjectObserver(lifecycle) {
                            override fun onSuccess(result: JSONObject) {
                                showToast("验证码已发送!")
                                hideLoading()
                            }

                            override fun onFail(msg: String) {
                                tvGetcode!!.isEnabled = true
                                countDownTimerUtils.cancel()
                                countDownTimerUtils.onFinish()
                                showToast(msg)
                                hideLoading()

                            }
                        })
                    } else {
                        showToast("输入的手机号不正确,请重新输入!")

                    }
                }
            R.id.btn_send -> {
                if (TextUtils.isEmpty(account)) {
                    showToast("账号不能为空!")
                    return
                }
                if (TextUtils.isEmpty(telephone)) {
                    showToast("手机号不能为空!")
                    return
                }
                if (TextUtils.isEmpty(verificationCode)) {
                    showToast("验证码不能为空!")
                    return
                }
                if (TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(confirmPwd)) {
                    showToast("密码不能为空!")
                    return
                }
                if (newPwd != confirmPwd) {
                    showToast("2次输入的密码不同!")
                    return
                }
                if (!CommonUtil.isPassWordFormat(newPwd)) {
                    showToast("新密码格式不正确,密码是数字和字母的6-20位组合！")
                    return
                }
                if (!CommonUtil.isPassWordFormat(confirmPwd)) {
                    showToast("确认密码格式不正确，密码是数字和字母的6-20位组合！")
                    return
                }
                if (CommonUtil.isPhoneFormat(telephone)) {
                    showLoading()
                    HttpManager.resetPassword(account, telephone, verificationCode, newPwd, confirmPwd, object : ResultJSONObjectObserver(lifecycle) {
                        override fun onSuccess(result: JSONObject) {
                            hideLoading()
                            finish()
                        }

                        override fun onFail(msg: String) {
                            showToast(msg)
                            hideLoading()
                        }
                    })
                } else {
                    showToast("输入的手机号不正确,请重新输入！")
                }
            }
        }
    }
}
