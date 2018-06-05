package com.yijian.staff.mvp.huiji.huifang.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.huiji.huifang.bean.HuiFangTypeBean;
import com.yijian.staff.mvp.huiji.huifang.history.HuiFangHistoryActivity;
import com.yijian.staff.mvp.huiji.huifang.task.fragment.BaseHuiFangTaskFragment;
import com.yijian.staff.mvp.huiji.huifang.task.pageadapter.HuiFangPagerAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.PagerSlidingTabStrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/test/5")
public class HuiFangTaskActivity extends MvcBaseActivity {

    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ArrayList<HuiFangTypeBean> huiFangTypeBeanArrayList = new ArrayList<>();


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
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.hui_fang_task_navigation_bar);
        navigationBar2.setTitle("回访任务");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }


    private void initData() {
        HttpManager.getHasHeaderNoParam(HttpManager.GET_HUI_JI_HUI_FANG_TYPE_LIST_URL, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                JSONArray configVOs = JsonUtil.getJsonArray(result, "configVOs");
                try {
                    if (configVOs != null && configVOs.length() > 0) {
                        for (int i = 0; i < configVOs.length(); i++) {
                            JSONObject jsonObject = configVOs.getJSONObject(i);
                            HuiFangTypeBean huiFangTypeBean = new HuiFangTypeBean(jsonObject);
                            huiFangTypeBeanArrayList.add(huiFangTypeBean);
                        }
                        DBManager.getInstance().insertHuiFangTypeBeanList(huiFangTypeBeanArrayList);
                        initIndicatorAndViewPager();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
    }

    private void initIndicatorAndViewPager() {


        List<String> mTitleList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        mTitleList.add("全部");
        fragmentList.add(new BaseHuiFangTaskFragment(this, 0));
        for (int i = 0; i < huiFangTypeBeanArrayList.size(); i++) {
            HuiFangTypeBean huiFangTypeBean = huiFangTypeBeanArrayList.get(i);
            mTitleList.add(huiFangTypeBean.getConfigName());
            fragmentList.add(new BaseHuiFangTaskFragment(this, huiFangTypeBean.getConfigType()));
        }
        HuiFangPagerAdapter huiFangPagerAdapter = new HuiFangPagerAdapter(getSupportFragmentManager(), fragmentList, mTitleList);
        viewPager.setAdapter(huiFangPagerAdapter);
        tabs.setViewPager(viewPager);
        updateAllNoticeNum();
    }

    public void updateAllNoticeNum() {
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNum", "1");
        params.put("pageSize", "1");
        params.put("type", "0");
        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUI_JI_HUI_FANG_TASK_URL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                int pages = JsonUtil.getInt(result, "pages");
                tabs.updateBubbleNum(0, pages);
                //初始化显示第一页
                viewPager.setCurrentItem(0);
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
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


