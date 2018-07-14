package com.yijian.staff.mvp.course.setclass;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ExperienceClassRecord2Activity extends MvcBaseActivity {

    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();


    @Override
    protected int getLayoutID() {
        return R.layout.activity_open_lesson_new2;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitle();
        initData();
        initView();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle("");
        navigationBar2.setmRightTvText("保存");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setBackLLVisiable(View.GONE);
        navigationBar2.setSecondLeftIvVisiable(View.VISIBLE);
        navigationBar2.getSecondLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        fragmentList.add(new ExperienceRecordFragment(1));
        fragmentList.add(new ExperienceRecordFragment(2));
        fragmentList.add(new ExperienceRecordFragment(3));
        mTitleList.add("无器械");
        mTitleList.add("有氧器械");
        mTitleList.add("力量器械");
    }

    private void initView() {
        ExperienceRecordPagerAdapter experienceRecordPagerAdapter = new ExperienceRecordPagerAdapter(getSupportFragmentManager(), fragmentList, mTitleList);
        viewPager.setAdapter(experienceRecordPagerAdapter);
        tabs.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }


    public class ExperienceRecordPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        private List<String> mTitleDataList = new ArrayList<>();


        public ExperienceRecordPagerAdapter(FragmentManager mFragmentManager, List<Fragment> fragments, List<String> mTitleDataList) {
            super(mFragmentManager);
            this.fragments = fragments;
            this.mTitleDataList = mTitleDataList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleDataList.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }

}

