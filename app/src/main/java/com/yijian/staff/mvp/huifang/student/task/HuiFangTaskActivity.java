package com.yijian.staff.mvp.huifang.student.task;

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
import com.yijian.staff.mvp.huifang.student.history.HuiFangHistoryActivity;
import com.yijian.staff.mvp.huifang.student.task.fragment.BaseHuiFangTaskFragment;
import com.yijian.staff.mvp.huifang.student.task.pageadapter.HuiFangPagerAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.bean.HuiFangTypeRequestBody;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.PagerSlidingTabStrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.hui_fang_task_navigation_bar);
        navigationBar2.setTitle("会员回访");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

    private void initData() {
        showLoading();
        HuiFangTypeRequestBody body=new HuiFangTypeRequestBody();
        body.setChief(true);
        body.setType(1);
        HttpManager.postHuiFangType(body, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {
                hideLoading();
                try {
                    if (result != null) {
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject jsonObject = result.getJSONObject(i);
                            if (jsonObject.has("totalNum")) {
                                totalNum = JsonUtil.getInt(jsonObject, "totalNum");
                            }
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


