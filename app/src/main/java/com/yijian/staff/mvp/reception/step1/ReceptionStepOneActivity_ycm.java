package com.yijian.staff.mvp.reception.step1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.QuestionEntry;
import com.yijian.staff.mvp.reception.step1.bean.QuestionOption;
import com.yijian.staff.mvp.reception.step2.ReceptionStepTwoActivity;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ReceptionStepOneActivity_ycm extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ReceptionStepOneActivit";

    private Step1QuestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reception_step_one_ycm);

        initView();

    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_step_one_navigation_bar);

        navigationBar2.setNavigationBarBackgroudColor(Color.parseColor("#1997F8"));
        navigationBar2.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar2.getmRightTv().setOnClickListener(this);
        navigationBar2.getFirstLeftIv().setOnClickListener(this);
        navigationBar2.setTitle("填写问卷(1/5)");
        navigationBar2.setmRightTvText("下一步");


        mockData();
        TimeBar timeBar = findViewById(R.id.step_one_timebar);
        timeBar.showTimeBar(1);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_request);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        adapter = new Step1QuestAdapter(datas,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<Integer, Integer> singleCheck = adapter.getSingleCheck();
                Log.e(TAG, "onClick: singleCheck"+singleCheck.toString() );

                Map<Integer, HashSet<Integer>> multiCheck = adapter.getMultiCheck();
                Log.e(TAG, "onClick: multiCheck"+multiCheck.toString() );

                Map<Integer, String> write = adapter.getWrite();
                Log.e(TAG, "onClick: write"+write.toString() );
            }
        });

    }



    List<QuestionEntry> datas;
    private void mockData() {
        ArrayList<QuestionOption> type0 = new ArrayList<>();
        ArrayList<QuestionOption> type1 = new ArrayList<>();
        ArrayList<QuestionOption> type2 = new ArrayList<>();

        ArrayList<QuestionOption> type3 = new ArrayList<>();
        ArrayList<QuestionOption> type4 = new ArrayList<>();
        ArrayList<QuestionOption> type5 = new ArrayList<>();
        ArrayList<QuestionOption> type6 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            QuestionOption questionOption_type0 = new QuestionOption("Type__single" + "no." + i, QuestionOption.TYPE_SINGLECHECK,false);
            type0.add(questionOption_type0);
        }

        for (int i = 0; i < 7; i++) {
            QuestionOption questionOption_type1= new QuestionOption("Type1__multi" + "no." + i, QuestionOption.TYPE_MULTICHECK,false);
            type1.add(questionOption_type1);
        }

        type2.add(new QuestionOption("isWriteType",QuestionOption.TYPE_WRITE,false));



        for (int i = 0; i < 5; i++) {
            QuestionOption questionOption_type0 = new QuestionOption("Type__single" + "no." + i, QuestionOption.TYPE_SINGLECHECK,false);
            type4.add(questionOption_type0);
        }

        for (int i = 0; i < 7; i++) {
            QuestionOption questionOption_type1= new QuestionOption("Type1__multi" + "no." + i, QuestionOption.TYPE_MULTICHECK,false);
            type3.add(questionOption_type1);
        }

        type5.add(new QuestionOption("isWriteType",QuestionOption.TYPE_WRITE,false));
        type6.add(new QuestionOption("isWriteType",QuestionOption.TYPE_WRITE,false));



        QuestionEntry questionEntry0 = new QuestionEntry("这是一个单选",0,type0);
        QuestionEntry questionEntry1 = new QuestionEntry("这是一个多选多选1",1,type1);
        QuestionEntry questionEntry2 = new QuestionEntry("这是一个填空2",2,type2);
        QuestionEntry questionEntry3 = new QuestionEntry("这是一个填空6",3,type6);
        QuestionEntry questionEntry4 = new QuestionEntry("这是一个填空5",4,type5);
        QuestionEntry questionEntry5 = new QuestionEntry("这是一个单选4",5,type4);
        QuestionEntry questionEntry6 = new QuestionEntry("这是一个多选多选3",6,type3);

        datas = Arrays.asList(questionEntry0,questionEntry1,questionEntry2,questionEntry3,questionEntry4,questionEntry5,questionEntry6);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;

            case R.id.right_tv:
                Intent intent = new Intent(ReceptionStepOneActivity_ycm.this, ReceptionStepTwoActivity.class);
                startActivity(intent);

                break;

        }
    }
}
