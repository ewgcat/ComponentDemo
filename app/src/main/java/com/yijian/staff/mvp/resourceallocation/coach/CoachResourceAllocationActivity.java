package com.yijian.staff.mvp.resourceallocation.coach;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.bean.CoachResourceAllocationInfo;
import com.yijian.staff.mvp.resourceallocation.coach.adapter.CoachResourceAllocationAdatper;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资源分配(教练总监角色)
 */
@Route(path = "/test/10.2")
public class CoachResourceAllocationActivity extends AppCompatActivity {


    private NavigationBar2 navigationBar2;


    SmartRefreshLayout refreshLayout;
    RecyclerView rv_resource_allocation;
    private List<CoachResourceAllocationInfo> resourceAllocationInfoList = new ArrayList<>();
    public CoachResourceAllocationAdatper resourceAllocationAdatper;
    private int pageNum = 1;
    private int pageSize = 1;
    private int pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_resource_allocation);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        navigationBar2 = findViewById(R.id.coach_navigation_bar2);
        navigationBar2.setTitle("资源分配");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        initComponent();
    }


    public void initComponent() {

        rv_resource_allocation = findViewById(R.id.rv_resource_allocation);
        refreshLayout = findViewById(R.id.refreshLayout);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_resource_allocation.setLayoutManager(layoutmanager);
         resourceAllocationAdatper = new CoachResourceAllocationAdatper(this, resourceAllocationInfoList);
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

        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_ENABLE_RESOURCE_ALLOCATION__PHONE_URL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                resourceAllocationInfoList.add(new CoachResourceAllocationInfo(new JSONObject()));
                resourceAllocationAdatper.update(resourceAllocationInfoList);
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

        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_ENABLE_RESOURCE_ALLOCATION__PHONE_URL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败
            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
            }
        });
    }

}
