package com.yijian.staff.mvp.reception.step1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.Decorator.MySelectorDecorator;
import com.yijian.staff.mvp.reception.step1.Decorator.OneDayDecorator;
import com.yijian.staff.mvp.reception.step1.bean.QuestionEntry;
import com.yijian.staff.mvp.reception.step1.bean.Step1Bean;
import com.yijian.staff.mvp.reception.step1.bean.Step1MockData;
import com.yijian.staff.mvp.reception.step1.bean.Step1WrapBean;
import com.yijian.staff.mvp.reception.step2.ReceptionStepTwoActivity_ycm;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ReceptionStepOneActivity_ycm extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ReceptionStepOneActivit";

    private Step1QuestAdapter adapter;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    private List<Step1Bean> step1bean;

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
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new Step1QuestAdapter(step1bean,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Integer> singleCheck = adapter.getSingleCheck();
                Log.e(TAG, "onClick: singleCheck"+singleCheck.toString() );

                Map<String, HashSet<Integer>> multiCheck = adapter.getMultiCheck();
                Log.e(TAG, "onClick: multiCheck"+multiCheck.toString() );

                Map<Integer, String> write = adapter.getWrite();
                Log.e(TAG, "onClick: write"+write.toString() );

            }
        });


        MaterialCalendarView calendarView = findViewById(R.id.calendarView);
        initCalendarView(calendarView);
    }

    private void initCalendarView(MaterialCalendarView widget) {
        widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        widget.setPagingEnabled(false);
        widget.setTopbarVisible(false);
        widget.setWeekDayLabels(new String[]{"日","一","二","三","四","五","六"});

        widget.setWeekDayTextAppearance(R.style.MyTextAppearance_MaterialCalendarWidget_WeekDay);
        widget.setDateTextAppearance(R.style.MyTextAppearance_MaterialCalendarWidget_Date);
        widget.addDecorators( new MySelectorDecorator(this),oneDayDecorator);

        widget.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
//                List<CalendarDay> selectedDates = widget.getSelectedDates();
//                for (int i = 0; i < selectedDates.size(); i++) {
//                    oneDayDecorator.setDate(selectedDates.get(i).getDate());
//                }
//                widget.invalidateDecorators();
                oneDayDecorator.setDate(date.getDate());

            }
        });
    }


    List<QuestionEntry> datas;
    private void mockData() {
        Step1WrapBean bean = new Gson().fromJson(Step1MockData.step1Data, Step1WrapBean.class);
        step1bean = bean.getStep1();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;

            case R.id.right_tv:
                Intent intent = new Intent(ReceptionStepOneActivity_ycm.this, ReceptionStepTwoActivity_ycm.class);
                startActivity(intent);

                break;

        }
    }
}
