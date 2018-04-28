package com.yijian.staff.mvp.mine.calendartable;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.yijian.staff.R;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarSettingActivity extends AppCompatActivity {

    @BindView(R.id.calendarView)
    MaterialCalendarView materialCalendarView;//布局内的控件

    List<CalendarDay> hasClassList; //当日有课日期集合
    List<CalendarDay> disableAppointmentList; //不可预约日期集合
    boolean isEdit = false;  //是否可编辑的状态
    AllNoneSelectedDecorator allNoneSelectedDecorator;//所有都不可编辑
    DisableAppointDecorator disableAppointDecorator;//设置不可预约状态
    EventDecorator eventDecorator; //设置小红点
    List<CalendarDay> calendarDayList; //装载选中的日程集合
    @BindView(R.id.tv_previewTime)
    TextView tv_previewTime;
    @BindView(R.id.tv_internal)
    TextView tv_internal;
    private OptionsPickerView optionsPickerView;
    public static final int REQUEST_CODE_SETTING_PREVIEW = 0;
    public static final int RESULT_CODE_SETTING_PREVIEW = 1;
    private String startTime = "";
    private String endTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_setting);
        ButterKnife.bind(this);
        initTitle();
        initData();
        initView();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar2.setTitle("日程表设置");
        navigationBar2.setmRightTvText("编辑");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEdit) { //isEdit false : 不可编辑，true  ： 可编辑（完成）
                    materialCalendarView.removeDecorator(allNoneSelectedDecorator);
                    navigationBar2.setmRightTvText("完成");
                } else { //点击完成 在此处做逻辑处理
                    navigationBar2.setmRightTvText("编辑");
                    calendarDayList = materialCalendarView.getSelectedDates();
                    for (CalendarDay calendarDay : calendarDayList) {
                        Log.e("Test", "年份===" + calendarDay.getYear() + "  月份==" + (calendarDay.getMonth() + 1) + " 日==" + calendarDay.getDay());
                    }
//                    materialCalendarView.clearSelection(); //清除掉所有选中日程
                    materialCalendarView.addDecorator(allNoneSelectedDecorator);
                }
                isEdit = !isEdit;
            }
        });
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();

    }

    private void initData() {
        calendarDayList = new ArrayList<CalendarDay>();

        hasClassList = new ArrayList<CalendarDay>();
        CalendarDay day = CalendarDay.from(2018, 2, 7);
        CalendarDay day2 = CalendarDay.from(2018, 2, 8);
        hasClassList.add(day);
        hasClassList.add(day2);

        disableAppointmentList = new ArrayList<CalendarDay>();
        CalendarDay day3 = CalendarDay.from(2018, 2, 13);
        CalendarDay day4 = CalendarDay.from(2018, 2, 14);
        CalendarDay day5 = CalendarDay.from(2018, 2, 15);
        disableAppointmentList.add(day3);
        disableAppointmentList.add(day4);
        disableAppointmentList.add(day5);

        allNoneSelectedDecorator = new AllNoneSelectedDecorator();
        disableAppointDecorator = new DisableAppointDecorator();
        eventDecorator = new EventDecorator(Color.RED, hasClassList);
        loadData();
    }

    private void initView() {
        Calendar instance = Calendar.getInstance();
//        materialCalendarView.setSelectedDate(instance.getTime());
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE); //设置多选
        materialCalendarView.setSelectionColor(getResources().getColor(R.color.blue));
        //设置多个不可选日期的颜色
        materialCalendarView.addDecorator(disableAppointDecorator);
        materialCalendarView.addDecorator(eventDecorator);
        materialCalendarView.addDecorator(allNoneSelectedDecorator);


        materialCalendarView.invalidateDecorators();
        //编辑日历属性
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                .setMinimumDate(CalendarDay.from(2010, 4, 3))  //设置可以显示的最早时间
                .setMaximumDate(CalendarDay.from(2020, 5, 12))//设置可以显示的最晚时间
                .setCalendarDisplayMode(CalendarMode.MONTHS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                .commit();// 返回对象并保存

        ArrayList<String> timeList = new ArrayList<>();
        timeList.add("10分钟");
        timeList.add("15分钟");
        timeList.add("20分钟");
        timeList.add("30分钟");

        optionsPickerView = new OptionsPickerView.Builder(CalendarSettingActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_internal.setText(timeList.get(options1));
            }
        }).build();
        optionsPickerView.setPicker(timeList);

    }


    private void loadData(){
        HttpManager.postHasHeaderNoParam(HttpManager.COACH_PRIVATE_COURSE_GET_TIME_URL, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    startTime = result.getString("startTime").substring(0,result.getString("startTime").lastIndexOf(":"));
                    endTime = result.getString("endTime").substring(0,result.getString("endTime").lastIndexOf(":"));
                    tv_internal.setText(startTime);
                    tv_previewTime.setText(startTime+"-"+endTime);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(CalendarSettingActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick({R.id.rel_disable_date, R.id.rel_enable_preview, R.id.rel_internal})
    public void click(View v) {

        switch (v.getId()) {
            case R.id.rel_disable_date: //设置不可约日期
                break;
            case R.id.rel_enable_preview: //可约时间段
                Intent intent = new Intent(this,PreviewTimeActivity.class);
                intent.putExtra("startTime",startTime);
                intent.putExtra("endTime",endTime);
                startActivityForResult(intent,REQUEST_CODE_SETTING_PREVIEW);
                break;
            case R.id.rel_internal: //课程间隔时间
                optionsPickerView.show();
                break;
        }

    }

    /**
     * 设置不可预约的状态
     */
    private class DisableAppointDecorator implements DayViewDecorator {

        private final Drawable backgroundDrawable;

        public DisableAppointDecorator() {
            backgroundDrawable = getResources().getDrawable(R.mipmap.my_circle_red_big);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {

            boolean flag = false;
            for (CalendarDay calendarDay : disableAppointmentList) {
                if (day.equals(calendarDay)) {
                    flag = true;
                }
            }

            return flag;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setDaysDisabled(true);
            view.setBackgroundDrawable(backgroundDrawable);
        }
    }

    /**
     * 设置所有选项都不可编辑
     */
    private class AllNoneSelectedDecorator implements DayViewDecorator {

        public AllNoneSelectedDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return true;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setDaysDisabled(true);
        }
    }


}
