package com.yijian.staff.mvp.huiji.viperlist.fragment;

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
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.bean.HuiJiViperBean;
import com.yijian.staff.mvp.huiji.bean.TodayHuiJiViperBean;
import com.yijian.staff.mvp.huiji.viperlist.adapter.HuiJiTodayVisitAdapter;
import com.yijian.staff.mvp.huiji.viperlist.filter.HuijiViperFilterBean;
import com.yijian.staff.mvp.huiji.viperlist.adapter.HuijiViperListAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
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

public class HuijiTodayVisitFragment extends Fragment {

    SmartRefreshLayout refreshLayout;
    private RecyclerView rv_vip_all;
    private List<TodayHuiJiViperBean> viperBeanList = new ArrayList<>();
    private int pageNum = 1;//页码
    private int pageSize = 1;//每页数量
    private int pages;
    private HuijiViperFilterBean huijiViperFilterBean;


    private static HuijiTodayVisitFragment huijiTodayVisitFragment;
    private final HuiJiTodayVisitAdapter huijiViperListAdapter = new HuiJiTodayVisitAdapter(getActivity());

    public static HuijiTodayVisitFragment getInstance(){
        if(huijiTodayVisitFragment == null){
            huijiTodayVisitFragment = new HuijiTodayVisitFragment();
        }
        return huijiTodayVisitFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vip_huiji_today_info,container,false);
        initView(view);
        return view;
    }

    private void initView(View view){
        rv_vip_all = view.findViewById(R.id.rv_vip_all);
        refreshLayout = view.findViewById(R.id.refreshLayout);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
        //设置RecyclerView 布局
        rv_vip_all.setLayoutManager(layoutmanager);
        rv_vip_all.setAdapter(huijiViperListAdapter);
        initComponent();
        refresh(null);

        Disposable disposable = RxBus.getDefault().toDefaultFlowable(HuijiViperFilterBean.class, new Consumer<HuijiViperFilterBean>() {
            @Override
            public void accept(HuijiViperFilterBean filterBean) throws Exception {
                refresh(filterBean);
            }
        });
    }
    private void refresh(HuijiViperFilterBean huijiViperFilterBean) {
        viperBeanList.clear();
        pageNum=1;
        pageSize=10;

        this.huijiViperFilterBean = huijiViperFilterBean;
        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());

        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        if (huijiViperFilterBean != null) {

            if (huijiViperFilterBean.getJoinTimeType() != -2) {
                map.put("joinTimeType", huijiViperFilterBean.getJoinTimeType() + "");
            }
            if (huijiViperFilterBean.getExpiringDay() != -1) {
                map.put("expiringDay", huijiViperFilterBean.getExpiringDay() + "");
            }
            if (huijiViperFilterBean.getSex()!=-1) {
                map.put("sex", huijiViperFilterBean.getSex() + "");
            }
            if (huijiViperFilterBean.getCardType()!=-1) {
                map.put("cardType", huijiViperFilterBean.getCardType() + "");
            }
            if (huijiViperFilterBean.getPrivateCourseState()!=-1) {
                map.put("privateCourseState", huijiViperFilterBean.getPrivateCourseState() + "");
            }

            if (!TextUtils.isEmpty(huijiViperFilterBean.getStartTime())) {
                map.put("startTime", huijiViperFilterBean.getStartTime() + "");
            }
            if (!TextUtils.isEmpty(huijiViperFilterBean.getEndTime())) {
                map.put("endTime", huijiViperFilterBean.getEndTime() + "");
            }

        }

        HttpManager.getHuiJiTodayViperList(header, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                viperBeanList.clear();
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        TodayHuiJiViperBean viperBean = new TodayHuiJiViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    } catch (JSONException e) {


                    }
                }
                huijiViperListAdapter.update(viperBeanList);
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                huijiViperListAdapter.update(viperBeanList);


            }
        });
    }

    public void loadMore() {
        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());

        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        if (huijiViperFilterBean != null) {

            if (huijiViperFilterBean.getJoinTimeType() != -2) {
                map.put("joinTimeType", huijiViperFilterBean.getJoinTimeType() + "");
            }
            if (huijiViperFilterBean.getExpiringDay() != -1) {
                map.put("expiringDay", huijiViperFilterBean.getExpiringDay() + "");
            }
            if (huijiViperFilterBean.getSex()!=-1) {
                map.put("sex", huijiViperFilterBean.getSex() + "");
            }
            if (huijiViperFilterBean.getCardType()!=-1) {
                map.put("cardType", huijiViperFilterBean.getCardType() + "");
            }
            if (huijiViperFilterBean.getPrivateCourseState()!=-1) {
                map.put("privateCourseState", huijiViperFilterBean.getPrivateCourseState() + "");
            }
            if (huijiViperFilterBean.getPrivateCourseState()!=-1) {
                map.put("source", huijiViperFilterBean.getPrivateCourseState() + "");
            }
            if (!TextUtils.isEmpty(huijiViperFilterBean.getStartTime())) {
                map.put("startTime", huijiViperFilterBean.getStartTime() + "");
            }
            if (!TextUtils.isEmpty(huijiViperFilterBean.getEndTime())) {
                map.put("endTime", huijiViperFilterBean.getEndTime() + "");
            }

        }
        HttpManager.getHuiJiTodayViperList(header, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");

                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, true,  !hasMore);//传入false表示刷新失败

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        TodayHuiJiViperBean viperBean = new TodayHuiJiViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    } catch (JSONException e) {
                    }
                }
                huijiViperListAdapter.update(viperBeanList);
            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false,  !hasMore);//传入false表示刷新失败
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
