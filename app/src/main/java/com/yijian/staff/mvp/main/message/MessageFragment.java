package com.yijian.staff.mvp.main.message;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class MessageFragment extends Fragment {

    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private static final String TAG = "MessageFragment";
    //测试图片的存位置

    public static MessageFragment mMessageFragment = null;
    Unbinder unbinder;

    public static MessageFragment getInstance() {
        if (mMessageFragment == null) {
            mMessageFragment = new MessageFragment();
        }
        return mMessageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        unbinder = ButterKnife.bind(this, view);
        initIndicatorAndViewPager();
        return view;
    }

    private void initIndicatorAndViewPager() {
        List<String> mTitleList = new ArrayList<>();
        mTitleList.add("系统消息");
        mTitleList.add("业务消息");


        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new SystemNoticeFragment());
        fragmentList.add(new BaseSubMessageFragment(getContext(), 1));


        MessagePagerAdapter messagePagerAdapter = new MessagePagerAdapter(getActivity().getSupportFragmentManager(), fragmentList, mTitleList);
        viewPager.setAdapter(messagePagerAdapter);
        tabs.setViewPager(viewPager);
        //初始化显示第一页
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
