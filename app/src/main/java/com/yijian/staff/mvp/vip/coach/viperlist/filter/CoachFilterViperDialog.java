package com.yijian.staff.mvp.vip.coach.viperlist.filter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.util.CommonUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoachFilterViperDialog extends Dialog implements DialogInterface.OnDismissListener {

    private static String TAG = CoachFilterViperDialog.class.getSimpleName();
    @BindView(R.id.tv_sex_man)
    TextView tvSexMan;
    @BindView(R.id.ll_sex_man)
    LinearLayout llSexMan;
    @BindView(R.id.tv_sex_woman)
    TextView tvSexWoman;
    @BindView(R.id.ll_sex_woman)
    LinearLayout llSexWoman;
    @BindView(R.id.tv_sijiao_class)
    TextView tvSijiaoClass;
    @BindView(R.id.tv_tiyan_class)
    TextView tvTiyanClass;
    @BindView(R.id.tv_time1)
    TextView tvTime1;
    @BindView(R.id.tv_time2)
    TextView tvTime2;
    @BindView(R.id.tv_time3)
    TextView tvTime3;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_buy_time1)
    TextView tvBuyTime1;
    @BindView(R.id.tv_buy_time2)
    TextView tvBuyTime2;
    @BindView(R.id.tv_buy_time3)
    TextView tvBuyTime3;
    @BindView(R.id.tv_day1)
    TextView tvDay1;
    @BindView(R.id.tv_day2)
    TextView tvDay2;
    @BindView(R.id.tv_day3)
    TextView tvDay3;
    @BindView(R.id.tv_xianxia)
    TextView tvXianxia;
    @BindView(R.id.tv_jianshen_guan)
    TextView tvJianshenGuan;



    private Activity activity;

    private int sex = -1;//性别：【0:未知 1:男 2:女】
    private int classType = -1;//课程类型：【1:私教课，1:体验课】

    private int joinTimeType = -2;//入籍时间类型：【0:今日，7:最近七天，30:最近30天，-1:可编辑日期】
    private String startTime = null;//开始时间
    private String endTime = null;//结束时间


    private int buyClassTime = -1;//购买时间：【7:7天，14:14天，30:30天】

    private int expiringDay = -1;//快过期天数:【7:7天，14:14天，30:30天】



    public CoachFilterViperDialog(Activity activity) {
        super(activity, R.style.Transparent);
        setOwnerActivity(activity);
        this.activity = activity;
        View contentView = LayoutInflater.from(activity).inflate(R.layout.view_coach_filter_viper, null);
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

        tvSexMan.setTextColor(Color.parseColor("#666666"));
        tvSexWoman.setTextColor(Color.parseColor("#666666"));

        tvSijiaoClass.setTextColor(Color.parseColor("#666666"));
        tvTiyanClass.setTextColor(Color.parseColor("#666666"));


        //互斥
        tvTime1.setTextColor(Color.parseColor("#666666"));
        tvTime2.setTextColor(Color.parseColor("#666666"));
        tvTime3.setTextColor(Color.parseColor("#666666"));
        tvStartTime.setTextColor(Color.parseColor("#666666"));
        tvEndTime.setTextColor(Color.parseColor("#666666"));

        tvBuyTime1.setTextColor(Color.parseColor("#666666"));
        tvBuyTime2.setTextColor(Color.parseColor("#666666"));
        tvBuyTime3.setTextColor(Color.parseColor("#666666"));

        tvDay1.setTextColor(Color.parseColor("#666666"));
        tvDay2.setTextColor(Color.parseColor("#666666"));
        tvDay3.setTextColor(Color.parseColor("#666666"));

        tvXianxia.setTextColor(Color.parseColor("#666666"));
        tvJianshenGuan.setTextColor(Color.parseColor("#666666"));

        llSexMan.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        llSexWoman.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvSijiaoClass.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvTiyanClass.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvTime1.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvTime2.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvTime3.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvBuyTime1.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvBuyTime2.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvBuyTime3.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvDay1.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvDay2.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvDay3.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvXianxia.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvJianshenGuan.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));


        tvSexMan.setCompoundDrawables(getContext().getDrawable(R.mipmap.lg_man), null, null, null);
        tvSexWoman.setCompoundDrawables(getContext().getDrawable(R.mipmap.lg_women), null, null, null);

        tvSijiaoClass.setCompoundDrawables(null, null, null, null);
        tvTiyanClass.setCompoundDrawables(null, null, null, null);

        tvTime1.setCompoundDrawables(null, null, null, null);
        tvTime2.setCompoundDrawables(null, null, null, null);
        tvTime3.setCompoundDrawables(null, null, null, null);
        tvStartTime.setCompoundDrawables(null, null, null, null);
        tvEndTime.setCompoundDrawables(null, null, null, null);
        tvBuyTime1.setCompoundDrawables(null, null, null, null);
        tvBuyTime2.setCompoundDrawables(null, null, null, null);
        tvBuyTime3.setCompoundDrawables(null, null, null, null);
        tvDay1.setCompoundDrawables(null, null, null, null);
        tvDay2.setCompoundDrawables(null, null, null, null);
        tvDay3.setCompoundDrawables(null, null, null, null);

        tvXianxia.setCompoundDrawables(null, null, null, null);
        tvJianshenGuan.setCompoundDrawables(null, null, null, null);
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


    //性别
    private void selectSex(int index) {
        if (index == 0) {
            sex = 1;
            tvSexMan.setTextColor(Color.parseColor("#1997f8"));
            tvSexMan.setBackgroundColor(Color.parseColor("#ffffff"));
            llSexMan.setBackground(getContext().getDrawable(R.drawable.blue_stroke_select_bg));
            Drawable jd_choose = getContext().getResources().getDrawable(R.mipmap.jd_choose);
            jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
            tvSexMan.setCompoundDrawables(jd_choose, null, null, null);

            tvSexWoman.setTextColor(Color.parseColor("#666666"));
            tvSexWoman.setBackgroundColor(Color.parseColor("#f2f2f2"));
            llSexWoman.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
            tvSexWoman.setCompoundDrawables(null, null, null, null);
        } else {
            sex = 2;
            tvSexWoman.setTextColor(Color.parseColor("#1997f8"));
            tvSexWoman.setBackgroundColor(Color.parseColor("#ffffff"));
            llSexWoman.setBackground(getContext().getDrawable(R.drawable.blue_stroke_select_bg));
            Drawable jd_choose = getContext().getResources().getDrawable(R.mipmap.jd_choose);
            jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
            tvSexWoman.setCompoundDrawables(jd_choose, null, null, null);

            tvSexMan.setTextColor(Color.parseColor("#666666"));
            tvSexMan.setBackgroundColor(Color.parseColor("#f2f2f2"));
            llSexMan.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
            tvSexMan.setCompoundDrawables(null, null, null, null);
        }
    }

    //课程类型
    private void selectClassType(int index) {
        if (index == 1) {
            classType = 1;
            setSelectStyle(tvSijiaoClass);
            setUnSelectStyle(tvTiyanClass);

        } else if (index == 2) {
            classType = 2;
            setSelectStyle(tvTiyanClass);
            setUnSelectStyle(tvSijiaoClass);
        }

    }

    //入籍时间
    private void selectRuJiTime(int index) {
        if (index == 1) {
            joinTimeType = 0;
            setSelectStyle(tvTime1);
            setUnSelectStyle(tvTime2);
            setUnSelectStyle(tvTime3);

        } else if (index == 2) {
            joinTimeType = 7;
            setSelectStyle(tvTime2);
            setUnSelectStyle(tvTime1);
            setUnSelectStyle(tvTime3);

        } else if (index == 3) {
            joinTimeType = 30;
            setSelectStyle(tvTime3);
            setUnSelectStyle(tvTime1);
            setUnSelectStyle(tvTime2);
        } else if (index == 4) {
            joinTimeType = -1;
            setUnSelectStyle(tvTime1);
            setUnSelectStyle(tvTime2);
            setUnSelectStyle(tvTime3);
            Calendar c = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                            String endTime = tvEndTime.getText().toString();
                            String startTime = tvStartTime.getText().toString();
                            if (!TextUtils.isEmpty(endTime)) {
                                endTime = endTime.replace("-", "");
                            }
                            if (!TextUtils.isEmpty(startTime)) {
                                startTime = startTime.replace("-", "");
                            }
                            if (!TextUtils.isEmpty(endTime) && !TextUtils.isEmpty(startTime)) {
                                if (Integer.parseInt(endTime) >= Integer.parseInt(startTime)) {
                                    tvStartTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                                } else {
                                    Toast.makeText(getContext(), "开始时间不得大于结束时间", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                tvStartTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            }
                        }
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        } else if (index == 5) {
            joinTimeType = -1;
            setUnSelectStyle(tvTime1);
            setUnSelectStyle(tvTime2);
            setUnSelectStyle(tvTime3);
            Calendar c = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                            String endTime = tvEndTime.getText().toString();
                            String startTime = tvStartTime.getText().toString();
                            if (!TextUtils.isEmpty(endTime)) {
                                endTime = endTime.replace("-", "");
                            }
                            if (!TextUtils.isEmpty(startTime)) {
                                startTime = startTime.replace("-", "");
                            }
                            if (!TextUtils.isEmpty(endTime) && !TextUtils.isEmpty(startTime)) {
                                if (Integer.parseInt(endTime) >= Integer.parseInt(startTime)) {
                                    tvEndTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                                } else {
                                    Toast.makeText(getContext(), "结束时间不得小于开始时间", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                tvEndTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            }
                        }
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
    }

    //私教课购买情况
    private void selectBuyClassTime(int index) {

        if (index == 1) {
            buyClassTime = 7;
            setSelectStyle(tvBuyTime1);
            setUnSelectStyle(tvBuyTime2);
            setUnSelectStyle(tvBuyTime3);
        } else if (index == 2) {
            buyClassTime = 15;
            setSelectStyle(tvBuyTime2);
            setUnSelectStyle(tvBuyTime1);
            setUnSelectStyle(tvBuyTime3);
        } else if (index == 3) {
            buyClassTime = 30;
            setSelectStyle(tvBuyTime3);
            setUnSelectStyle(tvBuyTime1);
            setUnSelectStyle(tvBuyTime2);
        }


    }


    //快到期时间
    private void selectExpiringDay(int index) {

        if (index == 1) {
            expiringDay = 7;
            setSelectStyle(tvDay1);
            setUnSelectStyle(tvDay2);
            setUnSelectStyle(tvDay3);
        } else if (index == 2) {
            expiringDay = 15;
            setSelectStyle(tvDay2);
            setUnSelectStyle(tvDay1);
            setUnSelectStyle(tvDay3);
        } else if (index == 3) {
            expiringDay = 30;
            setSelectStyle(tvDay3);
            setUnSelectStyle(tvDay1);
            setUnSelectStyle(tvDay2);
        }
    }

    @OnClick({R.id.empty_view, R.id.ll_sex_man, R.id.ll_sex_woman, R.id.tv_sijiao_class, R.id.tv_tiyan_class, R.id.tv_time1, R.id.tv_time2, R.id.tv_time3, R.id.tv_start_time, R.id.tv_end_time, R.id.tv_buy_time1, R.id.tv_buy_time2, R.id.tv_buy_time3, R.id.tv_day1, R.id.tv_day2, R.id.tv_day3, R.id.tv_reset, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //性别
            case R.id.ll_sex_man:
                selectSex(0);
                break;
            case R.id.ll_sex_woman:
                selectSex(1);
                break;

            //课程类型
            case R.id.tv_sijiao_class:
                selectClassType(1);
                break;
            case R.id.tv_tiyan_class:
                selectClassType(2);
                break;

            //入籍时间
            case R.id.tv_time1:
                selectRuJiTime(1);
                break;
            case R.id.tv_time2:
                selectRuJiTime(2);
                break;
            case R.id.tv_time3:
                selectRuJiTime(3);
                break;
            case R.id.tv_start_time:
                selectRuJiTime(4);
                break;
            case R.id.tv_end_time:
                selectRuJiTime(5);
                break;

            //购买时间
            case R.id.tv_buy_time1:
                selectBuyClassTime(1);
                break;
            case R.id.tv_buy_time2:
                selectBuyClassTime(2);
                break;
            case R.id.tv_buy_time3:
                selectBuyClassTime(3);
                break;

            //快到期时间
            case R.id.tv_day1:
                selectExpiringDay(1);
                break;
            case R.id.tv_day2:
                selectExpiringDay(2);
                break;
            case R.id.tv_day3:
                selectExpiringDay(3);
                break;

     

            //按钮
            case R.id.tv_reset:
                resetView();
                break;
            case R.id.tv_confirm:
                dismiss();
            case R.id.empty_view:
                dismiss();
                break;
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {

        CoachViperFilterBean coachViperFilterBean = new CoachViperFilterBean();
        coachViperFilterBean.setSex(sex);
        coachViperFilterBean.setClassType(classType);
        coachViperFilterBean.setJoinTimeType(joinTimeType);
        if (joinTimeType == -1) {
            coachViperFilterBean.setStartTime(startTime);
            coachViperFilterBean.setEndTime(endTime);
        }
        coachViperFilterBean.setBuyClassTime(buyClassTime);
        coachViperFilterBean.setExpiringDay(expiringDay);


        onDismissListener.onDismiss(coachViperFilterBean);
    }



    public interface OnDismissListener {
        void onDismiss(CoachViperFilterBean coachViperFilterBean);
    }

    private OnDismissListener onDismissListener;

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

}
