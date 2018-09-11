package com.yijian.staff.mvp.huifang.search;

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
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.HuiFangInfo;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.SearchKey;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.vipermanage.search.SearchKeyAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.HuifangRecordRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.SystemUtil;
import com.yijian.staff.widget.EmptyView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchHuiFangHistoryActivity extends MvcBaseActivity {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.empty_view)
    EmptyView emptyView;
    private List<HuiFangInfo> huiFangInfoList = new ArrayList<>();

    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量
    private int type;

    private SearchHuiFangHistoryAdapter searchHuiFangHistoryAdapter;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_search_hui_fang_history;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        showKeyBoard(etSearch);

         type = getIntent().getIntExtra("type", 0);

        emptyView.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });


        etSearch.setHintTextColor(Color.parseColor("#666666"));

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        SystemUtil.hideKeyBoard(etSearch, SearchHuiFangHistoryActivity.this);
                        refresh();
                        break;
                }
                return true;
            }
        });
        initComponent();

    }

    public void initComponent() {
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


        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv.setLayoutManager(layoutmanager);
        searchHuiFangHistoryAdapter = new SearchHuiFangHistoryAdapter(this, huiFangInfoList);
        rv.setAdapter(searchHuiFangHistoryAdapter);
    }

    public void refresh() {
        String keyWord = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(keyWord)){
            showToast("请输入关键字！");
            return;
        }
        pageNum = 1;
        pageSize = 10;
        huiFangInfoList.clear();
        HuifangRecordRequestBody huifangRecordRequestBody = new HuifangRecordRequestBody();
        huifangRecordRequestBody.setChief(true);
        huifangRecordRequestBody.setPageNum(pageNum);
        huifangRecordRequestBody.setPageSize(pageSize);
        huifangRecordRequestBody.setType(type);
        huifangRecordRequestBody.setKeyWord(keyWord);
        showLoading();
        HttpManager.postHuiFangRecord(huifangRecordRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                refreshLayout.finishRefresh(2000, true);


                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                JSONArray records = JsonUtil.getJsonArray(result, "records");

                List<HuiFangInfo> list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), HuiFangInfo.class);
                huiFangInfoList.addAll(list);
                searchHuiFangHistoryAdapter.update(huiFangInfoList);
            }

            @Override
            public void onFail(String msg) {
                hideLoading();

                refreshLayout.finishRefresh(2000, false);
                showToast(msg);
            }
        });
    }

    public void loadMore() {
        String keyWord = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(keyWord)){
            showToast("请输入关键字！");
            return;
        }
        HuifangRecordRequestBody huifangRecordRequestBody = new HuifangRecordRequestBody();
        huifangRecordRequestBody.setChief(true);
        huifangRecordRequestBody.setPageNum(pageNum);
        huifangRecordRequestBody.setPageSize(pageSize);
        huifangRecordRequestBody.setType(type);
        huifangRecordRequestBody.setKeyWord(keyWord);
        showLoading();
        showLoading();
        HttpManager.postHuiFangRecord(huifangRecordRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;

                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                JSONArray records = JsonUtil.getJsonArray(result, "records");

                List<HuiFangInfo> list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), HuiFangInfo.class);
                huiFangInfoList.addAll(list);
                searchHuiFangHistoryAdapter.update(huiFangInfoList);
            }

            @Override
            public void onFail(String msg) {
                hideLoading();

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                showToast(msg);
            }
        });
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


}
