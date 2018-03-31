package com.yijian.staff.mvp.vip.potential;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.ViperBean;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;
import com.yijian.staff.mvp.huiji.intent.HuijiIntentViperListActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;
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
@Route(path = "/test/3")
public class PotentialViperListActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_vip_intention)
    RecyclerView rv_vip_intention;
    private PotentialViperListAdapter potentialViperListAdapter;
    private List<HuiJiViperBean> viperBeanList=new ArrayList<>();

    private int pageNum = 1;//页码
    private int pageSize = 1;//每页数量
    private int pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potential_viper_list);
        ButterKnife.bind(this);

        initTitle();
        initView();
//        initVipPeopleList();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_intent_navigation_bar);
        navigationBar2.hideBottomLine();
        navigationBar2.hideLeftSecondIv();
        navigationBar2.getmRightTv().setOnClickListener(this);
        navigationBar2.setBackClickListener(this);
        ImageView rightIv = navigationBar2.getmRightIv();
        Glide.with(this).load(R.mipmap.shaixuan_white).into(rightIv);
        navigationBar2.setTitle("潜在会员");
        navigationBar2.setmRightTvText("筛选");
    }

    private void initView(){
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_vip_intention.setLayoutManager(layoutmanager);
        potentialViperListAdapter = new PotentialViperListAdapter(this, viperBeanList);
        rv_vip_intention.setAdapter(potentialViperListAdapter);
        initComponent();
    }

    /*private void initVipPeopleList(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("gender", "0");
            jsonObject.put("birth", "1990-8-9");
            jsonObject.put("birthType", "农历");
            jsonObject.put("bodyStatus", "正常");
            jsonObject.put("bodybuildingHobby", "跑步");
            jsonObject.put("interestHobby", "打橄榄球");
            jsonObject.put("useCar", "无");
            jsonObject.put("isIntentVip","0");
            for (int i = 0; i < 10; i++) {
                ViperBean viperBean = new ViperBean(jsonObject);
                viperBeanList.add(viperBean);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            rv_vip_intention.setLayoutManager(layoutmanager);
            PotentialViperListAdapter potentialViperListAdapter = new PotentialViperListAdapter(this, viperBeanList);
            rv_vip_intention.setAdapter(potentialViperListAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }*/


    @Override
    public void onClick(View v) {
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
        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());

        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", 1 + "");
        map.put("pageSize", 1 + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJI_POTENTIAL_VIPER_LIST_URL,header, map, new ResultObserver() {
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
                        HuiJiViperBean viperBean = new HuiJiViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    } catch (JSONException e) {


                    }
                }
                potentialViperListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(PotentialViperListActivity.this,msg,Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadMore() {
        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());

        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJI_POTENTIAL_VIPER_LIST_URL,header, map, new ResultObserver() {
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
                        HuiJiViperBean viperBean = new HuiJiViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    } catch (JSONException e) {
                    }
                }
                potentialViperListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                Toast.makeText(PotentialViperListActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
