package com.yijian.clubmodule.ui.vipermanage.search;

import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.db.ClubDBManager;
import com.yijian.clubmodule.db.bean.SearchKey;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.bean.HuiJiViperBean;
import com.yijian.clubmodule.net.httpmanager.HttpManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.util.JsonUtil;
import com.yijian.commonlib.util.Logger;
import com.yijian.commonlib.util.SystemUtil;
import com.yijian.commonlib.widget.EmptyView;

public class HuiJiSearchActivity extends MvcBaseActivity {

    private static final String TAG = HuiJiSearchActivity.class.getSimpleName();
    LinearLayout lin_search_container; //搜索面板的容器

    EditText etSearch;
    EmptyView empty_view;
    RecyclerView rv;
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
        etSearch = findViewById(R.id.et_search);
        rv = findViewById(R.id.rv);
        refreshLayout = findViewById(R.id.refreshLayout);
        empty_view = findViewById(R.id.empty_view);
        lin_search_container = findViewById(R.id.lin_search_container);
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemUtil.hideKeyBoard(etSearch, HuiJiSearchActivity.this);
                finish();
            }
        });
        empty_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        showKeyBoard(etSearch);
        rv.setLayoutManager(new LinearLayoutManager(this));
        searchKeyAdapter = new SearchKeyAdapter(this, searchList);
        searchKeyAdapter.setClickKeyListener(new SearchKeyAdapter.ClickKeyListener() {
            @Override
            public void onClick(SearchKey searchKey) {
                etSearch.setText(searchKey.getKey());
                refresh();
                clearEditTextFocus();
            }
        });
        searchKeyAdapter.setClearKeyListener(new SearchKeyAdapter.ClearKeyListener() {
            @Override
            public void onClick() {
                ClubDBManager.getInstance().clearSearchList();
                initSearchData();
            }
        });
        searchKeyAdapter.setRemoveKeyListener(new SearchKeyAdapter.RemoveKeyListener() {
            @Override
            public void onClick(SearchKey searchKey) {
                hideKeyBoard(etSearch);
                ClubDBManager.getInstance().deleteSearch(searchKey);
                initSearchData();
            }
        });
        rv.setAdapter(searchKeyAdapter);
        lin_search_container.setVisibility(View.GONE);

        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {

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
        rv.setLayoutManager(layoutmanager);

        //TODO 设置适配器
        adapter = new HuiJiVipSearchAdapter(this, viperBeanList);
        rv.setAdapter(adapter);

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


                refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

    private void refresh() {
        String name = etSearch.getText().toString().trim();
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
            HttpManager.searchViperByHuiJi(params, new ResultJSONObjectObserver(getLifecycle()) {
                @Override
                public void onSuccess(JSONObject result) {
                    hideLoading();

                    SearchKey searchKey = new SearchKey(null, etSearch.getText().toString(), SharePreferenceUtil.getUserId() + "");
                    ClubDBManager.getInstance().insertOrReplaceSearch(searchKey);
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

            HttpManager.searchViperByCoach(params, new ResultJSONObjectObserver(getLifecycle()) {
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
        searchList = ClubDBManager.getInstance().querySearchList();
        Logger.i("TEST", "" + searchList.size());
        if (searchList != null && searchList.size() > 0) {
            searchKeyAdapter.update(searchList);
            lin_search_container.setVisibility(View.VISIBLE);
        } else {
            lin_search_container.setVisibility(View.GONE);
        }
    }


    public void clearEditTextFocus() {
        lin_search_container.setVisibility(View.GONE);
        etSearch.clearFocus();
        rv.requestFocus();
        SystemUtil.hideKeyBoard(etSearch, this);
    }

}