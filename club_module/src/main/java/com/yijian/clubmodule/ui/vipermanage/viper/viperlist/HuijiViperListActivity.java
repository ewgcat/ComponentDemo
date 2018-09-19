package com.yijian.clubmodule.ui.vipermanage.viper.viperlist;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.yijian.clubmodule.ui.vipermanage.viper.viperlist.filter.HuijiFilterViperDialog;
import com.yijian.clubmodule.ui.vipermanage.viper.viperlist.filter.HuijiViperFilterBean;
import com.yijian.clubmodule.ui.vipermanage.viper.viperlist.fragment.HuijiAllViperFragment;
import com.yijian.clubmodule.ui.vipermanage.viper.viperlist.fragment.HuijiTodayVisitFragment;
import com.yijian.commonlib.rx.RxBus;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;


@Route(path = "/test/1")
public class HuijiViperListActivity extends MvcBaseActivity implements View.OnClickListener {

    TextView tv_label_all;
    TextView tv_label_visit;
    View view_all;
    View view_today_visit;

    private HuijiTodayVisitFragment huijiTodayVisitFragment;
    private HuijiAllViperFragment huijiAllViperFragment;
    private HuijiFilterViperDialog filterDialog;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_vip_huiji_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initView();

    }

    private void initView() {
        tv_label_all = findView(R.id.tv_label_all);
        tv_label_visit = findView(R.id.tv_label_visit);
        view_all = findView(R.id.view_all);
        view_today_visit = findView(R.id.view_today_visit);

        findView(R.id.lin_all_vip).setOnClickListener(this);
        findView(R.id.lin_today_visit).setOnClickListener(this);

        NavigationBar navigationBar = findViewById(R.id.vip_over_navigation_bar);
        navigationBar.hideLeftSecondIv();
        navigationBar.setBackClickListener(this);
        Glide.with(this).load(R.mipmap.shaixuan_black).into(navigationBar.getmRightIv());
        navigationBar.setTitle("会员信息");
        navigationBar.setmRightTvText("筛选");
        changeFragment(0);
        filterDialog = new HuijiFilterViperDialog(this);
        filterDialog.setOnDismissListener(new HuijiFilterViperDialog.OnDismissListener() {
            @Override
            public void onDismiss(HuijiViperFilterBean huijiViperFilterBean) {
                RxBus.getDefault().post(huijiViperFilterBean);
            }
        });
        navigationBar.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog.showFilterDialog();
            }
        });


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
                huijiTodayVisitFragment = new HuijiTodayVisitFragment();
                fragmentTransaction.add(R.id.fl_content, huijiTodayVisitFragment);
            } else {
                fragmentTransaction.show(huijiTodayVisitFragment);
            }
        }
        fragmentTransaction.commit();


    }

    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {

        if (huijiAllViperFragment != null && huijiAllViperFragment.isAdded()) {
            fragmentTransaction.hide(huijiAllViperFragment);
        }

        if (huijiTodayVisitFragment != null && huijiTodayVisitFragment.isAdded()) {
            fragmentTransaction.hide(huijiTodayVisitFragment);
        }
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.lin_all_vip) {
            changeFragment(0);

        } else if (i == R.id.lin_today_visit) {
            changeFragment(1);

        }

    }
}
