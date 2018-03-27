package com.yijian.staff.mvp.coach.classbaojia.filter;

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
import com.yijian.staff.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoachClassFilterDialog extends Dialog implements DialogInterface.OnDismissListener {

    private static String TAG = CoachClassFilterDialog.class.getSimpleName();

    @BindView(R.id.tv_price1)
    TextView tvPrice1;
    @BindView(R.id.tv_price2)
    TextView tvPrice2;
    @BindView(R.id.tv_price3)
    TextView tvPrice3;
    @BindView(R.id.tv_price4)
    TextView tvPrice4;

    @BindView(R.id.tv_class_jieshu1)
    TextView tvClassJieshu1;
    @BindView(R.id.tv_class_jieshu2)
    TextView tvClassJieshu2;
    @BindView(R.id.tv_class_jieshu3)
    TextView tvClassJieshu3;

    @BindView(R.id.tv_longtime1)
    TextView tvLongtime1;
    @BindView(R.id.tv_longtime2)
    TextView tvLongtime2;
    @BindView(R.id.tv_longtime3)
    TextView tvLongtime3;
    @BindView(R.id.tv_longtime4)
    TextView tvLongtime4;

    @BindView(R.id.tv_youxiaoqi1)
    TextView tvYouxiaoqi1;
    @BindView(R.id.tv_youxiaoqi2)
    TextView tvYouxiaoqi2;
    @BindView(R.id.tv_youxiaoqi3)
    TextView tvYouxiaoqi3;

    @BindView(R.id.empty_view)
    View emptyView;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    private Activity activity;
    private int price;
    private int classJieShu;
    private int classLongTime;
    private int classYouXiaoQi;


    public CoachClassFilterDialog(Activity activity) {
        super(activity, R.style.Transparent);
        setOwnerActivity(activity);
        this.activity = activity;
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
        this.setOnDismissListener(this);


        initView();
    }


    private void initView() {
        resetView();
        setCanceledOnTouchOutside(false);
    }


    public void showFilterDialog() {
        show();
    }


    private void resetView() {
        tvClassJieshu1.setTextColor(Color.parseColor("#666666"));
        tvClassJieshu2.setTextColor(Color.parseColor("#666666"));
        tvClassJieshu3.setTextColor(Color.parseColor("#666666"));

        tvYouxiaoqi1.setTextColor(Color.parseColor("#666666"));
        tvYouxiaoqi2.setTextColor(Color.parseColor("#666666"));
        tvYouxiaoqi3.setTextColor(Color.parseColor("#666666"));

        tvPrice1.setTextColor(Color.parseColor("#666666"));
        tvPrice2.setTextColor(Color.parseColor("#666666"));
        tvPrice3.setTextColor(Color.parseColor("#666666"));
        tvPrice4.setTextColor(Color.parseColor("#666666"));

        tvLongtime1.setTextColor(Color.parseColor("#666666"));
        tvLongtime2.setTextColor(Color.parseColor("#666666"));
        tvLongtime3.setTextColor(Color.parseColor("#666666"));
        tvLongtime4.setTextColor(Color.parseColor("#666666"));

        tvClassJieshu1.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvClassJieshu2.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvClassJieshu3.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvYouxiaoqi1.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvYouxiaoqi2.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvYouxiaoqi3.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvPrice1.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice2.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice3.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice4.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvLongtime1.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvLongtime2.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvLongtime3.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvLongtime4.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));


        tvLongtime1.setCompoundDrawables(null, null, null, null);
        tvLongtime2.setCompoundDrawables(null, null, null, null);
        tvLongtime3.setCompoundDrawables(null, null, null, null);
        tvLongtime4.setCompoundDrawables(null, null, null, null);
        tvPrice1.setCompoundDrawables(null, null, null, null);
        tvPrice2.setCompoundDrawables(null, null, null, null);
        tvPrice3.setCompoundDrawables(null, null, null, null);
        tvPrice4.setCompoundDrawables(null, null, null, null);

        tvClassJieshu1.setCompoundDrawables(null, null, null, null);
        tvClassJieshu2.setCompoundDrawables(null, null, null, null);
        tvClassJieshu3.setCompoundDrawables(null, null, null, null);
        tvYouxiaoqi1.setCompoundDrawables(null, null, null, null);
        tvYouxiaoqi2.setCompoundDrawables(null, null, null, null);
        tvYouxiaoqi3.setCompoundDrawables(null, null, null, null);
    }

    private void setSelectStyle(TextView textView) {
        textView.setTextColor(Color.parseColor("#1997f8"));
        textView.setBackground(getContext().getDrawable(R.drawable.blue_stroke_select_bg));
        Drawable jd_choose = getContext().getResources().getDrawable(R.mipmap.jd_choose);
        jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
        textView.setCompoundDrawables(jd_choose, null, null, null);

    }

    private void setUnSelectStyle(TextView textView) {
        textView.setTextColor(Color.parseColor("#666666"));
        textView.setBackgroundColor(Color.parseColor("#f2f2f2"));
        textView.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        textView.setCompoundDrawables(null, null, null, null);
    }


    @OnClick({R.id.tv_reset, R.id.tv_confirm, R.id.tv_price1, R.id.tv_price2, R.id.tv_price3, R.id.tv_price4, R.id.empty_view, R.id.tv_class_jieshu1, R.id.tv_class_jieshu2, R.id.tv_class_jieshu3, R.id.tv_longtime1, R.id.tv_longtime2, R.id.tv_longtime3, R.id.tv_longtime4, R.id.tv_youxiaoqi1, R.id.tv_youxiaoqi2, R.id.tv_youxiaoqi3})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //课程节数
            case R.id.tv_class_jieshu1:
                selectClassJieShu(1);
                break;
            case R.id.tv_class_jieshu2:
                selectClassJieShu(2);
                break;
            case R.id.tv_class_jieshu3:
                selectClassJieShu(3);
                break;

            //价格区间
            case R.id.tv_price1:
                selectPrice(1);
                break;
            case R.id.tv_price2:
                selectPrice(2);
                break;
            case R.id.tv_price3:
                selectPrice(3);
                break;
            case R.id.tv_price4:
                selectPrice(4);
                break;

            //单节时长
            case R.id.tv_longtime1:
                selectClassLongTime(1);
                break;
            case R.id.tv_longtime2:
                selectClassLongTime(2);
                break;
            case R.id.tv_longtime3:
                selectClassLongTime(3);
                break;
            case R.id.tv_longtime4:
                selectClassLongTime(4);
                break;

            //有效期
            case R.id.tv_youxiaoqi1:
                selectClassYouXiaoQi(1);
                break;
            case R.id.tv_youxiaoqi2:
                selectClassYouXiaoQi(2);
                break;
            case R.id.tv_youxiaoqi3:
                selectClassYouXiaoQi(3);
                break;

            case R.id.tv_reset:
                resetView();
                break;
            case R.id.empty_view:
            case R.id.tv_confirm:
                dismiss();
                break;
        }
    }

    private void selectPrice(int i) {
        if (i == 1) {
            price = 1;
            setSelectStyle(tvPrice1);
            setUnSelectStyle(tvPrice2);
            setUnSelectStyle(tvPrice3);
            setUnSelectStyle(tvPrice4);
        } else if (i == 2) {
            price = 2;

            setSelectStyle(tvPrice2);
            setUnSelectStyle(tvPrice1);
            setUnSelectStyle(tvPrice3);
            setUnSelectStyle(tvPrice4);
        } else if (i == 3) {
            price = 3;

            setSelectStyle(tvPrice3);
            setUnSelectStyle(tvPrice1);
            setUnSelectStyle(tvPrice2);
            setUnSelectStyle(tvPrice4);
        } else if (i == 4) {
            price = 4;

            setSelectStyle(tvPrice4);
            setUnSelectStyle(tvPrice1);
            setUnSelectStyle(tvPrice2);
            setUnSelectStyle(tvPrice3);
        }
    }

    private void selectClassJieShu(int i) {
        if (i == 1) {
            classJieShu = 1;
            setSelectStyle(tvClassJieshu1);
            setUnSelectStyle(tvClassJieshu2);
            setUnSelectStyle(tvClassJieshu3);
        } else if (i == 2) {
            classJieShu = 1;

            setSelectStyle(tvClassJieshu2);
            setUnSelectStyle(tvClassJieshu1);
            setUnSelectStyle(tvClassJieshu3);
        } else if (i == 3) {
            classJieShu = 1;

            setSelectStyle(tvClassJieshu3);
            setUnSelectStyle(tvClassJieshu1);
            setUnSelectStyle(tvClassJieshu2);
        }
    }


    private void selectClassLongTime(int i) {

        if (i == 1) {
            classLongTime = 1;
            setSelectStyle(tvLongtime1);
            setUnSelectStyle(tvLongtime2);
            setUnSelectStyle(tvLongtime3);
            setUnSelectStyle(tvLongtime4);
        } else if (i == 2) {
            classLongTime = 2;

            setSelectStyle(tvLongtime2);
            setUnSelectStyle(tvLongtime1);
            setUnSelectStyle(tvLongtime3);
            setUnSelectStyle(tvLongtime4);
        } else if (i == 3) {
            classLongTime = 3;

            setSelectStyle(tvLongtime3);
            setUnSelectStyle(tvLongtime1);
            setUnSelectStyle(tvLongtime2);
            setUnSelectStyle(tvLongtime4);
        } else if (i == 4) {
            classLongTime = 4;

            setSelectStyle(tvLongtime4);
            setUnSelectStyle(tvLongtime1);
            setUnSelectStyle(tvLongtime2);
            setUnSelectStyle(tvLongtime3);
        }
    }

    private void selectClassYouXiaoQi(int i) {
        if (i == 1) {
            classYouXiaoQi = 1;
            setSelectStyle(tvYouxiaoqi1);
            setUnSelectStyle(tvYouxiaoqi2);
            setUnSelectStyle(tvYouxiaoqi3);
        } else if (i == 2) {
            classYouXiaoQi = 2;
            setSelectStyle(tvYouxiaoqi2);
            setUnSelectStyle(tvYouxiaoqi1);
            setUnSelectStyle(tvYouxiaoqi3);
        } else if (i == 3) {
            classYouXiaoQi = 3;
            setSelectStyle(tvYouxiaoqi3);
            setUnSelectStyle(tvYouxiaoqi1);
            setUnSelectStyle(tvYouxiaoqi2);
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        CoachClassFilterBean coachClassFilterBean = new CoachClassFilterBean();
        coachClassFilterBean.setClassJieShu(classJieShu);
        coachClassFilterBean.setClassLongTime(classLongTime);
        coachClassFilterBean.setPrice(price);
        coachClassFilterBean.setClassYouXiaoQi(classYouXiaoQi);
        if (onDismissListener!=null){
            onDismissListener.onDismiss(coachClassFilterBean);
        }
    }

    public interface OnDismissListener {
        void onDismiss(CoachClassFilterBean coachClassFilterBean);
    }

    private CoachClassFilterDialog.OnDismissListener onDismissListener;

    public void setOnDismissListener(CoachClassFilterDialog.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }
}
