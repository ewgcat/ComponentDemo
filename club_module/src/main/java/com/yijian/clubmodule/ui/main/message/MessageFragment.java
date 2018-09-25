package com.yijian.clubmodule.ui.main.message;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.MessageBean;
import com.yijian.commonlib.base.mvc.MvcBaseFragment;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.clubmodule.net.requestbody.message.BusinessMessageRequestBody;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.util.JsonUtil;
import com.yijian.commonlib.widget.EmptyView;
import com.yijian.commonlib.widget.MyDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends MvcBaseFragment {


    private static final String TAG = "MessageFragment";
    RecyclerView rv;
    SmartRefreshLayout refreshLayout;
    EmptyView emptyView;

    private int pageSize = 10;
    private int pageNum = 1;
    private MessageListAdapter messageListAdapter;
    private List<MessageBean> messageBeanList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView() {
        initComponent(getRootView());
    }


    public void initComponent(View view) {
        rv = getRootView().findViewById(R.id.rv);
        refreshLayout = getRootView().findViewById(R.id.refreshLayout);
        emptyView = getRootView().findViewById(R.id.empty_view);
        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

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
                List<MessageBean> list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), MessageBean.class);
                if (list != null && list.size() > 0) {
                    messageBeanList.addAll(list);
                    messageListAdapter.notifyDataSetChanged();
                }

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
                List<MessageBean> list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), MessageBean.class);

                if (list != null && list.size() > 0) {
                    messageBeanList.addAll(list);
                    messageListAdapter.notifyDataSetChanged();
                }


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
