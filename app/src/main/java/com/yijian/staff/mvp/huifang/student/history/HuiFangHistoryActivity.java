package com.yijian.staff.mvp.huifang.student.history;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.HuiFangInfo;
import com.yijian.staff.mvp.huifang.search.SearchHuiFangHistoryActivity;
import com.yijian.staff.mvp.huifang.vip.history.HuiFangHistoryAdapter;
import com.yijian.staff.net.requestbody.HuifangRecordRequestBody;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.OnClick;

public class HuiFangHistoryActivity extends MvcBaseActivity {
    private List<HuiFangInfo> huiFangInfoList = new ArrayList<>();

    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量
    private int type=1;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private com.yijian.staff.mvp.huifang.vip.history.HuiFangHistoryAdapter huiFangHistoryAdapter;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_hui_fang_history;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.hui_fang_history_navigation_bar);
        navigationBar.setTitle("回访记录");
        navigationBar.hideLeftSecondIv();
        navigationBar.setBackClickListener(this);
        navigationBar.getmRightTv().setText("搜索");
        ImageLoader.setImageResource(R.mipmap.home_search, this, navigationBar.getmRightIv());
        navigationBar.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HuiFangHistoryActivity.this, SearchHuiFangHistoryActivity.class);
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });
        initComponent();
    }


    public void initComponent() {
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
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


        recyclerView = findViewById(R.id.rlv);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        huiFangHistoryAdapter = new HuiFangHistoryAdapter(this, huiFangInfoList);
        recyclerView.setAdapter(huiFangHistoryAdapter);
        refresh();
    }

    public void refresh() {
        pageNum = 1;
        pageSize = 10;
        huiFangInfoList.clear();

        HuifangRecordRequestBody huifangRecordRequestBody = new HuifangRecordRequestBody();
        huifangRecordRequestBody.setChief(true);
        huifangRecordRequestBody.setPageNum(pageNum);
        huifangRecordRequestBody.setPageSize(pageSize);
        huifangRecordRequestBody.setType(type);
        HttpManager.postHuiFangRecord(huifangRecordRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);


                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                JSONArray records = JsonUtil.getJsonArray(result, "records");

                List<HuiFangInfo> list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), HuiFangInfo.class);
                huiFangInfoList.addAll(list);
                huiFangHistoryAdapter.update(huiFangInfoList);
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);
                showToast(msg);
            }
        });
    }

    public void loadMore() {

        HuifangRecordRequestBody huifangRecordRequestBody = new HuifangRecordRequestBody();
        huifangRecordRequestBody.setChief(true);
        huifangRecordRequestBody.setPageNum(pageNum);
        huifangRecordRequestBody.setPageSize(pageSize);
        huifangRecordRequestBody.setType(type);

        HttpManager.postHuiFangRecord(huifangRecordRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;

                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                JSONArray records = JsonUtil.getJsonArray(result, "records");

                List<HuiFangInfo> list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), HuiFangInfo.class);
                huiFangInfoList.addAll(list);
                huiFangHistoryAdapter.update(huiFangInfoList);
            }

            @Override
            public void onFail(String msg) {

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                showToast(msg);
            }
        });
    }

    @OnClick(R.id.ll_hui_fang_ren_wu)
    public void onViewClicked() {
        finish();
    }
}
