package com.yijian.staff.mvp.main.message.business;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
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

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/5 15:54:12
 */

@SuppressLint("ValidFragment")
public class BaseMessageFragment extends MvcBaseFragment {

    private RefreshLayout refreshLayout;
    private List<BusinessMessageBean> businessMessageBeans = new ArrayList<>();
    private RecyclerView recyclerView;

    private static final String TAG = "BaseBusiniessMessageFragment";
    //测试图片的存位置

    @BindView(R.id.empty_view)
    EmptyView empty_view;
    private int pageSize = 10;
    private int pageNum = 1;
    private int businessType = 0;
    private BusinessMessageListAdapter businessMessageListAdapter;

    public BaseMessageFragment(int businessType) {
        this.businessType = businessType;
    }

    public BaseMessageFragment() {
        super();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_message;
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
                    BusinessMessageBean businessMessageBean = new BusinessMessageBean(jsonObject);
                    businessMessageBeans.add(businessMessageBean);
                }
                businessMessageListAdapter.notifyDataSetChanged();

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
                    BusinessMessageBean businessMessageBean = new BusinessMessageBean(jsonObject);
                    businessMessageBeans.add(businessMessageBean);


                }
                businessMessageListAdapter.notifyDataSetChanged();


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
