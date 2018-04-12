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
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.main.MainActivityMvp;
import com.yijian.staff.net.requestbody.login.LoginRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.prefs.SharePreferenceUtil;

import org.json.JSONObject;

@Route(path = "/test/login")
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
        etAccount.setText(SharePreferenceUtil.getUserName());
        etAccount.setHintTextColor(Color.parseColor("#7FC7FF"));
        etPassword.setHintTextColor(Color.parseColor("#7FC7FF"));

        findViewById(R.id.ll_login).setOnClickListener(this);
        findViewById(R.id.forget_password).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String account = etAccount.getText().toString();
        String password = etPassword.getText().toString();


        switch (id) {
            case R.id.ll_login:

                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    LoginRequestBody loginRequest = new LoginRequestBody(account, password);
                    HttpManager.postLogin(loginRequest, new ResultJSONObjectObserver() {
                        @Override
                        public void onSuccess(JSONObject result) {

                            User user = new User(result);
                            SharePreferenceUtil.setUserName(account);
                            SharePreferenceUtil.setUserId(user.getUserId());
                            SharePreferenceUtil.setUserRole(user.getRole());

                            DBManager.getInstance().insertOrReplaceUser(user);
                            Intent i = new Intent(LoginActivity.this, MainActivityMvp.class);
                            startActivity(i);
                            finish();


                        }

                        @Override
                        public void onFail(String msg) {
                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

                        }
                    });


                }


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
