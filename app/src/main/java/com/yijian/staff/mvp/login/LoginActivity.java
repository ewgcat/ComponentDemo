package com.yijian.staff.mvp.login;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.yijian.staff.R;
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText etAccount;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        initView();
    }

    protected  void initView() {

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

        switch (id){
            case R.id.ll_login:
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

}
