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
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.bean.ReceptionRecordBean;
import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.bean.RecptionRecordListBean;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.widget.EmptyView;
import com.yijian.staff.widget.NavigationBar2;

import java.util.List;

/**
 * Created by The_P on 2018/5/14.
 */

public class ReceptionActivityTemp extends AppCompatActivity implements ReceptionContract.View {

    private NavigationBar2 navigation2;
    private RecyclerView recyclerView;
    private ReceptionHistoryAdapterTemp adapterTemp;
    private ReceptionPresenterTemp presenterTemp;
    private SmartRefreshLayout refreshLayout;
    private EmptyView emptyView;
    //    private RelativeLayout rlNoData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_temp);
        initView();

        presenterTemp = new ReceptionPresenterTemp(this);
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
        presenterTemp.getRecptionRecord(true);
    }


    private void initView() {
        navigation2 = findViewById(R.id.navigation_bar2);
        navigation2.setTitle("接待");
        navigation2.setBackClickListener(this);
        navigation2.setSecondLeftIvVisiable(View.GONE);

//        rlNoData = findViewById(R.id.rl_nodata);
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
//        rlNoData.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.VISIBLE);

    }
}
