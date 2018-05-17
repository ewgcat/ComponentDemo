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

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.reception.bean.ReceptionRecordBean;
import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.bean.RecptionRecordListBean;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.widget.EmptyView;
import com.yijian.staff.widget.NavigationBar2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by The_P on 2018/5/14.
 */

public class ReceptionActivityTemp extends MvcBaseActivity implements ReceptionContract.View {

    private NavigationBar2 navigation2;
    private ReceptionHistoryAdapterTemp adapterTemp;
    private ReceptionPresenterTemp presenterTemp;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.empty_view)
    EmptyView empty_view;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_reception_temp;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterTemp = new ReceptionHistoryAdapterTemp(this);
        recyclerView.setAdapter(adapterTemp);

        initRefresh();
        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBlueProgress();
                empty_view.setVisibility(View.GONE);
                presenterTemp.getRecptionRecord(true);
            }
        });
    }

    private void initRefresh() {

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
                showBlueProgress();
                empty_view.setVisibility(View.GONE);

                presenterTemp.getRecptionRecord(true);

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                showBlueProgress();
                empty_view.setVisibility(View.GONE);

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
        if (isRefresh) adapterTemp.clearData();
        adapterTemp.addData(recordList);
        if (recordList.size()==0){
            empty_view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void finishRefresh(boolean isRefresh) {
        if (isRefresh) {
            refreshLayout.finishRefresh();
            hideBlueProgress();
        } else {
            refreshLayout.finishLoadMore();
            hideBlueProgress();
        }
    }

    @Override
    public void showStatus(ReceptionStastuBean receptionStastuBean) {

    }

    @Override
    public void showEndRecption() {

    }
}
