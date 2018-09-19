package com.yijian.clubmodule.ui.vipermanage.student.viperlist;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.yijian.clubmodule.ui.vipermanage.student.viperlist.filter.CoachViperFilterBean;
import com.yijian.clubmodule.ui.vipermanage.student.viperlist.filter.OptionDialog;
import com.yijian.clubmodule.ui.vipermanage.student.viperlist.fragment.CoachAllViperFragment;
import com.yijian.clubmodule.ui.vipermanage.student.viperlist.fragment.CoachVipTodayVisitFragment;
import com.yijian.commonlib.rx.RxBus;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;






@Route(path = "/test/1.1")
public class CoachViperListActivity extends MvcBaseActivity implements View.OnClickListener {


    TextView tv_label_all;
    TextView tv_label_visit;
    View view_all;
    View view_today_visit;
    private CoachVipTodayVisitFragment coachVipTodayVisitFragment;
    private CoachAllViperFragment coachAllViperFragment;
    private CoachViperFilterBean coachViperFilterBean;
    private OptionDialog optionDialog;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_vip_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tv_label_all = findViewById(R.id.tv_label_all);
        tv_label_visit = findViewById(R.id.tv_label_visit);
        view_all = findViewById(R.id.view_all);
        view_today_visit = findViewById(R.id.view_today_visit);

        findViewById(R.id.lin_all_vip).setOnClickListener(this);
        findViewById(R.id.lin_today_visit).setOnClickListener(this);

        NavigationBar navigationBar = findViewById(R.id.vip_over_navigation_bar);
        navigationBar.hideLeftSecondIv();
        navigationBar.setBackClickListener(this);
        Glide.with(this).load(R.mipmap.shaixuan_black).into(navigationBar.getmRightIv());
        navigationBar.setTitle("正式学员");
        navigationBar.setmRightTvText("筛选");
        changeFragment(0);
        optionDialog = new OptionDialog();
        optionDialog.setOnDismissListener(new OptionDialog.OnDismissListener() {
            @Override
            public void onDismiss(CoachViperFilterBean viperFilterBean) {
                RxBus.getDefault().post(viperFilterBean);
                coachViperFilterBean = viperFilterBean;
            }
        });
        navigationBar.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("coachViperFilterBean", coachViperFilterBean);
                optionDialog.setArguments(bundle);
                optionDialog.show(getFragmentManager(), "OptionDialog");
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
            if (coachAllViperFragment == null) {
                coachAllViperFragment = new CoachAllViperFragment();
                fragmentTransaction.add(R.id.fl_content, coachAllViperFragment);
            } else {
                fragmentTransaction.show(coachAllViperFragment);
            }
        } else {
            tv_label_all.setTextColor(Color.parseColor("#666666"));
            tv_label_visit.setTextColor(Color.parseColor("#31a4fc"));
            view_all.setVisibility(View.GONE);
            view_today_visit.setVisibility(View.VISIBLE);
            if (coachVipTodayVisitFragment == null) {
                coachVipTodayVisitFragment = new CoachVipTodayVisitFragment();
                fragmentTransaction.add(R.id.fl_content, coachVipTodayVisitFragment);
            } else {
                fragmentTransaction.show(coachVipTodayVisitFragment);
            }
        }
        fragmentTransaction.commit();


    }

    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {
        if (coachAllViperFragment != null && coachAllViperFragment.isAdded()) {
            fragmentTransaction.hide(coachAllViperFragment);
        }

        if (coachVipTodayVisitFragment != null && coachVipTodayVisitFragment.isAdded()) {
            fragmentTransaction.hide(coachVipTodayVisitFragment);
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
