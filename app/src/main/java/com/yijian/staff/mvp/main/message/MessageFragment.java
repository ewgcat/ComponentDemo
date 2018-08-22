package com.yijian.staff.mvp.main.message;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.MessageBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.widget.MyDividerItemDecoration;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.message.BusinessMessageRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.EmptyView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class MessageFragment extends MvcBaseFragment {


    private static final String TAG = "MessageFragment";
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.empty_view)
    EmptyView emptyView;

    private int pageSize = 10;
    private int pageNum = 1;
    private int businessType = 0;
    private MessageListAdapter messageListAdapter;
    private List<MessageBean> messageBeanList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView() {
        initComponent(rootView);
    }


    public void initComponent(View view) {


        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        //设置RecyclerView 布局,,,
        rv.setLayoutManager(layoutmanager);
        rv.addItemDecoration(new MyDividerItemDecoration());
        messageListAdapter = new MessageListAdapter(getContext(), messageBeanList);
        rv.setAdapter(messageListAdapter);


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
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @OnClick(R.id.empty_view)
    public void onViewClicked() {
        refresh();
    }


    public void refresh() {
        messageBeanList.clear();
        pageNum = 1;
        pageSize = 10;
        emptyView.setVisibility(View.GONE);
        showLoading();
        BusinessMessageRequestBody businessMessageRequestBody = new BusinessMessageRequestBody();
        businessMessageRequestBody.setPageNum(pageNum);
        businessMessageRequestBody.setPageSize(pageSize);
        HttpManager.getBusinessMessage(businessMessageRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                messageBeanList.clear();
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    JSONObject jsonObject = JsonUtil.getJsonObject(records, i);
                    MessageBean businessMessageBean = new MessageBean(jsonObject);
                    messageBeanList.add(businessMessageBean);
                }
                messageListAdapter.notifyDataSetChanged();

                if (messageBeanList.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                }
                refreshLayout.finishRefresh(2000, true);//传入false表示刷新失败

            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                emptyView.setVisibility(View.VISIBLE);
            }
        });
    }


    private void loadMore() {
        showLoading();
        emptyView.setVisibility(View.GONE);
        BusinessMessageRequestBody businessMessageRequestBody = new BusinessMessageRequestBody();
        businessMessageRequestBody.setPageNum(pageNum);
        businessMessageRequestBody.setPageSize(pageSize);
        HttpManager.getBusinessMessage(businessMessageRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    JSONObject jsonObject = JsonUtil.getJsonObject(records, i);
                    MessageBean businessMessageBean = new MessageBean(jsonObject);
                    messageBeanList.add(businessMessageBean);


                }
                messageListAdapter.notifyDataSetChanged();


                if (messageBeanList.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                }
                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

            }

            @Override
            public void onFail(String msg) {
                hideLoading();

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                if (messageBeanList.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        });
    }


}
