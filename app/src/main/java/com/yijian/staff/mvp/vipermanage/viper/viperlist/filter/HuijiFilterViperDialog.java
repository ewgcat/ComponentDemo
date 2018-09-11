package com.yijian.staff.mvp.vipermanage.viper.viperlist.filter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuijiFilterViperDialog extends Dialog {

    private static String TAG = HuijiFilterViperDialog.class.getSimpleName();

    @BindView(R.id.tv_time_card)
    TextView tvTimeCard;
    @BindView(R.id.tv_cishu_card)
    TextView tvCishuCard;
    @BindView(R.id.tv_chuzhi_card)
    TextView tvChuzhiCard;
    @BindView(R.id.tv_huiyuan_card)
    TextView tvHuiyuanCard;
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
    @BindView(R.id.tv_un_buy)
    TextView tvUnBuy;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.tv_tiyanke)
    TextView tvTiyanke;
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
    @BindView(R.id.tv_sex_man)
    TextView tvSexMan;
    @BindView(R.id.tv_sex_woman)
    TextView tvSexWoman;


    private Activity activity;

    private int sex = -1;//性别：【0:未知 1:男 2:女】

    private int joinTimeType = -2;//入籍时间类型：【0:今日，7:最近七天，30:最近30天，-1:可编辑日期】
    private String startTime = null;//开始时间
    private String endTime = null;//结束时间

    private int cardType = -1;//卡类型：【0:时间卡，1:次卡，2:储值卡，3:会员制卡，4:员工卡】

    private int privateCourseState = -1;//私教课购买情况：【1.未购买，2.已购买，3.已购买的私课，且私课为体验课】

    private int expiringDay = -1;//快过期天数:【7:7天，14:14天，30:30天】

    private int source = -1;//来源（推广渠道)


    public HuijiFilterViperDialog(Activity activity) {
        super(activity, R.style.Transparent);
        setOwnerActivity(activity);
        this.activity = activity;
        View contentView = LayoutInflater.from(activity).inflate(R.layout.view_huiji_filter_viper, null);
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
        resetView();
        setCanceledOnTouchOutside(false);
    }


    public void showFilterDialog() {
        show();
    }

    private void resetView() {

        tvStartTime.setText("");
        tvEndTime.setText("");

        tvSexMan.setTextColor(Color.parseColor("#666666"));
        tvSexWoman.setTextColor(Color.parseColor("#666666"));

        tvTimeCard.setTextColor(Color.parseColor("#666666"));
        tvCishuCard.setTextColor(Color.parseColor("#666666"));
        tvChuzhiCard.setTextColor(Color.parseColor("#666666"));
        tvHuiyuanCard.setTextColor(Color.parseColor("#666666"));

        //互斥
        tvTime1.setTextColor(Color.parseColor("#666666"));
        tvTime2.setTextColor(Color.parseColor("#666666"));
        tvTime3.setTextColor(Color.parseColor("#666666"));


        tvUnBuy.setTextColor(Color.parseColor("#666666"));
        tvBuy.setTextColor(Color.parseColor("#666666"));
        tvTiyanke.setTextColor(Color.parseColor("#666666"));

        tvDay1.setTextColor(Color.parseColor("#666666"));
        tvDay2.setTextColor(Color.parseColor("#666666"));
        tvDay3.setTextColor(Color.parseColor("#666666"));

        tvXianxia.setTextColor(Color.parseColor("#666666"));
        tvJianshenGuan.setTextColor(Color.parseColor("#666666"));

        tvSexMan.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvSexWoman.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvTimeCard.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvCishuCard.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvChuzhiCard.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvHuiyuanCard.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvTime1.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvTime2.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvTime3.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvUnBuy.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvBuy.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvTiyanke.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvDay1.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvDay2.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvDay3.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvXianxia.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvJianshenGuan.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));


        tvSexMan.setCompoundDrawables(null, null, null, null);
        tvSexWoman.setCompoundDrawables(null, null, null, null);

        tvTimeCard.setCompoundDrawables(null, null, null, null);
        tvCishuCard.setCompoundDrawables(null, null, null, null);
        tvChuzhiCard.setCompoundDrawables(null, null, null, null);
        tvHuiyuanCard.setCompoundDrawables(null, null, null, null);

        tvTime1.setCompoundDrawables(null, null, null, null);
        tvTime2.setCompoundDrawables(null, null, null, null);
        tvTime3.setCompoundDrawables(null, null, null, null);

        tvUnBuy.setCompoundDrawables(null, null, null, null);
        tvBuy.setCompoundDrawables(null, null, null, null);
        tvTiyanke.setCompoundDrawables(null, null, null, null);
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
            if (sex==1){
                sex=-1;
                tvSexMan.setTextColor(Color.parseColor("#666666"));
                tvSexMan.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexMan.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexMan.setCompoundDrawables(null, null, null, null);

                tvSexWoman.setTextColor(Color.parseColor("#666666"));
                tvSexWoman.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexWoman.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexWoman.setCompoundDrawables(null, null, null, null);
            }else {
                sex = 1;
                tvSexMan.setTextColor(Color.parseColor("#1997f8"));
                tvSexMan.setBackgroundColor(Color.parseColor("#ffffff"));
                tvSexMan.setBackground(getContext().getDrawable(R.drawable.blue_stroke_select_bg));
                Drawable jd_choose = getContext().getResources().getDrawable(R.mipmap.jd_choose);
                jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
                tvSexMan.setCompoundDrawables(jd_choose, null, null, null);

                tvSexWoman.setTextColor(Color.parseColor("#666666"));
                tvSexWoman.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexWoman.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexWoman.setCompoundDrawables(null, null, null, null);
            }

        } else {
            if (sex==2){
                sex=-1;
                tvSexMan.setTextColor(Color.parseColor("#666666"));
                tvSexMan.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexMan.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexMan.setCompoundDrawables(null, null, null, null);

                tvSexWoman.setTextColor(Color.parseColor("#666666"));
                tvSexWoman.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexWoman.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexWoman.setCompoundDrawables(null, null, null, null);
            }else {
                sex = 2;
                tvSexWoman.setTextColor(Color.parseColor("#1997f8"));
                tvSexWoman.setBackgroundColor(Color.parseColor("#ffffff"));
                tvSexWoman.setBackground(getContext().getDrawable(R.drawable.blue_stroke_select_bg));
                Drawable jd_choose = getContext().getResources().getDrawable(R.mipmap.jd_choose);
                jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
                tvSexWoman.setCompoundDrawables(jd_choose, null, null, null);

                tvSexMan.setTextColor(Color.parseColor("#666666"));
                tvSexMan.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexMan.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexMan.setCompoundDrawables(null, null, null, null);
            }

        }
    }

    //卡类型
    private void selectCardType(int index) {
        if (index == 0) {
            if (cardType == 0) {
                cardType = -1;
                setUnSelectStyle(tvTimeCard);
                setUnSelectStyle(tvCishuCard);
                setUnSelectStyle(tvChuzhiCard);
                setUnSelectStyle(tvHuiyuanCard);
            } else {
                cardType = 0;
                setSelectStyle(tvTimeCard);
                setUnSelectStyle(tvCishuCard);
                setUnSelectStyle(tvChuzhiCard);
                setUnSelectStyle(tvHuiyuanCard);
            }


        } else if (index == 1) {
            if (cardType == 1) {
                cardType = -1;
                setUnSelectStyle(tvTimeCard);
                setUnSelectStyle(tvCishuCard);
                setUnSelectStyle(tvChuzhiCard);
                setUnSelectStyle(tvHuiyuanCard);
            } else {
                cardType = 1;
                setSelectStyle(tvCishuCard);
                setUnSelectStyle(tvTimeCard);
                setUnSelectStyle(tvChuzhiCard);
                setUnSelectStyle(tvHuiyuanCard);
            }

        } else if (index == 2) {
            if (cardType == 2) {
                cardType = -1;
                setUnSelectStyle(tvTimeCard);
                setUnSelectStyle(tvCishuCard);
                setUnSelectStyle(tvChuzhiCard);
                setUnSelectStyle(tvHuiyuanCard);
            } else {
                cardType = 2;
                setSelectStyle(tvChuzhiCard);
                setUnSelectStyle(tvTimeCard);
                setUnSelectStyle(tvCishuCard);
                setUnSelectStyle(tvHuiyuanCard);
            }

        } else if (index == 3) {
            if (cardType == 3) {
                cardType = -1;
                setUnSelectStyle(tvTimeCard);
                setUnSelectStyle(tvCishuCard);
                setUnSelectStyle(tvChuzhiCard);
                setUnSelectStyle(tvHuiyuanCard);
            } else {
                cardType = 3;
                setSelectStyle(tvHuiyuanCard);
                setUnSelectStyle(tvTimeCard);
                setUnSelectStyle(tvCishuCard);
                setUnSelectStyle(tvChuzhiCard);
            }


        }
    }

    //入籍时间
    private void selectRuJiTime(int index) {
        if (index == 1) {

            if (joinTimeType == 0) {
                joinTimeType = -1;
                setUnSelectStyle(tvTime1);
                setUnSelectStyle(tvTime2);
                setUnSelectStyle(tvTime3);
                tvStartTime.setText("");
                tvEndTime.setText("");
            } else {
                joinTimeType = 0;
                setSelectStyle(tvTime1);
                setUnSelectStyle(tvTime2);
                setUnSelectStyle(tvTime3);
                tvStartTime.setText("");
                tvEndTime.setText("");
            }
        } else if (index == 2) {
            if (joinTimeType == 7) {
                joinTimeType = -1;
                setUnSelectStyle(tvTime2);
                setUnSelectStyle(tvTime1);
                setUnSelectStyle(tvTime3);
                tvStartTime.setText("");
                tvEndTime.setText("");
            } else {
                joinTimeType = 7;
                setSelectStyle(tvTime2);
                setUnSelectStyle(tvTime1);
                setUnSelectStyle(tvTime3);
                tvStartTime.setText("");
                tvEndTime.setText("");
            }

        } else if (index == 3) {
            if (joinTimeType == 30) {
                joinTimeType = 30;
                setUnSelectStyle(tvTime3);
                setUnSelectStyle(tvTime1);
                setUnSelectStyle(tvTime2);
                tvStartTime.setText("");
                tvEndTime.setText("");
            } else {
                joinTimeType = 30;
                setSelectStyle(tvTime3);
                setUnSelectStyle(tvTime1);
                setUnSelectStyle(tvTime2);
                tvStartTime.setText("");
                tvEndTime.setText("");
            }

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

                            String time = "";
                            if (month < 9 && dayOfMonth < 10) {
                                time += year + "-0" + (month + 1) + "-0" + dayOfMonth;
                            } else if (month > 9 && dayOfMonth > 10) {
                                time += year + "-" + (month + 1) + "-" + dayOfMonth;

                            } else if (month < 9 && dayOfMonth > 10) {
                                time += year + "-0" + (month + 1) + "-" + dayOfMonth;

                            } else if (month > 9 && dayOfMonth < 10) {
                                time += year + "-" + (month + 1) + "-0" + dayOfMonth;

                            }
                            tvStartTime.setText(time);


                            String endTime = tvEndTime.getText().toString();
                            if (!TextUtils.isEmpty(endTime)) {
                                endTime = endTime.replace("-", "");
                            }


                            if (!TextUtils.isEmpty(endTime)) {
                                endTime = endTime.replace("-", "");
                            }
                            if (!TextUtils.isEmpty(time)) {
                                time = time.replace("-", "");
                            }
                            String s = "" + DateUtil.getCurrentYear() + DateUtil.getCurrentMonth() + DateUtil.getCurrentDay();
                            String s1 = "" + year + month + dayOfMonth;
                            if (Integer.parseInt(s) >= Integer.parseInt(s1)) {
                                if (!TextUtils.isEmpty(time) && !TextUtils.isEmpty(endTime)) {
                                    if (Integer.parseInt(time) > Integer.parseInt(endTime)) {
                                        tvStartTime.setText("");
                                        tvEndTime.setText("");
                                        Toast.makeText(getContext(), "结束时间不得小于开始时间", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                tvStartTime.setText("");
                                Toast.makeText(getContext(), "开始日期不得大于当前日期", Toast.LENGTH_SHORT).show();
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

                            String time = "";

                            if (month < 9 && dayOfMonth < 10) {
                                time += year + "-0" + (month + 1) + "-0" + dayOfMonth;
                            } else if (month > 9 && dayOfMonth > 10) {
                                time += year + "-" + (month + 1) + "-" + dayOfMonth;
                            } else if (month < 9 && dayOfMonth > 10) {
                                time += year + "-0" + (month + 1) + "-" + dayOfMonth;

                            } else if (month > 9 && dayOfMonth < 10) {
                                time += year + "-" + (month + 1) + "-0" + dayOfMonth;
                            }
                            tvEndTime.setText(time);


                            String startTime = tvStartTime.getText().toString();

                            if (!TextUtils.isEmpty(startTime)) {
                                startTime = startTime.replace("-", "");
                            }
                            if (!TextUtils.isEmpty(time)) {
                                time = time.replace("-", "");
                            }

                            String s = "" + DateUtil.getCurrentYear() + DateUtil.getCurrentMonth() + DateUtil.getCurrentDay();
                            String s1 = "" + year + month + dayOfMonth;
                            if (Integer.parseInt(s) >= Integer.parseInt(s1)) {
                                if (!TextUtils.isEmpty(time) && !TextUtils.isEmpty(startTime)) {
                                    if (Integer.parseInt(time) < Integer.parseInt(startTime)) {
                                        tvStartTime.setText("");
                                        tvEndTime.setText("");
                                        Toast.makeText(getContext(), "结束时间不得大于开始时间", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                tvEndTime.setText("");
                                Toast.makeText(getContext(), "结束日期不得大于当前日期", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
    }

    //私教课购买情况
    private void selectPrivateCourseState(int index) {

        if (index == 1) {
            if (privateCourseState == 1) {
                privateCourseState = -1;
                setUnSelectStyle(tvUnBuy);
                setUnSelectStyle(tvBuy);
                setUnSelectStyle(tvTiyanke);
            } else {
                privateCourseState = 1;
                setSelectStyle(tvUnBuy);
                setUnSelectStyle(tvBuy);
                setUnSelectStyle(tvTiyanke);
            }

        } else if (index == 2) {
            if (privateCourseState == 2) {
                privateCourseState = -1;
                setUnSelectStyle(tvBuy);
                setUnSelectStyle(tvUnBuy);
                setUnSelectStyle(tvTiyanke);
            } else {
                privateCourseState = 2;
                setSelectStyle(tvBuy);
                setUnSelectStyle(tvUnBuy);
                setUnSelectStyle(tvTiyanke);
            }

        } else if (index == 3) {
            if (privateCourseState == 3) {
                privateCourseState = -1;
                setUnSelectStyle(tvBuy);
                setUnSelectStyle(tvUnBuy);
                setUnSelectStyle(tvTiyanke);
            } else {
                privateCourseState = 3;
                setSelectStyle(tvTiyanke);
                setUnSelectStyle(tvBuy);
                setUnSelectStyle(tvUnBuy);
            }

        }


    }


    //快到期时间
    private void selectExpiringDay(int index) {

        if (index == 1) {

            if (expiringDay == 7) {
                expiringDay = -1;
                setUnSelectStyle(tvDay1);
                setUnSelectStyle(tvDay2);
                setUnSelectStyle(tvDay3);
            } else {
                expiringDay = 7;
                setSelectStyle(tvDay1);
                setUnSelectStyle(tvDay2);
                setUnSelectStyle(tvDay3);
            }
        } else if (index == 2) {
            if (expiringDay == 15) {
                expiringDay = -1;
                setUnSelectStyle(tvDay1);
                setUnSelectStyle(tvDay2);
                setUnSelectStyle(tvDay3);
            } else {
                expiringDay = 15;
                setSelectStyle(tvDay2);
                setUnSelectStyle(tvDay1);
                setUnSelectStyle(tvDay3);
            }

        } else if (index == 3) {


            if (expiringDay == 30) {
                expiringDay = -1;
                setUnSelectStyle(tvDay1);
                setUnSelectStyle(tvDay2);
                setUnSelectStyle(tvDay3);
            } else {
                expiringDay = 30;
                setSelectStyle(tvDay3);
                setUnSelectStyle(tvDay1);
                setUnSelectStyle(tvDay2);
            }
        }
    }

    @OnClick({R.id.empty_view, R.id.tv_sex_man, R.id.tv_sex_woman, R.id.tv_time_card, R.id.tv_cishu_card, R.id.tv_chuzhi_card, R.id.tv_huiyuan_card, R.id.tv_time1, R.id.tv_time2, R.id.tv_time3, R.id.tv_start_time, R.id.tv_end_time, R.id.tv_un_buy, R.id.tv_buy, R.id.tv_tiyanke, R.id.tv_day1, R.id.tv_day2, R.id.tv_day3, R.id.tv_xianxia, R.id.tv_jianshen_guan, R.id.scoll_view, R.id.tv_reset, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //性别
            case R.id.tv_sex_man:
                selectSex(0);
                break;
            case R.id.tv_sex_woman:
                selectSex(1);
                break;

            //卡类型
            case R.id.tv_time_card:
                selectCardType(0);
                break;
            case R.id.tv_cishu_card:
                selectCardType(1);
                break;
            case R.id.tv_chuzhi_card:
                selectCardType(2);
                break;
            case R.id.tv_huiyuan_card:
                selectCardType(3);
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

            //私教课购买情况
            case R.id.tv_un_buy:
                selectPrivateCourseState(1);
                break;
            case R.id.tv_buy:
                selectPrivateCourseState(2);
                break;
            case R.id.tv_tiyanke:
                selectPrivateCourseState(3);
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

            //来源
            case R.id.tv_xianxia:
                break;
            case R.id.tv_jianshen_guan:
                break;

            //按钮
            case R.id.tv_reset:
                reset();
                break;
            case R.id.tv_confirm:
                setResultSure();
                dismiss();
                break;
            case R.id.empty_view:
                setResultNoSure();
                break;
        }
    }

    private void reset() {
        resetView();
        sex = -1;
        cardType = -1;
        expiringDay = -1;
        privateCourseState = -1;
        joinTimeType = -2;
        startTime = null;
        endTime = null;
    }


    private void setResultNoSure() {
        reset();
        dismiss();
    }

    private void setResultSure() {
        startTime = tvStartTime.getText().toString();
        endTime = tvEndTime.getText().toString();

        HuijiViperFilterBean huijiViperFilterBean = new HuijiViperFilterBean();
        huijiViperFilterBean.setSex(sex);
        huijiViperFilterBean.setCardType(cardType);
        huijiViperFilterBean.setJoinTimeType(joinTimeType);
        if (joinTimeType == -1) {
            huijiViperFilterBean.setStartTime(startTime);
            huijiViperFilterBean.setEndTime(endTime);
        }
        huijiViperFilterBean.setPrivateCourseState(privateCourseState);
        huijiViperFilterBean.setExpiringDay(expiringDay);
        onDismissListener.onDismiss(huijiViperFilterBean);
        dismiss();
    }

    public interface OnDismissListener {
        void onDismiss(HuijiViperFilterBean huijiViperFilterBean);
    }

    private OnDismissListener onDismissListener;

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

}
