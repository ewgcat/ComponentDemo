package com.yijian.staff.mvp.vip.info;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.util.CommonUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterViperDialog extends Dialog {

    private static String TAG = FilterViperDialog.class.getSimpleName();
    @BindView(R.id.ll_sex_man)
    LinearLayout llSexMan;
    @BindView(R.id.ll_sex_woman)
    LinearLayout llSexWoman;
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
    private Date startData;


    public FilterViperDialog(Activity activity) {
        super(activity, R.style.Transparent);
        setOwnerActivity(activity);
        this.activity = activity;
        View contentView = LayoutInflater.from(activity).inflate(R.layout.view_filter_viper, null);
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
        tvStartTime.setTextColor(Color.parseColor("#666666"));
        tvEndTime.setTextColor(Color.parseColor("#666666"));

        tvUnBuy.setTextColor(Color.parseColor("#666666"));
        tvBuy.setTextColor(Color.parseColor("#666666"));
        tvTiyanke.setTextColor(Color.parseColor("#666666"));

        tvDay1.setTextColor(Color.parseColor("#666666"));
        tvDay2.setTextColor(Color.parseColor("#666666"));
        tvDay3.setTextColor(Color.parseColor("#666666"));

        tvXianxia.setTextColor(Color.parseColor("#666666"));
        tvJianshenGuan.setTextColor(Color.parseColor("#666666"));

        llSexMan.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        llSexWoman.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));

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


        tvSexMan.setCompoundDrawables(getContext().getDrawable(R.mipmap.lg_man), null, null, null);
        tvSexWoman.setCompoundDrawables(getContext().getDrawable(R.mipmap.lg_women), null, null, null);

        tvTimeCard.setCompoundDrawables(null, null, null, null);
        tvCishuCard.setCompoundDrawables(null, null, null, null);
        tvChuzhiCard.setCompoundDrawables(null, null, null, null);
        tvHuiyuanCard.setCompoundDrawables(null, null, null, null);

        tvTime1.setCompoundDrawables(null, null, null, null);
        tvTime2.setCompoundDrawables(null, null, null, null);
        tvTime3.setCompoundDrawables(null, null, null, null);
        tvStartTime.setCompoundDrawables(null, null, null, null);
        tvEndTime.setCompoundDrawables(null, null, null, null);
        tvUnBuy.setCompoundDrawables(null, null, null, null);
        tvBuy.setCompoundDrawables(null, null, null, null);
        tvTiyanke.setCompoundDrawables(null, null, null, null);
        tvDay1.setCompoundDrawables(null, null, null, null);
        tvDay2.setCompoundDrawables(null, null, null, null);
        tvDay3.setCompoundDrawables(null, null, null, null);
        tvXianxia.setCompoundDrawables(null, null, null, null);
        tvJianshenGuan.setCompoundDrawables(null, null, null, null);
    }


    private void changeTextViewStyle(TextView textView, LinearLayout linearLayout) {
        if (linearLayout == null) {
            if (textView.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
                textView.setTextColor(Color.parseColor("#666666"));
                textView.setBackgroundColor(Color.parseColor("#f2f2f2"));
                textView.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
                textView.setCompoundDrawables(null, null, null, null);
            } else {
                textView.setTextColor(Color.parseColor("#1997f8"));
                textView.setBackground(getContext().getDrawable(R.drawable.blue_stroke_select_bg));
                Drawable jd_choose = getContext().getResources().getDrawable(R.mipmap.jd_choose);
                jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
                textView.setCompoundDrawables(jd_choose, null, null, null);
            }
        } else {
            if (textView.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
                textView.setTextColor(Color.parseColor("#666666"));
                linearLayout.setBackgroundColor(Color.parseColor("#f2f2f2"));
                linearLayout.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
                if (textView.getText().toString().equals("男")){
                    tvSexMan.setCompoundDrawables(getContext().getDrawable(R.mipmap.lg_man), null, null, null);
                }else {
                    tvSexWoman.setCompoundDrawables(getContext().getDrawable(R.mipmap.lg_women), null, null, null);
                }
            } else {
                textView.setTextColor(Color.parseColor("#1997f8"));
                linearLayout.setBackground(getContext().getDrawable(R.drawable.blue_stroke_select_bg));
                Drawable jd_choose = getContext().getResources().getDrawable(R.mipmap.jd_choose);
                jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
                textView.setCompoundDrawables(jd_choose, null, null, null);
            }
        }


    }

    private void changeTextViewStyle(TextView textView) {
        changeTextViewStyle(textView, null);
    }


    @OnClick({R.id.empty_view,R.id.ll_sex_man, R.id.ll_sex_woman, R.id.tv_time_card, R.id.tv_cishu_card, R.id.tv_chuzhi_card, R.id.tv_huiyuan_card, R.id.tv_time1, R.id.tv_time2, R.id.tv_time3, R.id.tv_start_time, R.id.tv_end_time, R.id.tv_un_buy, R.id.tv_buy, R.id.tv_tiyanke, R.id.tv_day1, R.id.tv_day2, R.id.tv_day3, R.id.tv_xianxia, R.id.tv_jianshen_guan, R.id.scoll_view, R.id.tv_reset, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.empty_view:
                dismiss();
                break;
            case R.id.ll_sex_man:
                changeTextViewStyle(tvSexMan, llSexMan);
                break;
            case R.id.ll_sex_woman:
                changeTextViewStyle(tvSexWoman,llSexWoman);
                break;
            case R.id.tv_time_card:
                changeTextViewStyle(tvTimeCard);
                break;
            case R.id.tv_cishu_card:
                changeTextViewStyle(tvCishuCard);
                break;
            case R.id.tv_chuzhi_card:
                changeTextViewStyle(tvChuzhiCard);
                break;
            case R.id.tv_huiyuan_card:
                changeTextViewStyle(tvHuiyuanCard);
                break;

            //入籍时间
            case R.id.tv_time1:
                changeSelectTime(1,view);
                break;
            case R.id.tv_time2:
                changeSelectTime(2,view);
                break;
            case R.id.tv_time3:
                changeSelectTime(3,view);
                break;
            case R.id.tv_start_time:
                changeSelectTime(4,view);
                break;
            case R.id.tv_end_time:
                changeSelectTime(5,view);
                break;

            //私教课购买情况
            case R.id.tv_un_buy:
                changeTextViewStyle(tvUnBuy);
                break;
            case R.id.tv_buy:
                changeTextViewStyle(tvBuy);
                break;
            case R.id.tv_tiyanke:
                changeTextViewStyle(tvTiyanke);
                break;

            //快到期时间
            case R.id.tv_day1:
                changeTextViewStyle(tvDay1);
                break;
            case R.id.tv_day2:
                changeTextViewStyle(tvDay2);
                break;
            case R.id.tv_day3:
                changeTextViewStyle(tvDay3);
                break;

            //来源
            case R.id.tv_xianxia:
                changeTextViewStyle(tvXianxia);
                break;
            case R.id.tv_jianshen_guan:
                changeTextViewStyle(tvJianshenGuan);
                break;

            //按钮
            case R.id.tv_reset:
                resetView();
                break;
            case R.id.tv_confirm:
                //TODO 传值
                dismiss();

                break;
        }
    }

    private void changeSelectTime(int index,View view) {
        if (index==1){
            changeTextViewStyle(tvTime1);
        }else if (index==2){
            changeTextViewStyle(tvTime2);
        }else if (index==3){
            changeTextViewStyle(tvTime3);

        }else if (index==4){
            TimePickerView pickerView = new TimePickerView.Builder(view.getContext(), new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View view) {
                    startData = date;
                    String result = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    tvStartTime.setText(result);
                }
            }).build();
            pickerView.show(view);
        }else if (index==5){
            TimePickerView pickerView = new TimePickerView.Builder(view.getContext(), new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View view) {
                    String result = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    if (TextUtils.isEmpty(tvStartTime.getText().toString())){
                        Toast.makeText(getContext(),"请先选择开始时间",Toast.LENGTH_SHORT).show();
                    }else {
                        if (startData.before(date)){
                            tvEndTime.setText(result);
                        }else {
                            Toast.makeText(getContext(),"结束时间不得小于开始时间",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }).build();
            pickerView.show(view);

        }

    }
}
