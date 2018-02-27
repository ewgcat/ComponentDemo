package com.yijian.staff.mvp.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commonlibrary.BaseActivity;
import com.yijian.staff.R;
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.main.MainActivity;

public class LoginActivity extends BaseActivity<Object,LoginPresenter> implements View.OnClickListener{

    private EditText etAccount;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉信息栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @Override
    protected int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected  void initView() {

        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);

        etAccount.setHintTextColor(Color.parseColor("#7FC7FF"));
        etPassword.setHintTextColor(Color.parseColor("#7FC7FF"));

        findViewById(R.id.ll_login).setOnClickListener(this);
        findViewById(R.id.forget_password).setOnClickListener(this);
    }




    @Override
    protected boolean isNeedEmptyLayout() {
        return false;
    }

    @Override
    protected void initData() {

        //todo 读取本地保存的账号和密码，显示



        //todo 自动登录
    }





    @Override
    public void onClick(View v) {
        int id = v.getId();
        String account = etAccount.getText().toString();

        switch (id){
            case R.id.ll_login:
//                if (TextUtils.isEmpty(account)){
//                    Toast.makeText(LoginActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                String password = etPassword.getText().toString();
//                if (TextUtils.isEmpty(password)){
//                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                //TODO 发送登录请求
//                presenter.login(account,password);
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);

                break;
            case R.id.forget_password:
                //TODO 忘记密码
                Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                intent.putExtra("account",account);
                startActivity(intent);

                break;
        }
    }

    @Override
    public void updateData(Object o) {

    }
}
