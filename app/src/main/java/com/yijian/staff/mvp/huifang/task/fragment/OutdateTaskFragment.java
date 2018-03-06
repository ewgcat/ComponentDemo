package com.yijian.staff.mvp.huifang.task.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huifang.bean.HuiFangInfo;
import com.yijian.staff.mvp.huifang.task.adapter.HuiFangTaskAdapter;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 19:48:02
 */
@SuppressLint("ValidFragment")
public class OutdateTaskFragment extends CommonHuiFangTaskFragment {

    private RefreshLayout refreshLayout;
    private Context context;
    private List<HuiFangInfo> huiFangInfoList=new ArrayList<>();
    private RecyclerView recyclerView;
    private HuiFangTaskAdapter huiFangTaskAdapter;


    public OutdateTaskFragment() {
        super();
        viewId = R.layout.fragment_outdate_list;
    }

    public OutdateTaskFragment(Context context, int type) {
        super(context,  R.layout.fragment_outdate_list, type);
        this.context=context;
    }

    @Override
    public void initComponent(View view) {
        super.initComponent(view);

        refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);

        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(context).setEnableHorizontalDrag(true);
        header.setPrimaryColor(getResources().getColor(R.color.colorPrimary));
        refreshLayout.setRefreshHeader(header);

        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(context).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(getResources().getColor(R.color.colorPrimary));

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

        recyclerView = view. findViewById(R.id.rlv);

    }

    @Override
    public void refresh(boolean isShowLoading) {
        super.refresh(isShowLoading);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("name","朱沙");
            jsonObject.put("headUrl","headUrl");
            jsonObject.put("sex","男");
            jsonObject.put("quanyi","私教课");
            jsonObject.put("outdateTime","2018-2-2");
            jsonObject.put("outdateReason","工作太忙");
            jsonObject.put("huifangType","过期回访");
            jsonObject.put("huifangReason","　　中新社北京3月5日电 (记者 唐贵江)农业部部长韩长赋5日在全国两会“部长通道”回答媒体记者提问时表示，近年来中国粮食连年丰收，粮食产量连续5年稳定在1.2万亿斤的台阶上，化肥的使用对粮食增长有重要作用，但不能说是化肥“喂”出来的。2017年，中国的化肥使用实现了负增长，提前三年实现了“十三五”目标，也就是化肥农药使用量的零增长");
            for (int i = 0; i < 10; i++) {
                HuiFangInfo huiFangInfo = new HuiFangInfo(jsonObject);
                huiFangInfoList.add(huiFangInfo);
            }

            LinearLayoutManager layoutmanager = new LinearLayoutManager(context);
            //设置RecyclerView 布局
            recyclerView.setLayoutManager(layoutmanager);
            huiFangTaskAdapter = new HuiFangTaskAdapter(context, huiFangInfoList);
            recyclerView.setAdapter(huiFangTaskAdapter);
        } catch (JSONException e) {
            Logger.i("TEST","JSONException: "+e);
        }
    }
}
