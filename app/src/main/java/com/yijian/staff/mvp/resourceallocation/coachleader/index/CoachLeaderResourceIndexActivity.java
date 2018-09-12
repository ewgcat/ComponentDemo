package com.yijian.staff.mvp.resourceallocation.coachleader.index;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.coachleader.index.distribution.ResourceAllocationFragment;
import com.yijian.staff.mvp.resourceallocation.coachleader.index.history.HistoryAllocationFragment;
import com.yijian.staff.widget.NavigationBar;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资源分配(教练总监角色)
 */
@Route(path = "/test/10.4")
public class CoachLeaderResourceIndexActivity extends AppCompatActivity {


    private static final String[] FRAGMENT_TAG = {"CoachResourceAllocationFragment", "CoachHistoryAllocationFragment"};
    private ResourceAllocationFragment resourceAllocationFragment;
    private HistoryAllocationFragment historyAllocationFragment;
    private NavigationBar  navigationBar;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_leader_resource_allocation);
        ButterKnife.bind(this);
        initView();


    }


    private void initView() {
        navigationBar = findViewById(R.id.coach_leader_navigation_bar2);

        navigationBar.setTitle("资源分配");
        navigationBar .hideLeftSecondIv();
        navigationBar .setBackClickListener(this);
        resourceAllocationFragment = ResourceAllocationFragment.getInstance();
        historyAllocationFragment = HistoryAllocationFragment.getInstance();
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
        switch (index) {
            case 0:
                popFragement(index);
                break;
            case 1:
                popFragement(index);
                break;
        }
    }

    public void popFragement(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        hideAllIndex(transaction);
        switch (index) {
            case 0:
                if (!resourceAllocationFragment.isAdded()) {
                    // 如果WorkFragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.fl_home, resourceAllocationFragment, FRAGMENT_TAG[index]);
                } else {

                    transaction.show(resourceAllocationFragment);
                }
                // 如果WorkFragment不为空，则直接将它显示出来

                break;
            case 1:
                if (!historyAllocationFragment.isAdded()) {
                    // 如果mesageFragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.fl_home, historyAllocationFragment, FRAGMENT_TAG[index]);
                    transaction.show(historyAllocationFragment);
                }
                // 如果mesageFragment不为空，则直接将它显示出来
                transaction.show(historyAllocationFragment);

                break;

        }
        transaction.commit();
    }


    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {
        if (resourceAllocationFragment != null && resourceAllocationFragment.isAdded()) {
            fragmentTransaction.hide(resourceAllocationFragment);
        }
        if (historyAllocationFragment != null && historyAllocationFragment.isAdded()) {
            fragmentTransaction.hide(historyAllocationFragment);
        }

    }

    public void setBotoomStyle(int index) {
        navigationBar.setTitle(index == 0 ? "资源分配" : "历史分配");
        tv_resource_allowcation.setTextColor(index == 0 ? Color.parseColor("#1997f8") : Color.parseColor("#666666"));
        tv_history_allowcation.setTextColor(index == 0 ? Color.parseColor("#666666") : Color.parseColor("#1997f8"));

        iv_resource_allowcation.setImageResource(index == 0 ? R.mipmap.fp_ziyuanlan : R.mipmap.fp_ziyuan);
        iv_history_allowcation.setImageResource(index == 0 ? R.mipmap.fp_lishi : R.mipmap.fp_lishilan);
    }

}
