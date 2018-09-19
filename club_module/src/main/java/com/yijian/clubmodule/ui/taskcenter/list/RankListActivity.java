package com.yijian.clubmodule.ui.taskcenter.list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.commonlib.widget.EmptyView;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.bean.RankBean;

import java.util.ArrayList;
import java.util.List;



public class RankListActivity extends MvcBaseActivity {
    RecyclerView rv;
    SmartRefreshLayout refreshLayout;
    EmptyView emptyView;
    private List<RankBean> rankBeanList = new ArrayList<>();

    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量

    private int total;

    public final static int TODAY_RANK_TYPE = 0;
    public final static int MONTH_RANK_TYPE = 1;
    private int type;
    private RankListAdatper adatper;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_rank_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationBar navigationBar = findViewById(R.id.rank_list_navigation_bar);
        navigationBar .hideLeftSecondIv();
        navigationBar .setBackClickListener(this);
        type = getIntent().getIntExtra("RANK_TYPE", 0);
        if (type == TODAY_RANK_TYPE) {
            navigationBar.setTitle("今日排名");
        } else {
            navigationBar.setTitle("本月排名");
        }
        initView();
    }


    public void initView() {

        rv= findView(R.id.rv);
        refreshLayout= findView(R.id.refreshLayout);
        emptyView= findView(R.id.empty_view);
        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        refreshLayout = findViewById(R.id.refreshLayout);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv.setLayoutManager(layoutmanager);
        //设置RecyclerView 布局

        adatper = new RankListAdatper(this, rankBeanList);
        rv.setAdapter(adatper);


        initComponent();


    }

    private void refresh() {
        rankBeanList.clear();
        for (int i = 0; i < pageSize; i++) {
            RankBean rankBean = new RankBean();
            rankBean.setName("员工" + (i + 1));
            rankBean.setCompletedPrecent((100 - (30 * i)) + "");
            rankBean.setTvRankPosition((i + 1) + "");
            rankBeanList.add(rankBean);
        }
        adatper.notifyDataSetChanged();
        refreshLayout.finishRefresh(2000, true);
    }

    public void loadMore() {

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

}
