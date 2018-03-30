package com.yijian.staff.mvp.huiji.goodsbaojia.filter;

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
    private int price;
    private int cardType=-1;
    private int changguan;


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

            case R.id.tv_reset:
                resetView();
                break;
            case R.id.empty_view:
            case R.id.tv_confirm:
                dismiss();
                break;

            case R.id.tv_time_card:
                selectCardType(1);
                break;
            case R.id.tv_cishu_card:
                selectCardType(2);
                break;
            case R.id.tv_chuzhi_card:
                selectCardType(3);
                break;
            case R.id.tv_huiyuan_card:
                selectCardType(4);
                break;

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

            case R.id.tv_youyong_place:
                selectChangguan(1);
                break;
            case R.id.tv_jianshen_place:
                selectChangguan(2);
                break;
        }
    }

    private void selectChangguan(int i) {
        if (i == 1) {
            changguan = 1;
            setSelectStyle(tvYouyongPlace);
            setUnSelectStyle(tvJianshenPlace);
        } else if (i == 2) {
            changguan = 2;
            setSelectStyle(tvJianshenPlace);
            setUnSelectStyle(tvYouyongPlace);
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

    private void selectCardType(int i) {
            if (i == 1) {
                cardType = 0;
                setSelectStyle(tvTimeCard);
                setUnSelectStyle(tvCishuCard);
                setUnSelectStyle(tvChuzhiCard);
                setUnSelectStyle(tvHuiyuanCard);
            } else if (i == 2) {
                cardType = 1;
                setSelectStyle(tvCishuCard);
                setUnSelectStyle(tvTimeCard);
                setUnSelectStyle(tvChuzhiCard);
                setUnSelectStyle(tvHuiyuanCard);
            } else if (i == 3) {
                cardType = 2;
                setSelectStyle(tvChuzhiCard);
                setUnSelectStyle(tvTimeCard);
                setUnSelectStyle(tvCishuCard);
                setUnSelectStyle(tvHuiyuanCard);
            } else if (i == 4) {
                cardType = 3;
                setSelectStyle(tvHuiyuanCard);
                setUnSelectStyle(tvTimeCard);
                setUnSelectStyle(tvCishuCard);
                setUnSelectStyle(tvChuzhiCard);
            }
    }

    private void resetView() {
        cardType=-1;
        price=-1;
        changguan=-1;
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


    @Override
    public void onDismiss(DialogInterface dialog) {
        HuiJiGoodsFilterBean huiJiGoodsFilterBean=new HuiJiGoodsFilterBean();
        huiJiGoodsFilterBean.setCardType(cardType);
        if (price == 1) {
            huiJiGoodsFilterBean.setStartPrice("0");
            huiJiGoodsFilterBean.setEndPrice("1000");
        } else if (price == 2) {
            huiJiGoodsFilterBean.setStartPrice("1000");
            huiJiGoodsFilterBean.setEndPrice("2000");
        } else if (price == 3) {
            huiJiGoodsFilterBean.setStartPrice("2000");
            huiJiGoodsFilterBean.setEndPrice("3000");
        } else if (price == 4) {
            huiJiGoodsFilterBean.setStartPrice("3000");
            huiJiGoodsFilterBean.setEndPrice("4000");
        } else {
            huiJiGoodsFilterBean.setStartPrice("-1");
            huiJiGoodsFilterBean.setEndPrice("-1");
        }

        if (changguan==1){
            huiJiGoodsFilterBean.setVenueName("游泳馆");
        }else if (changguan==2){
            huiJiGoodsFilterBean.setVenueName("健身馆");
        }else {
            huiJiGoodsFilterBean.setVenueName("-1");
        }
        if (onDismissListener!=null){
            onDismissListener.onDismiss(huiJiGoodsFilterBean);
        }

    }

    public interface OnDismissListener {
        void onDismiss(HuiJiGoodsFilterBean huiJiGoodsFilterBean);
    }

    private HuiJiFilterGoodsDialog.OnDismissListener onDismissListener;

    public void setOnDismissListener(HuiJiFilterGoodsDialog.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

}
