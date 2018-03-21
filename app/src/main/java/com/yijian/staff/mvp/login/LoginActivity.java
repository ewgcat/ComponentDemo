package com.yijian.staff.mvp.login;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.yijian.staff.R;
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.main.MainActivity;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.prefs.SharePreferenceUtil;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText etAccount;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        initView();
    }

    protected void initView() {

        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);

        etAccount.setHintTextColor(Color.parseColor("#7FC7FF"));
        etPassword.setHintTextColor(Color.parseColor("#7FC7FF"));

        findViewById(R.id.ll_login).setOnClickListener(this);
        findViewById(R.id.forget_password).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String account = etAccount.getText().toString();

        switch (id) {
            case R.id.ll_login:
//                JSONObject jsonObject = new JSONObject();
//                HttpManager.postLogin(jsonObject, new ResultObserver() {
//                    @Override
//                    public void onSuccess(JSONObject result) {
//
//                    }
//
//                    @Override
//                    public void onFail(String msg) {
//
//                    }
//                });

                if (!TextUtils.isEmpty(account)) {
                    if (account.equals("0")) {
                        SharePreferenceUtil.setUserRole(0);
                    } else if (account.equals("1")) {
                        SharePreferenceUtil.setUserRole(1);
                    } else if (account.equals("2")) {
                        SharePreferenceUtil.setUserRole(2);
                    } else if (account.equals("3")) {
                        SharePreferenceUtil.setUserRole(3);
                    } else if (account.equals("4")) {
                        SharePreferenceUtil.setUserRole(4);
                    } else if (account.equals("5")) {
                        SharePreferenceUtil.setUserRole(5);
                    } else if (account.equals("6")) {
                        SharePreferenceUtil.setUserRole(6);
                    }
                }
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);

                break;
            case R.id.forget_password:
                //TODO 忘记密码
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);

                break;
        }
    }

}
