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

/**
 * Created by The_P on 2018/5/15.
 */

public class ViewHolderHuijiVipType_2 extends  ViewHolderHuijiVipper{
//    @BindView(R.id.rv_card)
    RecyclerView rv_card;
//    @BindView(R.id.tv_tuijian_ren)
    TextView tvTuijianRen;
//    @BindView(R.id.tv_tuijian_ren_phone)
    TextView tvTuijianRenPhone;
//    @BindView(R.id.tv_huoqu_qudao)
    TextView tvHuoquQudao;
//    @BindView(R.id.tv_tianjia_ren_name)
    TextView tvTianjiaRenName;
//    @BindView(R.id.tv_fuwu_huiji)
    TextView tvFuwuHuiji;
//    @BindView(R.id.tv_fuwu_jiaolian)
    TextView tvFuwuJiaolian;
//    @BindView(R.id.tv_sijiao_class)
    TextView tvSijiaoClass;
//    @BindView(R.id.rl_sijiao_class)
    RelativeLayout rlSijiaoClass;
    public ViewHolderHuijiVipType_2(View itemView) {
        super(itemView);
        rv_card= itemView.findViewById(R.id.rv_card);
        tvTuijianRen= itemView.findViewById(R.id.tv_tuijian_ren);
        tvTuijianRenPhone= itemView.findViewById(R.id.tv_tuijian_ren_phone);
        tvHuoquQudao= itemView.findViewById(R.id.tv_huoqu_qudao);
        tvTianjiaRenName= itemView.findViewById(R.id.tv_tianjia_ren_name);
        tvFuwuHuiji= itemView.findViewById(R.id.tv_fuwu_huiji);
        tvFuwuJiaolian= itemView.findViewById(R.id.tv_fuwu_jiaolian);
        tvSijiaoClass= itemView.findViewById(R.id.tv_sijiao_class);
        rlSijiaoClass= itemView.findViewById(R.id.rl_sijiao_class);


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
        tvFuwuHuiji.setText(judgeNull(customerServiceInfoBean.getServiceSale()));
        tvFuwuJiaolian.setText(judgeNull(customerServiceInfoBean.getServiceCoach()));
        List<String> privateCourses = customerServiceInfoBean.getPrivateCourses();
        if (privateCourses != null && privateCourses.size() > 0) {
            rlSijiaoClass.setVisibility(View.VISIBLE);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < privateCourses.size(); i++) {
                String s = privateCourses.get(i) + " ";
                sb.append(s);
            }
            tvSijiaoClass.setText(sb.toString());
        } else {
            rlSijiaoClass.setVisibility(View.GONE);
        }

    }
}
