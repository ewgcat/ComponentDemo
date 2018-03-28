package com.yijian.staff.mvp.coach.huifang.history;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.huifang.bean.CoachHuiFangInfo;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoachHuiFangHistoryActivity extends AppCompatActivity {
    private List<CoachHuiFangInfo> coachHuiFangInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hui_fang_history);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.hui_fang_history_navigation_bar);
        navigationBar2.setTitle("回访记录");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.rlv);


        RefreshLayout   refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
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

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("name", "朱沙");
            jsonObject.put("headUrl", "headUrl");
            jsonObject.put("sex", "男");
            jsonObject.put("quanyi", "私教课");
            jsonObject.put("outdateTime", "2018-2-2");
            jsonObject.put("outdateReason", "工作太忙");
            jsonObject.put("huifangType", "过期回访");
            jsonObject.put("huifangReason", "　　中新社北京3月5日电 (记者 唐贵江)农业部部长韩长赋5日在全国两会“部长通道”回答媒体记者提问时表示，近年来中国粮食连年丰收，粮食产量连续5年稳定在1.2万亿斤的台阶上，化肥的使用对粮食增长有重要作用，但不能说是化肥“喂”出来的。2017年，中国的化肥使用实现了负增长，提前三年实现了“十三五”目标，也就是化肥农药使用量的零增长");
            for (int i = 0; i < 10; i++) {
                CoachHuiFangInfo coachHuiFangInfo = new CoachHuiFangInfo(jsonObject);
                coachHuiFangInfoList.add(coachHuiFangInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            recyclerView.setLayoutManager(layoutmanager);
            CoachHuiFangHistoryAdapter coachHuiFangHistoryAdapter = new CoachHuiFangHistoryAdapter(this, coachHuiFangInfoList);
            recyclerView.setAdapter(coachHuiFangHistoryAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }

    @OnClick(R.id.ll_hui_fang_ren_wu)
    public void onViewClicked() {
        finish();
    }
}
