package com.yijian.staff.mvp.reception.reception_step_ycm.step4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStatusChange;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.mvp.reception.step3.coach.RightsAcitity;
import com.yijian.staff.mvp.reception.step3.coach.bean.CardGiftDto;
import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;
import com.yijian.staff.mvp.reception.step4.ReceptionStepFourContract;
import com.yijian.staff.mvp.reception.step4.ReceptionStepFourPresenter;
import com.yijian.staff.widget.NavigationBar2;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by The_P on 2018/4/20.
 */

public class Step4Fragment_Sale extends Fragment implements ReceptionStepFourContract.View {
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
    @BindView(R.id.tv_yu_er_tip)
    TextView tvYuErTip;
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
    private RecptionerInfoBean consumerBean;

    Unbinder unbinder;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        consumerBean = arguments.getParcelable("recptionerInfoBean");
        if (consumerBean!=null)memberId = consumerBean.getId();
        NavigationBar2 navigationBar2 = ((ReceptionStepActivity) getActivity()).getNavigationBar2();

        navigationBar2.setmRightTvText("下一步");
        navigationBar2.getmRightTv().setVisibility(View.VISIBLE);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (statusChangeLisenter!=null)statusChangeLisenter.ReceptionStep3SaleToStep4();
                if (presenter!=null)presenter.getStatus(true,memberId);
            }
        });

        navigationBar2.getBackLL().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusChangeLisenter!=null)statusChangeLisenter.ReceptionStep4Back();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step4_sale, container, false);
        unbinder = ButterKnife.bind(this, view);

        presenter = new ReceptionStepFourPresenter(getContext());
        presenter.setView(this);
        if (!TextUtils.isEmpty(memberId)) presenter.getProductDetail(memberId);
        initView(view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void initView(View view) {

        tvChakanxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productDetail==null)return;
                Intent intent2 = new Intent(getContext(), RightsAcitity.class);
                intent2.putExtra("productDetail",productDetail);
                startActivity(intent2);
            }
        });
    }


    @Override
    public void showProductDetail(ProductDetail productDetail) {
        this.productDetail =productDetail;

        String cardName = productDetail.getCardName();
        tvCardName.setText(""+cardName );

        String cardTypeName = productDetail.getCardTypeName();
        tvCardtype.setText(""+cardTypeName);

//        BigDecimal salePrice = productDetail.getSalePrice();
//        if (salePrice!=null) tvPrice.setText(""+salePrice.doubleValue());
        String salePrice = productDetail.getSalePrice();
        if (!TextUtils.isEmpty(salePrice))tvPrice.setText(""+salePrice);


//        Integer cardType = productDetail.getCardType();// 卡类型:0期限卡,1次数卡,2储值卡,3会员制卡 ,
//        if (cardType!=null){
//            if (cardType==0){
//                tvCardtype.setText("期限卡");
//            }else if (cardType==1){
//                tvCardtype.setText("次数卡");
//            }else if (cardType==2){
//                tvCardtype.setText("储值卡");
//            }else if (cardType==3){
//                tvCardtype.setText("会员制卡");
//            }
//        }


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
        String strRestKey = productDetail.getStrRestKey();
        String strRestVal = productDetail.getStrRestVal();
        if (!TextUtils.isEmpty(strRestKey)&&!TextUtils.isEmpty(strRestVal)){
            tvYuErTip.setText(strRestKey);
            tvYuer.setText(strRestVal);
        }


        Map<Integer, List<CardGiftDto>> gift = productDetail.getGift();
        if (gift!=null){
            String giftName=" ";
            for(Map.Entry<Integer, List<CardGiftDto>> vo : gift.entrySet()){
                List<CardGiftDto> value = vo.getValue();
                for (int i = 0; i < value.size(); i++) {
                    giftName=giftName+value.get(i).getName()+"x"+value.get(i).getNum();
                }
            }

            tvZengsongkecheng.setText(giftName);
        }



        String rechargeGivePercent = productDetail.getRechargeGivePercent();
        if (!TextUtils.isEmpty(rechargeGivePercent)) tvChuzhiyouhui.setText("赠送" + rechargeGivePercent+"%");

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

    @Override
    public void showToStepFive() {
        presenter.getStatus(false, memberId);
    }

    @Override
    public void showStatus(ReceptionStastuBean receptionStastuBean) {
        if (statusChangeLisenter!=null)statusChangeLisenter.ReceptionStep4SaleToStep5( receptionStastuBean.getOperatorType());
    }

    @Override
    public void toReceptionStepFive() {
        presenter.toReceptionStepFive(memberId);
    }


    private ReceptionStatusChange statusChangeLisenter;

    public void setStatusChangeLisenter(ReceptionStatusChange statusChangeLisenter) {
        this.statusChangeLisenter = statusChangeLisenter;
    }
}
