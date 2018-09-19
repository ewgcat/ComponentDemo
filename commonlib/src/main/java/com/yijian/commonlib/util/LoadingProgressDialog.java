package com.yijian.commonlib.util;

import android.app.Activity;

import com.yijian.commonlib.widget.WhiteLoadingDialog;


public class LoadingProgressDialog {

    private static WhiteLoadingDialog whiteLoadingDialog;

    public static void showBlueProgress(Activity activity) {
        if (whiteLoadingDialog == null) {
            whiteLoadingDialog = new WhiteLoadingDialog(activity);
        }
        if (whiteLoadingDialog != null) {
            //防止弹出之前activity已经被销毁了
            if (!activity.isFinishing()) {
                whiteLoadingDialog.show();
                whiteLoadingDialog.setCancelable(false);
            }
        }

    }

    public static void hideBlueProgress(Activity activity) {
        if (whiteLoadingDialog != null && whiteLoadingDialog.isShowing()) {
            //防止显示期间activity已经被销毁了
            if (!activity.isFinishing()) {
                whiteLoadingDialog.dismiss();
                whiteLoadingDialog = null;
            }
        }
    }

    public static void showLoading(Activity activity) {
        showBlueProgress(activity);
    }

    public static void hideLoading(Activity activity) {
        hideBlueProgress(activity);
    }

}
