package com.yijian.staff.mvp.resourceallocation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.invitation.InvitationRecordFragment;
import com.yijian.staff.mvp.invitation.InvitationResultFragment;
import com.yijian.staff.mvp.resourceallocation.adapter.ResourceAllocationAdatper;
import com.yijian.staff.mvp.resourceallocation.bean.HistoryResourceAllocationInfo;
import com.yijian.staff.mvp.resourceallocation.fragment.distribution.ResourceAllocationFragment;
import com.yijian.staff.mvp.resourceallocation.fragment.history.HistoryAllocationFragment;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资源分配(员工角色)
 */
@Route(path = "/test/10")
public class ResourceAllocationActivity extends AppCompatActivity {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_resource_allocation)
    RecyclerView rv_resource_allocation;
    @BindView(R.id.ll_kefu_layout)
    LinearLayout llKefuLayout;
    @BindView(R.id.ll_leader_layout)
    LinearLayout llLeaderLayout;
    private List<HistoryResourceAllocationInfo> resourceAllocationInfoList = new ArrayList<>();

    private int role = 1;


    /**
     * Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
     */
    private static final String[] FRAGMENT_TAG = {"ResourceAllocationFragment", "HistoryAllocationFragment"};
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRESELECTEDINDEX = "PREV_SELECTED_INDEX";
    private int selectedIndex = 0;

    private ResourceAllocationFragment resourceAllocationFragment;
    private HistoryAllocationFragment historyAllocationFragment;
    private NavigationBar navigationBar;

    @BindView(R.id.lin_resource_allowcation)
    LinearLayout lin_resource_allowcation;
    @BindView(R.id.lin_history_allowcation)
    LinearLayout lin_history_allowcation;
    @BindView(R.id.tv_resource_allowcation)
    TextView tv_resource_allowcation;
    @BindView(R.id.iv_resource_allowcation)
    ImageView iv_resource_allowcation;
    @BindView(R.id.tv_history_allowcation)
    TextView tv_history_allowcation;
    @BindView(R.id.iv_history_allowcation)
    ImageView iv_history_allowcation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_allocation);
        ButterKnife.bind(this);
        if (role == 0) {
            initKeFu();
        } else if (role == 1) {
            initLeader();
        }

    }


    /**
     * 客服界面
     */
    private void initKeFu() {
        llKefuLayout.setVisibility(View.VISIBLE);
        llLeaderLayout.setVisibility(View.GONE);
        initView0();
    }

    private void initTitle() {
        NavigationBar navigationBar = findViewById(R.id.vip_over_navigation_bar);
        navigationBar.setTitle("资源分配", "#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
    }

    private void initView0() {
        initTitle();
        initComponent();
        initResourceAllocationInfoList();
    }

    private void initResourceAllocationInfoList() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("name", "1");
            jsonObject.put("birthDay", "1990-8-9");
            jsonObject.put("wxIdentification", "wer2342344");
            jsonObject.put("email", "打橄榄球");
            jsonObject.put("serviceHuiJi", "壮壮");
            jsonObject.put("serviceCoach", "牛牛");
            for (int i = 0; i < 10; i++) {
                HistoryResourceAllocationInfo vipPeopleInfo = new HistoryResourceAllocationInfo(jsonObject);
                resourceAllocationInfoList.add(vipPeopleInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            rv_resource_allocation.setLayoutManager(layoutmanager);
            ResourceAllocationAdatper resourceAllocationAdatper = new ResourceAllocationAdatper(this, resourceAllocationInfoList, ResourceAllocationAdatper.ROLE_RESOURCE_TYPE);
            rv_resource_allocation.setAdapter(resourceAllocationAdatper);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }

    }

    public void initComponent() {
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(ResourceAllocationActivity.this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(ResourceAllocationActivity.this).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
            }
        });
    }


    /**
     *
     * 领导界面
     */

    private void initLeader() {
        llKefuLayout.setVisibility(View.GONE);
        llLeaderLayout.setVisibility(View.VISIBLE);
        initView();
        selectTab(0);
    }

    private void initView() {
        navigationBar = findViewById(R.id.vip_over_navigation_bar);
        navigationBar.setTitle("资源分配", "#ffffff");
        navigationBar.hideBottomLine();
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
    }

    @OnClick({R.id.lin_resource_allowcation, R.id.lin_history_allowcation})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.lin_resource_allowcation: //资源分配
                selectTab(0);
                break;
            case R.id.lin_history_allowcation: //历史分配
                selectTab(1);
                break;
        }
    }

    public void selectTab(int index) {
        selectedIndex = index;
        setBotoomStyle(index);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        hideAllIndex(transaction);
        switch (index) {
            case 0:
                popFragement(transaction, resourceAllocationFragment, index);
                break;
            case 1:
                popFragement(transaction, historyAllocationFragment, index);
                break;
        }
        transaction.commit();
    }

    public void popFragement(FragmentTransaction transaction, Fragment fragment, int index) {
        if (fragment == null) {
            // 如果ViperFragment为空，则创建一个并添加到界面上
            if (index == 0) {
                resourceAllocationFragment = ResourceAllocationFragment.getInstance();
                transaction.add(R.id.fl_invitation, resourceAllocationFragment, FRAGMENT_TAG[index]);
            } else if (index == 1) {
                historyAllocationFragment = HistoryAllocationFragment.getInstance();
                transaction.add(R.id.fl_invitation, historyAllocationFragment, FRAGMENT_TAG[index]);
            }
        } else {
            // 如果ViperFragment不为空，则直接将它显示出来
            if (index == 0) {
//                resourceAllocationFragment.resourceAllocationAdatper.setFlag_type(ResourceAllocationAdatper.RESOURCE_TYPE);
//                transaction.show(resourceAllocationFragment);
                transaction.replace(R.id.fl_invitation, resourceAllocationFragment);
            } else if (index == 1) {
//                historyAllocationFragment.resourceAllocationAdatper.setFlag_type(ResourceAllocationAdatper.RESOURCE_TYPE);
//                transaction.show(historyAllocationFragment);
                transaction.replace(R.id.fl_invitation, historyAllocationFragment);
            }

        }
    }

    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {
        Fragment fragment = InvitationRecordFragment.getInstance();
        if (fragment.isAdded()) {
            fragmentTransaction.hide(fragment);
        }
        fragment = InvitationResultFragment.getInstance();
        if (fragment.isAdded()) {
            fragmentTransaction.hide(fragment);
        }
    }

    public void setBotoomStyle(int index) {
        navigationBar.setTitle(index == 0 ? "资源分配" : "历史分配", "#ffffff");
        tv_resource_allowcation.setTextColor(index == 0 ? Color.parseColor("#1997f8") : Color.parseColor("#666666"));
        tv_history_allowcation.setTextColor(index == 0 ? Color.parseColor("#666666") : Color.parseColor("#1997f8"));

        iv_resource_allowcation.setImageResource(index == 0 ? R.mipmap.fp_ziyuanlan : R.mipmap.fp_ziyuan);
        iv_history_allowcation.setImageResource(index == 0 ? R.mipmap.fp_lishi : R.mipmap.fp_lishilan);
    }

}
