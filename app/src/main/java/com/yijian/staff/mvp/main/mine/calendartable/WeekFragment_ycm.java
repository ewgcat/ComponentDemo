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
import com.yijian.staff.mvp.coach.setclass.ExperienceClassRecordActivity;
import com.yijian.staff.mvp.coach.setclass.OpenLessonNewActivity;
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
    private RecyclerView rv_day;
    private AdapterWeekFragment adapter;
    private List<DayTask> dayTaskList = new ArrayList<>();

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
        llCalendar = view.findViewById(R.id.ll_calendar);
        wcvCalendar.setOnCalendarClickListener(mWeekCalendarClickListener);
        rv_day = view.findViewById(R.id.recyclerview);
        rv_day.setNestedScrollingEnabled(false);
        GridLayoutManager layout = new GridLayoutManager(getContext(),7);

        rv_day.setLayoutManager(layout);
        adapter = new AdapterWeekFragment(getContext());
        rv_day.setAdapter(adapter);
        adapter.setICourseListener(new AdapterWeekFragment.ICourseListener() {
            @Override
            public void onClick(DayTask.CoursesBean courseInfo) {
                Toast.makeText(getContext(),""+courseInfo.toString(),Toast.LENGTH_SHORT).show();

                if("0".equals(courseInfo.getIsExperience())){ // 0：私教课，
                    if(courseInfo.getIsPrepare() == 0){ // 备课

                    }else if(courseInfo.getIsPrepare() == 1){ // 上课
                        Intent intent = new Intent(getActivity(), OpenLessonNewActivity.class);
                        intent.putExtra("privateApplyId",courseInfo.getId());
                        intent.putExtra("startDateTime",courseInfo.getStartDatetime());
                        intent.putExtra("endDateTime",courseInfo.getEndDatetime());
                        intent.putExtra("startDate",courseInfo.getStartDate());
                        intent.putExtra("punchStatus",courseInfo.getPunchStatus());
                        startActivity(intent);
                    }
                }else if("1".equals(courseInfo.getIsExperience())){//1：体验课
                    if("0".equals(courseInfo.getIsUseTemplate())){ //体验课：0：用体侧模板，1：私教课模板 ,
                        Intent intent = new Intent(getActivity(), ExperienceClassRecordActivity.class);
                        intent.putExtra("privateApplyId",courseInfo.getId());
                        startActivity(intent);
                    }else if("1".equals(courseInfo.getIsUseTemplate())){
                        Intent intent = new Intent(getActivity(), OpenLessonNewActivity.class);
                        intent.putExtra("privateApplyId",courseInfo.getId());
                        intent.putExtra("startDateTime",courseInfo.getStartDatetime());
                        intent.putExtra("endDateTime",courseInfo.getEndDatetime());
                        intent.putExtra("startDate",courseInfo.getStartDate());
                        intent.putExtra("punchStatus",courseInfo.getPunchStatus());
                        startActivity(intent);
                    }

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
            Log.e("Test","year222==="+year+"  month==="+month + "   day==="+day);
            Log.e("Test","year222==="+year+"  month==="+month + "   day==="+day);
            CalendarDay calendarDay = CalendarDay.from(year, month, day);
            onChangeDateListener.onChangeDate(calendarDay);
            wcvCalendar.getCurrentWeekView().setSelectYearMonth(year,month,1);
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
    private void initDayTaskList(){
        dayTaskList.clear();
        WeekView weekView;
        if (wcvCalendar.getCurrentWeekView() == null) {
            weekView = wcvCalendar.getWeekAdapter().instanceWeekView(wcvCalendar.getCurrentItem());
        }else{
            weekView = wcvCalendar.getCurrentWeekView();
        }
        for (int i = 0; i < 7; i++) {
            DayTask dayTask = new DayTask();
            DateTime dateTime = weekView.getStartDate().plusDays(i);
            dayTask.setStartDate(dateTime.getYear()+"-"+dateTime.getMonthOfYear()+"-"+dateTime.getDayOfMonth());
            dayTaskList.add(dayTask);
        }
        //添加测试数据
        initDayCanlendarInfoList();
    }

    private void initDayCanlendarInfoList() {

        Map<String,String> map = new HashMap<String,String>();
        map.put("leftTime",dayTaskList.get(0).getStartDate());
        map.put("rightTime",dayTaskList.get(dayTaskList.size()-1).getStartDate());
        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_PRIVATEAPPLYBYWEEK_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    JSONArray jsonArray = (JSONArray) result.get("mapList");
                    List<DayTask> dayTasks = com.alibaba.fastjson.JSONArray.parseArray(jsonArray.toString(),DayTask.class);
                    for(DayTask dayTask : dayTasks){
                       for(int i = 0; i< dayTaskList.size(); i++){
                           DayTask curDayTask = dayTaskList.get(i);
                           if(dayTask.equals(curDayTask)){
                                dayTaskList.set(i,dayTask);
                           }
                       }
                    }
                    adapter.resetData(dayTaskList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
