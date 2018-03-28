package com.yijian.staff.mvp.coach.huifang.task.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.huifang.bean.CoachHuiFangInfo;
import com.yijian.staff.mvp.coach.huifang.task.adapter.CoachHuiFangTaskAdapter;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 19:39:24
 */
@SuppressLint("ValidFragment")
public class CoachBaseHuiFangTaskFragment extends Fragment {


    private RefreshLayout refreshLayout;
    private List<CoachHuiFangInfo> coachHuiFangInfoList =new ArrayList<>();
    private RecyclerView recyclerView;
    private CoachHuiFangTaskAdapter coachHuiFangTaskAdapter;
    private Context context;
    protected int viewId;
    private int type;
    private int defaultViewId= R.layout.common_hui_fang_task;



    public CoachBaseHuiFangTaskFragment(Context context, int type) {
        this.context = context;
        this.viewId = defaultViewId;
        this.type = type;
    }

    public CoachBaseHuiFangTaskFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(viewId, container, false);
        initComponent(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refresh(true);
    }


    public void initComponent(View view) {
        refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(context).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(context).setSpinnerStyle(SpinnerStyle.Scale);
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

        recyclerView = view. findViewById(R.id.rlv);

    }

    public void refresh(boolean isShowLoading) {
        JSONObject jsonObject=new JSONObject();
        try {
            if (type==0){
                jsonObject.put("name","朱沙");
                jsonObject.put("headUrl","headUrl");
                jsonObject.put("sex","男");
                jsonObject.put("quanyi","私教课");
                jsonObject.put("outdateTime","2018-2-2");
                jsonObject.put("outdateReason","工作太忙");
                jsonObject.put("huifangType","过期回访");
                jsonObject.put("huifangReason","　　中新社北京3月5日电 (记者 唐贵江)农业部部长韩长赋5日在全国两会“部长通道”回答媒体记者提问时表示，近年来中国粮食连年丰收，粮食产量连续5年稳定在1.2万亿斤的台阶上，化肥的使用对粮食增长有重要作用，但不能说是化肥“喂”出来的。2017年，中国的化肥使用实现了负增长，提前三年实现了“十三五”目标，也就是化肥农药使用量的零增长");

            }else if (type==1){
                jsonObject.put("name","朱沙");
                jsonObject.put("headUrl","headUrl");
                jsonObject.put("sex","男");
                jsonObject.put("shentiStatus","正常");
                jsonObject.put("jianshenAihao","哑铃");
                jsonObject.put("xingquAihao","游泳");
                jsonObject.put("huifangType","体验课回访");
                jsonObject.put("huifangReason","　　中新社北京3月5日电 (记者 唐贵江)农业部部长韩长赋5日在全国两会“部长通道”回答媒体记者提问时表示，近年来中国粮食连年丰收，粮食产量连续5年稳定在1.2万亿斤的台阶上，化肥的使用对粮食增长有重要作用，但不能说是化肥“喂”出来的。2017年，中国的化肥使用实现了负增长，提前三年实现了“十三五”目标，也就是化肥农药使用量的零增长");

            }else if (type==2){
                jsonObject.put("name","朱沙");
                jsonObject.put("headUrl","headUrl");
                jsonObject.put("sex","男");
                jsonObject.put("shentiStatus","正常");
                jsonObject.put("jianshenAihao","哑铃");
                jsonObject.put("xingquAihao","游泳");
                jsonObject.put("huifangType","生日回访");
                jsonObject.put("huifangReason","　　中新社北京3月5日电 (记者 唐贵江)农业部部长韩长赋5日在全国两会“部长通道”回答媒体记者提问时表示，近年来中国粮食连年丰收，粮食产量连续5年稳定在1.2万亿斤的台阶上，化肥的使用对粮食增长有重要作用，但不能说是化肥“喂”出来的。2017年，中国的化肥使用实现了负增长，提前三年实现了“十三五”目标，也就是化肥农药使用量的零增长");

            }
          for (int i = 0; i < 10; i++) {
                CoachHuiFangInfo coachHuiFangInfo = new CoachHuiFangInfo(jsonObject);
                coachHuiFangInfoList.add(coachHuiFangInfo);
            }
            LinearLayoutManager layoutmanager = new LinearLayoutManager(context);
            //设置RecyclerView 布局
            recyclerView.setLayoutManager(layoutmanager);
            coachHuiFangTaskAdapter = new CoachHuiFangTaskAdapter(context, coachHuiFangInfoList,type);
            recyclerView.setAdapter(coachHuiFangTaskAdapter);
        } catch (JSONException e) {
            Logger.i("TEST","JSONException: "+e);
        }
    }




}
