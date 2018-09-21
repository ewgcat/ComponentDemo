package com.yijian.clubmodule.ui.vipermanage.student.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.ViperDetailBean;
import com.yijian.clubmodule.ui.vipermanage.card.ViperCardAdapter;

/**
 * Created by The_P on 2018/5/17.
 */

public class ViewHolderCoachVipType_2 extends ViewHolderCoachVipper {
    TextView tvTuijianRen;
    TextView tvTuijianRenPhone;
//    TextView tvHuoquQudao;
//    TextView tvTianjiaRenName;
    RecyclerView rv_card;

    TextView tv_card_classamount;
    TextView tv_card_classnum;
    TextView tv_card_totalcost;
    TextView tv_card_consumenum;
    TextView tv_card_remaindermoney;
    TextView tv_card_remaindernum;
    TextView tv_card_amount; //卡总金额


    public ViewHolderCoachVipType_2(View itemView) {
        super(itemView);
        rv_card = itemView.findViewById(R.id.rv_card);
        tvTuijianRen = itemView.findViewById(R.id.tv_tuijian_ren);
        tvTuijianRenPhone = itemView.findViewById(R.id.tv_tuijian_ren_phone);
//        tvHuoquQudao = itemView.findViewById(R.id.tv_huoqu_qudao);
//        tvTianjiaRenName = itemView.findViewById(R.id.tv_tianjia_ren_name);
        rv_card.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        rv_card.setNestedScrollingEnabled(false);

        tv_card_classamount = itemView.findViewById(R.id.tv_card_classamount);
        tv_card_classnum = itemView.findViewById(R.id.tv_card_classnum);
        tv_card_totalcost = itemView.findViewById(R.id.tv_card_totalcost);
        tv_card_consumenum = itemView.findViewById(R.id.tv_card_consumenum);
        tv_card_remaindermoney = itemView.findViewById(R.id.tv_card_remaindermoney);
        tv_card_remaindernum = itemView.findViewById(R.id.tv_card_remaindernum);
        tv_card_amount = itemView.findViewById(R.id.tv_card_amount);
    }

    @Override
    public void bindView(ViperDetailBean viperDetailBean) {

        rv_card.setAdapter(new ViperCardAdapter(viperDetailBean.getCardprods()));
        ViperDetailBean.CustomerServiceInfoBean customerServiceInfoBean = viperDetailBean.getCustomerServiceInfo();
        tvTuijianRen.setText(judgeNull(customerServiceInfoBean.getReferee()));
        tvTuijianRenPhone.setText(judgeNull(customerServiceInfoBean.getRefereeMobile()));
      /*  tvHuoquQudao.setText(judgeNull(customerServiceInfoBean.getUserChannel()));
        tvTianjiaRenName.setText(judgeNull(customerServiceInfoBean.getReceptionSale()));*/

        ViperDetailBean.PrivateCourseAndConsumeInfoBean privateCourseInfoBean = viperDetailBean.getPrivateCourseAndConsumeInfo();
        String courseAmount = privateCourseInfoBean.getCourseAmount();
        if (!TextUtils.isEmpty(courseAmount)){
            tv_card_classamount.setText(courseAmount+"元");
        }else {
            tv_card_classamount.setText("暂无录入");
        }
        String courseNum =privateCourseInfoBean.getCourseNum();
        if (!TextUtils.isEmpty(courseNum)){
            tv_card_classnum.setText(courseNum+"节");
        }else {
            tv_card_classnum.setText("暂无录入");
        }
        String consumeAmount = privateCourseInfoBean.getConsumeAmount();
        if (!TextUtils.isEmpty(consumeAmount)){
            tv_card_totalcost.setText(consumeAmount+"元");
        }else {
            tv_card_totalcost.setText("暂无录入");
        }
        String courseConsumeNum = privateCourseInfoBean.getCourseConsumeNum();
        if (!TextUtils.isEmpty(courseConsumeNum)){
            tv_card_consumenum.setText(courseConsumeNum+"元");
        }else {
            tv_card_consumenum.setText("暂无录入");
        }
        String cardSurplusAmount = privateCourseInfoBean.getCardSurplusAmount();
        if (!TextUtils.isEmpty(cardSurplusAmount)){
            tv_card_remaindermoney.setText(cardSurplusAmount+"元");
        }else {
            tv_card_remaindermoney.setText("暂无录入");
        }
        String courseSurplusNum = privateCourseInfoBean.getCourseSurplusNum();
        if (!TextUtils.isEmpty(courseSurplusNum)){
            tv_card_remaindernum.setText(courseSurplusNum+"元");
        }else {
            tv_card_remaindernum.setText("暂无录入");
        }


    }

}
