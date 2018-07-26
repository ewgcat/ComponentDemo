package com.yijian.staff.mvp.reception.step1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.step1.Decorator.MySelectorDecorator;
import com.yijian.staff.mvp.reception.step1.Decorator.OneDayDecorator;
import com.yijian.staff.mvp.reception.step1.bean.DataListBean;
import com.yijian.staff.mvp.reception.step1.bean.TemplateBean;
import com.yijian.staff.mvp.reception.step2.CoachReceptionStepTwoActivity;
import com.yijian.staff.mvp.reception.step2.KeFuReceptionStepTwoActivity;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import java.util.ArrayList;
import java.util.List;

public class ReceptionStepOneActivity extends AppCompatActivity implements View.OnClickListener, ReceptionStep1Contract.View, Step1QuestAdapter.ComputerPercentLisenter {

    private static final String TAG = ReceptionStepOneActivity.class.getSimpleName();

    private Step1QuestAdapter adapter;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    private List<DataListBean> step1bean = new ArrayList<>();
    private RecptionStep1Presenter presenter;
    private RecptionerInfoBean consumerBean;
    private MaterialCalendarView calendarView;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reception_step_one);
        initView();
        Intent intent = getIntent();
        if (intent.hasExtra(ReceptionActivity.CONSUMER)) {
            consumerBean = intent.getParcelableExtra(ReceptionActivity.CONSUMER);
        } else {
            Toast.makeText(ReceptionStepOneActivity.this, "获取客户信息失败,请重新获取用户信息", Toast.LENGTH_SHORT).show();
            return;
        }


        presenter = new RecptionStep1Presenter(getLifecycle(),this);
        presenter.setView(this);
//        presenter.getQuestion();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_step_one_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setmRightTvClickListener(this);
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("填写问卷(1/5)");
        navigationBar2.setmRightTvText("下一步");


        TimeBar timeBar = findViewById(R.id.step_one_timebar);
        timeBar.showTimeBar(1);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_request);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new Step1QuestAdapter(step1bean, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setComputerPercentLisenter(this);


        calendarView = findViewById(R.id.calendarView);
        initCalendarView(calendarView);


        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (consumerBean == null) return;
                List<DataListBean> questionList = adapter.getQuestionList();
                List<CalendarDay> selectedDates = calendarView.getSelectedDates();
                presenter.uploadQusetion(questionList, consumerBean, selectedDates);

            }
        });
    }

    private void initCalendarView(MaterialCalendarView widget) {
        widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        widget.setPagingEnabled(false);
        widget.setTopbarVisible(false);
        widget.setWeekDayLabels(new String[]{"日", "一", "二", "三", "四", "五", "六"});

        widget.setWeekDayTextAppearance(R.style.MyTextAppearance_MaterialCalendarWidget_WeekDay);
        widget.setDateTextAppearance(R.style.MyTextAppearance_MaterialCalendarWidget_Date);
        widget.addDecorators(new MySelectorDecorator(this), oneDayDecorator);

        widget.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                List<CalendarDay> selectedDates = widget.getSelectedDates();
                for (int i = 0; i < selectedDates.size(); i++) {
                    oneDayDecorator.setDate(selectedDates.get(i).getDate());
                }
                widget.invalidateDecorators();

                computerPercent();
//                oneDayDecorator.setDate(date.getDate());

            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;

            case R.id.right_tv:
                int userRole = SharePreferenceUtil.getUserRole();
                if (userRole == 1) {
                    Intent intent = new Intent(ReceptionStepOneActivity.this, KeFuReceptionStepTwoActivity.class);
                    startActivity(intent);
                } else if (userRole == 2) {
                    Intent intent = new Intent(ReceptionStepOneActivity.this, CoachReceptionStepTwoActivity.class);
                    startActivity(intent);
                }


                break;

        }
    }

    @Override
    public void showQuestion(TemplateBean templateBean) {
        adapter.resetData(templateBean.getDataList());
    }


    @Override
    public void saveSucceed() {
        String id = consumerBean.getId();
        Intent intent = new Intent(ReceptionStepOneActivity.this, KeFuReceptionStepTwoActivity.class);
        intent.putExtra("memberId", id);
        startActivity(intent);
    }


    @Override
    public void computerPercent() {
        Log.e(TAG, "computerPercent: ");
        List<DataListBean> questionList = adapter.getQuestionList();
        List<CalendarDay> selectedDates = calendarView.getSelectedDates();
        String percent = presenter.computerPercent(questionList, selectedDates);
        btnSave.setText("保存（已完成" + percent + ")");

    }
}
