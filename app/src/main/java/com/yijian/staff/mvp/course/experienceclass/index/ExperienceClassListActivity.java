package com.yijian.staff.mvp.course.experienceclass.index;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.ExperienceClassBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.course.experienceclass.index.contract.ExperienceClassContract;
import com.yijian.staff.mvp.course.experienceclass.index.presenter.ExperienceClassPresenter;
import com.yijian.staff.widget.NavigationBar2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = "/test/17")
public class ExperienceClassListActivity extends MvcBaseActivity implements ExperienceClassContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout experienceClassRefreshLayout;

    List<ExperienceClassBean> experienceClassBeanList = new ArrayList<>();
    private ExperienceClassPresenter experienceClassPresenter;
    private ExperienceClassListAdatper experienceClassListAdatper;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_experience_class_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = findViewById(R.id.experience_class_navigation_bar2);
        navigationBar2.setTitle("体验课课程");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        experienceClassListAdatper = new ExperienceClassListAdatper(this, experienceClassBeanList);
        recyclerView.setAdapter(experienceClassListAdatper);

        experienceClassPresenter = new ExperienceClassPresenter(this);
        experienceClassPresenter.setView(this);

        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        experienceClassRefreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        experienceClassRefreshLayout.setRefreshFooter(footer);
        experienceClassRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                experienceClassPresenter.getExperienceClassListInfo(experienceClassRefreshLayout, true);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                experienceClassPresenter.getExperienceClassListInfo(experienceClassRefreshLayout, false);
            }
        });
        experienceClassPresenter.getExperienceClassListInfo(experienceClassRefreshLayout, true);

    }


    @Override
    public void showExperienceClassListView(List<ExperienceClassBean> experienceClassBeanList) {
        experienceClassListAdatper.update(experienceClassBeanList);
    }
}

