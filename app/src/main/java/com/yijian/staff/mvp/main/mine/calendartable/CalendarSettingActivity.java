package com.yijian.staff.mvp.main.mine.calendartable;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    @BindView(R.id.tv_time)
    TextView tvTime;
    private OptionsPickerView optionsPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_setting);
        ButterKnife.bind(this);
        initTitle();
        initData();
        initView();
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
        timeList.add("0分钟");
        timeList.add("15分钟");
        timeList.add("30分钟");
        timeList.add("60分钟");

        optionsPickerView = new OptionsPickerView.Builder(CalendarSettingActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvTime.setText(timeList.get(options1));
            }
        }).build();
        optionsPickerView.setPicker(timeList);

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

    @OnClick(R.id.rl_yueke_jiange_time)
    public void onViewClicked() {
        optionsPickerView.show();
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
