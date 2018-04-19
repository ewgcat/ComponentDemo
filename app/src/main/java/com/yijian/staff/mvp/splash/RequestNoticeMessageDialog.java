package com.yijian.staff.mvp.splash;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.classbaojia.filter.CoachClassFilterBean;
import com.yijian.staff.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestNoticeMessageDialog extends Dialog {

    private static String TAG = RequestNoticeMessageDialog.class.getSimpleName();




    public RequestNoticeMessageDialog(Activity activity) {
        super(activity, R.style.Transparent);
        setOwnerActivity(activity);
        View contentView = LayoutInflater.from(activity).inflate(R.layout.view_coach_class_filter, null);
        int statusBarHeight = CommonUtil.getStatusBarHeight(activity);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0, statusBarHeight, 0, 0);
        contentView.setLayoutParams(params);


        Window window = getWindow();
        //设置无标题栏
        window.requestFeature(Window.FEATURE_NO_TITLE);
        //背景为透明
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#602f2f2f")));
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //显示隐藏的动画效果
        layoutParams.windowAnimations = R.style.MyDialogAnimationCenter;
        params.gravity = Gravity.RIGHT;

        this.setContentView(contentView);

        ButterKnife.bind(this, contentView);


        initView();
    }


    private void initView() {
        setCanceledOnTouchOutside(false);
    }


    public void showFilterDialog() {
        show();
    }



}