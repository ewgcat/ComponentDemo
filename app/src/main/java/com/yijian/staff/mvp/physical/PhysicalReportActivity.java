package com.yijian.staff.mvp.physical;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.step2Bean.JsonStringData;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentQuestionBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.QustionBean;
import com.yijian.staff.widget.NavigationBar2;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

//体测报告
public class PhysicalReportActivity extends AppCompatActivity {
    private List<ParentQuestionBean> parentObj;
    private RecyclerView recyclerView;
    private TextView tvName;
    private TextView tvHeight;
    private TextView tvAge;
    private PhysicalReportAdapter demoAdapter;
    private List<ParentQuestionBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_report);
        ButterKnife.bind(this);
        initView();
        initData();

    }



    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.physical_report_navigation_bar);
        navigationBar2.setTitle("查看体测数据");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        RelativeLayout rlName = findViewById(R.id.rl_name);
        RelativeLayout rlHeight = findViewById(R.id.rl_height);
        RelativeLayout rlAge = findViewById(R.id.rl_age);
        tvName = findViewById(R.id.tv_name);
        tvHeight = findViewById(R.id.tv_height);
        tvAge = findViewById(R.id.tv_age);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        list = new ArrayList<>();
        demoAdapter = new PhysicalReportAdapter(list, this);
        recyclerView.setAdapter(demoAdapter);

    }

    private void initData() {
        QustionBean bean = new Gson().fromJson(JsonStringData.metaData, QustionBean.class);
        parentObj = bean.getParentObj();



        Handler handler= new Handler();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        demoAdapter.resetData(parentObj);
                    }
                });
            }
        },2000);
    }


}


