package com.yijian.clubmodule.ui.vipermanage.student.outdate;

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
 * 过期会员列表
 */
@Route(path = "/test/4.1")
public class CoachOutdateViperListActivity extends MvcBaseActivity {

    SmartRefreshLayout refreshLayout;
    RecyclerView rv_outdate;
    EmptyView empty_view;
    TextView tv_total_num;
    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量
    private int total;


    private List<CoachViperBean> coachViperBeanList = new ArrayList<CoachViperBean>();
    private CoachOutdateViperListAdapter coachOutdateViperListAdapter;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_outdate_viper_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rv_outdate = findViewById(R.id.rv);
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
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_expire_student",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        NavigationBar navigationBar = findViewById(R.id.vip_over_navigation_bar);
        navigationBar.setTitle("过期学员");
        navigationBar .hideLeftSecondIv();
        navigationBar .setBackClickListener(this);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_outdate.setLayoutManager(layoutmanager);
        coachOutdateViperListAdapter = new CoachOutdateViperListAdapter(getLifecycle(),this, coachViperBeanList);
        rv_outdate.setAdapter(coachOutdateViperListAdapter);
        initComponent();
        refresh();
        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
    }


    private void refresh() {
        coachViperBeanList.clear();
        empty_view.setVisibility(View.GONE);

        HashMap<String, String> map = new HashMap<>();
        pageNum = 1;
        pageSize = 10;
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        showLoading();
        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_OUTDATE_VIPER_LIST_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                coachViperBeanList.clear();

                refreshLayout.finishRefresh(2000, true);
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                total = JsonUtil.getInt(result, "total");
                if (total==-1){
                    total=0;
                }
                tv_total_num.setText("过期学员总人数："+total+"人");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {

                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachViperBean coachViperBean = new CoachViperBean(jsonObject);
                        coachViperBeanList.add(coachViperBean);

                    }
                    coachOutdateViperListAdapter.notifyDataSetChanged();
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
                coachOutdateViperListAdapter.notifyDataSetChanged();

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
        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_OUTDATE_VIPER_LIST_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;



                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachViperBean coachViperBean = new CoachViperBean(jsonObject);
                        coachViperBeanList.add(coachViperBean);
                    } catch (JSONException e) {
                    }
                }
                coachOutdateViperListAdapter.notifyDataSetChanged();

                if (coachViperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(String msg) {
                hideLoading();

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                coachOutdateViperListAdapter.notifyDataSetChanged();

                if (coachViperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void initComponent() {
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(CoachOutdateViperListActivity.this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(CoachOutdateViperListActivity.this).setSpinnerStyle(SpinnerStyle.Scale);
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
