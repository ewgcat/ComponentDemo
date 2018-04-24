package com.yijian.staff.mvp.reception;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.physical.PhysicalReportActivity;
import com.yijian.staff.mvp.questionnaireresult.QuestionnaireResultActivity;
import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.bean.RecptionRecordListBean;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.mvp.reception.step1.ReceptionStepOneActivity;
import com.yijian.staff.mvp.reception.step2.CoachReceptionStepTwoActivity;
import com.yijian.staff.mvp.reception.step3.ReceptionStepThreeActivity;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.widget.NavigationBar2;

import java.util.ArrayList;
import java.util.List;

public class ReceptionActivity extends AppCompatActivity implements View.OnClickListener, ReceptionContract.View, ReceptionHistoryAdapter.ReceptionHistoryListener {

    private List<ReceptionInfo> mReceptionInfoList = new ArrayList<>();
    private RecptionerInfoBean consumer;

    public static final String CONSUMER = "consumer";
    private RecyclerView recyclerView;
    private ReceptionPresenter presenter;
    private TextView tvName;
    private TextView tvSex;
    private TextView tvPhone;
    private ReceptionHistoryAdapter receptionHistoryAdapter;
    private SmartRefreshLayout refreshLayout;
    private Integer operatorType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reception);
        initView();
        presenter = new ReceptionPresenter(this);

        presenter.setView(this);
        presenter.getRecptionerInfo();

        presenter.getRecptionRecord(true);
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_activity_navigation_bar2);
        navigationBar2.setTitle("接待");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);


        tvName = findViewById(R.id.tv_name);
        tvSex = findViewById(R.id.tv_sex);
        tvPhone = findViewById(R.id.tv_phone);

        findViewById(R.id.tv_jiedai).setOnClickListener(this);
        findViewById(R.id.tv_stopJieDai).setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerview_jiedai_history);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setNestedScrollingEnabled(false);
        receptionHistoryAdapter = new ReceptionHistoryAdapter(this);
        recyclerView.setAdapter(receptionHistoryAdapter);

        initRefresh();

        receptionHistoryAdapter.setReceptionHistoryListener(this);
    }

    private void initRefresh() {
        refreshLayout = findViewById(R.id.refreshLayout);

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
                presenter.getRecptionRecord(true);

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getRecptionRecord(false);
            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.tv_stopJieDai:
                //TODO 结束接待


                break;
            case R.id.tv_jiedai:
                //TODO 接待流程
//                int userRole = SharePreferenceUtil.getUserRole();
//                if (userRole==1){
//                    Intent intent = new Intent(ReceptionActivity.this, ReceptionStepOneActivity.class);
//                    startActivity(intent);
//                }else if (userRole==2){
//                    Intent intent = new Intent(ReceptionActivity.this, CoachReceptionStepTwoActivity.class);
//                    startActivity(intent);
//                }else if (userRole==3|userRole==4){
//                    Intent intent = new Intent(ReceptionActivity.this, ReceptionStepThreeActivity.class);
//                    startActivity(intent);
//                }


//                if (consumer == null) {
//                    Toast.makeText(ReceptionActivity.this, "用户信息获取失败，不能进入接待流程", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                //会籍角色
//                Intent intent = new Intent(ReceptionActivity.this, ReceptionStepOneActivity.class);
//                intent.putExtra(CONSUMER, consumer);
//                startActivity(intent);

                //教练角色
//                Intent intent1 = new Intent(ReceptionActivity.this, CoachReceptionStepTwoActivity.class);
//                intent1.putExtra(CONSUMER, consumer);
//                startActivity(intent1);

//                第三步
//                Intent intent = new Intent(ReceptionActivity.this, ReceptionStepThreeActivity.class);
//                if (consumer!=null) {
//                    String id1 = consumer.getId();
//                    id1="1";
//                    intent.putExtra("memberId", id1);
//                }
//                startActivity(intent);


//                if (consumer==null||operatorType==null){
//                    Toast.makeText(this,"接待人信息或者接待人节点获取失败，请退出此页面重新获取",Toast.LENGTH_SHORT).show();
//                return;
//                }
                consumer.setStatus(operatorType);
                Intent intent1 = new Intent(ReceptionActivity.this, ReceptionStepActivity.class);
                intent1.putExtra(CONSUMER, consumer);
                startActivity(intent1);

                break;
        }
    }


    @Override
    public void showRecptionInfo(RecptionerInfoBean bean) {

        consumer = bean;
        tvName.setText("" + bean.getName());
        tvSex.setText("" + bean.getSex());
        tvPhone.setText("" + bean.getMobile());
        presenter.getRecptionStatus(bean.getId());
    }

    @Override
    public void showRecptionRecordList(List<RecptionRecordListBean.RecordsBean> recordList, boolean isRefresh) {

        if (isRefresh) receptionHistoryAdapter.clearData();
        receptionHistoryAdapter.addData(recordList);
    }

    @Override
    public void finishRefresh(boolean isRefresh) {
        if (isRefresh) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void showStatus(ReceptionStastuBean receptionStastuBean) {
        operatorType = receptionStastuBean.getOperatorType();

    }

    @Override
    public void onRequestClicked(int position) {
        RecptionRecordListBean.RecordsBean recordsBean = receptionHistoryAdapter.getmReceptionInfoList().get(position);
        Intent intent = new Intent(this, QuestionnaireResultActivity.class);
        intent.putExtra("memberId",recordsBean.getMemberId());
        intent.putExtra("memberName",recordsBean.getMemberName());
        startActivity(intent);

    }

    @Override
    public void onPhysicalReportClicked(int position) {
        RecptionRecordListBean.RecordsBean recordsBean = receptionHistoryAdapter.getmReceptionInfoList().get(position);
        Intent i = new Intent(this,PhysicalReportActivity.class);
        i.putExtra("memberId", recordsBean.getMemberId());
        i.putExtra("memberName",recordsBean.getMemberName());
        startActivity(i);
    }
}