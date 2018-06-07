package com.yijian.staff.mvp.user.login;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.user.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.main.MainActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.login.LoginRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.AndroidKeyBoardAssit;
import com.yijian.staff.util.CommonUtil;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/test/login")
public class LoginActivity extends MvcBaseActivity {


    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;


    private boolean hasStartAnimation = false;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        ll_content = findViewById(R.id.ll_content);
        etAccount.setText(SharePreferenceUtil.getUserName());
        etAccount.setHintTextColor(Color.parseColor("#7FC7FF"));
        etPassword.setHintTextColor(Color.parseColor("#7FC7FF"));


        AndroidKeyBoardAssit.assistActivity(this);
        ll_content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = ll_content.getRootView().getHeight() - ll_content.getHeight();
                if (heightDiff > CommonUtil.dp2px(LoginActivity.this, 1)) {
                    // 显示软键盘
                    startAnimation();
                } else {
                    //隐藏软键盘
                    endAnimation();
                }
            }
        });

    }

    private void startAnimation() {
        if (!hasStartAnimation) {
            hasStartAnimation = true;
            int i = CommonUtil.dp2px(this, 200);
            ObjectAnimator animator = ObjectAnimator.ofFloat(ll_content, "translationY", 0, -i);
            animator.setDuration(500);
            animator.start();

        }
    }

    private void endAnimation() {
        if (hasStartAnimation) {
            hasStartAnimation = false;
            int i = CommonUtil.dp2px(this, 200);
            ObjectAnimator animator = ObjectAnimator.ofFloat(ll_content, "translationY", -i, 0);
            animator.setDuration(500);
            animator.start();
        }

    }


    private void jumpToForgetPassword() {
        String account = etAccount.getText().toString();
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        intent.putExtra("account", account);
        startActivity(intent);
    }

    private void login() {
        String account = etAccount.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            showToast("账号和密码不能为空");
        } else {
            if (CommonUtil.isPassWordFormat(password)) {
                showLoading();
                LoginRequestBody loginRequest = new LoginRequestBody(account, password);
                HttpManager.postLogin(loginRequest, new ResultJSONObjectObserver() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        hideLoading();
                        User user = new User(result);
                        SharePreferenceUtil.setUserName(account);
                        SharePreferenceUtil.setUserId(user.getUserId());
                        SharePreferenceUtil.setUserRole(user.getRole());
                        DBManager.getInstance().insertOrReplaceUser(user);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onFail(String msg) {
                        hideLoading();
                        showToast(msg);
                    }
                });
            } else {
                showToast("密码格式错误，密码是数字和字母的6-20位组合！");
            }
        }
    }


    @OnClick({R.id.ll_login, R.id.forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_login:
                login();
                break;
            case R.id.forget_password:
                jumpToForgetPassword();
                break;
        }
    }
}
