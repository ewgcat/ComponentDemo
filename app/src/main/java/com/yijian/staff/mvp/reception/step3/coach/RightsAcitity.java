package com.yijian.staff.mvp.reception.step3.coach;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;
import com.yijian.staff.widget.NavigationBar2;

import java.math.BigDecimal;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by The_P on 2018/4/18.
 */

public class RightsAcitity extends AppCompatActivity {
    @BindView(R.id.tv_delay)
    TextView tvDelay;

    @BindView(R.id.tv_stop_card)
    TextView tvStopCard;
    @BindView(R.id.tv_transform)
    TextView tvTransform;
    @BindView(R.id.tv_exchange_shop)
    TextView tvExchangShop;
    @BindView(R.id.tv_back_card)
    TextView tvBackCard;
    @BindView(R.id.tv_card_transform)
    TextView tvCardTransform;
//    tv_card_transform


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_rights);
        ButterKnife.bind(this);
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar2);
        navigationBar2.setTitle("权益支持");
        navigationBar2.setTitleColor(R.color.text_black1);
        navigationBar2.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar2.setBackClickListener(this);

        Intent intent = getIntent();
        if (intent.hasExtra("productDetail")) {
            ProductDetail productDetail = (ProductDetail) intent.getSerializableExtra("productDetail");

            //延期
            String renewPoundageFee = productDetail.getRenewPoundageFee();
            Integer renewPoundageType = productDetail.getRenewPoundageType();// 续费折扣类型：0固定金额，1百分比 ,
            if (renewPoundageFee != null && renewPoundageType != null) {
                if (renewPoundageType.intValue() == 0) {
                    tvDelay.setText("" + renewPoundageFee + "元");
                } else if (renewPoundageType.intValue() == 1) {
                    tvDelay.setText("" + renewPoundageFee + "%");
                }
            }

            //停卡
            String stopPoundageFee = productDetail.getStopPoundageFee();
            Integer stopPoundageType = productDetail.getStopPoundageType();// 续费折扣类型：0固定金额，1百分比 ,
            if (stopPoundageFee != null && stopPoundageType != null) {
                if (stopPoundageType.intValue() == 0) {
                    tvStopCard.setText("" + stopPoundageFee + "元");
                } else if (stopPoundageType.intValue() == 1) {
                    tvStopCard.setText("" + stopPoundageFee + "%");
                }
            }

            //转让
            String transferFee = productDetail.getTransferPoundageFee();
            String transferType = productDetail.getTransferPoundageType();// 续费折扣类型：0固定金额，1百分比 ,
            if (transferFee != null && transferType != null) {
//                if (transferType.intValue()==0){
//                    tvTransform.setText(""+transferFee.doubleValue()+"元");
//                }else if (transferType.intValue()==1){
//                    NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
//                    tvTransform.setText(""+percent.format(transferFee.doubleValue()));
//                }
                if ("0".equals(transferType)) {
                    tvTransform.setText("" + transferFee + "元");
                } else if ("1".equals(transferType)) {
                    tvTransform.setText("" + transferFee + "%");
                }

            }

            //转店
            String changeShopFee = productDetail.getChangeShopPoundageFee();
            String changeShopType = productDetail.getChangeShopPoundageType();// 续费折扣类型：0固定金额，1百分比 ,
            if (changeShopType != null && changeShopFee != null) {
//                if (transferType.intValue()==0){
//                    tvExchangShop.setText(""+changeShopFee.doubleValue()+"元");
//                }else if (transferType.intValue()==1){
//                    NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
//                    tvExchangShop.setText(""+percent.format(changeShopFee.doubleValue()));
//                }

                if ("0".equals(changeShopType)) {
                    tvExchangShop.setText("" + changeShopFee + "元");
                } else if ("1".equals(changeShopType)) {
                    tvExchangShop.setText("" + changeShopFee + "%");
                }
            }


            //退卡
            String returnShopFee = productDetail.getReturnPoundageFee();
            Integer returnShopType = productDetail.getReturnPoundageType();// 续费折扣类型：0固定金额，1百分比 ,
            if (returnShopType != null && returnShopFee != null) {
//                if (transferType.intValue()==0){
//                    tvBackCard.setText(""+returnShopFee.doubleValue()+"元");
//                }else if (transferType.intValue()==1){
//                    NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
//                    tvBackCard.setText(""+percent.format(returnShopFee.doubleValue()));
//                }

                if (returnShopType.intValue() == 0) {
                    tvBackCard.setText("" + returnShopFee + "元");
                } else if (returnShopType.intValue() == 1) {
                    tvBackCard.setText("" + returnShopFee + "%");
                }

            }

            String switchPoundageFee = productDetail.getSwitchPoundageFee();
            String switchPoundageType = productDetail.getSwitchPoundageType();

            if (!TextUtils.isEmpty(switchPoundageFee) && !TextUtils.isEmpty(switchPoundageType)) {//卡品转换手续费类型：0固定金额，1百分比 ,
                if ("0".equals(switchPoundageType)) {
                    tvCardTransform.setText("" + changeShopFee + "元");
                } else if ("1".equals(switchPoundageType)) {
                    tvCardTransform.setText("" + changeShopFee + "%");
                }
            }

//            tvCardTransform


        }

    }


}
