package com.yijian.staff.mvp.huiji.intent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.bean.HuiJiViperBean;
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

/**
 * 意向会员  列表
 */
@Route(path = "/test/2")
public class HuijiIntentViperListActivity extends MvcBaseActivity {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv)
    RecyclerView rv_vip_intention;
    @BindView(R.id.empty_view)
    EmptyView empty_view;

    private HuijiIntentViperListAdapter huijiIntentViperListAdapter;
    private List<HuiJiViperBean> viperBeanList = new ArrayList<>();
    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量
    private int pages;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_intent_viper_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_intent_navigation_bar);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("意向会员");

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_vip_intention.setLayoutManager(layoutmanager);
        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        huijiIntentViperListAdapter = new HuijiIntentViperListAdapter(this, viperBeanList);
        rv_vip_intention.setAdapter(huijiIntentViperListAdapter);
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
        viperBeanList.clear();
        pageNum = 1;
        empty_view.setVisibility(View.GONE);

        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        showLoading();
        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJI_INTENT_VIPER_LIST_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                refreshLayout.finishRefresh(2000, true);
                viperBeanList.clear();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        HuiJiViperBean viperBean = new HuiJiViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    } catch (JSONException e) {


                    }
                }
                huijiIntentViperListAdapter.notifyDataSetChanged();
                if (viperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                showToast(msg);
                huijiIntentViperListAdapter.notifyDataSetChanged();
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
        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJI_INTENT_VIPER_LIST_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");


                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        HuiJiViperBean viperBean = new HuiJiViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    } catch (JSONException e) {
                    }
                }
                huijiIntentViperListAdapter.notifyDataSetChanged();
                if (viperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(String msg) {
                hideLoading();

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                showToast(msg);
                huijiIntentViperListAdapter.notifyDataSetChanged();
                if (viperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }
        });
    }


}
