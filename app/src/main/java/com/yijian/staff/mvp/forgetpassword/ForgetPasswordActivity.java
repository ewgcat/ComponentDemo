package com.yijian.staff.mvp.forgetpassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_phonenum)
    EditText etPhonenum;

    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.et_passwd)
    EditText etPasswd;
    @BindView(R.id.et_re_passwd)
    EditText etRePasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);

        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.forget_password_activity_navigation_bar);
        navigationBar.setTitle("忘记密码", "#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));


    }

    @OnClick({R.id.tv_getcode, R.id.btn_send})
    public void onViewClicked(View view) {

        String account = etAccount.getText().toString();
        String telephone = etPhonenum.getText().toString().trim();
        switch (view.getId()) {
            case R.id.tv_getcode:

                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(telephone)) {
                    Toast.makeText(ForgetPasswordActivity.this, "账号和手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (CommonUtil.isPhoneFormat(telephone)){
                        HttpManager.getCode(account, telephone, new ResultObserver() {
                            @Override
                            public void onSuccess(JSONObject result) {


                            }

                            @Override
                            public void onFail(String msg) {

                            }
                        });
                    }else {
                        Toast.makeText(ForgetPasswordActivity.this, "输入的手机号不正确,请重新输入！", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.btn_send:
                break;
        }
    }
}
