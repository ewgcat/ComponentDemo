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
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.bean.CoachViperBean;
import com.yijian.staff.mvp.coach.huifang.bean.CoachHuiFangInfo;
import com.yijian.staff.mvp.coach.huifang.task.adapter.CoachHuiFangTaskAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;

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
public class CoachBaseHuiFangTaskFragment extends Fragment {


    private RefreshLayout refreshLayout;
    private List<CoachHuiFangInfo> coachHuiFangInfoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CoachHuiFangTaskAdapter coachHuiFangTaskAdapter;
    private Context context;
    protected int viewId;
    private int type;
    private int defaultViewId = R.layout.common_hui_fang_task;
    private int pageNum = 1;//页码
    private int pageSize = 2;//每页数量
    private int pages;


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
        refresh();
    }


    public void initComponent(View view) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
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
                refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });

        recyclerView = view.findViewById(R.id.rlv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        coachHuiFangTaskAdapter=new CoachHuiFangTaskAdapter(context,coachHuiFangInfoList,type);
        recyclerView.setAdapter(coachHuiFangTaskAdapter);
    }

    public void refresh() {
        pageNum = 1;
        pageSize = 2;
        coachHuiFangInfoList.clear();
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");
        params.put("type", type + "");
        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_HUI_FANG_TASK_URL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);


                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachHuiFangInfo coachViperBean = new CoachHuiFangInfo(jsonObject);
                        coachHuiFangInfoList.add(coachViperBean);
                    } catch (JSONException e) {


                    }
                }
                coachHuiFangTaskAdapter.update(coachHuiFangInfoList);
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);
            }
        });
    }

    public void loadMore() {

        HashMap<String, String> params = new HashMap<>();
        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");
        params.put("type", type + "");
        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_HUI_FANG_TASK_URL, params, new ResultJSONObjectObserver() {
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
                        CoachHuiFangInfo coachViperBean = new CoachHuiFangInfo(jsonObject);
                        coachHuiFangInfoList.add(coachViperBean);
                    } catch (JSONException e) {


                    }
                }
                coachHuiFangTaskAdapter.update(coachHuiFangInfoList);
            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
