package com.yijian.staff.mvp.coach.potential;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.bean.CoachViperBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 潜在会员 列表
 */
@Route(path = "/test/3.1")
public class CoachPotentialViperListActivity extends AppCompatActivity  {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_vip_intention)
    RecyclerView rv_vip_intention;
    private CoachPotentialViperListAdapter coachPotentialViperListAdapter;
    private List<CoachViperBean> viperBeanList=new ArrayList<>();

    private int pageNum = 1;//页码
    private int pageSize = 1;//每页数量
    private int pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potential_viper_list);
        ButterKnife.bind(this);

        initView();
    }


    private void initView(){
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_intent_navigation_bar);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("潜在会员");
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_vip_intention.setLayoutManager(layoutmanager);
        coachPotentialViperListAdapter = new CoachPotentialViperListAdapter(this, viperBeanList);
        rv_vip_intention.setAdapter(coachPotentialViperListAdapter);
        initComponent();
        refresh();
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


        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", 1 + "");
        map.put("pageSize", 1 + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_POTENTIAL_VIPER_LIST_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);

                viperBeanList.clear();
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachViperBean viperBean = new CoachViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    } catch (JSONException e) {


                    }
                }
                coachPotentialViperListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(CoachPotentialViperListActivity.this,msg,Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadMore() {


        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_POTENTIAL_VIPER_LIST_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");

                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachViperBean viperBean = new CoachViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    } catch (JSONException e) {
                    }
                }
                coachPotentialViperListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                Toast.makeText(CoachPotentialViperListActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
