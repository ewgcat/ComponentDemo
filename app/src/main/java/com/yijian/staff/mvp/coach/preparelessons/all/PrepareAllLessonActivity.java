package com.yijian.staff.mvp.coach.preparelessons.all;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jeek.calendar.widget.calendar.CalendarUtils;
import com.jeek.calendar.widget.calendar.OnCalendarClickListener;
import com.jeek.calendar.widget.calendar.month.MonthCalendarView;
import com.jeek.calendar.widget.calendar.month.MonthView;
import com.jeek.calendar.widget.calendar.month.OnMonthClickListener;
import com.jeek.calendar.widget.calendar.week.WeekCalendarView;
import com.jeek.calendar.widget.calendar.week.WeekView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.createlession.ActionBean;
import com.yijian.staff.mvp.coach.preparelessons.createlession.ActionViewAdapter;
import com.yijian.staff.mvp.coach.preparelessons.createlession.EditActionObservable;
import com.yijian.staff.mvp.coach.preparelessons.createlession.SubActionBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 所有私教课备课
 */
public class PrepareAllLessonActivity extends AppCompatActivity {

    @BindView(R.id.tv_depart)
    TextView tv_depart;
    @BindView(R.id.rv_action_content)
    RecyclerView rv_action_content;
    @BindView(R.id.tv_lesson_name)
    TextView tv_lesson_name;
    @BindView(R.id.tv_lesson_num)
    TextView tv_lesson_num;
    @BindView(R.id.tv_lesson_time)
    TextView tv_lesson_time;
    @BindView(R.id.nestScrollView)
    NestedScrollView nestScrollView;
    @BindView(R.id.mcvCalendar)
    MonthCalendarView mcvCalendar;
    @BindView(R.id.wcvCalendar)
    WeekCalendarView wcvCalendar;

    private int mCurrentSelectYear;
    private int mCurrentSelectMonth;
    private int mCurrentSelectDay;

    List<PrepareLessonAllBean.PrepareListBean> prepareLessonAllBeanList = new ArrayList<>(); //装载RecyclerView的集合
    PrepareAllActionAdapter prepareAllActionAdapter;
    EditActionObservable editActionObservable = new EditActionObservable();
    String memberId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_all_lesson);
        ButterKnife.bind(this);
        initTitle();
        intView();
        initData();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar2);
        navigationBar2.setTitle("所有课程安排");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

    private void intView() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_action_content.setLayoutManager(layoutmanager);
        prepareAllActionAdapter = new PrepareAllActionAdapter(prepareLessonAllBeanList, editActionObservable, this);
        rv_action_content.setAdapter(prepareAllActionAdapter);

        mcvCalendar.setOnCalendarClickListener(mMonthCalendarClickListener);

        wcvCalendar.setOnCalendarClickListener(mWeekCalendarClickListener);

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
        }
    };

    private void resetCurrentSelectDate(int year, int month, int day) {
        mCurrentSelectYear = year;
        mCurrentSelectMonth = month;
        mCurrentSelectDay = day;
    }


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
        onDataChanged(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
    }
    //当日期被改变后
    private void onDataChanged(int mCurrentSelectYear, int mCurrentSelectMonth, int mCurrentSelectDay) {
        Log.e("Test","日期发生改变......");
    }

    private void initData() {
        /****************** 初始化动作内容选项数据 **************************/
       /* ActionBean actionBean1 = new ActionBean("1", "简单", "部位","平板支撑", "1组/1次", "无");
        ActionBean actionBean2 = new ActionBean("2", "中等", "部位","平板支撑2", "2组2次", "有");
        ActionBean actionBean3 = new ActionBean("3", "困难", "部位","平板支撑3", "3组/3次", "无");
        actionBeanList.add(actionBean1);
        actionBeanList.add(actionBean2);
        actionBeanList.add(actionBean3);
        prepareAllActionAdapter.notifyDataSetChanged();*/
        memberId = getIntent().getStringExtra("memberId");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(new Date());
        queryAllLesson(dateStr);
    }

    private void queryAllLesson(String dateStr) {
        Map<String, String> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("dateStr", dateStr);
        HttpManager.getHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_MEMBERCOURSE_URL, map, new ResultJSONArrayObserver() {
            @Override
            public void onSuccess(JSONArray result) {
                List<PrepareLessonAllBean> prepareLessonAllBeans = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), PrepareLessonAllBean.class);
                if (prepareLessonAllBeans.size() > 0) {
                    updateUI(prepareLessonAllBeans.get(0));
                    nestScrollView.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(PrepareAllLessonActivity.this,"暂无数据",Toast.LENGTH_SHORT).show();
                    nestScrollView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFail(String msg) {
                Log.e("Test",msg);
            }
        });
    }

    private void updateUI(PrepareLessonAllBean prepareLessonAllBean) {
        tv_lesson_name.setText(prepareLessonAllBean.getCourseName());
        tv_lesson_num.setText(prepareLessonAllBean.getCourseNum());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = "";
        try {
            Date date = simpleDateFormat.parse(prepareLessonAllBean.getStartDate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            dateStr = calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_lesson_time.setText(dateStr+" "+prepareLessonAllBean.getStartTime()+"-"+prepareLessonAllBean.getEndTime());
        prepareLessonAllBeanList.clear();
        prepareLessonAllBeanList.addAll(prepareLessonAllBean.getPrepareList());
        prepareAllActionAdapter.notifyDataSetChanged();
    }



    /**
     * 点击头部 监听各分组的隐藏和显示
     *
     * @param itemPosition
     */
    public void notifyClickHeader(int itemPosition, int eventType) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "6");
        map.put("itemPosition", itemPosition + "");
        editActionObservable.notifyObservers(map);
    }


}
