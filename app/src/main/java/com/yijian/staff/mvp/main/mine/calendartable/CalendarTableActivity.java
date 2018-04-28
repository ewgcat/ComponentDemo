package com.yijian.staff.mvp.mine.calendartable;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 日程表
 */
public class CalendarTableActivity extends AppCompatActivity implements OnChangeDateListener {

    /**
     * Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
     */
    private static final String[] FRAGMENT_TAG = {"DayFragment", "WeekFragment"};
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRESELECTEDINDEX = "PREV_SELECTED_INDEX";
    private int selectedIndex = 0;

    //    private DayFragment dayFragment;  //日视图Fragment
    private DayFragment_ycm dayFragment_ycm;  //日视图Fragment
    //    private WeekFragment weekFragment;  //周视图Fragment
    private WeekFragment_ycm weekFragment;  //周视图Fragment

    @BindView(R.id.tv_tab_day)
    TextView tv_tab_day;  //日视图的选项卡
    @BindView(R.id.tv_tab_week)
    TextView tv_tab_week;  //周视图的选项卡
    @BindView(R.id.view_line_day)
    View view_line_day;   //日视图的下划线
    @BindView(R.id.view_line_week)
    View view_line_week;  //周视图的下划线
    @BindView(R.id.tv_change_date)
    TextView tv_change_date;
    TitleChanger titleChanger;
    OnChangeDateListener onChangeDateListener;
    CalendarDay currentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_table);
        ButterKnife.bind(this);
        initTitle();
        initView();
    }

    private void initView() {
        currentDay = CalendarDay.today();
        titleChanger = new TitleChanger(tv_change_date);
        titleChanger.setTitleFormatter(new DateFormatTitleFormatter(new SimpleDateFormat("yyyy年MM月")));
        titleChanger.setPreviousMonth(currentDay);
        titleChanger.change(currentDay);
        selectTab(0);
    }


    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_activity_navigation_bar2);
        navigationBar2.setTitle("日程表");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setmRightTvText("设置");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalendarTableActivity.this, CalendarSettingActivity.class));

            }
        });
    }

    @OnClick({R.id.lin_day, R.id.lin_week})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.lin_day: //日视图
                selectTab(0);
                break;
            case R.id.lin_week: //周视图
                selectTab(1);
                break;
        }
    }

    public void selectTab(int index) {
        selectedIndex = index;
        setBotoomStyle(index);
        hideFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        hideAllIndex(transaction);
//        switch (index) {
//            case 0:
////                popFragement(transaction, dayFragment, index);
//                popFragement(transaction, dayFragment_ycm, index);
//                break;
//            case 1:
//                popFragement(transaction, weekFragment, index);
//                break;
//        }
//        transaction.commit();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (index) {

            case 0:

                if (dayFragment_ycm == null) {
                    dayFragment_ycm = DayFragment_ycm.getInstance();
                    dayFragment_ycm.setOnChangeDateListener(this);
                    transaction.add(R.id.fl_calendarTab, dayFragment_ycm, "Homefragment");
                }
                transaction.show(dayFragment_ycm);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                if (weekFragment == null) {
                    weekFragment = WeekFragment_ycm.getInstance();
                    weekFragment.setOnChangeDateListener(this);
                    transaction.add(R.id.fl_calendarTab, weekFragment, "Homefragment");
                }
                transaction.show(weekFragment);
                transaction.commitAllowingStateLoss();
                break;
        }


    }

//    public void popFragement(FragmentTransaction transaction, Fragment fragment, int index) {
//        if (fragment == null) {
//            // 如果ViperFragment为空，则创建一个并添加到界面上
//            if (index == 0) {
////                dayFragment = DayFragment.getInstance();
////                dayFragment.setOnChangeDateListener(this);
////                transaction.add(R.id.fl_calendarTab, dayFragment, FRAGMENT_TAG[index]);
//
//                dayFragment_ycm = DayFragment_ycm.getInstance();
//                transaction.add(R.id.fl_calendarTab, dayFragment_ycm, FRAGMENT_TAG[index]);
//                transaction.show(dayFragment_ycm);
//
//            } else if (index == 1) {
////                weekFragment = WeekFragment.getInstance();
////                transaction.add(R.id.fl_calendarTab, weekFragment, FRAGMENT_TAG[index]);
//
//                weekFragment= WeekFragment_ycm.getInstance();
//                transaction.add(R.id.fl_calendarTab, weekFragment, FRAGMENT_TAG[index]);
//                transaction.show(weekFragment);
//            }
//        } else {
//            // 如果ViperFragment不为空，则直接将它显示出来
//            transaction.show(fragment);
//        }
//
//    }

//    隐藏所有的Fragment
//    public void hideAllIndex(FragmentTransaction fragmentTransaction) {
//        Fragment fragment = DayFragment.getInstance();
//        if (fragment.isAdded()) {
//            fragmentTransaction.hide(fragment);
//        }
//        fragment = WeekFragment.getInstance();
//        if (fragment.isAdded()) {
//            fragmentTransaction.hide(fragment);
//        }
//    }

    private void hideFragment() {
        FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction ft = fm.beginTransaction();
        if (dayFragment_ycm != null) {
            ft.hide(dayFragment_ycm);
        }
        if (weekFragment != null) {
            ft.hide(weekFragment);
        }
        ft.commitAllowingStateLoss();
    }

    public void setBotoomStyle(int index) {
        tv_tab_day.setTextColor(index == 0 ? Color.parseColor("#333333") : Color.parseColor("#999999"));
        tv_tab_week.setTextColor(index == 0 ? Color.parseColor("#999999") : Color.parseColor("#333333"));
        view_line_day.setVisibility(index == 0 ? View.VISIBLE : View.GONE);
        view_line_week.setVisibility(index == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onChangeDate(CalendarDay calendarDay) {
        titleChanger.setPreviousMonth(currentDay);
        titleChanger.change(calendarDay);
        currentDay = calendarDay;
    }

}
