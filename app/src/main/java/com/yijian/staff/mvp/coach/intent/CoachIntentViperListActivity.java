package com.yijian.staff.mvp.coach.intent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.bean.CoachViperBean;
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

import static com.yijian.staff.tab.tools.ContextUtil.getContext;

/**
 * 意向会员  列表
 */
@Route(path = "/test/2.1")
public class CoachIntentViperListActivity extends MvcBaseActivity {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv)
    RecyclerView rv_vip_intention;

    @BindView(R.id.empty_view)
    EmptyView empty_view;


    private List<CoachViperBean> coachViperBeanList = new ArrayList<>();
    private CoachIntentViperListAdapter coachIntentViperListAdapter;

    private int pageNum;
    private int pages;
    private int pageSize = 10;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_intent_viper_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_intent_navigation_bar);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("意向学员");

        initComponent();
        LinearLayoutManager layoutmanager = new LinearLayoutManager(CoachIntentViperListActivity.this);
        //设置RecyclerView 布局
        rv_vip_intention.setLayoutManager(layoutmanager);
        coachIntentViperListAdapter = new CoachIntentViperListAdapter(CoachIntentViperListActivity.this, coachViperBeanList);
        rv_vip_intention.setAdapter(coachIntentViperListAdapter);
        refresh();
        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
    }


    public void initComponent() {
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(CoachIntentViperListActivity.this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(CoachIntentViperListActivity.this).setSpinnerStyle(SpinnerStyle.Scale);
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
        empty_view.setVisibility(View.GONE);

        coachViperBeanList.clear();
        pageNum = 1;
        pageSize = 10;
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        showBlueProgress();
        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_INTENT_VIPER_LIST_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideBlueProgress();
                refreshLayout.finishRefresh(2000, true);

                coachViperBeanList.clear();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {

                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachViperBean coachViperBean = new CoachViperBean(jsonObject);
                        coachViperBeanList.add(coachViperBean);

                    }
                    coachIntentViperListAdapter.notifyDataSetChanged();
                    if (coachViperBeanList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {


                }
            }

            @Override
            public void onFail(String msg) {
                hideBlueProgress();
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                showToast(msg);
                coachIntentViperListAdapter.notifyDataSetChanged();

                if (coachViperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }


            }
        });
    }

    public void loadMore() {

        empty_view.setVisibility(View.GONE);

        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        showBlueProgress();
        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_INTENT_VIPER_LIST_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideBlueProgress();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");

                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, true, !hasMore);//传入false表示刷新失败

                JSONArray records = JsonUtil.getJsonArray(result, "records");

                try {
                    for (int i = 0; i < records.length(); i++) {

                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachViperBean coachViperBean = new CoachViperBean(jsonObject);
                        coachViperBeanList.add(coachViperBean);

                    }
                    coachIntentViperListAdapter.notifyDataSetChanged();

                    if (coachViperBeanList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                }
            }

            @Override
            public void onFail(String msg) {
                hideBlueProgress();
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, !hasMore);//传入false表示刷新失败
                showToast(msg);
                coachIntentViperListAdapter.notifyDataSetChanged();

                if (coachViperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
