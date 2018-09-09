package com.yijian.staff.mvp.taskcenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.taskcenter.add.AddViperTaskCenterFragment;
import com.yijian.staff.mvp.taskcenter.invite.InviteTaskCenterFragment;
import com.yijian.staff.mvp.taskcenter.visit.VisitTaskCenterFragment;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TaskCenterActivity extends MvcBaseActivity {


    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;



    @Override
    protected int getLayoutID() {
        return R.layout.activity_task_center;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        NavigationBar NavigationBar = findViewById(R.id.task_center_navigation_bar2);
        NavigationBar.hideLeftSecondIv();
        NavigationBar.setBackClickListener(this);
        NavigationBar.setTitle("任务中心");
        initIndicatorAndViewPager();
    }

    private void initIndicatorAndViewPager() {

        List<String> mTitleList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        mTitleList.add("今日添加");
        mTitleList.add("今日邀约");
        mTitleList.add("今日回访");
        fragmentList.add(new AddViperTaskCenterFragment());
        fragmentList.add(new InviteTaskCenterFragment());
        fragmentList.add(new VisitTaskCenterFragment());

        TaskCenterPagerAdapter taskCenterPagerAdapter = new TaskCenterPagerAdapter(getSupportFragmentManager(), fragmentList, mTitleList);
        viewPager.setAdapter(taskCenterPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tabs.setViewPager(viewPager);
        viewPager.setCurrentItem(0);


    }




}
