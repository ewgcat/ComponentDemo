package com.yijian.staff.mvp.price.courseprice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.contract.ContractActivity;
import com.yijian.staff.widget.NavigationBar;

import butterknife.BindView;
import butterknife.OnClick;

public class CoachClassBaoJiaCompleteActivity extends MvcBaseActivity {

    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.tv_class_jieshu)
    TextView tvClassJieshu;
    @BindView(R.id.tv_class_long_time)
    TextView tvClassLongTime;
    @BindView(R.id.tv_shuangyuechenfa)
    TextView tvShuangyuechenfa;
    @BindView(R.id.tv_is_support_stop_class)
    TextView tvIsSupportStopClass;
    @BindView(R.id.tv_stop_class_charge)
    TextView tvStopClassCharge;
    @BindView(R.id.tv_stop_class_max_time)
    TextView tvStopClassMaxTime;
    @BindView(R.id.tv_is_support_delay)
    TextView tvIsSupportDelay;
    @BindView(R.id.tv_delay_charge)
    TextView tvDelayCharge;
    @BindView(R.id.tv_is_support_tui_ke)
    TextView tvIsSupportTuiKe;
    @BindView(R.id.tv_ke_pai_men_dian)
    TextView tvKePaiMenDian;
    @BindView(R.id.tv_kecheng_desc)
    TextView tvKechengDesc;


    private String memberName;
    private String memberId;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_coach_class_bao_jia_complete;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar NavigationBar = findViewById(R.id.no_search_bar_complete_navigationbar);
        NavigationBar.hideLeftSecondIv();
        NavigationBar.setBackClickListener(this);
        NavigationBar.setTitle("订单详情");
        memberId = getIntent().getStringExtra("memberId");
        memberName = getIntent().getStringExtra("memberName");
    }

    @OnClick(R.id.ll_post)
    public void onViewClicked() {
        //TODO 查看合同

        Intent intent1 = new Intent(CoachClassBaoJiaCompleteActivity.this, ContractActivity.class);
        intent1.putExtra("memberId", memberId);
//        intent1.putStringArrayListExtra("contractIds",vipDetailBean.getContractIds());
        startActivity(intent1);

    }
}
