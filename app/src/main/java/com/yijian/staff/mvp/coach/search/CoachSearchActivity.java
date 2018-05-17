package com.yijian.staff.mvp.coach.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.CoachSearchViperBean;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.SearchKey;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.huiji.search.SearchKeyAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.util.SystemUtil;
import com.yijian.staff.widget.EmptyView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class CoachSearchActivity extends MvcBaseActivity {

    private static final String TAG = CoachSearchActivity.class.getSimpleName();
    @BindView(R.id.top_view)
    LinearLayout top_view;

    @BindView(R.id.lin_search_container)
    LinearLayout lin_search_container; //搜索面板的容器
    @BindView(R.id.rcl_search)
    RecyclerView rcl_search;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv)
    RecyclerView rcl;
    @BindView(R.id.empty_view)
    EmptyView empty_view;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int pageNum = 1;
    private int pageSize = 10;
    private int pages;
    private List<CoachSearchViperBean> viperBeanList = new ArrayList<>();
    private CoachSearchViperListAdapter adapter;
    private SearchKeyAdapter searchKeyAdapter;
    private List<SearchKey> searchList;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_coach_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initComponent();

    }


    public void initComponent() {

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

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rcl.setLayoutManager(layoutmanager);

        //TODO 设置适配器
        adapter = new CoachSearchViperListAdapter(this, viperBeanList);
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
        searchKeyAdapter.setRemoveKeyListener(new SearchKeyAdapter.RemoveKeyListener() {
            @Override
            public void onClick(SearchKey searchKey) {
                DBManager.getInstance().insertOrReplaceSearch(searchKey);
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
    }

    private void refresh(String name) {
        pageNum = 1;
        pageSize = 10;
        viperBeanList.clear();
        Map<String, String> params = new HashMap<>();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show();
            refreshLayout.finishRefresh(2000, true);

            return;
        } else {
            params.put("name", name);
            params.put("pageNum", pageNum + "");
            params.put("pageSize", pageSize + "");
            showBlueProgress();
            HttpManager.searchViperByCoach(params, new ResultJSONObjectObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    hideBlueProgress();

                    SearchKey searchKey = new SearchKey(null, etSearch.getText().toString(), SharePreferenceUtil.getUserRole() + "");
                    DBManager.getInstance().insertOrReplaceSearch(searchKey);
                    clearEditTextFocus();
                    viperBeanList.clear();


                    refreshLayout.finishRefresh(2000, true);

                    pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                    pages = JsonUtil.getInt(result, "pages");
                    JSONArray records = JsonUtil.getJsonArray(result, "records");

                    try {
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject jsonObject = (JSONObject) records.get(i);
                            CoachSearchViperBean viperBean = new CoachSearchViperBean(jsonObject);
                            viperBeanList.add(viperBean);

                        }
                        adapter.update(viperBeanList);
                        if (viperBeanList.size() == 0) {
                            empty_view.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        Logger.i(TAG, e.toString());
                    }

                }

                @Override
                public void onFail(String msg) {
                    hideBlueProgress();
                    clearEditTextFocus();
                    refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                    showToast(msg);
                    adapter.update(viperBeanList);


                }
            });
        }

    }

    private void loadMore() {

        Map<String, String> params = new HashMap<>();

        String name = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show();
            boolean hasMore = pages > pageNum ? true : false;
            refreshLayout.finishLoadMore(2000, true, !hasMore);
            return;
        } else {
            params.put("name", name);
            params.put("pageNum", pageNum + "");
            params.put("pageSize", pageSize + "");
            showBlueProgress();
            empty_view.setVisibility(View.GONE);

            HttpManager.searchViperByCoach(params, new ResultJSONObjectObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    hideBlueProgress();
                    clearEditTextFocus();
                    pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                    pages = JsonUtil.getInt(result, "pages");

                    boolean hasMore = pages > pageNum ? true : false;
                    refreshLayout.finishLoadMore(2000, true, !hasMore);//传入false表示刷新失败
                    JSONArray records = JsonUtil.getJsonArray(result, "records");
                    try {
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject jsonObject = (JSONObject) records.get(i);
                            CoachSearchViperBean viperBean = new CoachSearchViperBean(jsonObject);
                            viperBeanList.add(viperBean);
                        }
                        adapter.update(viperBeanList);
                        if (viperBeanList.size() == 0) {
                            empty_view.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        Logger.i(TAG, e.toString());
                    }

                }

                @Override
                public void onFail(String msg) {
                    hideBlueProgress();
                    clearEditTextFocus();
                    boolean hasMore = pages > pageNum ? true : false;
                    refreshLayout.finishLoadMore(2000, false, !hasMore);//传入false表示刷新失败
                    showToast(msg);
                    adapter.update(viperBeanList);
                    if (viperBeanList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

    }


    public void initSearchData() {
        searchList = DBManager.getInstance().querySearchList();
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
        SystemUtil.hideKeyBoard(etSearch, this);
        etSearch.clearFocus();
        rcl.requestFocus();
    }


}
