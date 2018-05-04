package com.yijian.staff.mvp.coach.setclass.orderclass;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.yijian.staff.mvp.main.mine.calendartable.DayCanlendarAdapter;
import com.yijian.staff.mvp.main.mine.calendartable.DayCanlendarInfo;
import com.yijian.staff.mvp.main.mine.calendartable.OnChangeDateListener;
import com.yijian.staff.mvp.coach.setclass.bean.OrderClassDayBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderClassDayFragment extends Fragment {
    private static final String TAG = "DayFragment_ycm";
    private static OrderClassDayFragment dayFragment;
    private LinearLayout llCalendar;
    private int mCurrentSelectYear;
    private int mCurrentSelectMonth;
    private int mCurrentSelectDay;
    private WeekCalendarView wcvCalendar;
    private MonthCalendarView mcvCalendar;
    private RecyclerView rv_day;
    List<OrderClassDayBean> orderClassDayBeanList = new ArrayList<OrderClassDayBean>();
    OrderclassDayAdapter dayCanlendarAdapter;
    OnChangeDateListener onChangeDateListener;

    public void setOnChangeDateListener(OnChangeDateListener onChangeDateListener) {
        this.onChangeDateListener = onChangeDateListener;
    }

    public static OrderClassDayFragment getInstance(){
        if(dayFragment == null){
            dayFragment = new OrderClassDayFragment();
        }
        return dayFragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_class_day, container, false);
        initView(view);
        initData();

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

        Calendar calendar = Calendar.getInstance();
        resetCurrentSelectDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        rv_day = view.findViewById(R.id.rv);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
        //设置RecyclerView 布局
        rv_day.setLayoutManager(layoutmanager);
        dayCanlendarAdapter = new OrderclassDayAdapter(getActivity(), orderClassDayBeanList);
        rv_day.setAdapter(dayCanlendarAdapter);

    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        resetCurrentSelectDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        loadDayData(new Date());
        loadPreviewDayData(new Date());

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
            int position = 0;
            if (months != 0) {
                position = mcvCalendar.getCurrentItem() + months;
                mcvCalendar.setCurrentItem(position, false);
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(year + "-" + month + "-" + day);
                loadDayData(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            resetMonthView();
            mcvCalendar.setOnCalendarClickListener(mMonthCalendarClickListener);
        }

        @Override
        public void onPageChange(int year, int month, int day) {
            Log.e("Test", "year===" + year + "  month===" + month + "   day===" + day);

            CalendarDay calendarDay = CalendarDay.from(year, month, day);
            onChangeDateListener.onChangeDate(calendarDay);
            /*
            没切换日历页面的时候设置成每个月的第一天
            int startDay = wcvCalendar.getCurrentWeekView().getStartDate().plusDays(0).getDayOfMonth();
            int startYear = wcvCalendar.getCurrentWeekView().getStartDate().plusDays(0).getYear();
            int startMonth = wcvCalendar.getCurrentWeekView().getStartDate().plusDays(0).getMonthOfYear();
            wcvCalendar.getCurrentWeekView().selectWeekDay(startYear, startMonth, startDay);


            mcvCalendar.setOnCalendarClickListener(null);
            int months = CalendarUtils.getMonthsAgo(mCurrentSelectYear, mCurrentSelectMonth, startYear, startMonth);
            resetCurrentSelectDate(startYear, startMonth, startDay);*/

            wcvCalendar.getCurrentWeekView().selectWeekDay(year, month, day);


            mcvCalendar.setOnCalendarClickListener(null);
            int months = CalendarUtils.getMonthsAgo(mCurrentSelectYear, mCurrentSelectMonth, year, month);
            resetCurrentSelectDate(year, month, day);


            int position = 0;
            if (months != 0) {
                position = mcvCalendar.getCurrentItem() + months;
                mcvCalendar.setCurrentItem(position, false);
            }

            resetMonthView();
            mcvCalendar.setOnCalendarClickListener(mMonthCalendarClickListener);


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

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(year + "-" + month + "-" + day);
                loadDayData(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            resetWeekView(position);
            wcvCalendar.setOnCalendarClickListener(mWeekCalendarClickListener);
        }

        @Override
        public void onPageChange(int year, int month, int day) {
            Log.e("Test", "year===" + year + "  month===" + month + "   day===" + day);
            CalendarDay calendarDay = CalendarDay.from(year, month, day);
            onChangeDateListener.onChangeDate(calendarDay);

            loadPreviewDayData(year + "-" + month);

            //添加小圆点
           /* List<String> dateList = new ArrayList<String>();
            dateList.add("2018-5-2");
            dateList.add("2018-5-3");
            dateList.add("2018-5-4");
            dateList.add("2018-6-2");
            wcvCalendar.getCurrentWeekView().addDateTaskHint(dateList);
            mcvCalendar.getCurrentMonthView().addDateTaskHint(dateList);*/
            mcvCalendar.getCurrentMonthView().selectMonthDay(year, month, 1);

            wcvCalendar.setOnCalendarClickListener(null);
            int weeks = CalendarUtils.getWeeksAgo(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay, year, month, 1);
            resetCurrentSelectDate(year, month, 1);
            int position = wcvCalendar.getCurrentItem() + weeks;
            if (weeks != 0) {
                wcvCalendar.setCurrentItem(position, false);
            }
            resetWeekView(position);
            wcvCalendar.setOnCalendarClickListener(mWeekCalendarClickListener);

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
        onDataChanged(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);

    }

    private void resetMonthView() {
        MonthView monthView = mcvCalendar.getCurrentMonthView();
        if (monthView != null) {
            monthView.setSelectYearMonth(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
            monthView.invalidate();
        }
        onDataChanged(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
    }

    //当日期被改变后
    private void onDataChanged(int mCurrentSelectYear, int mCurrentSelectMonth, int mCurrentSelectDay) {

    }


    public void loadDayData(Date strDate) {
        Map<String,String> map = new HashMap<String,String>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        map.put("dateStr",simpleDateFormat.format(strDate));
        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_ORDER_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                orderClassDayBeanList.clear();
                JSONArray records = JsonUtil.getJsonArray(result, "list");
                orderClassDayBeanList = com.alibaba.fastjson.JSONArray.parseArray(records.toString(),OrderClassDayBean.class);
                dayCanlendarAdapter.resetDataList(orderClassDayBeanList);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadPreviewDayData(Object strDate) {
        Map<String, String> map = new HashMap<String, String>();
        if (strDate instanceof Date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            map.put("month", simpleDateFormat.format(strDate));
        } else if (strDate instanceof String) {
            map.put("month", (String) strDate);
        }
        HttpManager.getHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_DATES_ORDER_URL, map, new ResultJSONArrayObserver() {

            @Override
            public void onSuccess(JSONArray result) {
                //添加小圆点
                List<String> dateStrList = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), String.class);
                mcvCalendar.getCurrentMonthView().addDateTaskHint(dateStrList);
                if (wcvCalendar.getCurrentWeekView() == null) {
                    WeekView weekView = wcvCalendar.getWeekAdapter().instanceWeekView(wcvCalendar.getCurrentItem());
                    weekView.addDateTaskHint(dateStrList);
                }else{
                    wcvCalendar.getCurrentWeekView().addDateTaskHint(dateStrList);
                }
                Log.e("Test", "loadPreviewDayData.....");
                wcvCalendar.getCurrentItem();

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }


}
