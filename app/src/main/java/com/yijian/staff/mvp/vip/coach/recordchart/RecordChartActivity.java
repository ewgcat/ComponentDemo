package com.yijian.staff.mvp.vip.coach.recordchart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordChartActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    ArrayList<ClassRecordBean> classRecordBeanArrayList=new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_chart);
        ButterKnife.bind(this);


        NavigationBar2 navigationBar2 = findViewById(R.id.record_chart_navigation_bar);
        navigationBar2.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("上课记录表");
        for (int i = 0; i <10 ; i++) {
            classRecordBeanArrayList.add(new ClassRecordBean("体验课","2018-02-23","1","1"));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecordChartAdapter(classRecordBeanArrayList));

    }
}
