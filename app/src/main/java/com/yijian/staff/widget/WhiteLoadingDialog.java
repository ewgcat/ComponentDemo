package com.yijian.staff.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.yijian.staff.R;


/**
 * Created by ming on 2015/9/16.
 */
public class WhiteLoadingDialog extends Dialog {


    private WhiteRotateLoading loading;

    private WhiteLoadingDialog(Context context) {
        super(context);
    }

    private WhiteLoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    protected WhiteLoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private static WhiteLoadingDialog mDialog;

    public static WhiteLoadingDialog createProgressDialog(Context context) {
        mDialog = new WhiteLoadingDialog(context, R.style.loading_dialog_style);
        mDialog.setContentView(R.layout.view_custom_dialog);
        mDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        return mDialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (mDialog == null)
            return;
        loading = (WhiteRotateLoading) mDialog.findViewById(R.id.iv_progress_dialog);
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
