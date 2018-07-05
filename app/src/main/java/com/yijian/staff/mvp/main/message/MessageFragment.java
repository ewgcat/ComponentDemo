package com.yijian.staff.mvp.main.message;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.jpush.ClearRedPointUtil;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.huiji.huifang.task.pageadapter.HuiFangPagerAdapter;
import com.yijian.staff.mvp.main.message.business.BaseMessageFragment;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MessageFragment extends MvcBaseFragment {


    private static final String TAG = "MessageFragment";
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    private int currentItem = 0;
    private BaseMessageFragment fragment1;
    private BaseMessageFragment fragment2;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView() {
        initComponent(rootView);
    }


    public void initComponent(View view) {
        initIndicatorAndViewPager();
    }

    private void initIndicatorAndViewPager() {
        mTitleList.add("会籍消息");
        mTitleList.add("课程消息");

        fragment1 = new BaseMessageFragment(0);
        fragmentList.add(fragment1);
        fragment2 = new BaseMessageFragment(1);
        fragmentList.add(fragment2);
        MessagePagerAdapter messagePagerAdapter = new MessagePagerAdapter(getFragmentManager(), fragmentList, mTitleList);
        viewPager.setAdapter(messagePagerAdapter);
        tabs.setViewPager(viewPager);
        setCurrentItem(0);
        tabs.setOnPagerTitleItemClickListener(new PagerSlidingTabStrip.OnPagerTitleItemClickListener() {
            @Override
            public void onSingleClickItem(int position) {
                currentItem = position;
                if (currentItem == 0) {
                    SharePreferenceUtil.setHasNewSellBusinessPush(false);
                    fragment1.refresh();
                    tabs.updateBubblePoint(0, 0);
                    ClearRedPointUtil.clearSellBusinessNotice(getLifecycle());
                } else if (currentItem == 1) {
                    SharePreferenceUtil.setHasNewCourseBusinessPush(false);
                    fragment2.refresh();
                    tabs.updateBubblePoint(1, 0);
                    ClearRedPointUtil.clearCourseBusinessNotice(getLifecycle());
                }
            }

            @Override
            public void onDoubleClickItem(int position) {

            }
        });

        boolean hasNewSellBusinessPush = SharePreferenceUtil.hasNewSellBusinessPush();
        Boolean hasNewCourseBusinessPush = SharePreferenceUtil.hasNewCourseBusinessPush();
        if (hasNewSellBusinessPush) {
            tabs.updateBubblePoint(0, 1);
        } else if (hasNewCourseBusinessPush) {
            tabs.updateBubblePoint(1, 1);

        }
        ClearRedPointUtil.clearBusinessNotice(getLifecycle());


    }

    public void setCurrentItem(int i) {
        currentItem = i;
        viewPager.setCurrentItem(i);
    }

    public void refresh() {
        if (currentItem == 0) {
            tabs.updateBubblePoint(0, 1);
            fragment1.refresh();
        } else if (currentItem == 1) {
            tabs.updateBubblePoint(1, 1);
            fragment2.refresh();
        }
    }


}
