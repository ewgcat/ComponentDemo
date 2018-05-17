package com.yijian.staff.util;

import android.app.Activity;
import android.content.Context;

import com.yijian.staff.widget.BlueLoadingDialog;

public class LoadingProgressDialog {

    private static BlueLoadingDialog blueLoadingDialog;

    public static void showBlueProgress(Activity activity) {
        if (blueLoadingDialog == null) {
            blueLoadingDialog =new BlueLoadingDialog(activity);
        }
        if (blueLoadingDialog != null) {
            //防止弹出之前activity已经被销毁了
            if (!activity.isFinishing()) {
                blueLoadingDialog.show();
                blueLoadingDialog.setCancelable(false);
            }
        }

    }

    public static void hideBlueProgress(Activity activity) {
        if (blueLoadingDialog != null && blueLoadingDialog.isShowing()) {
            //防止显示期间activity已经被销毁了
            if (!activity.isFinishing()) {
                blueLoadingDialog.dismiss();
                blueLoadingDialog = null;
            }
        }
    }

    public static void showLoading(Activity activity){
        showBlueProgress(activity);
    }

    public static void hideLoading(Activity activity){
        hideBlueProgress(activity);
    }

}
