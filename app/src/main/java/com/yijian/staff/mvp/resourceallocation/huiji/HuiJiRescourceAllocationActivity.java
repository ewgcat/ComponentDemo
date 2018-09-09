package com.yijian.staff.mvp.resourceallocation.huiji;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.coach.adapter.CoachResourceAllocationAdatper;
import com.yijian.staff.mvp.resourceallocation.coach.bean.CoachResourceAllocationInfo;
import com.yijian.staff.mvp.resourceallocation.huiji.adapter.HuiJiResourceAllocationAdatper;
import com.yijian.staff.mvp.resourceallocation.huiji.bean.HuiJiResourceAllocationInfo;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

@Route(path = "/test/10")
public class HuiJiRescourceAllocationActivity extends AppCompatActivity {

    SmartRefreshLayout refreshLayout;
    RecyclerView rv_resource_allocation;
    private List<HuiJiResourceAllocationInfo> resourceAllocationInfoList = new ArrayList<>();
    public HuiJiResourceAllocationAdatper resourceAllocationAdatper;
    private int pageNum = 1;
    private int pageSize = 1;
    private int pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_ji_recource_allocation);
        ButterKnife.bind(this);
        initTitle();
        initView();
    }

    private void initTitle() {
        NavigationBar NavigationBar = findViewById(R.id.navigation_bar2);
        NavigationBar.setTitle("资源分配");
        NavigationBar.hideLeftSecondIv();
        NavigationBar.setBackClickListener(this);
    }

    private void initView() {
        initComponent();
        refresh();
    }

    public void initComponent() {

        rv_resource_allocation = findViewById(R.id.rv_resource_allocation);
        refreshLayout = findViewById(R.id.refreshLayout);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_resource_allocation.setLayoutManager(layoutmanager);
        resourceAllocationAdatper = new HuiJiResourceAllocationAdatper(this, resourceAllocationInfoList);
        rv_resource_allocation.setAdapter(resourceAllocationAdatper);
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }


    private void refresh() {

        pageNum = 1;
        pageSize = 6;
        resourceAllocationInfoList.clear();

        Map<String, String> params = new HashMap<>();

        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJI_RESOURCE_LIST_URL, params, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                resourceAllocationInfoList.add(new HuiJiResourceAllocationInfo());
                try {
                    org.json.JSONArray jsonArray = result.getJSONArray("records");
                    resourceAllocationInfoList = JSONArray.parseArray(jsonArray.toString(), HuiJiResourceAllocationInfo.class);
                    resourceAllocationAdatper.update(resourceAllocationInfoList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
            }
        });
    }


    private void loadMore() {

        Map<String, String> params = new HashMap<>();

        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_RESOURCE_LIST_URL, params, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");

                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
            }

            @Override
            public void onFail(String msg) {

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
            }
        });
    }


}
