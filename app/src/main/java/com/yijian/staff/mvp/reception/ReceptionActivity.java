package com.yijian.staff.mvp.reception;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.bean.RecptionRecordListBean;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.step1.ReceptionStepOneActivity;
import com.yijian.staff.mvp.reception.step2.CoachReceptionStepTwoActivity;
import com.yijian.staff.mvp.reception.step3.ReceptionStepThreeActivity;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.List;

public class ReceptionActivity extends AppCompatActivity implements View.OnClickListener ,ReceptionContract.View{

    private List<ReceptionInfo> mReceptionInfoList=new ArrayList<>();

    private RecyclerView recyclerView;
    private ReceptionPresenter presenter;
    private TextView tvName;
    private TextView tvSex;
    private TextView tvPhone;
    private ReceptionHistoryAdapter receptionHistoryAdapter;
    private SmartRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reception);
        initView();
        presenter = new ReceptionPresenter(this);

        presenter.setView(this);
        presenter.getRecptionerInfo();
        presenter. getRecptionRecord(true);
    }

    private void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar.setTitle("接待", "#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));


        tvName = findViewById(R.id.tv_name);
        tvSex = findViewById(R.id.tv_sex);
        tvPhone = findViewById(R.id.tv_phone);

        findViewById(R.id.tv_jiedai).setOnClickListener(this);
        findViewById(R.id.tv_stopJieDai).setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerview_jiedai_history);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setNestedScrollingEnabled(false);
        receptionHistoryAdapter = new ReceptionHistoryAdapter(this);
        recyclerView.setAdapter(receptionHistoryAdapter);

        initRefresh();


    }

    private void initRefresh() {
        refreshLayout =  findViewById(R.id.refreshLayout);

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
//                refresh(null);
                presenter. getRecptionRecord(true);
//                refreshLayout.finishRefresh(1000);

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter. getRecptionRecord(false);
//                refreshLayout.finishLoadMore(1000);
//                loadMore();
            }
        });
    }




    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.tv_stopJieDai:
                //TODO 结束接待



                break;
            case R.id.tv_jiedai:
                //TODO 接待流程
                int userRole = SharePreferenceUtil.getUserRole();
                if (userRole==1){
                    Intent intent = new Intent(ReceptionActivity.this, ReceptionStepOneActivity.class);
                    startActivity(intent);
                }else if (userRole==2){
                    Intent intent = new Intent(ReceptionActivity.this, CoachReceptionStepTwoActivity.class);
                    startActivity(intent);
                }else if (userRole==3|userRole==4){
                    Intent intent = new Intent(ReceptionActivity.this, ReceptionStepThreeActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }



    @Override
    public void showRecptionInfo(RecptionerInfoBean bean) {
        tvName.setText(""+bean.getName());
        tvSex.setText(""+bean.getSex());
        tvPhone.setText(""+bean.getMobile());

    }

    @Override
    public void showRecptionRecordList(List<RecptionRecordListBean.RecordsBean> recordList, boolean isRefresh) {

        if (isRefresh)receptionHistoryAdapter.clearData();
        receptionHistoryAdapter.addData(recordList);
    }

    @Override
    public void finishRefresh(boolean isRefresh) {
        if (isRefresh){
            refreshLayout.finishRefresh();
        }else {
            refreshLayout.finishLoadMore();
        }
    }
}