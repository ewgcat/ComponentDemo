package com.yijian.staff.mvp.login;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.main.MainActivity;
import com.yijian.staff.net.requestbody.login.LoginRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.CommonUtil;

import org.json.JSONObject;

@Route(path = "/test/login")
public class LoginActivity extends MvcBaseActivity implements View.OnClickListener {


    private EditText etAccount;
    private EditText etPassword;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        etAccount.setText(SharePreferenceUtil.getUserName());
        etAccount.setHintTextColor(Color.parseColor("#7FC7FF"));
        etPassword.setHintTextColor(Color.parseColor("#7FC7FF"));

        findViewById(R.id.ll_login).setOnClickListener(this);
        findViewById(R.id.forget_password).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_login:
                login();
                break;
            case R.id.forget_password:
                jumpToForgetPassword();
                break;
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
                LoginRequestBody loginRequest = new LoginRequestBody(account, password);
                HttpManager.postLogin(loginRequest, new ResultJSONObjectObserver() {
                    @Override
                    public void onSuccess(JSONObject result) {
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
                        showToast(msg);
                    }
                });
            } else {
                showToast("密码格式错误，密码是数字和字母的6-20位组合！");
            }
        }
    }


}
