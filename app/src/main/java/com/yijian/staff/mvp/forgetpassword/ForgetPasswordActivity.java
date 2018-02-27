package com.yijian.staff.mvp.forgetpassword;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.commonlibrary.BaseActivity;
import com.example.commonlibrary.widget.NavigationBar;
import com.example.commonlibrary.widget.NavigationBarItemFactory;
import com.yijian.staff.R;
public class ForgetPasswordActivity extends BaseActivity<Object,ForgetPasswordPresenter>  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //从登录界面传过来的账号
        String account = getIntent().getStringExtra("account");
        if (TextUtils.isEmpty(account)){
            //TODO 账号输入框不用设置默认值

        }else {

            //TODO 账号输入框设置默认值
        }
    }



    @Override
    protected boolean isNeedEmptyLayout() {
        return false;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initView() {
        NavigationBar navigationBar= (NavigationBar) findViewById(R.id.forget_password_activity_navigation_bar);
        navigationBar.setTitle("忘记密码","#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
    }


    @Override
    protected void initData() {

    }

    //验证码，提交修改密码
    @Override
    public void onClick(View v) {

    }

    @Override
    public void updateData(Object o) {

    }
}
