package com.yijian.staff.mvp.experienceclass.index;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/test/19")
public class ExperienceClassListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    List<ExperienceClassBean> experienceClassBeanList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_list);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        NavigationBar navigationBar = findViewById(R.id.experience_class_navigation_bar);
        navigationBar.setTitle("体验课课程", "#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
        initComponent();
        initData();
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
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
            }
        });
    }

    private void initData(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("sex", 0);
            jsonObject.put("cardName", "原力十周年纪念卡");
            jsonObject.put("cardType", "时间卡");
            jsonObject.put("ExperiencedCuont", 2);
            jsonObject.put("currentOperation", "回访");
            for (int i = 0; i < 10; i++) {
                ExperienceClassBean experienceClassBean = new ExperienceClassBean(jsonObject);
                experienceClassBeanList.add(experienceClassBean);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            recyclerView.setLayoutManager(layoutmanager);
            ExperienceClassListAdatper experienceClassListAdatper = new ExperienceClassListAdatper(this, experienceClassBeanList);
            recyclerView.setAdapter(experienceClassListAdatper);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }

}

