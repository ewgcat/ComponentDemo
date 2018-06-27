package com.yijian.staff.mvp.huiji.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.bean.HuiJiVipeCardAdapter;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by The_P on 2018/5/15.
 */

public class ViewHolderHuijiVipType_2 extends ViewHolderHuijiVipper {

    TextView tvTuijianRen;
    TextView tvTuijianRenPhone;
    TextView tvHuoquQudao;
    TextView tvTianjiaRenName;
    RecyclerView rv_card;

    TextView tv_card_classamount;
    TextView tv_card_classnum;
    TextView tv_card_totalcost;
    TextView tv_card_consumenum;
    TextView tv_card_remaindermoney;
    TextView tv_card_remaindernum;

    public ViewHolderHuijiVipType_2(View itemView) {
        super(itemView);
        rv_card = itemView.findViewById(R.id.rv_card);
        tvTuijianRen = itemView.findViewById(R.id.tv_tuijian_ren);
        tvTuijianRenPhone = itemView.findViewById(R.id.tv_tuijian_ren_phone);
        tvHuoquQudao = itemView.findViewById(R.id.tv_huoqu_qudao);
        tvTianjiaRenName = itemView.findViewById(R.id.tv_tianjia_ren_name);
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
    public void bindView(VipDetailBean vipDetailBean) {

        rv_card.setAdapter(new HuiJiVipeCardAdapter(vipDetailBean.getCardprods()));
        VipDetailBean.CustomerServiceInfoBean customerServiceInfoBean = vipDetailBean.getCustomerServiceInfo();
        tvTuijianRen.setText(judgeNull(customerServiceInfoBean.getReferee()));
        tvTuijianRenPhone.setText(judgeNull(customerServiceInfoBean.getRefereeMobile()));
        tvHuoquQudao.setText(judgeNull(customerServiceInfoBean.getUserChannel()));
        tvTianjiaRenName.setText(judgeNull(customerServiceInfoBean.getReceptionSale()));

        VipDetailBean.PrivateCourseInfoBean privateCourseInfoBean = vipDetailBean.getPrivateCourseInfo();
        VipDetailBean.ConsumeInfoBean consumeInfoBean = vipDetailBean.getConsumeInfo();
        tv_card_classamount.setText(judgeNull(privateCourseInfoBean.getCourseAmount()+""));
        tv_card_classnum.setText(judgeNull(privateCourseInfoBean.getCourseNum()+""));
        tv_card_totalcost.setText(judgeNull(consumeInfoBean.getConsumeAmount()+""));
        tv_card_consumenum.setText(judgeNull(privateCourseInfoBean.getCourseConsumeNum()+""));
        tv_card_remaindermoney.setText(judgeNull(consumeInfoBean.getCardSurplusAmount()+""));
        tv_card_remaindernum.setText(judgeNull(privateCourseInfoBean.getCourseSurplusNum()+""));


    }
}
