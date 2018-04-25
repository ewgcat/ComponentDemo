package com.yijian.staff.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;


/**
 * Created by ming on 2015/9/16.
 */
public class LoadingDialog extends Dialog {


    private LoadingDialog(Context context) {
        super(context);
    }

    private LoadingDialog(Context context, int theme) {
        super(context, theme);
        this.context=context;

    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private static LoadingDialog mDialog;
    Context context;

    public static LoadingDialog createProgressDialog(Context context) {
        mDialog = new LoadingDialog(context, R.style.loading_dialog_style);
        mDialog.setContentView(R.layout.view_custom_dialog);
        mDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        return mDialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (mDialog == null)
            return;
        ImageView imageView = (ImageView) mDialog.findViewById(R.id.iv_progress_dialog);
        Glide.with(context).load(R.drawable.loading).into(imageView);
    }

    public LoadingDialog setMessage(String s) {
        TextView textView = (TextView) mDialog.findViewById(R.id.tv_progress_dialog_text);
        if (s != null && !TextUtils.isEmpty(s)) {
            textView.setText(s);
        } else {
            textView.setText("");
        }
        return mDialog;
    }
}
