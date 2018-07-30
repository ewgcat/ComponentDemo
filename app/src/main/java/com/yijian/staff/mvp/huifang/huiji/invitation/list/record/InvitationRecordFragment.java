package com.yijian.staff.mvp.huifang.huiji.invitation.list.record;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huifang.huiji.invitation.list.bean.InvitationRecordBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.HuiJiInviteListRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 邀约记录
 */
public class InvitationRecordFragment extends Fragment {

    private static InvitationRecordFragment invitationRecordFragment;
    private InvitationRecordAdatper invitationRecordAdatper;

    public static InvitationRecordFragment getInstance() {
        if (invitationRecordFragment == null) {
            invitationRecordFragment = new InvitationRecordFragment();
        }
        return invitationRecordFragment;
    }

    SmartRefreshLayout refreshLayout;
    RecyclerView rv_invitation;
    private int pageNum = 1;//页码
    private int pageSize = 1;//每页数量

    private int pages;
    private List<InvitationRecordBean> invitationRecordBeanList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invitation_record, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv_invitation = view.findViewById(R.id.rv_invitation);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        initComponent();
    }


    private void refresh() {


        pageNum = 1;
        pageSize = 4;
        String curDate = DateUtil.getCurDate("yyyy-MM-dd HH:mm:ss");

        HuiJiInviteListRequestBody huiJiInviteListRequestBody = new HuiJiInviteListRequestBody(curDate, pageNum, pageSize);

        HttpManager.getHuiJiInviteRecord(huiJiInviteListRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);

                invitationRecordBeanList.clear();
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                List<InvitationRecordBean> invitationRecordBeans = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), InvitationRecordBean.class);
                invitationRecordBeanList.addAll(invitationRecordBeans);
                invitationRecordAdatper.update(invitationRecordBeanList);
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadMore() {
        String curDate = DateUtil.getCurDate("yyyy-MM-dd HH:mm:ss");
        HuiJiInviteListRequestBody huiJiInviteListRequestBody = new HuiJiInviteListRequestBody(curDate, pageNum, pageSize);

        HttpManager.getHuiJiInviteRecord(huiJiInviteListRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                List<InvitationRecordBean> invitationRecordBeans = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), InvitationRecordBean.class);
                invitationRecordBeanList.addAll(invitationRecordBeans);
                invitationRecordAdatper.update(invitationRecordBeanList);
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void initComponent() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
        //设置RecyclerView 布局
        rv_invitation.setLayoutManager(layoutmanager);
        invitationRecordAdatper = new InvitationRecordAdatper(getActivity(), invitationRecordBeanList);
        rv_invitation.setAdapter(invitationRecordAdatper);
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale);
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

}
