package com.yijian.staff.mvp.main.message;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.bean.AccessStatisticsRequestBody;
import com.yijian.staff.jpush.ClearRedPointUtil;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.main.message.business.BaseMessageFragment;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.PagerSlidingTabStrip;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


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
        boolean appSellerBuiness = SharePreferenceUtil.getAppSellerBuiness();
        if (appSellerBuiness){
            mTitleList.add("会籍消息");
            fragment1 = new BaseMessageFragment(0);
            fragmentList.add(fragment1);
        }
        boolean appCourseBuiness = SharePreferenceUtil.getAppCourseBuiness();
        if (appCourseBuiness){
            mTitleList.add("课程消息");
            fragment2 = new BaseMessageFragment(1);
            fragmentList.add(fragment2);
        }

        MessagePagerAdapter messagePagerAdapter = new MessagePagerAdapter(getFragmentManager(), fragmentList, mTitleList);
        viewPager.setAdapter(messagePagerAdapter);
        tabs.setViewPager(viewPager);
        tabs.setOnPagerTitleItemClickListener(new PagerSlidingTabStrip.OnPagerTitleItemClickListener() {
            @Override
            public void onSingleClickItem(int position) {
                currentItem = position;
                if (currentItem == 0) {
                    SharePreferenceUtil.setHasNewSellBusinessPush(false);
                    tabs.updateBubblePoint(0, 0);
                    ClearRedPointUtil.clearSellBusinessNotice(getLifecycle());
                } else if (currentItem == 1) {
                    SharePreferenceUtil.setHasNewCourseBusinessPush(false);
                    tabs.updateBubblePoint(1, 0);
                    ClearRedPointUtil.clearCourseBusinessNotice(getLifecycle());
                }
            }

            @Override
            public void onDoubleClickItem(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                refresh();
                Logger.i(TAG,"onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        updateRedPoint();
        setCurrentItem(0);
        String version = CommonUtil.getAccessStatisticsVersionName(getContext()) + " " + CommonUtil.getVersionCode(getContext());
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_business_message",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });

    }

    private void updateRedPoint() {
        boolean hasNewSellBusinessPush = SharePreferenceUtil.hasNewSellBusinessPush();
        Boolean hasNewCourseBusinessPush = SharePreferenceUtil.hasNewCourseBusinessPush();
        if (hasNewSellBusinessPush) {
            tabs.updateBubblePoint(0, 1);
        }else {
            tabs.updateBubblePoint(0, 0);
        }
        if (hasNewCourseBusinessPush) {
            tabs.updateBubblePoint(1, 1);
        }else {
            tabs.updateBubblePoint(1, 0);
        }
        ClearRedPointUtil.clearBusinessNotice(getLifecycle());

    }


    public void setCurrentItem(int i) {
        currentItem = i;
        viewPager.setCurrentItem(i);
        if (currentItem == 0) {
            SharePreferenceUtil.setHasNewSellBusinessPush(false);
            tabs.updateBubblePoint(0, 0);
            ClearRedPointUtil.clearSellBusinessNotice(getLifecycle());
        } else if (currentItem == 1) {
            SharePreferenceUtil.setHasNewCourseBusinessPush(false);
            tabs.updateBubblePoint(1, 0);
            ClearRedPointUtil.clearCourseBusinessNotice(getLifecycle());
        }
        updateRedPoint();
    }

    public void refresh() {
        if (currentItem == 0) {
            SharePreferenceUtil.setHasNewSellBusinessPush(false);
            tabs.updateBubblePoint(0, 0);
            ClearRedPointUtil.clearSellBusinessNotice(getLifecycle());
        } else if (currentItem == 1) {
            SharePreferenceUtil.setHasNewCourseBusinessPush(false);
            tabs.updateBubblePoint(1, 0);
            ClearRedPointUtil.clearCourseBusinessNotice(getLifecycle());
        }
    }


}
