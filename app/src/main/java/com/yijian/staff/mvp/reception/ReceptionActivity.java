package com.yijian.staff.mvp.reception;

import android.content.Context;
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
import com.yijian.staff.bean.ReceptionInfo;
import com.yijian.staff.mvp.main.MainActivity;
import com.yijian.staff.mvp.questionnaire.detail.QuestionnaireResultActivity;
import com.yijian.staff.mvp.reception.bean.ReceptionLog;
import com.yijian.staff.mvp.reception.bean.ReceptionRecordBean;
import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.bean.RecptionRecordListBean;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.physical.PhysicalReportActivity;
import com.yijian.staff.mvp.reception.reception_step_ycm.EndReceptionDialog;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.widget.NavigationBar2;

import java.util.ArrayList;
import java.util.List;

public class ReceptionActivity extends AppCompatActivity implements View.OnClickListener, ReceptionContract.View, ReceptionHistoryAdapter.ReceptionHistoryListener {

    private List<ReceptionInfo> mReceptionInfoList = new ArrayList<>();
    private RecptionerInfoBean consumer;

    public static final int RESULT_OK = 3;

    public static final String CONSUMER = "consumer";
    private RecyclerView recyclerView;
    private ReceptionPresenter presenter;
    private TextView tvName;
    private TextView tvSex;
    private TextView tvPhone;
    private ReceptionHistoryAdapter receptionHistoryAdapter;
    private SmartRefreshLayout refreshLayout;
    private Integer operatorType;
    private boolean isInit = true;

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

    public static void toReceptionActivity(Context context) {
        Intent intent = new Intent(context, ReceptionActivity.class);
        context.startActivity(intent);
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        presenter.getRecptionRecord(true);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isInit) presenter.getRecptionerInfo();
        isInit = false;
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_activity_navigation_bar2);
        navigationBar2.setTitle("接待");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);


        int userRole = SharePreferenceUtil.getUserRole();

        tvName = findViewById(R.id.tv_name);
        tvSex = findViewById(R.id.tv_sex);
        tvPhone = findViewById(R.id.tv_phone);

        findViewById(R.id.tv_jiedai).setOnClickListener(this);
        TextView stopJiedai = findViewById(R.id.tv_stopJieDai);
        if (userRole != 1) {
            stopJiedai.setVisibility(View.GONE);
        }
        stopJiedai.setOnClickListener(this);

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
                if (consumer == null) {
                    Toast.makeText(this, "未获得接待人信息", Toast.LENGTH_SHORT).show();
                    return;
                }

                EndReceptionDialog endReceptionDialog = new EndReceptionDialog();
                endReceptionDialog.setOklisenter(new EndReceptionDialog.DialogOklisenter() {
                    @Override
                    public void onClick() {
                        presenter.endRecption(consumer.getId());
                    }
                });
                endReceptionDialog.show(getFragmentManager(), "EndReceptionDialog");
                break;
            case R.id.tv_jiedai:
                if (consumer == null) {
                    Toast.makeText(this, "未获得接待人信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (operatorType == null) {
                    Toast.makeText(this, "未获得接待人节点", Toast.LENGTH_SHORT).show();
                    return;
                }

                consumer.setStatus(operatorType);
                consumer.setHistoryNode(HistoryNode);
                Intent intent1 = new Intent(ReceptionActivity.this, ReceptionStepActivity.class);
                intent1.putExtra(CONSUMER, consumer);
                startActivityForResult(intent1, 0);
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
    public void showRecptionRecordListTemp(List<ReceptionRecordBean> recordList, boolean isRefresh) {

    }

    @Override
    public void finishRefresh(boolean isRefresh) {
        if (isRefresh) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    private List<Integer> HistoryNode = new ArrayList<>();

    @Override
    public void showStatus(ReceptionStastuBean receptionStastuBean) {
        operatorType = receptionStastuBean.getOperatorType();
        HistoryNode.clear();
        List<ReceptionLog> receptionLogs = receptionStastuBean.getReceptionLogs();
        if (receptionLogs != null && !receptionLogs.isEmpty()) {
            for (ReceptionLog log : receptionLogs) {
                HistoryNode.add(log.getOperatorType());
            }
        }
    }

    @Override
    public void showEndRecption() {
        Toast.makeText(this, "接待已结束", Toast.LENGTH_SHORT).show();

        finish();

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void onRequestClicked(int position) {
        RecptionRecordListBean.RecordsBean recordsBean = receptionHistoryAdapter.getmReceptionInfoList().get(position);
        Intent intent = new Intent(this, QuestionnaireResultActivity.class);
        intent.putExtra("memberId", recordsBean.getMemberId());
        intent.putExtra("memberName", recordsBean.getMemberName());
        startActivity(intent);
    }

    @Override
    public void onPhysicalReportClicked(int position) {
        RecptionRecordListBean.RecordsBean recordsBean = receptionHistoryAdapter.getmReceptionInfoList().get(position);
        Intent i = new Intent(this, PhysicalReportActivity.class);
        i.putExtra("memberId", recordsBean.getMemberId());
        i.putExtra("memberName", recordsBean.getMemberName());
        startActivity(i);
    }


    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 根据上面发送过去的请求吗来区别
        switch (resultCode) {
            case RESULT_OK:
                ReceptionActivity.this.setResult(MainActivity.RESULT_OK_RECEPTION);
                finish();
                break;

            default:
                break;
        }
    }
}