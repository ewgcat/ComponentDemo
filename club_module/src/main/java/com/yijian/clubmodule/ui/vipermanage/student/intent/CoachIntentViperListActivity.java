package com.yijian.clubmodule.ui.vipermanage.student.intent;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.commonlib.widget.EmptyView;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.bean.CoachViperBean;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.util.CommonUtil;
import com.yijian.commonlib.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 意向会员  列表
 */
@Route(path = "/test/2.1")
public class CoachIntentViperListActivity extends MvcBaseActivity {

    SmartRefreshLayout refreshLayout;
    RecyclerView rv_vip_intention;
    EmptyView empty_view;
    TextView tv_total_num;

    private List<CoachViperBean> coachViperBeanList = new ArrayList<>();
    private CoachIntentViperListAdapter coachIntentViperListAdapter;

    private int pageNum;
    private int total;

    private int pageSize = 10;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_intent_viper_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rv_vip_intention = findViewById(R.id.rv);
        refreshLayout = findViewById(R.id.refreshLayout);
        empty_view = findViewById(R.id.empty_view);
        tv_total_num = findViewById(R.id.tv_total_num);

        empty_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_intention_student",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        NavigationBar navigationBar = findViewById(R.id.vip_intent_navigation_bar);
        navigationBar .hideLeftSecondIv();
        navigationBar .setBackClickListener(this);
        navigationBar.setTitle("意向学员");

        initComponent();
        LinearLayoutManager layoutmanager = new LinearLayoutManager(CoachIntentViperListActivity.this);
        //设置RecyclerView 布局
        rv_vip_intention.setLayoutManager(layoutmanager);
        coachIntentViperListAdapter = new CoachIntentViperListAdapter(getLifecycle(),CoachIntentViperListActivity.this, coachViperBeanList);
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
        showLoading();
        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_INTENT_VIPER_LIST_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                refreshLayout.finishRefresh(2000, true);

                coachViperBeanList.clear();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                total = JsonUtil.getInt(result, "total");
                if (total==-1){
                    total=0;
                }
                tv_total_num.setText("意向学员总人数："+total+"人");
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
                hideLoading();
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
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
        showLoading();
        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_INTENT_VIPER_LIST_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;


                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

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
                hideLoading();

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败

                coachIntentViperListAdapter.notifyDataSetChanged();

                if (coachViperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
