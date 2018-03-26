package com.yijian.staff.mvp.huiji.goodsbaojia;

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
import com.yijian.staff.mvp.huiji.viperlist.filter.HuijiFilterViperDialog;
import com.yijian.staff.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuiJiFilterGoodsDialog extends Dialog  implements DialogInterface.OnDismissListener {

    private static String TAG = HuiJiFilterGoodsDialog.class.getSimpleName();
    @BindView(R.id.tv_time_card)
    TextView tvTimeCard;
    @BindView(R.id.tv_cishu_card)
    TextView tvCishuCard;
    @BindView(R.id.tv_chuzhi_card)
    TextView tvChuzhiCard;
    @BindView(R.id.tv_huiyuan_card)
    TextView tvHuiyuanCard;
    @BindView(R.id.tv_price1)
    TextView tvPrice1;
    @BindView(R.id.tv_price2)
    TextView tvPrice2;
    @BindView(R.id.tv_price3)
    TextView tvPrice3;
    @BindView(R.id.tv_price4)
    TextView tvPrice4;
    @BindView(R.id.tv_youyong_place)
    TextView tvYouyongPlace;
    @BindView(R.id.tv_jianshen_place)
    TextView tvJianshenPlace;

    private Activity activity;


    public HuiJiFilterGoodsDialog(Activity activity) {
        super(activity, R.style.Transparent);
        setOwnerActivity(activity);
        this.activity = activity;
        View contentView = LayoutInflater.from(activity).inflate(R.layout.view_filter_goods, null);
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


    @OnClick({R.id.tv_reset, R.id.tv_confirm,
            R.id.tv_time_card, R.id.tv_cishu_card,
            R.id.tv_chuzhi_card, R.id.tv_huiyuan_card,
            R.id.tv_price1, R.id.tv_price2,
            R.id.empty_view,
            R.id.tv_price3, R.id.tv_price4,
            R.id.tv_youyong_place, R.id.tv_jianshen_place})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.empty_view:
                dismiss();
                break;
            case R.id.tv_reset:
                resetView();
                break;
            case R.id.tv_confirm:
                dismiss();
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
            case R.id.tv_price1:
                changeTextViewStyle(tvPrice1);

                break;
            case R.id.tv_price2:
                changeTextViewStyle(tvPrice2);

                break;
            case R.id.tv_price3:
                changeTextViewStyle(tvPrice3);
                break;
            case R.id.tv_price4:
                changeTextViewStyle(tvPrice4);
                break;
            case R.id.tv_youyong_place:
                changeTextViewStyle(tvYouyongPlace);
                break;
            case R.id.tv_jianshen_place:

                changeTextViewStyle(tvJianshenPlace);
                break;
        }
    }

    private void resetView() {
        tvTimeCard.setTextColor(Color.parseColor("#666666"));
        tvCishuCard.setTextColor(Color.parseColor("#666666"));
        tvChuzhiCard.setTextColor(Color.parseColor("#666666"));
        tvHuiyuanCard.setTextColor(Color.parseColor("#666666"));
        tvPrice1.setTextColor(Color.parseColor("#666666"));
        tvPrice2.setTextColor(Color.parseColor("#666666"));
        tvPrice3.setTextColor(Color.parseColor("#666666"));
        tvPrice4.setTextColor(Color.parseColor("#666666"));
        tvYouyongPlace.setTextColor(Color.parseColor("#666666"));
        tvJianshenPlace.setTextColor(Color.parseColor("#666666"));

        tvTimeCard.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvCishuCard.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvChuzhiCard.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvHuiyuanCard.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice1.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice2.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice3.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice4.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvYouyongPlace.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvJianshenPlace.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));


        tvTimeCard.setCompoundDrawables(null, null, null, null);
        tvCishuCard.setCompoundDrawables(null, null, null, null);
        tvChuzhiCard.setCompoundDrawables(null, null, null, null);
        tvHuiyuanCard.setCompoundDrawables(null, null, null, null);
        tvPrice1.setCompoundDrawables(null, null, null, null);
        tvPrice2.setCompoundDrawables(null, null, null, null);
        tvPrice3.setCompoundDrawables(null, null, null, null);
        tvPrice4.setCompoundDrawables(null, null, null, null);
        tvYouyongPlace.setCompoundDrawables(null, null, null, null);
        tvJianshenPlace.setCompoundDrawables(null, null, null, null);
    }

    private void changeTextViewStyle(TextView textView) {
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

    }


    @Override
    public void onDismiss(DialogInterface dialog) {


    }

    public interface OnDismissListener {
        void onDismiss(HuiJiGoodsFilterBean huiJiGoodsFilterBean);
    }

    private HuiJiFilterGoodsDialog.OnDismissListener onDismissListener;

    public void setOnDismissListener(HuiJiFilterGoodsDialog.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

}
