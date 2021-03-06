package com.yijian.clubmodule.ui.vipermanage.student.potential;

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
import com.yijian.clubmodule.bean.CoachViperBean;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
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
 * 潜在会员 列表
 */
@Route(path = "/test/3.1")
public class CoachPotentialViperListActivity extends MvcBaseActivity {

    SmartRefreshLayout refreshLayout;
    RecyclerView rv_vip_intention;
    EmptyView empty_view;
    TextView tv_total_num;
    private CoachPotentialViperListAdapter coachPotentialViperListAdapter;
    private List<CoachViperBean> viperBeanList = new ArrayList<>();

    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量
    private int total;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_potential_viper_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initView();
    }


    private void initView() {
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
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_potential_student",version);
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
        navigationBar.setTitle("潜在学员");
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_vip_intention.setLayoutManager(layoutmanager);
        coachPotentialViperListAdapter = new CoachPotentialViperListAdapter(getLifecycle(),this, viperBeanList);
        rv_vip_intention.setAdapter(coachPotentialViperListAdapter);
        initComponent();
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
        viperBeanList.clear();
        empty_view.setVisibility(View.GONE);
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        showLoading();
        HttpManager.postHasHeaderHasParam(HttpManager.GET_COACH_POTENTIAL_VIPER_LIST_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                refreshLayout.finishRefresh(2000, true);
                viperBeanList.clear();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                total = JsonUtil.getInt(result, "total");
                if (total==-1){
                    total=0;
                }
                tv_total_num.setText("潜在学员总人数："+total+"人");

                JSONArray records = JsonUtil.getJsonArray(result, "records");

                try {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachViperBean viperBean = new CoachViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    }
                    coachPotentialViperListAdapter.notifyDataSetChanged();
                    if (viperBeanList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {


                }
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                coachPotentialViperListAdapter.notifyDataSetChanged();
                if (viperBeanList.size() == 0) {
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
        HttpManager.postHasHeaderHasParam(HttpManager.GET_COACH_POTENTIAL_VIPER_LIST_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;


                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

                JSONArray records = JsonUtil.getJsonArray(result, "records");

                try {
                    for (int i = 0; i < records.length(); i++) {

                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachViperBean viperBean = new CoachViperBean(jsonObject);
                        viperBeanList.add(viperBean);

                    }
                    coachPotentialViperListAdapter.notifyDataSetChanged();
                    if (viperBeanList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                }
            }

            @Override
            public void onFail(String msg) {
                hideLoading();

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                coachPotentialViperListAdapter.notifyDataSetChanged();
                if (viperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
