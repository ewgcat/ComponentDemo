package com.yijian.staff.mvp.huiji.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.db.bean.SearchKey;
import com.yijian.staff.mvp.coach.search.CoachSearchActivity;
import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.util.SystemUtil;

public class HuiJiSearchActivity extends AppCompatActivity {

    private static final String TAG = CoachSearchActivity.class.getSimpleName();
    @BindView(R.id.top_view)
    LinearLayout top_view;
    @BindView(R.id.lin_click_search)
    LinearLayout lin_click_search;    //取消搜索点击区域
    @BindView(R.id.lin_search_container)
    LinearLayout lin_search_container; //搜索面板的容器
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    @BindView(R.id.tv_3)
    TextView tv_3;
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
        initSearchData();
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
                pageNum = 1;
                pageSize = 1;
                viperBeanList.clear();
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
            return;
        } else {
            params.put("name", name);
            params.put("pageNum", pageNum + "");
            params.put("pageSize", pageSize + "");

            HttpManager.searchViperByHuiJi(params, new ResultObserver() {
                @Override
                public void onSuccess(JSONObject result) {

                    SearchKey searchKey = new SearchKey(0L, etSearch.getText().toString(), SharePreferenceUtil.getUserRole() + "");
                    DBManager.getInstance().insertOrReplaceSearch(searchKey);
                    clearEditTextFocus();

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
                    clearEditTextFocus();
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

            HttpManager.searchViperByCoach(params, new ResultObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    clearEditTextFocus();
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
                    clearEditTextFocus();
                    boolean hasMore = pages > pageNum ? true : false;
                    refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                    Toast.makeText(HuiJiSearchActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void initSearchData() {
        List<SearchKey> searchList = DBManager.getInstance().querySearchList();
        int searchCount = searchList.size();
        tv_1.setVisibility(View.GONE);
        tv_2.setVisibility(View.GONE);
        tv_3.setVisibility(View.GONE);
        if (searchCount > 0 && searchCount <= 1) {
            tv_1.setText(searchList.get(0).getKey());
            tv_1.setVisibility(View.VISIBLE);
        } else if (searchCount > 1 && searchCount <= 2) {
            tv_1.setText(searchList.get(0).getKey());
            tv_2.setText(searchList.get(1).getKey());
            tv_1.setVisibility(View.VISIBLE);
            tv_2.setVisibility(View.VISIBLE);
        } else if (searchCount > 2 && searchCount <= 3) {
            tv_1.setText(searchList.get(0).getKey());
            tv_2.setText(searchList.get(1).getKey());
            tv_3.setText(searchList.get(2).getKey());
            tv_1.setVisibility(View.VISIBLE);
            tv_2.setVisibility(View.VISIBLE);
            tv_3.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.tv_cancel, R.id.lin_click_search, R.id.tv_1, R.id.tv_2, R.id.tv_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                SystemUtil.hideKeyBoard(etSearch, this);
                break;
            case R.id.lin_click_search: //点击取消区域
                clearEditTextFocus();

                break;
            case R.id.tv_1:
                refresh(tv_1.getText().toString());
                etSearch.setText(tv_1.getText().toString());
                clearEditTextFocus();
                break;
            case R.id.tv_2:
                refresh(tv_2.getText().toString());
                etSearch.setText(tv_2.getText().toString());
                clearEditTextFocus();
                break;
            case R.id.tv_3:
                refresh(tv_3.getText().toString());
                etSearch.setText(tv_3.getText().toString());
                clearEditTextFocus();
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