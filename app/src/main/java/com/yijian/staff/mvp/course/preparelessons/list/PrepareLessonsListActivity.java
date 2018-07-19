package com.yijian.staff.mvp.course.preparelessons.list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.yijian.staff.bean.PrepareLessonsBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

@Route(path = "/test/14")
public class PrepareLessonsListActivity extends MvcBaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int pageNum = 1;
    private int pageSize = 10;
    private int pages;
    private List<PrepareLessonsBean> prepareLessonsBeans = new ArrayList<>();
    private PrepareLessonsListAdapter adapter;
    private int total;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_bei_ke_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitle();
        initView();
        refresh();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.bei_ke_navigation_bar);
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setTitle("备课列表");
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PrepareLessonsListAdapter(this, prepareLessonsBeans);
        recyclerView.setAdapter(adapter);
        initComponent();
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


    private void refresh() {
        pageNum = 1;
        prepareLessonsBeans.clear();
        Map<String, String> params = new HashMap<>();
        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");
        showLoading();
        HttpManager.getHasHeaderHasParam(HttpManager.INDEX_COACH_QUERY_PREPARE_LESSON_URL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                total = JsonUtil.getInt(result, "total");

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                prepareLessonsBeans = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), PrepareLessonsBean.class);
                for (int i = 0; i < 10; i++) {
                    prepareLessonsBeans.add(new PrepareLessonsBean());
                }
                adapter.resetList(prepareLessonsBeans);
                hideLoading();
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败

                hideLoading();
            }
        });

    }

    private void loadMore() {
        boolean hasMore = total > pageNum * pageSize;
        if (hasMore){
            Map<String, String> params = new HashMap<>();
            params.put("pageNum", pageNum + "");
            params.put("pageSize", pageSize + "");
            showLoading();
            HttpManager.searchViperByCoach(params, new ResultJSONObjectObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    pageNum = JsonUtil.getInt(result, "pageNum") + 1;

                    boolean hasMore = total > pageNum * pageSize;
                    refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败
                    JSONArray records = JsonUtil.getJsonArray(result, "records");
                    prepareLessonsBeans = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), PrepareLessonsBean.class);
                    adapter.resetList(prepareLessonsBeans);
                    hideLoading();
                }

                @Override
                public void onFail(String msg) {

                    refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败

                    hideLoading();
                }
            });
        }else {
            refreshLayout.finishLoadMore(2000, false, true);//传入false表示刷新失败

        }


    }


}
