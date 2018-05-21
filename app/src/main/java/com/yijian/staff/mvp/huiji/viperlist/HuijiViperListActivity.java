package com.yijian.staff.mvp.huiji.viperlist;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.viperlist.filter.HuijiFilterViperDialog;
import com.yijian.staff.mvp.huiji.viperlist.filter.HuijiViperFilterBean;
import com.yijian.staff.mvp.huiji.viperlist.fragment.HuijiAllViperFragment;
import com.yijian.staff.mvp.huiji.viperlist.fragment.HuijiTodayVisitFragment;
import com.yijian.staff.rx.RxBus;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


@Route(path = "/test/1")
public class HuijiViperListActivity extends AppCompatActivity implements View.OnClickListener {

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
    private HuijiTodayVisitFragment huijiTodayVisitFragment;
    private HuijiAllViperFragment huijiAllViperFragment;
    private HuijiFilterViperDialog filterDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_huiji_info);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {


        NavigationBar2 navigationBar2 = findViewById(R.id.vip_over_navigation_bar2);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setmRightTvClickListener(this);
        navigationBar2.setBackClickListener(this);
        ImageView rightIv = navigationBar2.getmRightIv();
        Glide.with(this).load(R.mipmap.shaixuan_black).into(rightIv);
        navigationBar2.setTitle("会员信息");
        navigationBar2.setmRightTvText("筛选");
        changeFragment(0);
        filterDialog = new HuijiFilterViperDialog(this);
        filterDialog.setOnDismissListener(new HuijiFilterViperDialog.OnDismissListener() {
            @Override
            public void onDismiss(HuijiViperFilterBean huijiViperFilterBean) {
                RxBus.getDefault().post(huijiViperFilterBean);
            }
        });

    }

    @OnClick({R.id.lin_all_vip, R.id.lin_today_visit})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.lin_all_vip:
                changeFragment(0);
                break;

            case R.id.lin_today_visit:
                changeFragment(1);
                break;
        }

    }

    private void changeFragment(int status) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllIndex(fragmentTransaction);
        if (status == 0) {
            tv_label_all.setTextColor(Color.parseColor("#31a4fc"));
            tv_label_visit.setTextColor(Color.parseColor("#666666"));
            view_all.setVisibility(View.VISIBLE);
            view_today_visit.setVisibility(View.GONE);
            if (huijiAllViperFragment == null) {
                huijiAllViperFragment = new HuijiAllViperFragment();
                fragmentTransaction.add(R.id.fl_content, huijiAllViperFragment);
            } else {
                fragmentTransaction.show(huijiAllViperFragment);
            }
        } else {
            tv_label_all.setTextColor(Color.parseColor("#666666"));
            tv_label_visit.setTextColor(Color.parseColor("#31a4fc"));
            view_all.setVisibility(View.GONE);
            view_today_visit.setVisibility(View.VISIBLE);
            if (huijiTodayVisitFragment == null) {
                huijiTodayVisitFragment =new HuijiTodayVisitFragment();
                fragmentTransaction.add(R.id.fl_content, huijiTodayVisitFragment);
            } else {
                fragmentTransaction.show(huijiTodayVisitFragment);
            }
        }
        fragmentTransaction.commit();


    }

    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {

        if (huijiAllViperFragment!=null&&huijiAllViperFragment.isAdded()) {
            fragmentTransaction.hide(huijiAllViperFragment);
        }

        if (huijiTodayVisitFragment!=null&&huijiTodayVisitFragment.isAdded()) {
            fragmentTransaction.hide(huijiTodayVisitFragment);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;

            case R.id.right_tv:
                filterDialog.showFilterDialog();

                break;

        }
    }
}
