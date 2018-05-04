package com.yijian.staff.mvp.coach.resourceallocation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.resourceallocation.adapter.CoachResourceAllocationAdatper;
import com.yijian.staff.mvp.coach.resourceallocation.bean.CoachHistoryResourceAllocationInfo;
import com.yijian.staff.mvp.coach.resourceallocation.fragment.distribution.CoachResourceAllocationFragment;
import com.yijian.staff.mvp.coach.resourceallocation.fragment.history.CoachHistoryAllocationFragment;
import com.yijian.staff.mvp.huiji.resourceallocation.HuiJiResourceAllocationActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资源分配(员工角色)
 */
@Route(path = "/test/10.1")
public class CoachResourceAllocationActivity extends AppCompatActivity {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_resource_allocation)
    RecyclerView rv_resource_allocation;
    @BindView(R.id.ll_kefu_layout)
    LinearLayout llKefuLayout;
    @BindView(R.id.ll_leader_layout)
    LinearLayout llLeaderLayout;
    private List<CoachHistoryResourceAllocationInfo> resourceAllocationInfoList = new ArrayList<>();


    private static final String[] FRAGMENT_TAG = {"CoachResourceAllocationFragment", "CoachHistoryAllocationFragment"};
    private CoachResourceAllocationFragment resourceAllocationFragment;
    private CoachHistoryAllocationFragment historyAllocationFragment;
    private NavigationBar2 navigationBar2;

    @BindView(R.id.lin_resource_allowcation)
    LinearLayout lin_resource_allowcation;
    @BindView(R.id.lin_history_allowcation)
    LinearLayout lin_history_allowcation;
    @BindView(R.id.tv_resource_allowcation)
    TextView tv_resource_allowcation;
    @BindView(R.id.iv_resource_allowcation)
    ImageView iv_resource_allowcation;
    @BindView(R.id.tv_history_allowcation)
    TextView tv_history_allowcation;
    @BindView(R.id.iv_history_allowcation)
    ImageView iv_history_allowcation;

    private int pageNum = 1;
    private int pageSize = 1;
    private int pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_resource_allocation);
        ButterKnife.bind(this);
        navigationBar2 = findViewById(R.id.vip_over_navigation_bar2);

        int role = SharePreferenceUtil.getUserRole();
        if ( role == 4) { //教练总监
             initLeader();
        } else { // 不可操作
            initCoach();
        }

    }


    /**
     * 教练界面
     */
    private void initCoach() {
        llKefuLayout.setVisibility(View.VISIBLE);
        llLeaderLayout.setVisibility(View.GONE);
        navigationBar2.setTitle("资源分配");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_resource_allocation.setLayoutManager(layoutmanager);
        CoachResourceAllocationAdatper resourceAllocationAdatper = new CoachResourceAllocationAdatper(this, resourceAllocationInfoList, CoachResourceAllocationAdatper.ROLE_RESOURCE_TYPE);
        rv_resource_allocation.setAdapter(resourceAllocationAdatper);
        initComponent();
        initResourceAllocationInfoList();
        refresh();
    }



    private void initResourceAllocationInfoList() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("name", "1");
            jsonObject.put("birthDay", "1990-8-9");
            jsonObject.put("wxIdentification", "wer2342344");
            jsonObject.put("email", "打橄榄球");
            jsonObject.put("serviceHuiJi", "壮壮");
            jsonObject.put("serviceCoach", "牛牛");
            for (int i = 0; i < 10; i++) {
                CoachHistoryResourceAllocationInfo vipPeopleInfo = new CoachHistoryResourceAllocationInfo(jsonObject);
                resourceAllocationInfoList.add(vipPeopleInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            rv_resource_allocation.setLayoutManager(layoutmanager);
            CoachResourceAllocationAdatper resourceAllocationAdatper = new CoachResourceAllocationAdatper(this, resourceAllocationInfoList, CoachResourceAllocationAdatper.ROLE_RESOURCE_TYPE);
            rv_resource_allocation.setAdapter(resourceAllocationAdatper);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }

    }

    public void initComponent() {
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(CoachResourceAllocationActivity.this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(CoachResourceAllocationActivity.this).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                pageSize = 1;
                refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }


    private void refresh() {
        Map<String, String> params = new HashMap<>();

        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_RESOURCE_ALLOCATION__PHONE_URL,params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(CoachResourceAllocationActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadMore() {

        Map<String, String> params = new HashMap<>();

        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_RESOURCE_ALLOCATION__PHONE_URL,params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败
            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                Toast.makeText(CoachResourceAllocationActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     *
     * 领导界面
     */

    private void initLeader() {
        llKefuLayout.setVisibility(View.GONE);
        llLeaderLayout.setVisibility(View.VISIBLE);
        navigationBar2.setTitle("资源分配");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        selectTab(0);
    }



    @OnClick({R.id.lin_resource_allowcation, R.id.lin_history_allowcation})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.lin_resource_allowcation: //资源分配
                selectTab(0);
                break;
            case R.id.lin_history_allowcation: //历史分配
                selectTab(1);
                break;
        }
    }

    public void selectTab(int index) {
        setBotoomStyle(index);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case 0:
                popFragement(transaction, resourceAllocationFragment, index);
                break;
            case 1:
                popFragement(transaction, historyAllocationFragment, index);
                break;
        }
        transaction.commit();
    }

    public void popFragement(FragmentTransaction transaction, Fragment fragment, int index) {
        if (fragment == null) {
            // 如果ViperFragment为空，则创建一个并添加到界面上
            if (index == 0) {
                resourceAllocationFragment = CoachResourceAllocationFragment.getInstance();
                transaction.add(R.id.fl_invitation, resourceAllocationFragment, FRAGMENT_TAG[index]);
            } else if (index == 1) {
                historyAllocationFragment = CoachHistoryAllocationFragment.getInstance();
                transaction.add(R.id.fl_invitation, historyAllocationFragment, FRAGMENT_TAG[index]);
            }
        } else {
            // 如果ViperFragment不为空，则直接将它显示出来
            if (index == 0) {
                transaction.replace(R.id.fl_invitation, resourceAllocationFragment);
            } else if (index == 1) {
                transaction.replace(R.id.fl_invitation, historyAllocationFragment);
            }

        }
    }

    public void setBotoomStyle(int index) {
        navigationBar2.setTitle(index == 0 ? "资源分配" : "历史分配");
        tv_resource_allowcation.setTextColor(index == 0 ? Color.parseColor("#1997f8") : Color.parseColor("#666666"));
        tv_history_allowcation.setTextColor(index == 0 ? Color.parseColor("#666666") : Color.parseColor("#1997f8"));

        iv_resource_allowcation.setImageResource(index == 0 ? R.mipmap.fp_ziyuanlan : R.mipmap.fp_ziyuan);
        iv_history_allowcation.setImageResource(index == 0 ? R.mipmap.fp_lishi : R.mipmap.fp_lishilan);
    }

}
