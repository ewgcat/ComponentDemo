package com.yijian.staff.mvp.vip.coach.viperlist.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.ViperBean;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.vip.coach.viperlist.CoachViperListAdapter;
import com.yijian.staff.mvp.vip.coach.viperlist.filter.CoachViperFilterBean;
import com.yijian.staff.mvp.vip.huiji.viperlist.filter.HuijiViperFilterBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.rx.RxBus;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by yangk on 2018/3/6.
 * 今日来访
 */

public class CoachVipTodayVisitFragment extends Fragment {

    SmartRefreshLayout refreshLayout;
    private RecyclerView rv_vip_all;
    private List<ViperBean> viperBeanList = new ArrayList<>();
    private int pageNum = 1;//页码
    private int pageSize = 1;//每页数量
    private int pages;
    private CoachViperFilterBean coachViperFilterBean;


    private static CoachVipTodayVisitFragment coachVipTodayVisitFragment;
    private  CoachViperListAdapter coachViperListAdapter;

    public static CoachVipTodayVisitFragment getInstance(){
        if(coachVipTodayVisitFragment == null){
            coachVipTodayVisitFragment = new CoachVipTodayVisitFragment();
        }
        return coachVipTodayVisitFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vip_all_people_info,container,false);
        initView(view);
        return view;
    }

    private void initView(View view){
        rv_vip_all = view.findViewById(R.id.rv_vip_all);
        refreshLayout = view.findViewById(R.id.refreshLayout);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
        //设置RecyclerView 布局
        rv_vip_all.setLayoutManager(layoutmanager);
        coachViperListAdapter = new CoachViperListAdapter(getActivity(), viperBeanList,false);
        rv_vip_all.setAdapter(coachViperListAdapter);
        initComponent();
        refresh(null);
        initData();
        Disposable disposable = RxBus.getDefault().toDefaultFlowable(CoachViperFilterBean.class, new Consumer<CoachViperFilterBean>() {
            @Override
            public void accept(CoachViperFilterBean filterBean) throws Exception {
                refresh(filterBean);
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            viperBeanList.add(new ViperBean(new JSONObject()));
        }
        coachViperListAdapter.update(viperBeanList);

    }

    private void refresh(CoachViperFilterBean coachViperFilterBean) {

        this.coachViperFilterBean = coachViperFilterBean;
        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());

        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", 1 + "");
        map.put("pageSize", 1 + "");
        if (coachViperFilterBean != null) {

            if (coachViperFilterBean.getJoinTimeType() != -2) {
                map.put("joinTimeType", coachViperFilterBean.getJoinTimeType() + "");
            }
            if (coachViperFilterBean.getExpiringDay() != -1) {
                map.put("expiringDay", coachViperFilterBean.getExpiringDay() + "");
            }
            if (coachViperFilterBean.getSex()!=-1) {
                map.put("sex", coachViperFilterBean.getSex() + "");
            }
            if (coachViperFilterBean.getClassType()!=-1) {
                map.put("classType", coachViperFilterBean.getClassType() + "");
            }
            if (coachViperFilterBean.getBuyClassTime()!=-1) {
                map.put("buyClassTime", coachViperFilterBean.getBuyClassTime() + "");
            }

            if (!TextUtils.isEmpty(coachViperFilterBean.getStartTime())) {
                map.put("startTime", coachViperFilterBean.getStartTime() + "");
            }
            if (!TextUtils.isEmpty(coachViperFilterBean.getEndTime())) {
                map.put("endTime", coachViperFilterBean.getEndTime() + "");
            }

        }

        HttpManager.getTodayViperList(header, map, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);

                viperBeanList.clear();
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        ViperBean viperBean = new ViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    } catch (JSONException e) {


                    }
                }
                coachViperListAdapter.update(viperBeanList);
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadMore() {
        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());

        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        if (coachViperFilterBean != null) {
            if (coachViperFilterBean.getJoinTimeType() != -2) {
                map.put("joinTimeType", coachViperFilterBean.getJoinTimeType() + "");
            }
            if (coachViperFilterBean.getExpiringDay() != -1) {
                map.put("expiringDay", coachViperFilterBean.getExpiringDay() + "");
            }
            if (coachViperFilterBean.getSex()!=-1) {
                map.put("sex", coachViperFilterBean.getSex() + "");
            }
            if (coachViperFilterBean.getClassType()!=-1) {
                map.put("classType", coachViperFilterBean.getClassType() + "");
            }
            if (coachViperFilterBean.getBuyClassTime()!=-1) {
                map.put("buyClassTime", coachViperFilterBean.getBuyClassTime() + "");
            }

            if (!TextUtils.isEmpty(coachViperFilterBean.getStartTime())) {
                map.put("startTime", coachViperFilterBean.getStartTime() + "");
            }
            if (!TextUtils.isEmpty(coachViperFilterBean.getEndTime())) {
                map.put("endTime", coachViperFilterBean.getEndTime() + "");
            }


        }
        HttpManager.getTodayViperList(header, map, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");

                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        ViperBean viperBean = new ViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    } catch (JSONException e) {
                    }
                }
                coachViperListAdapter.update(viperBeanList);
            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initComponent() {


        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh(null);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }



}
