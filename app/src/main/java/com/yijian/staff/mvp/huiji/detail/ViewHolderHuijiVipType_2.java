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

public class ViewHolderHuijiVipType_2 extends  ViewHolderHuijiVipper{
    @BindView(R.id.tv_tuijian_ren)
    TextView tvTuijianRen;
    @BindView(R.id.tv_tuijian_ren_phone)
    TextView tvTuijianRenPhone;
    @BindView(R.id.tv_huoqu_qudao)
    TextView tvHuoquQudao;

    @BindView(R.id.tv_tianjia_ren_name)
    TextView tvTianjiaRenName;
    @BindView(R.id.rv_card)
    RecyclerView rv_card;

    public ViewHolderHuijiVipType_2(View itemView) {
        super(itemView);
        rv_card= itemView.findViewById(R.id.rv_card);
        tvTuijianRen= itemView.findViewById(R.id.tv_tuijian_ren);
        tvTuijianRenPhone= itemView.findViewById(R.id.tv_tuijian_ren_phone);
        tvHuoquQudao= itemView.findViewById(R.id.tv_huoqu_qudao);
        tvTianjiaRenName= itemView.findViewById(R.id.tv_tianjia_ren_name);

    }

    @Override
  public   void bindView(VipDetailBean vipDetailBean) {
        rv_card.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        rv_card.setNestedScrollingEnabled(false);
        rv_card.setAdapter(new HuiJiVipeCardAdapter(vipDetailBean.getCardprods()));
        VipDetailBean.CustomerServiceInfoBean customerServiceInfoBean = vipDetailBean.getCustomerServiceInfo();
        tvTuijianRen.setText(judgeNull(customerServiceInfoBean.getReferee()));
        tvTuijianRenPhone.setText(judgeNull(customerServiceInfoBean.getRefereeMobile()));
        tvHuoquQudao.setText(judgeNull(customerServiceInfoBean.getUserChannel()));
        tvTianjiaRenName.setText(judgeNull(customerServiceInfoBean.getReceptionSale()));

    }
}
