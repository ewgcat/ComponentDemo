package com.yijian.staff.mvp.huifang.task.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.HuiFangInfo;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.huifang.task.adapter.HuiFangTaskAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.huifang.HuifangTaskRequestBody;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 19:39:24
 */
@SuppressLint("ValidFragment")
public class BaseHuiFangTaskFragment extends MvcBaseFragment {


    private RefreshLayout refreshLayout;
    private List<HuiFangInfo> huiFangInfoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HuiFangTaskAdapter huiFangTaskAdapter;
    private Context context;
    protected int viewId;
    private int menu;
    private int defaultViewId = R.layout.common_hui_fang_task;
    private int offset = 1;//页码
    private int pageSize = 10;//每页数量
    private int pages;


    public BaseHuiFangTaskFragment(Context context, int menu) {
        this.context = context;
        this.viewId = defaultViewId;
        this.menu = menu;
    }

    public BaseHuiFangTaskFragment() {
        super();
    }


    @Override
    public int getLayoutId() {
        return defaultViewId;
    }

    @Override
    public void initView() {
        initComponent(rootView);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    public void initComponent(View view) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });

        recyclerView = view.findViewById(R.id.rlv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        huiFangTaskAdapter = new HuiFangTaskAdapter(context, huiFangInfoList, menu);
        recyclerView.setAdapter(huiFangTaskAdapter);
    }

    public void refresh() {
        offset = 0;
        pageSize = 10;
        huiFangInfoList.clear();
        showLoading();

        HuifangTaskRequestBody huifangTaskRequestBody = new HuifangTaskRequestBody();
        huifangTaskRequestBody.setChief(true);
        huifangTaskRequestBody.setMenu(menu);
        huifangTaskRequestBody.setOffset(offset);
        huifangTaskRequestBody.setSize(pageSize);
        HttpManager.postHuiFangTask(HttpManager.HUI_FANG_TASK_URL, huifangTaskRequestBody, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {
                refreshLayout.finishRefresh(2000, true);



                List<HuiFangInfo> list = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), HuiFangInfo.class);
                huiFangInfoList.addAll(list);


                huiFangTaskAdapter.update(huiFangInfoList);
                hideLoading();
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);
                hideLoading();
            }
        });
   
    }

    public void loadMore() {

        showLoading();
        offset=huiFangInfoList.size();
        HuifangTaskRequestBody huifangTaskRequestBody = new HuifangTaskRequestBody();
        huifangTaskRequestBody.setChief(true);
        huifangTaskRequestBody.setMenu(menu);
        huifangTaskRequestBody.setOffset(offset);
        huifangTaskRequestBody.setSize(pageSize);
        HttpManager.postHuiFangTask(HttpManager.HUI_FANG_TASK_URL, huifangTaskRequestBody, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {


                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

                List<HuiFangInfo> list = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), HuiFangInfo.class);
                huiFangInfoList.addAll(list);
                huiFangTaskAdapter.update(huiFangInfoList);

                hideLoading();
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                hideLoading();
            }
        });

    }


}
