package com.yijian.staff.mvp.vipermanage.viper.outdate;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.AccessStatisticsRequestBody;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.bean.HuiJiViperBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
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

import static com.yijian.staff.net.httpmanager.HttpManager.GET_HUIJI_OUTDATE_VIPER_LIST_URL;

/**
 * 过期会员列表
 */
@Route(path = "/test/4")
public class HuijiOutdateViperListActivity extends MvcBaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv_outdate;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.empty_view)
    EmptyView empty_view;
    @BindView(R.id.tv_total_num)
    TextView tv_total_num;

    List<HuiJiViperBean> vipOutdateInfoList = new ArrayList<HuiJiViperBean>();
    HuijiOutdateViperListAdapter huijiOutdateViperListAdapter;
    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量
    private int total;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_outdate_viper_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_expire_member",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_over_navigation_bar2);
        navigationBar2.setTitle("过期会员");
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
        //设置RecyclerView 布局
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_outdate.setLayoutManager(layoutmanager);
        huijiOutdateViperListAdapter = new HuijiOutdateViperListAdapter(this, vipOutdateInfoList);
        rv_outdate.setAdapter(huijiOutdateViperListAdapter);
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

        vipOutdateInfoList.clear();

        pageNum = 1;
        empty_view.setVisibility(View.GONE);
        showLoading();

        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(GET_HUIJI_OUTDATE_VIPER_LIST_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                refreshLayout.finishRefresh(2000, true);
                vipOutdateInfoList.clear();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;

                total = JsonUtil.getInt(result, "total");
                if (total==-1){
                    total=0;
                }
                tv_total_num.setText("过期会员总人数："+total+"人");

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {

                        JSONObject jsonObject = (JSONObject) records.get(i);
                        HuiJiViperBean vipOutdateInfo = new HuiJiViperBean(jsonObject);
                        vipOutdateInfoList.add(vipOutdateInfo);
                    }
                    huijiOutdateViperListAdapter.notifyDataSetChanged();
                    if (vipOutdateInfoList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {


                }

            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                hideLoading();
                showToast(msg);
                huijiOutdateViperListAdapter.notifyDataSetChanged();
                if (vipOutdateInfoList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void loadMore() {
        empty_view.setVisibility(View.GONE);

        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(GET_HUIJI_OUTDATE_VIPER_LIST_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;


                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        HuiJiViperBean vipOutdateInfo = new HuiJiViperBean(jsonObject);
                        vipOutdateInfoList.add(vipOutdateInfo);
                    } catch (JSONException e) {
                    }
                }
                huijiOutdateViperListAdapter.notifyDataSetChanged();
                if (vipOutdateInfoList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(String msg) {
                hideLoading();


                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                showToast(msg);
                huijiOutdateViperListAdapter.notifyDataSetChanged();
                if (vipOutdateInfoList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }
        });
    }


}
