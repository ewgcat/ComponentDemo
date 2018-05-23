package com.yijian.staff.mvp.huiji.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.db.bean.SearchKey;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.coach.search.CoachSearchActivity;
import com.yijian.staff.bean.HuiJiViperBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
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
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.util.SystemUtil;
import com.yijian.staff.widget.EmptyView;

public class HuiJiSearchActivity extends MvcBaseActivity {

    private static final String TAG = CoachSearchActivity.class.getSimpleName();
    @BindView(R.id.top_view)
    LinearLayout top_view;
    @BindView(R.id.lin_search_container)
    LinearLayout lin_search_container; //搜索面板的容器

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv)
    RecyclerView rcl;
    @BindView(R.id.empty_view)
    EmptyView empty_view;
    @BindView(R.id.rcl_search)
    RecyclerView rcl_search;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int pageNum = 1;
    private int pageSize = 10;
    private int pages;
    private List<HuiJiViperBean> viperBeanList = new ArrayList<HuiJiViperBean>();
    private HuiJiVipSearchAdapter adapter;
    private SearchKeyAdapter searchKeyAdapter;
    private List<SearchKey> searchList;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_hui_ji_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rcl_search.setLayoutManager(new LinearLayoutManager(this));
        searchKeyAdapter = new SearchKeyAdapter(this, searchList);
        searchKeyAdapter.setClickKeyListener(new SearchKeyAdapter.ClickKeyListener() {
            @Override
            public void onClick(SearchKey searchKey) {
                refresh(searchKey.getKey());
                clearEditTextFocus();
            }
        });
        searchKeyAdapter.setClearKeyListener(new SearchKeyAdapter.ClearKeyListener() {
            @Override
            public void onClick() {
                DBManager.getInstance().clearSearchList();
                initSearchData();
            }
        });
        rcl_search.setAdapter(searchKeyAdapter);
        lin_search_container.setVisibility(View.GONE);

        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etSearch.getText().toString().trim();
                refresh(name);
            }
        });

        etSearch.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    // 获得焦点
                    lin_search_container.setVisibility(View.VISIBLE);
                    initSearchData();
                } else {
                    // 失去焦点
                    lin_search_container.setVisibility(View.GONE);
                }

            }


        });

        etSearch.setHintTextColor(Color.parseColor("#666666"));

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        clearEditTextFocus();
                        String name = etSearch.getText().toString().trim();
                        refresh(name);
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

                String name = etSearch.getText().toString().trim();
                refresh(name);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

    private void refresh(String name) {
        Map<String, String> header = new HashMap<>();
        Map<String, String> params = new HashMap<>();


        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show();
            refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败

            return;
        } else {
            pageNum = 1;
            viperBeanList.clear();
            empty_view.setVisibility(View.GONE);
            params.put("name", name);
            params.put("pageNum", pageNum + "");
            params.put("pageSize", pageSize + "");
            viperBeanList.clear();
            showLoading();
            empty_view.setVisibility(View.GONE);
            HttpManager.searchViperByHuiJi(params, new ResultJSONObjectObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    hideLoading();

                    SearchKey searchKey = new SearchKey(null, etSearch.getText().toString(), SharePreferenceUtil.getUserRole() + "");
                    DBManager.getInstance().insertOrReplaceSearch(searchKey);
                    clearEditTextFocus();

                    refreshLayout.finishRefresh(2000, true);
                    viperBeanList.clear();

                    pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                    pages = JsonUtil.getInt(result, "pages");
                    JSONArray records = JsonUtil.getJsonArray(result, "records");

                    try {
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject jsonObject = (JSONObject) records.get(i);
                            HuiJiViperBean huiJiSearchViperBean = new HuiJiViperBean(jsonObject);
                            viperBeanList.add(huiJiSearchViperBean);
                        }
                        adapter.notifyDataSetChanged();

                        if (viperBeanList.size() == 0) {
                            empty_view.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        Logger.i(TAG, e.toString());
                    }


                }

                @Override
                public void onFail(String msg) {
                    hideLoading();
                    clearEditTextFocus();
                    refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                    adapter.notifyDataSetChanged();
                    if (viperBeanList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }

                }
            });
        }

    }

    private void loadMore() {

        Map<String, String> params = new HashMap<>();

        String name = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show();


            refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
            return;
        } else {
            params.put("name", name);
            params.put("pageNum", pageNum + "");
            params.put("pageSize", pageSize + "");
            showLoading();
            empty_view.setVisibility(View.GONE);

            HttpManager.searchViperByCoach(params, new ResultJSONObjectObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    hideLoading();
                    clearEditTextFocus();
                    pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                    pages = JsonUtil.getInt(result, "pages");


                    refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                    JSONArray records = JsonUtil.getJsonArray(result, "records");
                    try {
                        for (int i = 0; i < records.length(); i++) {

                            JSONObject jsonObject = (JSONObject) records.get(i);
                            HuiJiViperBean viperBean = new HuiJiViperBean(jsonObject);
                            viperBeanList.add(viperBean);

                        }
                        adapter.notifyDataSetChanged();
                        if (viperBeanList.size() == 0) {
                            empty_view.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {


                    }
                }

                @Override
                public void onFail(String msg) {
                    hideLoading();
                    clearEditTextFocus();

                    refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                    adapter.notifyDataSetChanged();
                    if (viperBeanList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }

                }
            });
        }

    }

    public void initSearchData() {
        searchList = DBManager.getInstance().querySearchList();
        Logger.i("TEST", "" + searchList.size());
        if (searchList != null && searchList.size() > 0) {
            searchKeyAdapter.update(searchList);
            lin_search_container.setVisibility(View.VISIBLE);
        } else {
            lin_search_container.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                SystemUtil.hideKeyBoard(etSearch, this);
                finish();
                break;

        }
    }

    public void clearEditTextFocus() {
        lin_search_container.setVisibility(View.GONE);
        etSearch.clearFocus();
        rcl.requestFocus();
        SystemUtil.hideKeyBoard(etSearch, this);
    }

}