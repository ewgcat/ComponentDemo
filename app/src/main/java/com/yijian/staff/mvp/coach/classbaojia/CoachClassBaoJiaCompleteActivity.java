package com.yijian.staff.mvp.coach.classbaojia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoachClassBaoJiaCompleteActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_class_bao_jia_complete);
        ButterKnife.bind(this);

        NavigationBar2 navigationBar2 = findViewById(R.id.no_search_bar_complete_navigationbar);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("产品报价");

    }

    @OnClick(R.id.ll_post)
    public void onViewClicked() {

        finish();
    }
}
