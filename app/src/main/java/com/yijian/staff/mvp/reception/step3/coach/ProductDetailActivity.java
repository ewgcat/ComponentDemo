package com.yijian.staff.mvp.reception.step3.coach;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by The_P on 2018/4/18.
 */

public class ProductDetailActivity extends AppCompatActivity{

    @BindView(R.id.tv_card_name)
    TextView tvCardName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_cardtype)
    TextView tvCardtype;
    @BindView(R.id.tv_changguan)
    TextView tvChangguan;
    @BindView(R.id.tv_zengsongkecheng)
    TextView tvZengsongkecheng;
    @BindView(R.id.tv_rights)
    TextView tvRights;
    @BindView(R.id.tv_yuer)
    TextView tvYuer;
    @BindView(R.id.tv_chuzhiyouhui)
    TextView tvChuzhiyouhui;
    @BindView(R.id.tv_chakanxiangqing)
    TextView tvChakanxiangqing;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    private ProductDetail productDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_product_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("productDetail")){
            productDetail = intent.getParcelableExtra("productDetail");
            String cardTypeName = productDetail.getCardTypeName();
            tvCardName.setText(""+cardTypeName);

            BigDecimal salePrice = productDetail.getSalePrice();
          if (salePrice!=null) tvPrice.setText(""+salePrice.doubleValue());

            Integer cardType = productDetail.getCardType();// 卡类型:0期限卡,1次数卡,2储值卡,3会员制卡 ,
            if (cardType!=null){
                if (cardType==0){
                    tvCardtype.setText("期限卡");
                }else if (cardType==1){
                    tvCardtype.setText("次数卡");
                }else if (cardType==2){
                    tvCardtype.setText("储值卡");
                }else if (cardType==3){
                    tvCardtype.setText("会员制卡");
                }
            }

            List<String> venueNames = productDetail.getVenueNames();
            if (venueNames != null && venueNames.size() != 0) {
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; i < venueNames.size(); i++) {
                    stringBuilder.append("、").append(venueNames.get(i));
                }
                String substring = stringBuilder.substring(1);
                tvChangguan.setText(substring);
            }

            Integer validDay = productDetail.getValidDay();
            if (validDay != null) tvYuer.setText("" + validDay + "天");

            Integer validTime = productDetail.getValidTime();
            if (validTime != null) tvYuer.setText("" + validTime + "次");

            BigDecimal rechargeGivePercent = productDetail.getRechargeGivePercent();
            if (rechargeGivePercent != null) {
                NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
                String format = percent.format(rechargeGivePercent);
                tvChuzhiyouhui.setText("赠送" + format);
            }

            List<String> rightsInterestsList = productDetail.getRightsInterestsList();
            if (rightsInterestsList != null && rightsInterestsList.size() != 0) {
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; i < rightsInterestsList.size(); i++) {
                    stringBuilder.append("、").append(venueNames.get(i));
                }
                String substring = stringBuilder.substring(1);
                tvRights.setText(substring);
            }


            String createDate = productDetail.getCreateDate();
            tvStartTime.setText(""+createDate);

            String expirationDate = productDetail.getExpirationDate();
            tvEndTime.setText(""+expirationDate);



        }



    }

    @OnClick({R.id.tv_chakanxiangqing})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_chakanxiangqing:
                Intent intent2 = new Intent(ProductDetailActivity.this, RightsAcitity.class);
                intent2.putExtra("productDetail",productDetail);
                startActivity(intent2);
                break;
        }
    }

}
