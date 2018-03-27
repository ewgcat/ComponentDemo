package com.yijian.staff.mvp.mine.calendartable;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.mine.calendartable.subdayfragment.SubDayOneFragment;
import com.yijian.staff.mvp.mine.calendartable.subdayfragment.SubDayThreeFragment;
import com.yijian.staff.mvp.mine.calendartable.subdayfragment.SubDayTwoFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 日视图
 */
public class DayFragment extends Fragment implements View.OnClickListener {

    private static DayFragment dayFragment;
    public static DayFragment getInstance(){
        if(dayFragment == null){
            dayFragment = new DayFragment();
        }
        return dayFragment;
    }

    MaterialCalendarView materialCalendarView;//布局内的控件
    LinearLayout lin_expand; //日历伸缩按钮
    private boolean isShowCanlendar = false; //设置是否展开日历的标识位
    OnChangeDateListener onChangeDateListener;

    public void setOnChangeDateListener(OnChangeDateListener onChangeDateListener) {
        this.onChangeDateListener = onChangeDateListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        materialCalendarView = view.findViewById(R.id.calendarView);
        lin_expand = view.findViewById(R.id.lin_expand);
        lin_expand.setOnClickListener(this);
        ViewPager vp = view.findViewById(R.id.view_pager);
        /*******************START  添加页面滑动 *******************************/
        final List<Fragment> list = new ArrayList<>();
        list.add(new SubDayThreeFragment());
        list.add(new SubDayOneFragment());
        list.add(new SubDayTwoFragment());
        list.add(new SubDayThreeFragment());
        list.add(new SubDayOneFragment());
        vp.setAdapter(new FragmentListAdapter(getActivity().getSupportFragmentManager(), list));
        vp.setCurrentItem(2);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    //延迟切换以便切换动画显示
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            vp.setCurrentItem(list.size() - 2, false);
                        }
                    }, 100);
                } else if (position == list.size() - 1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            vp.setCurrentItem(1, false);
                        }
                    }, 100);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /*******************END  添加页面滑动 *******************************/

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
        materialCalendarView.setTopbarVisible(false);
        materialCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Log.e("Test","月份===" + (date.getMonth() + 1) +  "年份===" + date.getYear());
                onChangeDateListener.onChangeDate(date);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lin_expand: //伸缩按钮
                if(!isShowCanlendar){
                    materialCalendarView.state().edit()
                            .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                            .setCalendarDisplayMode(CalendarMode.MONTHS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                            .commit();// 返回对象并保存
                }else {
                    materialCalendarView.state().edit()
                            .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                            .setCalendarDisplayMode(CalendarMode.WEEKS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                            .commit();// 返回对象并保存
                }
                isShowCanlendar = !isShowCanlendar;
                break;
        }
    }
}
