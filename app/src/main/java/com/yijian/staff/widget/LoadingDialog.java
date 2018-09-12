package com.yijian.staff.widget;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;


/**
 * Created by ming on 2015/9/16.
 */
public class LoadingDialog extends Dialog {


    private ImageView loading;


    private Activity activity;
    private final View contentView;


    public LoadingDialog(Activity activity) {
        super(activity, R.style.Transparent);
        setOwnerActivity(activity);
        contentView = LayoutInflater.from(activity).inflate(R.layout.view_loading, null);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(params);
        this.setContentView(contentView);
        loading = (ImageView) contentView.findViewById(R.id.iv_loading);
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(R.drawable.loading).into(loading);
        }else {
            Glide.with(CustomApplication.instance.getApplicationContext()).load(R.drawable.loading).into(loading);
        }
        Window dialogWindow = this.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    public void hide() {
        super.hide();

    }
}
