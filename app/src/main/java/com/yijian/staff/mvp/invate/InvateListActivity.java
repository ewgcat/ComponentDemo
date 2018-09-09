package com.yijian.staff.mvp.invate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.InvitationRecordBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.HuiJiInviteListRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/10 16:32:42
 */
@Route(path = "/test/6")
public class InvateListActivity extends MvcBaseActivity {

    private List<InvitationRecordBean> invitationRecordBeanList = new ArrayList<>();
    SmartRefreshLayout refreshLayout;
    RecyclerView rv_invitation;
    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量

    private int pages;
    private InvitationRecordAdatper invitationRecordAdatper;
    private NavigationBar NavigationBar;
    private int total;


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        NavigationBar = findViewById(R.id.navigation_bar2);
        NavigationBar.setTitle("邀约记录");
        NavigationBar.setBackClickListener(this);
        NavigationBar.hideLeftSecondIv();
        rv_invitation = findViewById(R.id.rv_invitation);
        refreshLayout = findViewById(R.id.refreshLayout);
        initComponent();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_invitation;
    }


    private void refresh() {


        pageNum = 1;
        pageSize = 10;
        String curDate = DateUtil.getCurDate("yyyy-MM-dd HH:mm:ss");

        HuiJiInviteListRequestBody huiJiInviteListRequestBody = new HuiJiInviteListRequestBody(curDate, pageNum, pageSize);

        HttpManager.getHuiJiInviteRecord(huiJiInviteListRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);
                total = JsonUtil.getInt(result, "total");

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
                Toast.makeText(InvateListActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadMore() {
        String curDate = DateUtil.getCurDate("yyyy-MM-dd HH:mm:ss");
        HuiJiInviteListRequestBody huiJiInviteListRequestBody = new HuiJiInviteListRequestBody(curDate, pageNum, pageSize);

        HttpManager.getHuiJiInviteRecord(huiJiInviteListRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                if (total<=(pageNum*pageSize)){
                    refreshLayout.finishLoadMore(2000, true, true);//传入false表示刷新失败
                }else {
                    refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                }

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                List<InvitationRecordBean> invitationRecordBeans = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), InvitationRecordBean.class);
                invitationRecordBeanList.addAll(invitationRecordBeans);
                invitationRecordAdatper.update(invitationRecordBeanList);
            }

            @Override
            public void onFail(String msg) {
                if (total<=(pageNum*pageSize)){
                    refreshLayout.finishLoadMore(2000, false, true);//传入false表示刷新失败
                }else {
                    refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                }
                Toast.makeText(InvateListActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void initComponent() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_invitation.setLayoutManager(layoutmanager);
        invitationRecordAdatper = new InvitationRecordAdatper(this, invitationRecordBeanList);
        rv_invitation.setAdapter(invitationRecordAdatper);
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
        refresh();
    }
    
    
}
