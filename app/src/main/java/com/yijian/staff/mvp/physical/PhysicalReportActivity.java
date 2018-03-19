package com.yijian.staff.mvp.physical;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.ReceptionStep2Adapter;
import com.yijian.staff.mvp.reception.step2.step2Bean.JsonStringData;
import com.yijian.staff.mvp.reception.step2.step2Bean.OptionItemData;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentQuestionBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.QustionBean;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.BodyTestReportView;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.List;

import butterknife.ButterKnife;

//体测报告
public class PhysicalReportActivity extends AppCompatActivity {
    private List<ParentQuestionBean> parentObj;
    private RecyclerView recyclerView;
    private TextView tvName;
    private TextView tvHeight;
    private TextView tvAge;
    private PhysicalReportAdapter demoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setLightStatusBar(this, 0xffffff);
        setContentView(R.layout.activity_physical_report);
        ButterKnife.bind(this);
        initView();
        initData();

    }



    private void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.physical_report_navigation_bar);
        navigationBar.setTitle("查看体测数据", "#000000");
        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));

//        BodyTestReportView bodyTestReportView=   findViewById(R.id.physical_report_body_test_report);
        RelativeLayout rlName = findViewById(R.id.rl_name);
        RelativeLayout rlHeight = findViewById(R.id.rl_height);
        RelativeLayout rlAge = findViewById(R.id.rl_age);
        tvName = findViewById(R.id.tv_name);
        tvHeight = findViewById(R.id.tv_height);
        tvAge = findViewById(R.id.tv_age);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);


    }

    private void initData() {
        QustionBean bean = new Gson().fromJson(JsonStringData.metaData, QustionBean.class);
        parentObj = bean.getParentObj();

        demoAdapter = new PhysicalReportAdapter(parentObj, this);
        recyclerView.setAdapter(demoAdapter);
    }


}


