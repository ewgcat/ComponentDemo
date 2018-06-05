package com.yijian.staff.mvp.questionnaire.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.reception.step1.Decorator.OneDayDecorator;
import com.yijian.staff.mvp.reception.step1.bean.DataListBean;
import com.yijian.staff.mvp.reception.step1.bean.TemplateBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//问卷结果
public class QuestionnaireResultActivity extends MvcBaseActivity {
    private static final String TAG = QuestionnaireResultActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<DataListBean> step1bean = new ArrayList<>();
    private QuestionnaireAdapter adapter;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    private String memberId;
    private MaterialCalendarView calendarView;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_questionnaire_result;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.questionnaire_result_navigation_bar2);
        navigationBar2.setTitle("查看问卷");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        recyclerView = findViewById(R.id.recyclerview_request);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new QuestionnaireAdapter(step1bean, QuestionnaireResultActivity.this);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent.hasExtra("memberId")) {
            memberId = intent.getStringExtra("memberId");
        } else {
            Toast.makeText(QuestionnaireResultActivity.this, "用户信息获取失败", Toast.LENGTH_SHORT).show();
            return;
        }

        calendarView = findViewById(R.id.calendarView);
        initCalendarView(calendarView);
        initData();
    }

    private void initCalendarView(MaterialCalendarView widget) {
        widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        widget.setPagingEnabled(false);
        widget.setTopbarVisible(false);
        widget.setWeekDayLabels(new String[]{"日", "一", "二", "三", "四", "五", "六"});

        widget.setWeekDayTextAppearance(R.style.MyTextAppearance_MaterialCalendarWidget_WeekDay);
        widget.setDateTextAppearance(R.style.MyTextAppearance_MaterialCalendarWidget_Date);
        calendarView.addDecorator(new AllNoneSelectedDecorator());
    }

    private void initData() {


        Map<String, String> params = new HashMap<>();
        params.put("memberId", memberId);

        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_QUESTION_RESULT, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                TemplateBean templateBean = new Gson().fromJson(result.toString(), TemplateBean.class);
                if (templateBean == null) return;
//                view.showQuestion(templateBean);
                adapter.resetData(templateBean.getDataList());
            }

            @Override
            public void onFail(String msg) {

            }
        });


        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_QUESTION_RESULT_FITNESSTIME, params, new Observer<JSONObject>() {

            private Date parse;

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JSONObject jsonObject) {
                try {
                    int code = jsonObject.getInt("code");

                    if (code == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        ArrayList<CalendarDay> dates = new ArrayList<>();
                        for (int i = 0; i < data.length(); i++) {
                            String o = data.getString(i);

                            parse = simpleDateFormat.parse(o);
                            dates.add(CalendarDay.from(parse));
                        }
                        calendarView.setCurrentDate(parse);
                        calendarView.addDecorator(new EventDecorator(QuestionnaireResultActivity.this, dates));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }
}
