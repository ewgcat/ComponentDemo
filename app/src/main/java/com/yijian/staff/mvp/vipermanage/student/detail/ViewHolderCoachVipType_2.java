package com.yijian.staff.mvp.vipermanage.student.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.vipermanage.card.ViperCardAdapter;
import com.yijian.staff.bean.ViperDetailBean;

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
        tv_card_classamount.setText(judgeNull(privateCourseInfoBean.getCourseAmount()+"元"));
        tv_card_classnum.setText(judgeNull(privateCourseInfoBean.getCourseNum()+"节"));
        tv_card_totalcost.setText(judgeNull(privateCourseInfoBean.getConsumeAmount()+"元"));
        tv_card_consumenum.setText(judgeNull(privateCourseInfoBean.getCourseConsumeNum()+"节"));
        tv_card_remaindermoney.setText(judgeNull(privateCourseInfoBean.getCardSurplusAmount()+"元"));
        tv_card_remaindernum.setText(judgeNull(privateCourseInfoBean.getCourseSurplusNum()+"节"));

    }

}
