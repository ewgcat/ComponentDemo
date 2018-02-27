package com.example.commonlibrary.utils;

import android.content.Context;

/**
 * Created by tdd
 * on 2016/10/28 0028
 * description: 对Toast简单封装
 */


public class Toast {

    public static android.widget.Toast mToast;

    public static void showShort(Context context, String msg) {
        Toast.cancel();
        if (mToast == null) {
            mToast = android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showLong(Context context, String msg) {
        Toast.cancel();
        if (mToast == null) {
            mToast = android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
}
