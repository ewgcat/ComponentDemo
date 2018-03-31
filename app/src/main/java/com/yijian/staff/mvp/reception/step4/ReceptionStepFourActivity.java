package com.yijian.staff.mvp.reception.step4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.yijian.staff.R;
import com.yijian.staff.mvp.goodsdetail.GoodsRightSupportActivity;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.step5.ReceptionStepFiveActivity;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceptionStepFourActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_cardtype)
    TextView tvCardtype;
    @BindView(R.id.tv_changguan)
    TextView tvChangguan;
    @BindView(R.id.tv_zengsongkecheng)
    TextView tvZengsongkecheng;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_step_four);
        ButterKnife.bind(this);
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
                Intent intent = new Intent(ReceptionStepFourActivity.this, ReceptionStepFiveActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                break;
            case R.id.tv_chakanxiangqing:
                startActivity(new Intent(ReceptionStepFourActivity.this, GoodsRightSupportActivity.class));

                break;
        }
    }
}
