package com.yijian.staff.mvp.base.mvc;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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


}
