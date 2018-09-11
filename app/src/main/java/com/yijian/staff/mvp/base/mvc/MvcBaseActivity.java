package com.yijian.staff.mvp.base.mvc;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.widget.LoadingDialog;
import com.yijian.staff.widget.WhiteLoadingDialog;

import butterknife.ButterKnife;

/**
 * 无MVP的activity基类
 */

public abstract class MvcBaseActivity extends AppCompatActivity {

    public Context mContext;

    protected LoadingDialog loadingDialog;

    private int DEFAULT_STATUS_BAR_COLOR=Color.WHITE;



    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        if (loadingDialog != null) {
            //防止弹出之前activity已经被销毁了
            if (!this.isFinishing()) {
                loadingDialog.show();
                loadingDialog.setCancelable(false);
            }
        }

    }

    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            //防止显示期间activity已经被销毁了
            if (!this.isFinishing()) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }
        }
    }






    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(DEFAULT_STATUS_BAR_COLOR);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        mContext = this;
        initView(savedInstanceState);
        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();
        if (upgradeInfo != null) {
            int versionCode = upgradeInfo.versionCode;
            if (versionCode > BuildConfig.VERSION_CODE) {
                Beta.checkUpgrade(false, false);
            }
        }

    }

    protected abstract int getLayoutID();

    protected void onViewCreated() {

    }

    protected void initView(Bundle savedInstanceState) {
    }


    /**
     * 隐藏键盘
     */
    public void hideKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 显示键盘
     */
    public void showKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(v, 0);

        }
    }


    public void showToast(int id) {
        showToast(getString(id));
    }

    private Toast toast;

    public void showToast(String msg) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast(String msg, int time) {
        Toast.makeText(this, msg, time).show();
    }

    public <T extends View> T findView(int viewId) {
        return (T) this.findViewById(viewId);
    }

    public <T extends View> T findView(View view, int viewId) {
        return (T) view.findViewById(viewId);
    }


    /**
     * 设置沉浸状态栏
     */
    protected void setImmersionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    protected int getStatusBarHeight() {
        int height = 0;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            height = getResources().getDimensionPixelSize(resourceId);
        }
        return height;
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



}
