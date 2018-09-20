package module.login


import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.alibaba.android.arouter.launcher.ARouter
import com.jaeger.library.StatusBarUtil

import com.yijian.commonlib.base.mvc.MvcBaseActivity
import com.yijian.commonlib.db.DBManager
import com.yijian.commonlib.db.bean.User
import com.yijian.commonlib.net.response.ResultJSONObjectObserver
import com.yijian.commonlib.prefs.SharePreferenceUtil
import com.yijian.commonlib.util.*
import com.yijian.workspace.BuildConfig
import com.yijian.workspace.R
import com.yijian.workspace.net.HttpManagerWorkSpace
import module.LoginRequestBody

import org.json.JSONObject

class LoginActivity : MvcBaseActivity(), AndroidAdjustResizeBugFix.CallKeyBoardStatu, View.OnClickListener {
    internal lateinit var etAccount: EditText
    internal lateinit var etPassword: EditText
    internal lateinit var ll_content: LinearLayout
    internal lateinit var rel_container: RelativeLayout
    private var containerHeight: Float = 0F
    private var difference: Float = 0F
    private val TAG = LoginActivity::class.java.simpleName

    override fun getLayoutID(): Int {
        return R.layout.activity_login
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun initView(savedInstanceState: Bundle?) {
        etAccount = findViewById(R.id.et_account)
        etPassword = findViewById(R.id.et_password)
        ll_content = findViewById(R.id.ll_content)
        rel_container = findViewById(R.id.rel_container)
        findViewById<View>(R.id.ll_login).setOnClickListener(this)
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

        SharePreferenceUtil.setHostUrl(BuildConfig.WORKSPACE_HOST)
        SharePreferenceUtil.setImageUrl(BuildConfig.WORKSPACE_FILE_HOST)
        SharePreferenceUtil.setH5Url(BuildConfig.H5_HOST)

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
                HttpManagerWorkSpace.postLogin(loginRequest, object : ResultJSONObjectObserver(lifecycle) {
                    override fun onSuccess(result: JSONObject) {
                        hideLoading()
                        val user = User(result)
                        SharePreferenceUtil.setUserName(account)
                        SharePreferenceUtil.setUserId(user.userId)
                        SharePreferenceUtil.setUserRole(user.role)
                        DBManager.getInstance().insertOrReplaceUser(user)

                        ARouter.getInstance().build("/workspace/workspace").navigation()

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
                ObjectAnimator.ofFloat(ll_content, "translationY", -difference ).setDuration(300).start()
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
        }
    }


}