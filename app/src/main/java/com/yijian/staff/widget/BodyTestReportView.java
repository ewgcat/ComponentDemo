package com.yijian.staff.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/3 11:07:05
 */
public class BodyTestReportView extends LinearLayout {


    @BindView(R.id.ll_rentichengfen)
    LinearLayout llRentichengfen;
    @BindView(R.id.tv_rentichenfendetail_controller)
    TextView tvRentichenfendetailController;

    @BindView(R.id.ll_jichuceliang)
    LinearLayout llJichuceliang;
    @BindView(R.id.tv_jichuceliangdetail_controller)
    TextView tvJichuceliangdetailController;

    @BindView(R.id.ll_titai_cemian)
    LinearLayout llTitaiCemian;
    @BindView(R.id.tv_titaipinggucemiandetail_controller)
    TextView tvTitaipinggucemiandetailController;


    @BindView(R.id.ll_titai_beimian)
    LinearLayout llTitaiBeimian;
    @BindView(R.id.tv_titaipinggubeimiandetail_controller)
    TextView tvTitaipinggubeimiandetailController;


    @BindView(R.id.ll_jianshenchufang)
    LinearLayout llJianshenchufang;
    @BindView(R.id.tv_jianshenchufangdetail_controller)
    TextView tvJianshenchufangdetailController;

    private Context context;

    public BodyTestReportView(Context context) {
        this(context, null);
    }

    public BodyTestReportView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BodyTestReportView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_titaipinggucemiandetail_controller,
            R.id.tv_titaipinggubeimiandetail_controller,
            R.id.tv_jichuceliangdetail_controller,
            R.id.tv_rentichenfendetail_controller,
            R.id.tv_jianshenchufangdetail_controller})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rentichenfendetail_controller:
                changeDetailShow(llRentichengfen, tvRentichenfendetailController);
                break;
            case R.id.tv_jichuceliangdetail_controller:
                changeDetailShow(llJichuceliang, tvJichuceliangdetailController);
                break;
            case R.id.tv_titaipinggucemiandetail_controller:
                changeDetailShow(llTitaiCemian, tvTitaipinggucemiandetailController);
                break;
            case R.id.tv_titaipinggubeimiandetail_controller:
                changeDetailShow(llTitaiBeimian, tvTitaipinggubeimiandetailController);
                break;
            case R.id.tv_jianshenchufangdetail_controller:
                changeDetailShow(llJianshenchufang, tvJianshenchufangdetailController);
                break;


        }
    }

    private void changeDetailShow(LinearLayout linearLayout, TextView textView) {
        if (linearLayout.getVisibility() == View.VISIBLE) {
            linearLayout.setVisibility(View.GONE);
            textView.setText("展开");
            Drawable icon_zhankai = getResources().getDrawable(R.mipmap.lg_zhankai);
            icon_zhankai.setBounds(0, 0, icon_zhankai.getMinimumWidth(), icon_zhankai.getMinimumHeight());
            textView.setCompoundDrawables(null, null, icon_zhankai, null);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
            textView.setText("收起");
            Drawable icon_shouqi = getResources().getDrawable(R.mipmap.lg_shouqi);
            icon_shouqi.setBounds(0, 0, icon_shouqi.getMinimumWidth(), icon_shouqi.getMinimumHeight());

            textView.setCompoundDrawables(null, null, icon_shouqi, null);
        }
    }
}
