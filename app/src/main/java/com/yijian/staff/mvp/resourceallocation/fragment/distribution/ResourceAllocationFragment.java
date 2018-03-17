package com.yijian.staff.mvp.resourceallocation.fragment.distribution;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.adapter.ResourceAllocationAdatper;
import com.yijian.staff.mvp.resourceallocation.bean.HistoryResourceAllocationInfo;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源分配
 */
public class ResourceAllocationFragment extends Fragment {

    private static ResourceAllocationFragment resourceAllocationFragment;
    public static ResourceAllocationFragment getInstance(){
        if(resourceAllocationFragment == null){
            resourceAllocationFragment = new ResourceAllocationFragment();
        }
        return resourceAllocationFragment;
    }

    SmartRefreshLayout refreshLayout;
    RecyclerView rv_resource_allocation;
    private List<HistoryResourceAllocationInfo> resourceAllocationInfoList=new ArrayList<>();
    public ResourceAllocationAdatper resourceAllocationAdatper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resource_allocation, container, false);
        initView(view);
        initResourceAllocationInfoList();
        return view;
    }

    private void initView(View view){
        rv_resource_allocation = view.findViewById(R.id.rv_resource_allocation);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        initComponent();
    }

    private void initResourceAllocationInfoList() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("name", "1");
            jsonObject.put("birthDay", "1990-8-9");
            jsonObject.put("wxIdentification", "wer2342344");
            jsonObject.put("email", "打橄榄球");
            jsonObject.put("serviceHuiJi", "壮壮");
            jsonObject.put("serviceCoach", "牛牛");
            for (int i = 0; i < 10; i++) {
                HistoryResourceAllocationInfo vipPeopleInfo = new HistoryResourceAllocationInfo(jsonObject);
                resourceAllocationInfoList.add(vipPeopleInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
            //设置RecyclerView 布局
            rv_resource_allocation.setLayoutManager(layoutmanager);
            resourceAllocationAdatper = new ResourceAllocationAdatper(getActivity(), resourceAllocationInfoList, ResourceAllocationAdatper.RESOURCE_TYPE);
            rv_resource_allocation.setAdapter(resourceAllocationAdatper);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }

    }

    public void initComponent() {
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997F8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997F8"));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
            }
        });
    }

}
