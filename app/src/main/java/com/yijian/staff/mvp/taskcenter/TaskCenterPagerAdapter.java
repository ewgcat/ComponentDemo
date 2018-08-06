package com.yijian.staff.mvp.taskcenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 19:14:06
 */
public class TaskCenterPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private FragmentManager mFragmentManager;
    private List<String> mTitleDataList = new ArrayList<>();
    private FragmentTransaction fragmentTransaction;


    public TaskCenterPagerAdapter(FragmentManager mFragmentManager, List<Fragment> fragments, List<String> mTitleDataList) {
        super(mFragmentManager);
        this.mFragmentManager = mFragmentManager;
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
