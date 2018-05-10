package com.yijian.staff.mvp.coach.recordchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;

public class RecordChartActivity extends MvcBaseActivity {

    @BindView(R.id.rlv)
    RecyclerView recyclerView;
    ArrayList<ClassRecordBean> classRecordBeanArrayList = new ArrayList<>();
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;



    private int pageNum = 1;//页码
    private int pageSize = 2;//每页数量
    private int pages;
    private String memberId;
    private RecordChartAdapter adapter;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_record_chart;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = findViewById(R.id.record_chart_navigation_bar);
        navigationBar2.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("上课记录表");



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecordChartAdapter(this,classRecordBeanArrayList);
        recyclerView.setAdapter(adapter);


        memberId = getIntent().getStringExtra("memberId");
        initComponent();
        refresh();
    }

    private void refresh() {
        pageNum = 1;
        pageSize = 2;
        classRecordBeanArrayList.clear();
showBlueProgress();
        HttpManager.getCoachVipCourseListList(memberId, pageNum, pageSize, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");

                try {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        ClassRecordBean classRecordBean = new ClassRecordBean(jsonObject);
                        classRecordBeanArrayList.add(classRecordBean);
                    }
                } catch (JSONException e) {
                    Logger.i("TEST", e.getMessage());
                }
                adapter.update(classRecordBeanArrayList);
                hideBlueProgress();
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);
                showToast(msg);
                hideBlueProgress();

            }
        });


    }


    public void loadMore() {
        showBlueProgress();
        HttpManager.getCoachVipCourseListList(memberId,pageNum, pageSize, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        ClassRecordBean classRecordBean = new ClassRecordBean(jsonObject);
                        classRecordBeanArrayList.add(classRecordBean);
                    }
                } catch (JSONException e) {
                    Logger.i("TEST", e.getMessage());
                }
                adapter.update(classRecordBeanArrayList);
                hideBlueProgress();

            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                showToast(msg);
                hideBlueProgress();

            }
        });

    }

    public void initComponent() {
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
}
