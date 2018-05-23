package com.yijian.staff.mvp.main.message;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.main.message.bean.MessageInfo;
import com.yijian.staff.mvp.main.message.business.BusinessMessageBean;
import com.yijian.staff.mvp.main.message.business.BusinessMessageListAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.message.BusinessMessageRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.EmptyView;
import com.yijian.staff.widget.PagerSlidingTabStrip;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class MessageFragment extends MvcBaseFragment {

    private RefreshLayout refreshLayout;
    private List<MessageInfo> messageInfoList = new ArrayList<>();
    private List<BusinessMessageBean> businessMessageBeans = new ArrayList<>();
    private RecyclerView recyclerView;
    private MessageListAdapter messageListAdapter;

    private static final String TAG = "MessageFragment";
    //测试图片的存位置

    public static MessageFragment mMessageFragment = null;
    @BindView(R.id.empty_view)
    EmptyView empty_view;
    private int pageSize = 10;
    private int pageNum = 1;
    private int pages;
    private BusinessMessageListAdapter businessMessageListAdapter;


    public static MessageFragment getInstance() {
        if (mMessageFragment == null) {
            mMessageFragment = new MessageFragment();
        }
        return mMessageFragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView() {
        initComponent(rootView);
    }


    public void initComponent(View view) {

        recyclerView = view.findViewById(R.id.rv);
        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        //设置RecyclerView 布局,,,
        recyclerView.setLayoutManager(layoutmanager);
        businessMessageListAdapter = new BusinessMessageListAdapter(getContext(), businessMessageBeans);
        recyclerView.setAdapter(businessMessageListAdapter);


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
        refresh();

    }


    private void refresh() {
        businessMessageBeans.clear();
        pageNum = 1;
        pageSize = 10;
        empty_view.setVisibility(View.GONE);

        BusinessMessageRequestBody businessMessageRequestBody = new BusinessMessageRequestBody();
        businessMessageRequestBody.setPageNum(pageNum);
        businessMessageRequestBody.setPageSize(pageSize);
        HttpManager.getBusinessMessage(businessMessageRequestBody, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                businessMessageBeans.clear();
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    JSONObject jsonObject = JsonUtil.getJsonObject(records, i);
                    BusinessMessageBean businessMessageBean = new BusinessMessageBean(jsonObject);
                    businessMessageBeans.add(businessMessageBean);


                }
                businessMessageListAdapter.notifyDataSetChanged();

                if (businessMessageBeans.size()==0){
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
        HttpManager.getBusinessMessage(businessMessageRequestBody, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    JSONObject jsonObject = JsonUtil.getJsonObject(records, i);
                    BusinessMessageBean businessMessageBean = new BusinessMessageBean(jsonObject);
                    businessMessageBeans.add(businessMessageBean);


                }
                businessMessageListAdapter.notifyDataSetChanged();



                if (businessMessageBeans.size()==0){
                    empty_view.setVisibility(View.VISIBLE);
                }
                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

            }

            @Override
            public void onFail(String msg) {

                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                if (businessMessageBeans.size()==0){
                    empty_view.setVisibility(View.VISIBLE);
                }
            }
        });
    }


}
