package com.yijian.staff.mvp.huifang.vip.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.bean.HuiFangTypeBean;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.huifang.vip.history.HuiFangHistoryActivity;
import com.yijian.staff.mvp.huifang.vip.task.fragment.BaseHuiFangTaskFragment;
import com.yijian.staff.mvp.huifang.vip.task.pageadapter.HuiFangPagerAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.HuiFangTypeRequestBody;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.PagerSlidingTabStrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/test/33")
public class HuiFangTaskActivity extends MvcBaseActivity {

    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ArrayList<HuiFangTypeBean> huiFangTypeBeanArrayList = new ArrayList<>();
    private int totalNum;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_hui_fang_task;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        initNavigation();
        initData();
    }


    private void initNavigation() {
        NavigationBar NavigationBar = (NavigationBar) findViewById(R.id.hui_fang_task_navigation_bar);
        NavigationBar.setTitle("会员回访");
        NavigationBar.hideLeftSecondIv();
        NavigationBar.setBackClickListener(this);
    }


    private void initData() {
        showLoading();
        HuiFangTypeRequestBody body=new HuiFangTypeRequestBody();
        body.setChief(true);
        body.setType(0);
        HttpManager.postHuiFangType(body, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {
                hideLoading();
                try {
                    if (result != null&&result.length()>0) {
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject jsonObject = result.getJSONObject(i);
                            HuiFangTypeBean huiFangTypeBean = new HuiFangTypeBean(jsonObject);
                            huiFangTypeBeanArrayList.add(huiFangTypeBean);
                        }
                        JSONObject jsonObject = result.getJSONObject(0);
                        if (jsonObject.has("totalNum")) {
                            totalNum = JsonUtil.getInt(jsonObject, "totalNum");
                        }
                        DBManager.getInstance().insertOrReplaceHuiFangTypeBeans(huiFangTypeBeanArrayList);
                        initIndicatorAndViewPager();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                showToast(msg);
            }
        });
    }

    private void initIndicatorAndViewPager() {
        List<String> mTitleList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < huiFangTypeBeanArrayList.size(); i++) {
            HuiFangTypeBean huiFangTypeBean = huiFangTypeBeanArrayList.get(i);
            mTitleList.add(huiFangTypeBean.getName());
            fragmentList.add(new BaseHuiFangTaskFragment(this, huiFangTypeBean.getMenu()));
        }
        HuiFangPagerAdapter huiFangPagerAdapter = new HuiFangPagerAdapter(getSupportFragmentManager(), fragmentList, mTitleList);
        viewPager.setAdapter(huiFangPagerAdapter);
        tabs.setViewPager(viewPager);
        tabs.updateBubbleNum(0, totalNum);
        //初始化显示第一页
        viewPager.setCurrentItem(0);
    }


    @OnClick({R.id.ll_hui_fang_ji_lu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_hui_fang_ji_lu:
                startActivity(new Intent(HuiFangTaskActivity.this, HuiFangHistoryActivity.class));
                break;
        }
    }

}


