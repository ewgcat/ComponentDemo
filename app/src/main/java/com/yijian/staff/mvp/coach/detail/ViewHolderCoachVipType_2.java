package com.yijian.staff.mvp.coach.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.CoachVipDetailBean;
import com.yijian.staff.mvp.coach.card.CoachVipCardListAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by The_P on 2018/5/17.
 */

public class ViewHolderCoachVipType_2 extends ViewHolderCoachVipper {
    //会籍信息
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

    public ViewHolderCoachVipType_2(View itemView) {
        super(itemView);
        rv_card=  itemView.findViewById(R.id.rv_card);
        tvTuijianRen=  itemView.findViewById(R.id.tv_tuijian_ren);
        tvTuijianRenPhone=  itemView.findViewById(R.id.tv_tuijian_ren_phone);
        tvHuoquQudao=  itemView.findViewById(R.id.tv_huoqu_qudao);
        tvTianjiaRenName=  itemView.findViewById(R.id.tv_tianjia_ren_name);
        tvFuwuHuiji=  itemView.findViewById(R.id.tv_fuwu_huiji);
        tvFuwuJiaolian=  itemView.findViewById(R.id.tv_fuwu_jiaolian);
        tvSijiaoClass= itemView.findViewById(R.id.tv_sijiao_class);
        rlSijiaoClass= itemView.findViewById(R.id.rl_sijiao_class);


        //会籍信息
        rv_card.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        rv_card.setNestedScrollingEnabled(false);
    }

    @Override
    public void bindView(CoachVipDetailBean coachVipDetailBean) {

        List<CoachVipDetailBean.CardprodsBean>   cardprodsBeans = coachVipDetailBean.getCardprods();
        rv_card.setAdapter(new CoachVipCardListAdapter(cardprodsBeans));
        CoachVipDetailBean.CustomerServiceInfoBean customerServiceInfoBean = coachVipDetailBean.getCustomerServiceInfo();
        tvTuijianRen.setText(customerServiceInfoBean.getReferee());
        tvTuijianRenPhone.setText(customerServiceInfoBean.getRefereeMobile());
        tvHuoquQudao.setText(customerServiceInfoBean.getUserChannel());
        tvTianjiaRenName.setText(customerServiceInfoBean.getReceptionSale());
        tvFuwuHuiji.setText(customerServiceInfoBean.getServiceSale());
        tvFuwuJiaolian.setText(customerServiceInfoBean.getServiceCoach());
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
