package com.yijian.staff.mvp.huiji.huifang.task;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.huifang.history.HuiFangHistoryActivity;
import com.yijian.staff.mvp.huiji.huifang.task.fragment.BaseHuiFangTaskFragment;
import com.yijian.staff.mvp.huiji.huifang.task.pageadapter.HuiFangPagerAdapter;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/test/5")
public class HuiFangTaskActivity extends AppCompatActivity {

    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_fang_task);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initNavigation();
        initIndicatorAndViewPager();
    }


    private void initNavigation() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.hui_fang_task_navigation_bar);
        navigationBar2.setTitle("回访任务");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

    }


    private void initIndicatorAndViewPager() {
        List<String> mTitleList = new ArrayList<>();
        mTitleList.add("全部");
        mTitleList.add("体验课");
        mTitleList.add("生日");
        mTitleList.add("昨日到访");
        mTitleList.add("昨日开卡");
        mTitleList.add("潜在会员");
        mTitleList.add("沉寂会员");
        mTitleList.add("恢复健身");
        mTitleList.add("复访");
        mTitleList.add("过期");
        mTitleList.add("快到期");
        mTitleList.add("易健平台");

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new BaseHuiFangTaskFragment(this, 0));
        fragmentList.add(new BaseHuiFangTaskFragment(this, 1));
        fragmentList.add(new BaseHuiFangTaskFragment(this, 2));
        fragmentList.add(new BaseHuiFangTaskFragment(this, 3));
        fragmentList.add(new BaseHuiFangTaskFragment(this, 4));
        fragmentList.add(new BaseHuiFangTaskFragment(this, 5));
        fragmentList.add(new BaseHuiFangTaskFragment(this, 6));
        fragmentList.add(new BaseHuiFangTaskFragment(this, 7));
        fragmentList.add(new BaseHuiFangTaskFragment(this, 8));
        fragmentList.add(new BaseHuiFangTaskFragment(this, 9));
        fragmentList.add(new BaseHuiFangTaskFragment(this, 10));
        fragmentList.add(new BaseHuiFangTaskFragment(this, 11));


        HuiFangPagerAdapter huiFangPagerAdapter = new HuiFangPagerAdapter(getSupportFragmentManager(), fragmentList, mTitleList);
        viewPager.setAdapter(huiFangPagerAdapter);
        tabs.setViewPager(viewPager);
        tabs.updateBubbleNum(0, 12);

        //初始化显示第一页
        viewPager.setCurrentItem(0);


    }


    @OnClick({ R.id.ll_hui_fang_ji_lu})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ll_hui_fang_ji_lu:
                startActivity(new Intent(HuiFangTaskActivity.this,HuiFangHistoryActivity.class));
                break;
        }
    }

}


