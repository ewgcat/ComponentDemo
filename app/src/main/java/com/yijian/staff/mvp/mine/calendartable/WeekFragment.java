package com.yijian.staff.mvp.mine.calendartable;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.yijian.staff.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 周视图
 */
public class WeekFragment extends Fragment {

    private static WeekFragment weekFragment;
    public static WeekFragment getInstance(){
        if(weekFragment == null){
            weekFragment = new WeekFragment();
        }
        return weekFragment;
    }

    MaterialCalendarView materialCalendarView;//布局内的控件
    TextView tv_change_date;
    TitleChanger titleChanger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        materialCalendarView = view.findViewById(R.id.calendarView);
        tv_change_date = view.findViewById(R.id.tv_change_date);
        titleChanger = new TitleChanger(tv_change_date);
        titleChanger.setTitleFormatter(new DateFormatTitleFormatter(new SimpleDateFormat("yyyy年MM月")));
    }

    private void initData() {
        //编辑日历属性
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                .setMinimumDate(CalendarDay.from(2010, 4, 3))  //设置可以显示的最早时间
                .setMaximumDate(CalendarDay.from(2020, 5, 12))//设置可以显示的最晚时间
                .setCalendarDisplayMode(CalendarMode.WEEKS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                .commit();// 返回对象并保存
        Calendar instance = Calendar.getInstance();
        materialCalendarView.setSelectedDate(instance.getTime());
//        materialCalendarView.setTopbarVisible(false);
        materialCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Log.e("Test","月份===" + (date.getMonth() + 1) +  "年份===" + date.getYear());
                titleChanger.change(date);
                Calendar calendar = date.getCalendar();
                calendar.getFirstDayOfWeek();
            }
        });
    }

}
