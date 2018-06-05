package com.yijian.staff.mvp.reception.reception_step_ycm.step1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStatusChange;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.mvp.reception.step1.Decorator.MySelectorDecorator;
import com.yijian.staff.mvp.reception.step1.Decorator.OneDayDecorator;
import com.yijian.staff.mvp.reception.step1.ReceptionStep1Contract;
import com.yijian.staff.mvp.reception.step1.RecptionStep1Presenter;
import com.yijian.staff.mvp.reception.step1.Step1QuestAdapter;
import com.yijian.staff.mvp.reception.step1.bean.DataListBean;
import com.yijian.staff.mvp.reception.step1.bean.TemplateBean;
import com.yijian.staff.mvp.reception.step2.KeFuReceptionStepTwoActivity;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/4/20.
 */

public class Step1Fragment_Sale extends Fragment implements ReceptionStep1Contract.View, Step1QuestAdapter.ComputerPercentLisenter {
    private static final String TAG = "Step1Fragment_Sale";
    private Step1QuestAdapter adapter;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    private List<DataListBean> step1bean = new ArrayList<>();
    private RecptionStep1Presenter presenter;
    private RecptionerInfoBean consumerBean;
    private MaterialCalendarView calendarView;
    private Button btnSave;
    private String memberId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        consumerBean = arguments.getParcelable("recptionerInfoBean");
        memberId = consumerBean.getId();
        NavigationBar2 navigationBar2 = ((ReceptionStepActivity) getActivity()).getNavigationBar2();

        if (consumerBean.getStatus() != 0 && (consumerBean.getStatus() != 10)) {
            navigationBar2.setmRightTvText("下一步");
            navigationBar2.getmRightTv().setVisibility(View.VISIBLE);
            navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (statusChangeLisenter != null) statusChangeLisenter.ReceptionStep1ToStep2();

                }
            });
        }

        navigationBar2.getBackLL().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusChangeLisenter != null) statusChangeLisenter.ReceptionStep1Back();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step1_sale, container, false);

        presenter = new RecptionStep1Presenter(getContext());
        presenter.setView(this);
        initView(view);

        return view;
    }

    private void initView(View view) {

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_request);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new Step1QuestAdapter(step1bean, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setComputerPercentLisenter(this);


        calendarView = view.findViewById(R.id.calendarView);
        initCalendarView(calendarView);


        btnSave = view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (consumerBean == null) return;
                List<DataListBean> questionList = adapter.getQuestionList();
                List<CalendarDay> selectedDates = calendarView.getSelectedDates();
                presenter.uploadQusetion(questionList, consumerBean, selectedDates);

            }
        });


        if (consumerBean.getStatus() != 0 && (consumerBean.getStatus() != 10)) {
            btnSave.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(memberId)) {
                presenter.getQuestionAndAnswer(memberId);
//                presenter.getQuestion();
            } else {
                presenter.getQuestion();
            }
        } else {
            presenter.getQuestion();
        }

    }

    private void initCalendarView(MaterialCalendarView widget) {
        widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        widget.setPagingEnabled(false);
        widget.setTopbarVisible(false);
        widget.setWeekDayLabels(new String[]{"日", "一", "二", "三", "四", "五", "六"});

        widget.setWeekDayTextAppearance(R.style.MyTextAppearance_MaterialCalendarWidget_WeekDay);
        widget.setDateTextAppearance(R.style.MyTextAppearance_MaterialCalendarWidget_Date);
        widget.addDecorators(new MySelectorDecorator(getActivity()), oneDayDecorator);

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
    public void showQuestion(TemplateBean templateBean) {
        adapter.resetData(templateBean.getDataList());
    }


    @Override
    public void saveSucceed() {

        if (statusChangeLisenter != null) statusChangeLisenter.ReceptionStep1RequestionSaved();
    }


    @Override
    public void computerPercent() {
//        Log.e(TAG, "computerPercent: " );
        List<DataListBean> questionList = adapter.getQuestionList();
        List<CalendarDay> selectedDates = calendarView.getSelectedDates();
        String percent = presenter.computerPercent(questionList, selectedDates);
        btnSave.setText("保存（已完成" + percent + ")");
    }


    private ReceptionStatusChange statusChangeLisenter;

    public void setStatusChangeLisenter(ReceptionStatusChange statusChangeLisenter) {
        this.statusChangeLisenter = statusChangeLisenter;
    }
}
