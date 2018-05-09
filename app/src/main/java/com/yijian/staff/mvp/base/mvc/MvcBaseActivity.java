package com.yijian.staff.mvp.base.mvc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.yijian.staff.widget.BlueLoadingDialog;
import com.yijian.staff.widget.WhiteLoadingDialog;

import butterknife.ButterKnife;

/**
 * 无MVP的activity基类
 */

public abstract class MvcBaseActivity extends AppCompatActivity {

    public Context mContext;

    protected BlueLoadingDialog blueLoadingDialog;


    public void showBlueProgress() {
        if (blueLoadingDialog == null) {
            blueLoadingDialog =new BlueLoadingDialog(this);
        }
        if (blueLoadingDialog != null) {
            //防止弹出之前activity已经被销毁了
            if (!this.isFinishing()) {
                blueLoadingDialog.show();
                blueLoadingDialog.setCancelable(false);
            }
        }

    }

    public void hideBlueProgress() {
        if (blueLoadingDialog != null && blueLoadingDialog.isShowing()) {
            //防止显示期间activity已经被销毁了
            if (!this.isFinishing()) {
                blueLoadingDialog.dismiss();
                blueLoadingDialog = null;
            }
        }
    }

    protected WhiteLoadingDialog whiteLoadingDialog;


    public void showWhiteProgress() {
        if (whiteLoadingDialog == null) {
            whiteLoadingDialog =new  WhiteLoadingDialog(this);
        }
        if (whiteLoadingDialog != null) {
            //防止弹出之前activity已经被销毁了
            if (!this.isFinishing()) {
                whiteLoadingDialog.show();
                whiteLoadingDialog.setCancelable(false);
            }
        }

    }

    public void hideWhiteProgress() {
        if (whiteLoadingDialog != null && whiteLoadingDialog.isShowing()) {
            //防止显示期间activity已经被销毁了
            if (!this.isFinishing()) {
                whiteLoadingDialog.dismiss();
                whiteLoadingDialog = null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        mContext = this;
        initView(savedInstanceState);
    }

    protected abstract int getLayoutID();

    protected void onViewCreated() {

    }

    protected abstract void initView(Bundle savedInstanceState);


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
