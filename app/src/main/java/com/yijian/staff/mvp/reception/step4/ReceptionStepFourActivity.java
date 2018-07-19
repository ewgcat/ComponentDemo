package com.yijian.staff.mvp.reception.step4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.step3.coach.RightsAcitity;
import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;
import com.yijian.staff.mvp.reception.step5.ReceptionStepFiveActivity;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceptionStepFourActivity extends AppCompatActivity implements View.OnClickListener, ReceptionStepFourContract.View {
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
    private String memberId;
    private ProductDetail productDetail;
    private ReceptionStepFourPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_step_four);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("memberId")) {
            memberId = intent.getStringExtra("memberId");
        }
        presenter = new ReceptionStepFourPresenter(this);
        presenter.setView(this);
        if (!TextUtils.isEmpty(memberId)) presenter.getProductDetail(memberId);
        initView();


    }

    private void initView() {

        NavigationBar2 navigationBar2 = findViewById(R.id.step_four_navigation_bar2);
        navigationBar2.getFirstLeftIv().setOnClickListener(this);
        navigationBar2.getSecondLeftIv().setOnClickListener(this);
        navigationBar2.getmRightTv().setOnClickListener(this);
        navigationBar2.setTitle("订单详情(4/5)");
        navigationBar2.setmRightTvText("下一步");

        TimeBar timeBar = findViewById(R.id.step_four_timebar);
        timeBar.showTimeBar(4);


        findViewById(R.id.tv_chakanxiangqing).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;
            case R.id.iv_second_left:

                Intent i = new Intent(this, ReceptionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

                break;
            case R.id.right_tv:
                if (!TextUtils.isEmpty(memberId)) presenter.toReceptionStepFive(memberId);
                break;
            case R.id.tv_chakanxiangqing:
//                startActivity(new Intent(ReceptionStepFourActivity.this, GoodsRightSupportActivity.class));
                if (productDetail == null) return;
                Intent intent2 = new Intent(ReceptionStepFourActivity.this, RightsAcitity.class);
                intent2.putExtra("productDetail", productDetail);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void showProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;


        String cardTypeName = productDetail.getCardTypeName();
        tvCardName.setText("" + cardTypeName);

//        BigDecimal salePrice = productDetail.getSalePrice();
//        if (salePrice!=null) tvPrice.setText(""+salePrice.doubleValue());

        Integer cardType = productDetail.getCardType();// 卡类型:0期限卡,1次数卡,2储值卡,3会员制卡 ,
        if (cardType != null) {
            if (cardType == 0) {
                tvCardtype.setText("期限卡");
            } else if (cardType == 1) {
                tvCardtype.setText("次数卡");
            } else if (cardType == 2) {
                tvCardtype.setText("储值卡");
            } else if (cardType == 3) {
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

//        Integer validDay = productDetail.getValidDay();
//        if (validDay != null) tvYuer.setText("" + validDay + "天");
//
//        Integer validTime = productDetail.getValidTime();
//        if (validTime != null) tvYuer.setText("" + validTime + "次");
//
//        BigDecimal rechargeGivePercent = productDetail.getRechargeGivePercent();
//        if (rechargeGivePercent != null) {
//            NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
//            String format = percent.format(rechargeGivePercent);
//            tvChuzhiyouhui.setText("赠送" + format);
//        }

        String strRestKey = productDetail.getStrRestKey();
        String strRestVal = productDetail.getStrRestVal();
        if (!TextUtils.isEmpty(strRestKey) && !TextUtils.isEmpty(strRestVal)) {
            tvYuer.setText(strRestKey + strRestVal);
        }

        String rechargeGivePercent = productDetail.getRechargeGivePercent();
        if (!TextUtils.isEmpty(rechargeGivePercent)) tvChuzhiyouhui.setText("赠送" + rechargeGivePercent + "%");

        String salePrice = productDetail.getSalePrice();
        if (!TextUtils.isEmpty(salePrice)) tvPrice.setText("" + salePrice);

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
        tvStartTime.setText("" + createDate);

        String expirationDate = productDetail.getExpirationDate();
        tvEndTime.setText("" + expirationDate);


    }

    @Override
    public void showToStepFive() {
        Intent intent = new Intent(ReceptionStepFourActivity.this, ReceptionStepFiveActivity.class);
        if (!TextUtils.isEmpty(memberId)) intent.putExtra("memberId", memberId);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public void showStatus(ReceptionStastuBean receptionStastuBean) {

    }

    @Override
    public void toReceptionStepFive() {

    }

//    @Override
//    public void shouldOrderToFinish() {
//
//    }
}
