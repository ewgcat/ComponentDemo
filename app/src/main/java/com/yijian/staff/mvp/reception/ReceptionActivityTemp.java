package com.yijian.staff.mvp.reception;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.AccessStatisticsRequestBody;
import com.yijian.staff.bean.ReceptionRecordBean;
import com.yijian.staff.bean.ReceptionStastuBean;
import com.yijian.staff.bean.RecptionRecordListBean;
import com.yijian.staff.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.EmptyView;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by The_P on 2018/5/14.
 */

public class ReceptionActivityTemp extends MvcBaseActivity implements ReceptionContract.View {

    private NavigationBar2 navigation2;
    private RecyclerView recyclerView;
    private ReceptionHistoryAdapterTemp adapterTemp;
    private ReceptionPresenterTemp presenterTemp;
    private SmartRefreshLayout refreshLayout;
    private EmptyView emptyView;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_reception_temp;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initView();

        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_reception",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        presenterTemp = new ReceptionPresenterTemp(getLifecycle(),this);
        presenterTemp.setView(this);
        presenterTemp.getRecptionRecord(true);
    }

    public static void toReceptionActivityTemp(Context context) {
        Intent intent = new Intent(context, ReceptionActivityTemp.class);
        context.startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        SharePreferenceUtil.setHasNewJiedaiPush(false);
        presenterTemp.getRecptionRecord(true);
    }


    private void initView() {
        navigation2 = findViewById(R.id.navigation_bar2);
        navigation2.setTitle("接待");
        navigation2.setBackClickListener(this);
        navigation2.setSecondLeftIvVisiable(View.GONE);

        emptyView = findViewById(R.id.empty_view);
        emptyView.getEmptyBt().setVisibility(View.GONE);

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterTemp = new ReceptionHistoryAdapterTemp(this);
        recyclerView.setAdapter(adapterTemp);

        initRefresh();
    }

    private void initRefresh() {
        refreshLayout = findViewById(R.id.refreshLayout);

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
                presenterTemp.getRecptionRecord(true);

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenterTemp.getRecptionRecord(false);
            }
        });
    }

    @Override
    public void showRecptionInfo(RecptionerInfoBean bean) {

    }

    @Override
    public void showRecptionRecordList(List<RecptionRecordListBean.RecordsBean> recordList, boolean isRefresh) {

    }

    @Override
    public void showRecptionRecordListTemp(List<ReceptionRecordBean> recordList, boolean isRefresh) {
        emptyView.setVisibility(View.GONE);
        if (isRefresh) adapterTemp.clearData();
        adapterTemp.addData(recordList);
    }

    @Override
    public void finishRefresh(boolean isRefresh) {
        if (isRefresh) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void showStatus(ReceptionStastuBean receptionStastuBean) {

    }

    @Override
    public void showEndRecption() {

    }

    @Override
    public void showNoData() {
        emptyView.setVisibility(View.VISIBLE);

    }
}
