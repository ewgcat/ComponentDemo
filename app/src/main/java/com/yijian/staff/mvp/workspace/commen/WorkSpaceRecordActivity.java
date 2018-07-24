package com.yijian.staff.mvp.workspace.commen;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.bean.WorkSpaceRecordBean;
import com.yijian.staff.mvp.workspace.bean.WorkSpaceVipBean;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.EmptyView;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkSpaceRecordActivity extends MvcBaseActivity {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.empty_view)
    EmptyView empty_view;
    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量
    private int pagesTotal; //总共多少页
    private List<WorkSpaceRecordBean> workSpaceRecordBeanList = new ArrayList<>();
    private  WorkSpaceRecordAdapter workSpaceRecordAdapter;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_work_space_record;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        showLoading();
        initTitle();
        initComponent();
        initData();
        refresh();
    }

    private void initTitle() {
        String name = getIntent().getStringExtra("name");
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle(ActivityUtils.name + ActivityUtils.moduleType+"测评记录");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

    private void initData() {
        workSpaceRecordAdapter = new WorkSpaceRecordAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(workSpaceRecordAdapter);
    }

    public void initComponent() {
        BezierRadarHeader header = new BezierRadarHeader(this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
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
        workSpaceRecordBeanList.clear();
        pageNum = 1;//页码
        pageSize = 10;
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        String type = "";
        if(ActivityUtils.MODULE_PERFECT.equals(ActivityUtils.moduleType)){ //完美围度
            type = "1";
        }else if(ActivityUtils.MODULE_SPORT.equals(ActivityUtils.moduleType)){ //运动表现
            type = "2";
        }
        map.put("types", type);
        map.put("memberId", ActivityUtils.workSpaceVipBean.getMemberId());
        empty_view.setVisibility(View.GONE);
        HttpManager.getHasHeaderHasParam(HttpManager.WORKSPACE_QUERY_RESULT_LIST__URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                refreshLayout.finishRefresh(2000, true);
                pagesTotal = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        WorkSpaceRecordBean workSpaceRecordBean = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), WorkSpaceRecordBean.class);
                        workSpaceRecordBeanList.add(workSpaceRecordBean);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                empty_view.setVisibility(workSpaceRecordBeanList.size()>0?View.GONE:View.VISIBLE);
                workSpaceRecordAdapter.resetDataList(workSpaceRecordBeanList);
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(WorkSpaceRecordActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadMore() {

        if(pageNum < pagesTotal){
            pageNum++;
            HashMap<String, String> map = new HashMap<>();
            map.put("pageNum", pageNum + "");
            map.put("pageSize", pageSize + "");
            String type = "";
            if(ActivityUtils.MODULE_PERFECT.equals(ActivityUtils.moduleType)){ //完美围度
                type = "1";
            }else if(ActivityUtils.MODULE_SPORT.equals(ActivityUtils.moduleType)){ //运动表现
                type = "2";
            }
            map.put("types", type);
            map.put("memberId", ActivityUtils.workSpaceVipBean.getMemberId());
            HttpManager.getHasHeaderHasParam(HttpManager.WORKSPACE_QUERY_RESULT_LIST__URL, map, new ResultJSONObjectObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    pagesTotal = JsonUtil.getInt(result, "pages");
                    refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                    JSONArray records = JsonUtil.getJsonArray(result, "records");
                    for (int i = 0; i < records.length(); i++) {
                        try {
                            JSONObject jsonObject = (JSONObject) records.get(i);
                            WorkSpaceRecordBean workSpaceRecordBean = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), WorkSpaceRecordBean.class);
                            workSpaceRecordBeanList.add(workSpaceRecordBean);
                        } catch (JSONException e) {
                        }
                    }
                    workSpaceRecordAdapter.resetDataList(workSpaceRecordBeanList);
                }

                @Override
                public void onFail(String msg) {
                    refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                    Toast.makeText(WorkSpaceRecordActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
        }

    }


}
