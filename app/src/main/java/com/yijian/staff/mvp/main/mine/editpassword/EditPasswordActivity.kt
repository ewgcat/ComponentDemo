package com.yijian.staff.mvp.main.mine.editpassword

import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView

import com.yijian.staff.R
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity
import com.yijian.staff.net.httpmanager.HttpManager
import com.yijian.staff.net.response.ResultJSONObjectObserver
import com.yijian.staff.prefs.SharePreferenceUtil
import com.yijian.staff.util.CommonUtil

import org.json.JSONObject

import java.util.HashMap

import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_edit_password.*
import kotlinx.android.synthetic.main.view_navigation_bar.*

class EditPasswordActivity : MvcBaseActivity() {



    override fun getLayoutID(): Int {
        return R.layout.activity_edit_password
    }

    override fun initView(savedInstanceState: Bundle?) {

        navigationBar.setTitle("修改密码")
        navigationBar.hideLeftSecondIv()
        navigationBar.setBackClickListener(this)

        val name = SharePreferenceUtil.getUserName()
        if (!TextUtils.isEmpty(name)) {
            tv_user_name.text = name
        }
    }


    @OnClick(R.id.btn_send)
    fun onViewClicked() {
        val username = tv_user_name.text.toString()
        val password = et_password.text.toString()
        if (TextUtils.isEmpty(password)) {
            showToast("原密码不能为空！")
            return
        }
        val psd = et_passwd.text.toString()
        if (TextUtils.isEmpty(psd)) {
            showToast("新密码不能为空！")
            return
        }
        val psd2 = et_re_passwd.text.toString()
        if (TextUtils.isEmpty(psd2)) {
            showToast("确认密码不能为空！")
            return
        }
        if (psd != psd2) {
            showToast("2次输入的密码不同")
            return
        }
        if (!CommonUtil.isPassWordFormat(psd)) {
            showToast("新密码格式不正确,密码是数字和字母的6-20位组合！")
            return
        }
        if (!CommonUtil.isPassWordFormat(psd2)) {
            showToast("确认密码格式不正确,密码是数字和字母的6-20位组合！")
            return
        }

        val params = HashMap<String, String>()
        params["newPwd"] = psd
        params["confirmPwd"] = psd2
        params["originalPwd"] = password
        params["username"] = username
        HttpManager.postHasHeaderHasParam(HttpManager.EDIT_PASSWORD_URL, params, object : ResultJSONObjectObserver(lifecycle) {
            override fun onSuccess(result: JSONObject) {
                setResult(4567)
                finish()
            }

            override fun onFail(msg: String) {
                showToast(msg)
            }
        })
    }
}
