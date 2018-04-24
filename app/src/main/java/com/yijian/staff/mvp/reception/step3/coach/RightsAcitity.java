package com.yijian.staff.mvp.reception.step3.coach;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_rights);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("productDetail")) {
            ProductDetail productDetail = intent.getParcelableExtra("productDetail");

            //延期
            BigDecimal renewPoundageFee = productDetail.getRenewPoundageFee();
            BigDecimal renewPoundageType = productDetail.getRenewPoundageType();// 续费折扣类型：0固定金额，1百分比 ,
            if (renewPoundageFee!=null&&renewPoundageType!=null){
                    if (renewPoundageType.intValue()==0){
                        tvDelay.setText(""+renewPoundageFee.doubleValue()+"元");
                    }else if (renewPoundageType.intValue()==1){
                        NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
                        tvDelay.setText(""+percent.format(renewPoundageFee.doubleValue()));
                    }
            }

            //停卡
            BigDecimal stopPoundageFee = productDetail.getStopPoundageFee();
            BigDecimal stopPoundageType = productDetail.getStopPoundageType();// 续费折扣类型：0固定金额，1百分比 ,
            if (stopPoundageFee!=null&&stopPoundageType!=null){
                if (stopPoundageType.intValue()==0){
                    tvStopCard.setText(""+stopPoundageFee.doubleValue()+"元");
                }else if (stopPoundageType.intValue()==1){
                    NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
                    tvStopCard.setText(""+percent.format(stopPoundageFee.doubleValue()));
                }
            }

            //转让
            BigDecimal transferFee = productDetail.getTransferPoundageFee();
            BigDecimal transferType = productDetail.getTransferPoundageType();// 续费折扣类型：0固定金额，1百分比 ,
            if (transferFee!=null&&transferType!=null){
                if (transferType.intValue()==0){
                    tvTransform.setText(""+transferFee.doubleValue()+"元");
                }else if (transferType.intValue()==1){
                    NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
                    tvTransform.setText(""+percent.format(transferFee.doubleValue()));
                }
            }

            //转店
            BigDecimal changeShopFee = productDetail.getChangeShopPoundageFee();
            BigDecimal changeShopType = productDetail.getChangeShopPoundageType();// 续费折扣类型：0固定金额，1百分比 ,
            if (changeShopType!=null&&changeShopFee!=null){
                if (transferType.intValue()==0){
                    tvExchangShop.setText(""+changeShopFee.doubleValue()+"元");
                }else if (transferType.intValue()==1){
                    NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
                    tvExchangShop.setText(""+percent.format(changeShopFee.doubleValue()));
                }
            }


            //退卡
            BigDecimal returnShopFee = productDetail.getReturnPoundageFee();
            BigDecimal returnShopType = productDetail.getReturnPoundageType();// 续费折扣类型：0固定金额，1百分比 ,
            if (returnShopType!=null&&returnShopFee!=null){
                if (transferType.intValue()==0){
                    tvBackCard.setText(""+returnShopFee.doubleValue()+"元");
                }else if (transferType.intValue()==1){
                    NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
                    tvBackCard.setText(""+percent.format(returnShopFee.doubleValue()));
                }
            }


        }

    }


}
