package com.yijian.staff.mvp.reception;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.ReceptionListBean;
import com.yijian.staff.bean.ReceptionRecordBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.EmptyView;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceptionActivity extends MvcBaseActivity {

    @BindView(R.id.navigation_bar)
    NavigationBar navigationBar;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.empty_view)
    EmptyView emptyView;
    @BindView(R.id.rv)
    RecyclerView rv;

    private List<ReceptionRecordBean> list = new ArrayList<>();

    private int pageNum = 1;
    private int pageSize = 10;
    private ReceptionHistoryAdapter adapter;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_reception;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body = new AccessStatisticsRequestBody("app_reception", version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        navigationBar.setTitle("接待");
        navigationBar.setBackClickListener(this);
        navigationBar.setSecondLeftIvVisiable(View.GONE);

        emptyView.setVisibility(View.GONE);
        emptyView.getEmptyBt().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        adapter = new ReceptionHistoryAdapter(this, list);
        rv.setAdapter(adapter);
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
        refresh();
    }


    private void refresh() {
        pageNum = 1;
        Map<String, Integer> params = new HashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);

        showLoading();
        HttpManager.postHasHeaderHasParamOfInteger(HttpManager.RECEPTION_RECORD_TEMP, params, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                ReceptionListBean recptionRecordListBean = new Gson().fromJson(result.toString(), ReceptionListBean.class);


                List<ReceptionRecordBean> records = recptionRecordListBean.getRecords();
                if (records == null || records.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                    emptyView.getEmptyBt().setVisibility(View.GONE);
                } else {
                    list.clear();
                    list.addAll(records);
                    emptyView.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
                ++pageNum;
                refreshLayout.finishRefresh(2000, true);
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                showToast(msg);
                refreshLayout.finishRefresh(2000, false);
                if (list.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                    emptyView.getEmptyBt().setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void loadMore() {
        Map<String, Integer> params = new HashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);

        showLoading();
        HttpManager.postHasHeaderHasParamOfInteger(HttpManager.RECEPTION_RECORD_TEMP, params, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                ReceptionListBean recptionRecordListBean = new Gson().fromJson(result.toString(), ReceptionListBean.class);


                List<ReceptionRecordBean> records = recptionRecordListBean.getRecords();
                if (records == null || records.size() == 0) {
                    refreshLayout.finishLoadMore(2000, true, true);//传入false表示刷新失败
                } else {
                    list.addAll(records);
                    adapter.notifyDataSetChanged();
                    refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                }
                ++pageNum;
                if (list.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                showToast(msg);
                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}