package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jeek.calendar.widget.calendar.CalendarUtils;
import com.jeek.calendar.widget.calendar.OnCalendarClickListener;
import com.jeek.calendar.widget.calendar.month.MonthCalendarView;
import com.jeek.calendar.widget.calendar.month.MonthView;
import com.jeek.calendar.widget.calendar.week.WeekCalendarView;
import com.jeek.calendar.widget.calendar.week.WeekView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.yijian.staff.R;
import com.yijian.staff.mvp.main.mine.calendartable.bean.CourseInfo;
import com.yijian.staff.mvp.main.mine.calendartable.bean.DayTask;
import com.yijian.staff.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.security.auth.login.LoginException;

/**
 * 周视图
 */
public class WeekFragment_ycm extends Fragment  {

    private static final String TAG = "WeekFragment_ycm";
    private static WeekFragment_ycm weekFragment;
    private LinearLayout llCalendar;
    private int mCurrentSelectYear;
    private int mCurrentSelectMonth;
    private int mCurrentSelectDay;
    private WeekCalendarView wcvCalendar;
//    private MonthCalendarView mcvCalendar;
    private RecyclerView rv_day;
    private AdapterWeekFragment adapter;

    OnChangeDateListener onChangeDateListener;

    public void setOnChangeDateListener(OnChangeDateListener onChangeDateListener) {
        this.onChangeDateListener = onChangeDateListener;
    }

    public static WeekFragment_ycm getInstance(){
        if(weekFragment == null){
            weekFragment = new WeekFragment_ycm();
        }
        return weekFragment;
    }
    private Handler handler=new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week_ycm, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ImageView ivToggle = view.findViewById(R.id.iv_toggle);

        wcvCalendar = view.findViewById(R.id.wcvCalendar);
//        mcvCalendar = view.findViewById(R.id.mcvCalendar);
        llCalendar = view.findViewById(R.id.ll_calendar);

//        mcvCalendar.setOnCalendarClickListener(mMonthCalendarClickListener);

        wcvCalendar.setOnCalendarClickListener(mWeekCalendarClickListener);

        /*ivToggle.setOnClickListener(new View.OnClickListener() {
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
        });*/

        rv_day = view.findViewById(R.id.recyclerview);
        rv_day.setNestedScrollingEnabled(false);
        GridLayoutManager layout = new GridLayoutManager(getContext(),7);

        rv_day.setLayoutManager(layout);
        adapter = new AdapterWeekFragment(getContext());
        rv_day.setAdapter(adapter);
        initData();
       /* handler.post(new Runnable() {
            @Override
            public void run() {
                int height = llCalendar.getHeight();
                float range = height - getDependentViewCollapsedHeight();
                llCalendar.setTranslationY(-range);
            }
        });*/
    }


    private float getDependentViewCollapsedHeight() {
        return getContext().getResources().getDimension(R.dimen.collapsed_header_height);
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


    private OnCalendarClickListener mWeekCalendarClickListener = new OnCalendarClickListener() {
        @Override
        public void onClickDate(int year, int month, int day) {
//            mcvCalendar.setOnCalendarClickListener(null);
            int months = CalendarUtils.getMonthsAgo(mCurrentSelectYear, mCurrentSelectMonth, year, month);
            resetCurrentSelectDate(year, month, day);
            /*if (months != 0) {
                int position = mcvCalendar.getCurrentItem() + months;
                mcvCalendar.setCurrentItem(position, false);
            }*/
            resetMonthView();
//            mcvCalendar.setOnCalendarClickListener(mMonthCalendarClickListener);

        }

        @Override
        public void onPageChange(int year, int month, int day) {
            Log.e("Test","year222==="+year+"  month==="+month + "   day==="+day);
            Log.e("Test","year222==="+year+"  month==="+month + "   day==="+day);
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
//            computeCurrentRowsIsSix(year, month);
            Log.e("Test","year==="+year+"  month==="+month + "   day==="+day);
            CalendarDay calendarDay = CalendarDay.from(year, month, day);
            onChangeDateListener.onChangeDate(calendarDay);

            //添加小圆点
            List<String> dateList = new ArrayList<String>();
            dateList.add("2018-5-17");
            dateList.add("2018-5-18");
            dateList.add("2018-5-19");
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
        /*MonthView monthView = mcvCalendar.getCurrentMonthView();
        if (monthView != null) {
            monthView.setSelectYearMonth(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
            monthView.invalidate();
        }*/
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
        List<DayTask> list=new ArrayList<>();

        DayTask dayTask = new DayTask();
        dayTask.setName("星期一");

        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setName("语文课");
        courseInfo.setStart("2008-03-10 10:25:02");
        courseInfo.setEnd("2008-03-10 11:25:02");

        CourseInfo courseInfo1 = new CourseInfo();
        courseInfo1.setName("英语课");
        courseInfo1.setStart("2008-03-10 14:00:02");
        courseInfo1.setEnd("2008-03-10 17:00:02");
        dayTask.setCourseInfos(Arrays.asList(courseInfo,courseInfo1));

        list.add(dayTask);



        DayTask dayTask1 = new DayTask();
        dayTask1.setName("星期二");

        CourseInfo courseInfoa = new CourseInfo();
        courseInfoa.setName("语文课");
        courseInfoa.setStart("2008-03-12 7:15:02");
        courseInfoa.setEnd("2008-03-12 10:55:06");

        CourseInfo courseInfob = new CourseInfo();
        courseInfob.setName("英语课");
        courseInfob.setStart("2008-03-12 17:06:02");
        courseInfob.setEnd("2008-03-12 17:56:55");
        dayTask1.setCourseInfos(Arrays.asList(courseInfoa,courseInfob));
        list.add(dayTask1);

        adapter.resetData(list);
        adapter.setICourseListener(new AdapterWeekFragment.ICourseListener() {
            @Override
            public void onClick(CourseInfo courseInfo) {
                Toast.makeText(getContext(),""+courseInfo.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
