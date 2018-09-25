package com.yijian.clubmodule.ui.taskcenter;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yijian.clubmodule.ui.taskcenter.add.AddViperTaskCenterFragment;
import com.yijian.clubmodule.ui.taskcenter.invite.InviteTaskCenterFragment;
import com.yijian.clubmodule.ui.taskcenter.visit.VisitTaskCenterFragment;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.commonlib.widget.PagerSlidingTabStrip;


import java.util.ArrayList;
import java.util.List;



public class TaskCenterActivity extends MvcBaseActivity {


    PagerSlidingTabStrip tabs;
    ViewPager viewPager;



    @Override
    protected int getLayoutID() {
        return R.layout.activity_task_center;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        NavigationBar navigationBar = findViewById(R.id.tabs);
        tabs = findViewById(R.id.task_center_navigation_bar);
        viewPager = findViewById(R.id.view_pager);
        navigationBar .hideLeftSecondIv();
        navigationBar .setBackClickListener(this);
        navigationBar.setTitle("任务中心");
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
