package com.yijian.staff.mvp.main.mine.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tencent.bugly.beta.Beta;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.mvp.main.mine.aboutus.AboutUsActivity;
import com.yijian.staff.mvp.main.mine.editpassword.EditPasswordActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.NavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class SettingActivity extends AppCompatActivity {

    private static final String TAG = SettingActivity.class.getSimpleName();
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ButterKnife.bind(this);
        initView();
    }


    protected void initView() {
        NavigationBar NavigationBar = (NavigationBar) findViewById(R.id.setting_activity_navigation_bar2);
        NavigationBar.setTitle("系统设置");
        NavigationBar.setBackClickListener(this);
        NavigationBar.hideLeftSecondIv();

        tvVersion.setText(CommonUtil.getVersionName(this) + " " + CommonUtil.getVersionCode(this));
    }


    @OnClick({R.id.ll_password, R.id.ll_about, R.id.ll_version,R.id.tv_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_password:
                startActivityForResult(new Intent(SettingActivity.this, EditPasswordActivity.class), 1124);
                break;
            case R.id.ll_about:
                startActivity(new Intent(SettingActivity.this, AboutUsActivity.class));
                break;
            case R.id.ll_version:
                Beta.checkUpgrade();
                break;
            case R.id.tv_exit_login:
                exitLogin();
                break;
        }
    }



    private void exitLogin() {
        //发送退出登录请求

        DBManager.getInstance().clearUser();
        DBManager.getInstance().clearRoleVoBean();
        DBManager.getInstance().clearOthermodelVo();
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
}
