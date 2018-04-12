package com.yijian.staff.mvp.base.mvc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * 无MVP的activity基类
 */

public abstract class MvcBaseActivity extends AppCompatActivity {

    public Context mContext;

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



    public void hideSoftKeyboard() {// 隐藏软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    public void showOrHideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
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
