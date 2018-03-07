package com.yijian.staff.mvp.vip.info;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.goodsdetail.GoodsRightSupportActivity;
import com.yijian.staff.mvp.reception.step4.ReceptionStepFourActivity;
import com.yijian.staff.mvp.reception.step5.ReceptionStepFiveActivity;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VipInfoActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.lin_all_vip)
    RelativeLayout lin_all_vip;
    @BindView(R.id.lin_today_visit)
    RelativeLayout lin_today_visit;
    @BindView(R.id.tv_label_all)
    TextView tv_label_all;
    @BindView(R.id.tv_label_visit)
    TextView tv_label_visit;
    @BindView(R.id.view_all)
    View view_all;
    @BindView(R.id.view_today_visit)
    View view_today_visit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_info);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {



        NavigationBar2 navigationBar2 = findViewById(R.id.vip_over_navigation_bar);
        navigationBar2.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar2.getmRightTv().setOnClickListener(this);
        navigationBar2.getFirstLeftIv().setOnClickListener(this);
        ImageView rightIv = navigationBar2.getmRightIv();
        Glide.with(this).load(R.mipmap.wt_shuaixuan).into(rightIv);
        navigationBar2.setTitle("会员信息");
        navigationBar2.setmRightTvText("筛选");
        changeFragment(0);

    }

    @OnClick({R.id.lin_all_vip, R.id.lin_today_visit})
    public void click(View view) {

        switch (view.getId()){
            case R.id.lin_all_vip:
                changeFragment(0);
                break;

            case R.id.lin_today_visit:
                changeFragment(1);
                break;
        }

    }

    private void changeFragment(int status){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(status == 0){
            tv_label_all.setTextColor(Color.parseColor("#31a4fc"));
            tv_label_visit.setTextColor(Color.parseColor("#666666"));
            view_all.setVisibility(View.VISIBLE);
            view_today_visit.setVisibility(View.GONE);
            fragmentTransaction.replace(R.id.fl_content,VipAllPeopleInfoFragment.getInstance());
        }else{
            tv_label_all.setTextColor(Color.parseColor("#666666"));
            tv_label_visit.setTextColor(Color.parseColor("#31a4fc"));
            view_all.setVisibility(View.GONE);
            view_today_visit.setVisibility(View.VISIBLE);
            fragmentTransaction.replace(R.id.fl_content,VipTodayVisitInfoFragment.getInstance());
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;

            case R.id.right_tv:


                break;

        }
    }
}
