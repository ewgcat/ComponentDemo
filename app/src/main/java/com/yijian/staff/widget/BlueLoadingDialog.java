package com.yijian.staff.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.yijian.staff.R;


/**
 * Created by ming on 2015/9/16.
 */
public class BlueLoadingDialog extends Dialog {


    private BlueRotateLoading loading;

    private BlueLoadingDialog(Context context) {
        super(context);
    }

    private BlueLoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    protected BlueLoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private static BlueLoadingDialog mDialog;

    public static BlueLoadingDialog createProgressDialog(Context context) {
        mDialog = new BlueLoadingDialog(context, R.style.loading_dialog_style);
        mDialog.setContentView(R.layout.view_blue_rotate_loading);
        mDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        return mDialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (mDialog == null)
            return;
        loading = (BlueRotateLoading) mDialog.findViewById(R.id.iv_progress_dialog);
        if (!loading.isStart()){
            loading.start();
        }
    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    public void hide() {
        super.hide();
        if (loading.isStart()){
            loading.stop();
        }
    }
}
