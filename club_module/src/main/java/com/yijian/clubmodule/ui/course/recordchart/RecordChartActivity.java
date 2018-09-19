package com.yijian.clubmodule.ui.course.recordchart;

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
import com.yijian.commonlib.util.Logger;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.ClassRecordBean;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class RecordChartActivity extends MvcBaseActivity {

    RecyclerView recyclerView;
    ArrayList<ClassRecordBean> classRecordBeanArrayList = new ArrayList<>();
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
        recyclerView= findViewById(R.id.rlv);
        refreshLayout= findViewById(R.id.refreshLayout);
        NavigationBar navigationBar = findViewById(R.id.record_chart_navigation_bar);
        navigationBar.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar .setBackClickListener(this);
        navigationBar.setTitle("上课记录表");


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecordChartAdapter(this, classRecordBeanArrayList);
        recyclerView.setAdapter(adapter);


        memberId = getIntent().getStringExtra("memberId");
        initComponent();
        refresh();
    }

    private void refresh() {
        pageNum = 1;
        pageSize = 2;
        classRecordBeanArrayList.clear();
        showLoading();
        HttpManager.getCoachVipCourseListList(memberId, pageNum, pageSize, new ResultJSONObjectObserver(getLifecycle()) {
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
                hideLoading();
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);

                hideLoading();

            }
        });


    }


    public void loadMore() {
        showLoading();
        HttpManager.getCoachVipCourseListList(memberId, pageNum, pageSize, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {


                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

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
                hideLoading();

            }

            @Override
            public void onFail(String msg) {

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败

                hideLoading();

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
