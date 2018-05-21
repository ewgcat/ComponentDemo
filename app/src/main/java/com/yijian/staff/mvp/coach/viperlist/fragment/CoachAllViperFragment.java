package com.yijian.staff.mvp.coach.viperlist.fragment;

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
import com.yijian.staff.bean.CoachViperBean;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.coach.viperlist.adpater.CoachViperListAdapter;
import com.yijian.staff.mvp.coach.viperlist.filter.CoachViperFilterBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.rx.RxBus;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.EmptyView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by yangk on 2018/3/6.
 * 全部会员信息
 */

public class CoachAllViperFragment extends MvcBaseFragment {


    @BindView(R.id.rv)
    RecyclerView rv_vip_all;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.empty_view)
    EmptyView empty_view;

    private List<CoachViperBean> coachViperBeanList = new ArrayList<>();
    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量
    private int pages;

    private CoachViperListAdapter coachViperListAdapter;
    private CoachViperFilterBean coachViperFilterBean;



    @Override
    public int getLayoutId() {
        return R.layout.fragment_vip_all_people_info;
    }

    @Override
    public void initView() {
        initView(rootView);
        refresh(null);
    }


    private void initView(View view) {

        LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
        //设置RecyclerView 布局
        rv_vip_all.setLayoutManager(layoutmanager);

        coachViperListAdapter = new CoachViperListAdapter(getActivity(), coachViperBeanList, true);
        rv_vip_all.setAdapter(coachViperListAdapter);


        initComponent();

        Disposable disposable = RxBus.getDefault().toDefaultFlowable(CoachViperFilterBean.class, new Consumer<CoachViperFilterBean>() {
            @Override
            public void accept(CoachViperFilterBean filterBean) throws Exception {
                refresh(filterBean);
            }
        });

        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(coachViperFilterBean);
            }
        });
    }

    private void refresh(CoachViperFilterBean coachViperFilterBean) {
        showBlueProgress();
        empty_view.setVisibility(View.GONE);
        coachViperBeanList.clear();
        pageNum = 1;
        pageSize = 10;
        this.coachViperFilterBean = coachViperFilterBean;
        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());

        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        if (coachViperFilterBean != null) {

            if (coachViperFilterBean.getJoinTimeType() != -2) {
                map.put("joinTimeType", coachViperFilterBean.getJoinTimeType());
            }
            if (coachViperFilterBean.getExpiringDay() != -1) {
                map.put("expiringDay", coachViperFilterBean.getExpiringDay());
            }
            if (coachViperFilterBean.getSex() != -1) {
                map.put("sex", coachViperFilterBean.getSex());
            }
            if (coachViperFilterBean.getCourseType() != null) {
                map.put("courseType", coachViperFilterBean.getCourseType());
            }
            if (coachViperFilterBean.getBuyTime() != -1) {
                map.put("buyTime", coachViperFilterBean.getBuyTime());
            }
            if (!TextUtils.isEmpty(coachViperFilterBean.getStartTime())) {
                map.put("startTime", coachViperFilterBean.getStartTime());
            }
            if (!TextUtils.isEmpty(coachViperFilterBean.getEndTime())) {
                map.put("endTime", coachViperFilterBean.getEndTime());
            }


        }

        HttpManager.getCoachAllViperList(header, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideBlueProgress();
                refreshLayout.finishRefresh(2000, true);

                coachViperBeanList.clear();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachViperBean coachViperBean = new CoachViperBean(jsonObject);
                        coachViperBeanList.add(coachViperBean);
                    }
                    coachViperListAdapter.update(coachViperBeanList);
                    if (coachViperBeanList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {


                }

            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                hideBlueProgress();
                coachViperListAdapter.update(coachViperBeanList);
                if (coachViperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    public void loadMore() {
        showBlueProgress();
        empty_view.setVisibility(View.GONE);

        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());

        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        if (coachViperFilterBean != null) {
            if (coachViperFilterBean.getJoinTimeType() != -2) {
                map.put("joinTimeType", coachViperFilterBean.getJoinTimeType());
            }
            if (coachViperFilterBean.getExpiringDay() != -1) {
                map.put("expiringDay", coachViperFilterBean.getExpiringDay());
            }
            if (coachViperFilterBean.getSex() != -1) {
                map.put("sex", coachViperFilterBean.getSex());
            }
            if (coachViperFilterBean.getCourseType() != null) {
                map.put("courseType", coachViperFilterBean.getCourseType());
            }
            if (coachViperFilterBean.getBuyTime() != -1) {
                map.put("buyTime", coachViperFilterBean.getBuyTime());
            }

            if (!TextUtils.isEmpty(coachViperFilterBean.getStartTime())) {
                map.put("startTime", coachViperFilterBean.getStartTime());
            }
            if (!TextUtils.isEmpty(coachViperFilterBean.getEndTime())) {
                map.put("endTime", coachViperFilterBean.getEndTime());
            }


        }
        HttpManager.getCoachAllViperList(header, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideBlueProgress();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");


                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachViperBean coachViperBean = new CoachViperBean(jsonObject);
                        coachViperBeanList.add(coachViperBean);
                    } catch (JSONException e) {
                    }
                }
                coachViperListAdapter.update(coachViperBeanList);
                if (coachViperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(String msg) {
                hideBlueProgress();

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                coachViperListAdapter.update(coachViperBeanList);
                if (coachViperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
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
                refresh(coachViperFilterBean);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

}
