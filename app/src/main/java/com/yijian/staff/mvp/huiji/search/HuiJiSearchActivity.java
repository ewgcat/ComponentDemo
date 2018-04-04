package com.yijian.staff.mvp.huiji.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.search.CoachSearchActivity;
import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;

public class HuiJiSearchActivity extends AppCompatActivity {

    private static final String TAG = CoachSearchActivity.class.getSimpleName();
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rcl)
    RecyclerView rcl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int pageNum = 1;
    private int pageSize = 1;
    private int pages;
    private List<HuiJiViperBean> viperBeanList = new ArrayList<HuiJiViperBean>();
    private HuiJiVipSearchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_ji_search);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        etSearch.setHintTextColor(Color.parseColor("#666666"));

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        refresh();
                        break;
                }
                return true;
            }
        });
        initComponent();
    }

    public void initComponent() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rcl.setLayoutManager(layoutmanager);

        //TODO 设置适配器
        adapter = new HuiJiVipSearchAdapter(this, viperBeanList);
        rcl.setAdapter(adapter);

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
                pageNum = 1;
                pageSize = 1;
                viperBeanList.clear();
                refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

    private void refresh() {
        Map<String, String> header = new HashMap<>();
        Map<String, String> params = new HashMap<>();


        String name = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show();
            return;
        } else {
            params.put("name", name);
            params.put("pageNum", pageNum + "");
            params.put("pageSize", pageSize + "");

            HttpManager.searchViperByHuiJi( params, new ResultObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    refreshLayout.finishRefresh(2000, true);

                    pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                    pages = JsonUtil.getInt(result, "pages");
                    JSONArray records = JsonUtil.getJsonArray(result, "records");
                    for (int i = 0; i < records.length(); i++) {
                        try {
                            JSONObject jsonObject = (JSONObject) records.get(i);
                            HuiJiViperBean huiJiSearchViperBean = new HuiJiViperBean(jsonObject);
                            viperBeanList.add(huiJiSearchViperBean);
                        } catch (JSONException e) {
                            Logger.i(TAG, e.toString());
                        }
                    }

                    adapter.update(viperBeanList);


                }

                @Override
                public void onFail(String msg) {
                    refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                    Toast.makeText(HuiJiSearchActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void loadMore() {

        Map<String, String> params = new HashMap<>();

        String name = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show();
            return;
        } else {
            params.put("name", name);
            params.put("pageNum", pageNum + "");
            params.put("pageSize", pageSize + "");

            HttpManager.searchViperByCoach( params, new ResultObserver() {
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
                            HuiJiViperBean viperBean = new HuiJiViperBean(jsonObject);
                            viperBeanList.add(viperBean);
                        } catch (JSONException e) {


                        }
                    }
                    adapter.update(viperBeanList);
                }

                @Override
                public void onFail(String msg) {
                    boolean hasMore = pages > pageNum ? true : false;
                    refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                    Toast.makeText(HuiJiSearchActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        finish();
    }
}