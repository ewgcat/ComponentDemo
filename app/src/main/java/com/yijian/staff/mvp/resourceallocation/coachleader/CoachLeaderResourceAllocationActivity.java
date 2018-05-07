package com.yijian.staff.mvp.resourceallocation.coachleader;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.coachleader.fragment.distribution.CoachResourceAllocationFragment;
import com.yijian.staff.mvp.resourceallocation.coachleader.fragment.history.CoachHistoryAllocationFragment;
import com.yijian.staff.widget.NavigationBar2;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资源分配(教练总监角色)
 */
@Route(path = "/test/10.4")
public class CoachLeaderResourceAllocationActivity extends AppCompatActivity {





    private static final String[] FRAGMENT_TAG = {"CoachResourceAllocationFragment", "CoachHistoryAllocationFragment"};
    private CoachResourceAllocationFragment resourceAllocationFragment;
    private CoachHistoryAllocationFragment historyAllocationFragment;
    private NavigationBar2 navigationBar2;

    @BindView(R.id.lin_resource_allowcation)
    LinearLayout lin_resource_allowcation;
    @BindView(R.id.lin_history_allowcation)
    LinearLayout lin_history_allowcation;
    @BindView(R.id.tv_resource_allowcation)
    TextView tv_resource_allowcation;
    @BindView(R.id.iv_resource_allowcation)
    ImageView iv_resource_allowcation;
    @BindView(R.id.tv_history_allowcation)
    TextView tv_history_allowcation;
    @BindView(R.id.iv_history_allowcation)
    ImageView iv_history_allowcation;

    private int pageNum = 1;
    private int pageSize = 1;
    private int pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_leader_resource_allocation);
        ButterKnife.bind(this);
        initView();


    }



    private void initView() {
        navigationBar2 = findViewById(R.id.coach_leader_navigation_bar2);

        navigationBar2.setTitle("资源分配");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        selectTab(0);
    }





    @OnClick({R.id.lin_resource_allowcation, R.id.lin_history_allowcation})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.lin_resource_allowcation: //资源分配
                selectTab(0);
                break;
            case R.id.lin_history_allowcation: //历史分配
                selectTab(1);
                break;
        }
    }

    public void selectTab(int index) {
        setBotoomStyle(index);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case 0:
                popFragement(transaction, resourceAllocationFragment, index);
                break;
            case 1:
                popFragement(transaction, historyAllocationFragment, index);
                break;
        }
        transaction.commit();
    }

    public void popFragement(FragmentTransaction transaction, Fragment fragment, int index) {
        if (fragment == null) {
            if (index == 0) {
                resourceAllocationFragment = CoachResourceAllocationFragment.getInstance();
                transaction.add(R.id.fl_home, resourceAllocationFragment, FRAGMENT_TAG[index]);
            } else if (index == 1) {
                historyAllocationFragment = CoachHistoryAllocationFragment.getInstance();
                transaction.add(R.id.fl_home, historyAllocationFragment, FRAGMENT_TAG[index]);
            }
        } else {
            if (index == 0) {
                transaction.replace(R.id.fl_home, resourceAllocationFragment);
            } else if (index == 1) {
                transaction.replace(R.id.fl_home, historyAllocationFragment);
            }

        }
    }

    public void setBotoomStyle(int index) {
        navigationBar2.setTitle(index == 0 ? "资源分配" : "历史分配");
        tv_resource_allowcation.setTextColor(index == 0 ? Color.parseColor("#1997f8") : Color.parseColor("#666666"));
        tv_history_allowcation.setTextColor(index == 0 ? Color.parseColor("#666666") : Color.parseColor("#1997f8"));

        iv_resource_allowcation.setImageResource(index == 0 ? R.mipmap.fp_ziyuanlan : R.mipmap.fp_ziyuan);
        iv_history_allowcation.setImageResource(index == 0 ? R.mipmap.fp_lishi : R.mipmap.fp_lishilan);
    }

}
