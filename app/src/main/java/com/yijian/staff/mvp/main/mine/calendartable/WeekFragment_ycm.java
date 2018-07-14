package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Context;
import android.content.Intent;
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
import com.yijian.staff.mvp.course.setclass.ExperienceClassRecordActivity;
import com.yijian.staff.mvp.course.setclass.OpenLessonNewActivity;
import com.yijian.staff.mvp.main.mine.calendartable.AdapterWeekFragment;
import com.yijian.staff.mvp.main.mine.calendartable.OnChangeDateListener;
import com.yijian.staff.mvp.main.mine.calendartable.bean.DayTask;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.Logger;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

import static com.yijian.staff.mvp.course.setclass.orderclass.OrderClassActivity.ORDER_REFRESH_REQUESTCODE;

/**
 * 周视图
 */
public class WeekFragment_ycm extends Fragment {

    private static final String TAG = "WeekFragment_ycm";
    private static WeekFragment_ycm weekFragment;
    private int mCurrentSelectYear;
    private int mCurrentSelectMonth;
    private int mCurrentSelectDay;
    private WeekCalendarView wcvCalendar;
    private RecyclerView rv_day;
    private AdapterWeekFragment adapter;
    private List<DayTask> dayTaskList = new ArrayList<>();

    OnChangeDateListener onChangeDateListener;

    public void setOnChangeDateListener(OnChangeDateListener onChangeDateListener) {
        this.onChangeDateListener = onChangeDateListener;
    }

    public static WeekFragment_ycm getInstance() {
        if (weekFragment == null) {
            weekFragment = new WeekFragment_ycm();
        }
        return weekFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week_ycm, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        wcvCalendar = view.findViewById(R.id.wcvCalendar);
        wcvCalendar.setOnCalendarClickListener(mWeekCalendarClickListener);
        rv_day = view.findViewById(R.id.recyclerview);
        rv_day.setNestedScrollingEnabled(false);
        GridLayoutManager layout = new GridLayoutManager(getContext(), 7);

        rv_day.setLayoutManager(layout);
        adapter = new AdapterWeekFragment(getContext());
        rv_day.setAdapter(adapter);
        adapter.setICourseListener(new AdapterWeekFragment.ICourseListener() {
            @Override
            public void onClick(DayTask.CoursesBean courseInfo) {
                int status = courseInfo.getStatus();
                if(status == 2){
                    Toast.makeText(getActivity(),"该课已取消预约",Toast.LENGTH_SHORT).show();
                }else if(status == 4){
                    Toast.makeText(getActivity(),"该课已爽约",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(), OpenLessonNewActivity.class);
                    intent.putExtra("startDate", courseInfo.getStartDate());
                    intent.putExtra("startTimeActual", courseInfo.getStartTimeActual());
                    intent.putExtra("endTimeActual", courseInfo.getEndTimeActual());
                    intent.putExtra("punchStatus", courseInfo.getPunchStatus());
                    intent.putExtra("privateApplyId", courseInfo.getId());
                    startActivityForResult(intent, ORDER_REFRESH_REQUESTCODE);
                }
            }

        });
        initData();
        initDayTaskList();
    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        resetCurrentSelectDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void resetCurrentSelectDate(int year, int month, int day) {
        mCurrentSelectYear = year;
        mCurrentSelectMonth = month;
        mCurrentSelectDay = day;
    }


    private OnCalendarClickListener mWeekCalendarClickListener = new OnCalendarClickListener() {
        @Override
        public void onClickDate(int year, int month, int day) {
            int months = CalendarUtils.getMonthsAgo(mCurrentSelectYear, mCurrentSelectMonth, year, month);
            resetCurrentSelectDate(year, month, day);
            resetMonthView();
        }

        @Override
        public void onPageChange(int year, int month, int day) {
            Log.e("Test", "year222===" + year + "  month===" + month + "   day===" + day);
            Log.e("Test", "year222===" + year + "  month===" + month + "   day===" + day);
            CalendarDay calendarDay = CalendarDay.from(year, month, day);
            onChangeDateListener.onChangeDate(calendarDay);
           /*
           每次滑动周视图的时候，设置成每周的第一天
           wcvCalendar.getCurrentWeekView().setSelectYearMonth(year,month,1);*/
            wcvCalendar.getCurrentWeekView().setSelectYearMonth(year, month, day);
            initDayTaskList();
        }
    };

    private void resetMonthView() {
        onDataChanged(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
    }

    //当日期被改变后
    private void onDataChanged(int mCurrentSelectYear, int mCurrentSelectMonth, int mCurrentSelectDay) {

    }

    /**
     * 初始化周视图列表数据
     */
    private void initDayTaskList() {
        dayTaskList.clear();
        WeekView weekView;
        if (wcvCalendar.getCurrentWeekView() == null) {
            weekView = wcvCalendar.getWeekAdapter().instanceWeekView(wcvCalendar.getCurrentItem());
        } else {
            weekView = wcvCalendar.getCurrentWeekView();
        }
        for (int i = 0; i < 7; i++) {
            DayTask dayTask = new DayTask();
            DateTime dateTime = weekView.getStartDate().plusDays(i);
            dayTask.setStartDate(dateTime.getYear() + "-" + dateTime.getMonthOfYear() + "-" + dateTime.getDayOfMonth());
            dayTaskList.add(dayTask);
        }
        //添加测试数据
        initDayCanlendarInfoList();
    }

    private void initDayCanlendarInfoList() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("leftTime", dayTaskList.get(0).getStartDate());
        map.put("rightTime", dayTaskList.get(dayTaskList.size() - 1).getStartDate());
        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_PRIVATEAPPLYBYWEEK_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    JSONArray jsonArray = (JSONArray) result.get("mapList");
                    List<DayTask> dayTasks = com.alibaba.fastjson.JSONArray.parseArray(jsonArray.toString(), DayTask.class);
                    Log.e(TAG, "onSuccess:000 " + dayTasks.size());
                    for (DayTask dayTask : dayTasks) {
                        for (int i = 0; i < dayTaskList.size(); i++) {
                            DayTask curDayTask = dayTaskList.get(i);
                            if (dayTask.equals(curDayTask)) {
                                dayTaskList.set(i, dayTask);
                            }
                        }
                    }

                    Log.e(TAG, "onSuccess: " + dayTaskList.toString());
                    adapter.resetData(dayTaskList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ORDER_REFRESH_REQUESTCODE) {
            initDayCanlendarInfoList();
        }
    }
*/

}
