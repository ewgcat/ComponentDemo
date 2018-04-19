package com.yijian.staff.mvp.splash;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.login.LoginActivity;
import com.yijian.staff.mvp.main.MainActivity;
import com.yijian.staff.rx.RxUtil;
import com.yijian.staff.util.NotificationsUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;


public class SplashActivity extends AppCompatActivity {

    String[] permissions = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private int index = 0;


    @BindView(R.id.iv_splash_bg)
    ImageView ivSplashBg;
    @BindView(R.id.tv_splash_author)
    TextView tvSplashAuthor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        initRxPermissions(index, permissions);
    }


    public void jumpToNext() {
        User user = DBManager.getInstance().queryUser();
        if (user != null) {
            String token = user.getToken().trim();
            if (TextUtils.isEmpty(token)) {
                Intent intent = new Intent();
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else {

                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }


    }


    /**
     * 运行时请求权限
     */
    private void initRxPermissions(int i, String[] permissions) {
        RxPermissions rxPermissions = new RxPermissions(this);
        Observable.timer(200, TimeUnit.MILLISECONDS)
                .compose(rxPermissions.ensureEach(
                        permissions[i]
                ))
                .compose(RxUtil.rxObservableSchedulerHelper())
                .subscribe(permission -> {
                    index = i + 1;
                    if (permissions.length > index) {
                        initRxPermissions(index, permissions);
                    } else {
                        String msg = checkPermissions();
                        if (!TextUtils.isEmpty(msg) && msg.contains("【通知与状态栏权限】")) {
                            requestPermissions("    【通知与状态栏权限】\n");
                        } else {
                            jumpToNext();
                        }
                    }
                });
    }

    /**
     * 请求通知权限  100
     */
    private void requestPermissions(String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(SplashActivity.this, R.style.AlertDialogCustom))

                .setTitle("获取必要权限")
                .setMessage(msg)
                .setPositiveButton("立即获取", (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivityForResult(intent, 100);
                })
                .setNegativeButton("拒绝", (dialog, which) -> {
                    jumpToNext();
                }).create();

        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    private String checkPermissions() {
        String msg = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !NotificationsUtil.isNotificationEnabled(this)) {
            msg = "    【通知与状态栏权限】\n";
        }
        return msg;
    }


    /**
     * 请求权限回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                String msg = checkPermissions();
                if (!TextUtils.isEmpty(msg)) {
                    Toast.makeText(this, "缺少" + msg, Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();

                break;
        }
    }

    protected long mExitTime;

    /**
     * 监听按键的点击
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if ((secondTime - mExitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次返回键退出程序!", Toast.LENGTH_SHORT).show();
                mExitTime = secondTime;
                return true;
            } else {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
        return true;
    }


}
