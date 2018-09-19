package com.yijian.clubmodule.ui.vipermanage.viper.viperlist.fragment;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.clubmodule.ui.vipermanage.viper.viperlist.adapter.HuijiViperListAdapter;
import com.yijian.clubmodule.ui.vipermanage.viper.viperlist.filter.HuijiViperFilterBean;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.db.bean.User;
import com.yijian.clubmodule.bean.HuiJiViperBean;
import com.yijian.commonlib.base.mvc.MvcBaseFragment;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.rx.RxBus;
import com.yijian.commonlib.util.CommonUtil;
import com.yijian.commonlib.util.JsonUtil;
import com.yijian.commonlib.widget.EmptyView;

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
 * 全部会员信息
 */

public class HuijiAllViperFragment extends MvcBaseFragment {

    RecyclerView rv_vip_all;
    SmartRefreshLayout refreshLayout;
    EmptyView empty_view;
    TextView tv_total_num;
    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量

    private int total;

    private HuijiViperListAdapter huijiViperListAdapter;
    private HuijiViperFilterBean huijiViperFilterBean;
    List<HuiJiViperBean> viperBeanList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_vip_huiji_all_info;
    }

    @Override
    public void initView() {
        rv_vip_all = getRootView().findViewById(R.id.rv);
        refreshLayout = getRootView().findViewById(R.id.refreshLayout);
        empty_view = getRootView().findViewById(R.id.empty_view);
        tv_total_num = getRootView().findViewById(R.id.tv_total_num);
        empty_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(null);
            }
        });
        String version = CommonUtil.getAccessStatisticsVersionName(getContext()) + " " + CommonUtil.getVersionCode(getContext());
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_formal_member",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        initView(getRootView());
        refresh(null);
    }

    private void initView(View view) {
        rv_vip_all = view.findViewById(R.id.rv);
        empty_view = view.findViewById(R.id.empty_view);
        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(huijiViperFilterBean);
            }
        });
        refreshLayout = view.findViewById(R.id.refreshLayout);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
        //设置RecyclerView 布局
        rv_vip_all.setLayoutManager(layoutmanager);

        huijiViperListAdapter = new HuijiViperListAdapter(getLifecycle(),getActivity(), viperBeanList);
        rv_vip_all.setAdapter(huijiViperListAdapter);


        initComponent();

        Disposable disposable = RxBus.getDefault().toDefaultFlowable(HuijiViperFilterBean.class, new Consumer<HuijiViperFilterBean>() {
            @Override
            public void accept(HuijiViperFilterBean filterBean) throws Exception {
                refresh(filterBean);
            }
        });


    }

    private void refresh(HuijiViperFilterBean huijiViperFilterBean) {
        pageNum = 1;
        pageSize = 10;
        viperBeanList.clear();
        empty_view.setVisibility(View.GONE);

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
            if (huijiViperFilterBean.getSex() != -1) {
                map.put("sex", huijiViperFilterBean.getSex() + "");
            }
            if (huijiViperFilterBean.getCardType() != -1) {
                map.put("cardType", huijiViperFilterBean.getCardType() + "");
            }
            if (huijiViperFilterBean.getPrivateCourseState() != -1) {
                map.put("privateCourseState", huijiViperFilterBean.getPrivateCourseState() + "");
            }

            if (!TextUtils.isEmpty(huijiViperFilterBean.getStartTime())) {
                map.put("startTime", huijiViperFilterBean.getStartTime() + "");
            }
            if (!TextUtils.isEmpty(huijiViperFilterBean.getEndTime())) {
                map.put("endTime", huijiViperFilterBean.getEndTime() + "");
            }

        }
        showLoading();
        HttpManager.getHuiJiAllViperList(header, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                refreshLayout.finishRefresh(2000, true);

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                total = JsonUtil.getInt(result, "total");

                if (total==-1){
                    total=0;
                }
                tv_total_num.setText("会员总人数："+total+"人");
                viperBeanList.clear();
                JSONArray records = JsonUtil.getJsonArray(result, "records");

                try {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        HuiJiViperBean viperBean = new HuiJiViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    }
                    huijiViperListAdapter.notifyDataSetChanged();

                    if (viperBeanList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                hideLoading();
                huijiViperListAdapter.notifyDataSetChanged();

                if (viperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    public void loadMore() {
        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());
        empty_view.setVisibility(View.GONE);

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
            if (huijiViperFilterBean.getSex() != -1) {
                map.put("sex", huijiViperFilterBean.getSex() + "");
            }
            if (huijiViperFilterBean.getCardType() != -1) {
                map.put("cardType", huijiViperFilterBean.getCardType() + "");
            }
            if (huijiViperFilterBean.getPrivateCourseState() != -1) {
                map.put("privateCourseState", huijiViperFilterBean.getPrivateCourseState() + "");
            }
            if (huijiViperFilterBean.getPrivateCourseState() != -1) {
                map.put("source", huijiViperFilterBean.getPrivateCourseState() + "");
            }
            if (!TextUtils.isEmpty(huijiViperFilterBean.getStartTime())) {
                map.put("startTime", huijiViperFilterBean.getStartTime() + "");
            }
            if (!TextUtils.isEmpty(huijiViperFilterBean.getEndTime())) {
                map.put("endTime", huijiViperFilterBean.getEndTime() + "");
            }

        }
        showLoading();
        HttpManager.getHuiJiAllViperList(header, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;


                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        HuiJiViperBean viperBean = new HuiJiViperBean(jsonObject);
                        viperBeanList.add(viperBean);
                    } catch (JSONException e) {
                    }
                }
                huijiViperListAdapter.notifyDataSetChanged();

                if (viperBeanList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(String msg) {
                hideLoading();


                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                huijiViperListAdapter.notifyDataSetChanged();
                if (viperBeanList.size() == 0) {
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
                refresh(null);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

}
