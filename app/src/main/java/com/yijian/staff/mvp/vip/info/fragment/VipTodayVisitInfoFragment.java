package com.yijian.staff.mvp.vip.info.fragment;

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
import com.yijian.staff.mvp.vip.filter.ViperFilterBean;
import com.yijian.staff.mvp.vip.info.VipPeopleInfoAdapter;
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

public class VipTodayVisitInfoFragment extends Fragment {

    SmartRefreshLayout refreshLayout;
    private RecyclerView rv_vip_all;
    private List<ViperBean> viperBeanList = new ArrayList<>();
    private int pageNum = 1;//页码
    private int pageSize = 1;//每页数量
    private int pages;
    private ViperFilterBean viperFilterBean;


    private static VipTodayVisitInfoFragment vipTodayVisitInfoFragment;
    private final VipPeopleInfoAdapter vipPeopleInfoAdapter = new VipPeopleInfoAdapter(getActivity(), viperBeanList,false);

    public static VipTodayVisitInfoFragment getInstance(){
        if(vipTodayVisitInfoFragment == null){
            vipTodayVisitInfoFragment = new VipTodayVisitInfoFragment();
        }
        return vipTodayVisitInfoFragment;
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
        rv_vip_all.setAdapter(vipPeopleInfoAdapter);
        initComponent();
        refresh(null);

        Disposable disposable = RxBus.getDefault().toDefaultFlowable(ViperFilterBean.class, new Consumer<ViperFilterBean>() {
            @Override
            public void accept(ViperFilterBean filterBean) throws Exception {
                refresh(filterBean);
            }
        });
    }
    private void refresh(ViperFilterBean viperFilterBean) {

        this.viperFilterBean = viperFilterBean;
        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());

        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", 1 + "");
        map.put("pageSize", 1 + "");
        if (viperFilterBean != null) {

            if (viperFilterBean.getJoinTimeType() != -2) {
                map.put("joinTimeType", viperFilterBean.getJoinTimeType() + "");
            }
            if (viperFilterBean.getExpiringDay() != -1) {
                map.put("expiringDay", viperFilterBean.getExpiringDay() + "");
            }
            if (viperFilterBean.getSex()!=-1) {
                map.put("sex", viperFilterBean.getSex() + "");
            }
            if (viperFilterBean.getCardType()!=-1) {
                map.put("cardType", viperFilterBean.getCardType() + "");
            }
            if (viperFilterBean.getPrivateCourseState()!=-1) {
                map.put("privateCourseState", viperFilterBean.getPrivateCourseState() + "");
            }

            if (!TextUtils.isEmpty(viperFilterBean.getStartTime())) {
                map.put("startTime", viperFilterBean.getStartTime() + "");
            }
            if (!TextUtils.isEmpty(viperFilterBean.getEndTime())) {
                map.put("endTime", viperFilterBean.getEndTime() + "");
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
                vipPeopleInfoAdapter.update(viperBeanList);
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
        if (viperFilterBean != null) {

            if (viperFilterBean.getJoinTimeType() != -2) {
                map.put("joinTimeType", viperFilterBean.getJoinTimeType() + "");
            }
            if (viperFilterBean.getExpiringDay() != -1) {
                map.put("expiringDay", viperFilterBean.getExpiringDay() + "");
            }
            if (viperFilterBean.getSex()!=-1) {
                map.put("sex", viperFilterBean.getSex() + "");
            }
            if (viperFilterBean.getCardType()!=-1) {
                map.put("cardType", viperFilterBean.getCardType() + "");
            }
            if (viperFilterBean.getPrivateCourseState()!=-1) {
                map.put("privateCourseState", viperFilterBean.getPrivateCourseState() + "");
            }
            if (viperFilterBean.getPrivateCourseState()!=-1) {
                map.put("source", viperFilterBean.getPrivateCourseState() + "");
            }
            if (!TextUtils.isEmpty(viperFilterBean.getStartTime())) {
                map.put("startTime", viperFilterBean.getStartTime() + "");
            }
            if (!TextUtils.isEmpty(viperFilterBean.getEndTime())) {
                map.put("endTime", viperFilterBean.getEndTime() + "");
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
                vipPeopleInfoAdapter.update(viperBeanList);
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
