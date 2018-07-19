package com.yijian.staff.mvp.main.message;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.MessageBean;
import com.yijian.staff.bean.MessageBean;
import com.yijian.staff.jpush.ClearRedPointUtil;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.main.message.business.MessageListAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.message.BusinessMessageRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.EmptyView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends MvcBaseFragment {


    private static final String TAG = "MessageFragment";
    private RefreshLayout refreshLayout;
    private List<MessageBean> businessMessageBeans = new ArrayList<>();
    private RecyclerView recyclerView;
    EmptyView empty_view;
    private int pageSize = 10;
    private int pageNum = 1;
    private int businessType = 0;
    private MessageListAdapter messageListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView() {
        initComponent(rootView);
    }


    public void initComponent(View view) {
        ClearRedPointUtil.clearBusinessNotice(getLifecycle());
        recyclerView = view.findViewById(R.id.rv);
        empty_view=view.findViewById(R.id.empty_view);

        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        //设置RecyclerView 布局,,,
        recyclerView.setLayoutManager(layoutmanager);
        messageListAdapter = new MessageListAdapter(getContext(), businessMessageBeans);
        recyclerView.setAdapter(messageListAdapter);


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

    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {
        businessMessageBeans.clear();
        pageNum = 1;
        pageSize = 10;
        empty_view.setVisibility(View.GONE);

        BusinessMessageRequestBody businessMessageRequestBody = new BusinessMessageRequestBody();
        businessMessageRequestBody.setPageNum(pageNum);
        businessMessageRequestBody.setPageSize(pageSize);
        businessMessageRequestBody.setBusinessType(businessType);
        HttpManager.getBusinessMessage(businessMessageRequestBody, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                businessMessageBeans.clear();
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    JSONObject jsonObject = JsonUtil.getJsonObject(records, i);
                    MessageBean businessMessageBean = new MessageBean(jsonObject);
                    businessMessageBeans.add(businessMessageBean);
                }
                messageListAdapter.notifyDataSetChanged();

                if (businessMessageBeans.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
                refreshLayout.finishRefresh(2000, true);//传入false表示刷新失败

            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                empty_view.setVisibility(View.VISIBLE);
            }
        });
    }


    private void loadMore() {
        empty_view.setVisibility(View.GONE);
        BusinessMessageRequestBody businessMessageRequestBody = new BusinessMessageRequestBody();
        businessMessageRequestBody.setPageNum(pageNum);
        businessMessageRequestBody.setPageSize(pageSize);
        businessMessageRequestBody.setBusinessType(businessType);
        HttpManager.getBusinessMessage(businessMessageRequestBody, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    JSONObject jsonObject = JsonUtil.getJsonObject(records, i);
                    MessageBean businessMessageBean = new MessageBean(jsonObject);
                    businessMessageBeans.add(businessMessageBean);


                }
                messageListAdapter.notifyDataSetChanged();


                if (businessMessageBeans.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

            }

            @Override
            public void onFail(String msg) {

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                if (businessMessageBeans.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }
        });
    }


}
