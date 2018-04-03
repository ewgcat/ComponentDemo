package com.yijian.staff.mvp.questionnaireresult;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.Decorator.MySelectorDecorator;
import com.yijian.staff.mvp.reception.step1.Decorator.OneDayDecorator;
import com.yijian.staff.mvp.reception.step1.bean.Step1Bean;
import com.yijian.staff.mvp.reception.step1.bean.Step1MockData;
import com.yijian.staff.mvp.reception.step1.bean.Step1WrapBean;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//问卷结果
public class QuestionnaireResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Step1Bean> step1bean=new ArrayList<>();
    private QuestionnaireAdapter adapter;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_questionnaire_result);
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.questionnaire_result_navigation_bar2);
        navigationBar2.setTitle("查看问卷");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        recyclerView = findViewById(R.id.recyclerview_request);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new QuestionnaireAdapter(step1bean,QuestionnaireResultActivity.this);
        recyclerView.setAdapter(adapter);
        initData();
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

    private void initData() {

        Handler handler= new Handler();
        Step1WrapBean bean = new Gson().fromJson(Step1MockData.step1Data, Step1WrapBean.class);
        List<Step1Bean> step1 = bean.getStep1();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.resetData(step1);
                    }
                });
            }
        },2000);


    }
}
