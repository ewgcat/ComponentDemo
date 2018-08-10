package com.yijian.staff.mvp.huifang.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.yijian.staff.R;
import com.yijian.staff.bean.HuiFangTypeBean;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.huifang.history.HuiFangHistoryActivity;
import com.yijian.staff.mvp.huifang.task.fragment.BaseHuiFangTaskFragment;
import com.yijian.staff.mvp.huifang.task.pageadapter.HuiFangPagerAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.huifang.HuifangTaskRequestBody;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
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
        HttpManager.postHasHeaderNoParam(HttpManager.GET_HUI_JI_HUI_FANG_TYPE_LIST_URL, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {

                try {
                    if (result != null) {
                        for (int i = 0; i < result.length(); i++) {

                            JSONObject jsonObject = result.getJSONObject(i);
                            HuiFangTypeBean huiFangTypeBean = new HuiFangTypeBean(jsonObject);
                            huiFangTypeBeanArrayList.add(huiFangTypeBean);
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
        updateAllNoticeNum();
    }

    public void updateAllNoticeNum() {


        HuifangTaskRequestBody huifangTaskRequestBody = new HuifangTaskRequestBody();
        huifangTaskRequestBody.setChief(true);
        huifangTaskRequestBody.setMenu(huiFangTypeBeanArrayList.get(0).getMenu());
        huifangTaskRequestBody.setPageNum(1);
        huifangTaskRequestBody.setPageSize(1);
        HttpManager.postHuiFangTask(HttpManager.GET_HUI_JI_HUI_FANG_TASK_URL, huifangTaskRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
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


