package com.yijian.staff.mvp.resourceallocation.huijileader.index.distribution;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.huijileader.bean.ResourceInfo;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源分配
 */
public class ResourceAllocationFragment extends Fragment {

    private static ResourceAllocationFragment resourceAllocationFragment;

    public static ResourceAllocationFragment getInstance() {
        if (resourceAllocationFragment == null) {
            resourceAllocationFragment = new ResourceAllocationFragment();
        }
        return resourceAllocationFragment;
    }

    SmartRefreshLayout refreshLayout;
    RecyclerView rv_resource_allocation;
    private List<ResourceInfo> resourceAllocationInfoList = new ArrayList<>();
    public ResourceAllocationAdatper adapter;

    private int pageNum = 1;
    private int pageSize = 10;
    private int pages;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coach_resource_allocation, container, false);
        initView(view);
        refresh();
        return view;
    }

    private void initView(View view) {
        rv_resource_allocation = view.findViewById(R.id.rv_resource_allocation);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
        //设置RecyclerView 布局
        rv_resource_allocation.setLayoutManager(layoutmanager);
        adapter = new ResourceAllocationAdatper(getActivity(), resourceAllocationInfoList);
        rv_resource_allocation.setAdapter(adapter);
        initComponent();
    }


    public void initComponent() {

        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale);
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


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            refresh();
        }
    }

    private void refresh() {
        resourceAllocationInfoList.clear();
        pageNum = 1;

        Map<String, String> params = new HashMap<>();

        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJIZONGJIAN_RESOURCE_LIST_URL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");


                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        ResourceInfo resourceInfo = new ResourceInfo(jsonObject);
                        resourceAllocationInfoList.add(resourceInfo);
                    }
                    adapter.update(resourceAllocationInfoList);

                } catch (JSONException e) {
                    Logger.i("TEST", e.getMessage());

                }
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadMore() {

        Map<String, String> params = new HashMap<>();

        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJIZONGJIAN_RESOURCE_LIST_URL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");

                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        ResourceInfo resourceInfo = new ResourceInfo(jsonObject);
                        resourceAllocationInfoList.add(resourceInfo);
                    }
                    adapter.update(resourceAllocationInfoList);

                } catch (JSONException e) {
                    Logger.i("TEST", e.getMessage());

                }
            }

            @Override
            public void onFail(String msg) {

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
