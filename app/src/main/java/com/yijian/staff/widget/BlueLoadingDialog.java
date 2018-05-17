package com.yijian.staff.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.yijian.staff.R;


/**
 * Created by ming on 2015/9/16.
 */
public class BlueLoadingDialog extends Dialog {


    private BlueRotateLoading loading;



    private Activity activity;
    private final View contentView;


    public BlueLoadingDialog(Activity activity) {
        super(activity, R.style.Transparent);
        setOwnerActivity(activity);
        contentView = LayoutInflater.from(activity).inflate(R.layout.view_blue_rotate_loading, null);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(params);
        this.setContentView(contentView);
        Window dialogWindow = this.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (contentView == null)
            return;
        loading = (BlueRotateLoading) contentView.findViewById(R.id.iv_progress_dialog);
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
