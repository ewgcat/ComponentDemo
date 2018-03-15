package com.yijian.staff.mvp.mine.editpassword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPasswordActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_edit_password);
        ButterKnife.bind(this);

        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.edit_password_activity_navigation_bar);
        navigationBar.setTitle("修改密码", "#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));


    }

    @OnClick({R.id.tv_getcode, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_getcode:
                //TODO 获取验证码

                break;
            case R.id.btn_send:
                //TODO 发送修改密码请求

                break;
        }
    }
}
