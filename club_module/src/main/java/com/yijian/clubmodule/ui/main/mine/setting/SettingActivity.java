package com.yijian.clubmodule.ui.main.mine.setting;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tencent.bugly.beta.Beta;
import com.yijian.clubmodule.ui.main.mine.aboutus.AboutUsActivity;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.db.ClubDBManager;
import com.yijian.clubmodule.ui.main.mine.editpassword.EditPasswordActivity;
import com.yijian.commonlib.util.CommonUtil;



import cn.jpush.android.api.JPushInterface;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SettingActivity.class.getSimpleName();
    TextView tvVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(Color.WHITE);
        setContentView(R.layout.activity_setting);
        tvVersion = findViewById(R.id.tv_version);
        findViewById(R.id.ll_password).setOnClickListener(this);
        findViewById(R.id.ll_about).setOnClickListener(this);
        findViewById(R.id.ll_version).setOnClickListener(this);
        findViewById(R.id.tv_exit_login).setOnClickListener(this);


        initView();
    }


    protected void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.setting_activity_navigation_bar);
        navigationBar.setTitle("系统设置");
        navigationBar.setBackClickListener(this);
        navigationBar.hideLeftSecondIv();

        tvVersion.setText(CommonUtil.getVersionName(this) + " " + CommonUtil.getVersionCode(this));
    }

    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (ColorUtils.calculateLuminance(color) >= 0.5) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
            getWindow().setStatusBarColor(color);
        }
    }


    private void exitLogin() {
        //发送退出登录请求

        DBManager.getInstance().clearUser();
        ClubDBManager.getInstance().clearRoleVoBean();
        ClubDBManager.getInstance().clearOthermodelVo();
        JPushInterface.stopPush(this);
        setResult(1234);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 4567) {
            setResult(1234);
            finish();

        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ll_password) {
            startActivityForResult(new Intent(SettingActivity.this, EditPasswordActivity.class), 1124);

        } else if (i == R.id.ll_about) {
            startActivity(new Intent(SettingActivity.this, AboutUsActivity.class));

        } else if (i == R.id.ll_version) {
            Beta.checkUpgrade();

        } else if (i == R.id.tv_exit_login) {
            exitLogin();

        }
    }
}
