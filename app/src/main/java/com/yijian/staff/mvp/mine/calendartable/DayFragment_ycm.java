package com.yijian.staff.mvp.mine.calendartable;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeek.calendar.widget.calendar.CalendarUtils;
import com.jeek.calendar.widget.calendar.OnCalendarClickListener;
import com.jeek.calendar.widget.calendar.month.MonthCalendarView;
import com.jeek.calendar.widget.calendar.month.MonthView;
import com.jeek.calendar.widget.calendar.week.WeekCalendarView;
import com.jeek.calendar.widget.calendar.week.WeekView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.yijian.staff.R;

import com.yijian.staff.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 日视图
 */
public class DayFragment_ycm extends Fragment  {
    private static final String TAG = "DayFragment_ycm";
    private static DayFragment_ycm dayFragment;
    private LinearLayout llCalendar;
    private int mCurrentSelectYear;
    private int mCurrentSelectMonth;
    private int mCurrentSelectDay;
    private WeekCalendarView wcvCalendar;
    private MonthCalendarView mcvCalendar;
    private RecyclerView rv_day;
    OnChangeDateListener onChangeDateListener;

    public void setOnChangeDateListener(OnChangeDateListener onChangeDateListener) {
        this.onChangeDateListener = onChangeDateListener;
    }


    public static DayFragment_ycm getInstance(){
        if(dayFragment == null){
            dayFragment = new DayFragment_ycm();
        }
        return dayFragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_ycm, container, false);
        initView(view);


        return view;
    }


    private void initView(View view) {

        ImageView ivToggle = view.findViewById(R.id.iv_toggle);

        wcvCalendar = view.findViewById(R.id.wcvCalendar);
        mcvCalendar = view.findViewById(R.id.mcvCalendar);
        llCalendar = view.findViewById(R.id.ll_calendar);

        mcvCalendar.setOnCalendarClickListener(mMonthCalendarClickListener);

        wcvCalendar.setOnCalendarClickListener(mWeekCalendarClickListener);



        ivToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int height = llCalendar.getHeight();
                float translationY = llCalendar.getTranslationY();
                if (translationY==0){//收缩

                    float range = height - getDependentViewCollapsedHeight();
                    llCalendar.setTranslationY(-range);
                }else if (-translationY==height-getDependentViewCollapsedHeight()){//伸张
                    llCalendar.setTranslationY(0);
                }

            }
        });


        rv_day = view.findViewById(R.id.rv);

        initData();

    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        resetCurrentSelectDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        initDayCanlendarInfoList();
    }

    private void resetCurrentSelectDate(int year, int month, int day) {
        mCurrentSelectYear = year;
        mCurrentSelectMonth = month;
        mCurrentSelectDay = day;
    }


    private float getDependentViewCollapsedHeight() {
        return getContext().getResources().getDimension(R.dimen.collapsed_header_height);
    }




    private OnCalendarClickListener mWeekCalendarClickListener = new OnCalendarClickListener() {
        @Override
        public void onClickDate(int year, int month, int day) {
            mcvCalendar.setOnCalendarClickListener(null);
            int months = CalendarUtils.getMonthsAgo(mCurrentSelectYear, mCurrentSelectMonth, year, month);
            resetCurrentSelectDate(year, month, day);
            if (months != 0) {
                int position = mcvCalendar.getCurrentItem() + months;
                mcvCalendar.setCurrentItem(position, false);
            }
            resetMonthView();
            mcvCalendar.setOnCalendarClickListener(mMonthCalendarClickListener);

        }

        @Override
        public void onPageChange(int year, int month, int day) {
            Log.e("Test","year==="+year+"  month==="+month + "   day==="+day);
            CalendarDay calendarDay = CalendarDay.from(year, month, day);
            onChangeDateListener.onChangeDate(calendarDay);
        }
    };

    private OnCalendarClickListener mMonthCalendarClickListener = new OnCalendarClickListener() {
        @Override
        public void onClickDate(int year, int month, int day) {
            wcvCalendar.setOnCalendarClickListener(null);
            int weeks = CalendarUtils.getWeeksAgo(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay, year, month, day);
            resetCurrentSelectDate(year, month, day);
            int position = wcvCalendar.getCurrentItem() + weeks;
            if (weeks != 0) {
                wcvCalendar.setCurrentItem(position, false);
            }
            resetWeekView(position);
            wcvCalendar.setOnCalendarClickListener(mWeekCalendarClickListener);
        }

        @Override
        public void onPageChange(int year, int month, int day) {
            Log.e("Test","year==="+year+"  month==="+month + "   day==="+day);
            CalendarDay calendarDay = CalendarDay.from(year, month, day);
            onChangeDateListener.onChangeDate(calendarDay);

            //添加小圆点
            List<String> dateList = new ArrayList<String>();
            dateList.add("2018-5-2");
            dateList.add("2018-5-3");
            dateList.add("2018-5-4");
            dateList.add("2018-6-2");
            wcvCalendar.getCurrentWeekView().addDateTaskHint(dateList);
        }
    };

    private void resetWeekView(int position) {
        WeekView weekView = wcvCalendar.getCurrentWeekView();
        if (weekView != null) {
            weekView.setSelectYearMonth(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
            weekView.invalidate();
        } else {
            WeekView newWeekView = wcvCalendar.getWeekAdapter().instanceWeekView(position);
            newWeekView.setSelectYearMonth(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
            newWeekView.invalidate();
            wcvCalendar.setCurrentItem(position);
        }
//        if (mOnCalendarClickListener != null) {
//            mOnCalendarClickListener.onClickDate(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
//        }
        onDataChanged(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);

    }

    private void resetMonthView() {
        MonthView monthView = mcvCalendar.getCurrentMonthView();
        if (monthView != null) {
            monthView.setSelectYearMonth(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
            monthView.invalidate();
        }
//        if (mOnCalendarClickListener != null) {
//            mOnCalendarClickListener.onClickDate(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
//        }
//        resetCalendarPosition();

        onDataChanged(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
    }
    //当日期被改变后
    private void onDataChanged(int mCurrentSelectYear, int mCurrentSelectMonth, int mCurrentSelectDay) {

    }


    private List<DayCanlendarInfo> dayCanlendarInfoList=new ArrayList<>();
    private void initDayCanlendarInfoList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("className", "张一一");
            jsonObject.put("venue", "场馆1");
            jsonObject.put("intervalTime", "20分钟");
            jsonObject.put("startOrderTime", "09:00");
            jsonObject.put("endOrderTime", "11:00");
            jsonObject.put("status", "1");
            JSONArray stuArray = new JSONArray();
            stuArray.put("张三");
            stuArray.put("李四");
            jsonObject.put("stuList", stuArray);
            for (int i = 0; i < 10; i++) {
                DayCanlendarInfo dayCanlendarInfo = new DayCanlendarInfo(jsonObject);
                dayCanlendarInfoList.add(dayCanlendarInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
            //设置RecyclerView 布局
            rv_day.setLayoutManager(layoutmanager);
            DayCanlendarAdapter dayCanlendarAdapter = new DayCanlendarAdapter(getActivity(), dayCanlendarInfoList);
            rv_day.setAdapter(dayCanlendarAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }

    }
}
