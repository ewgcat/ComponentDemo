package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yijian.staff.mvp.course.setclass.orderclass.OrderClassActivity.ORDER_REFRESH_REQUESTCODE;

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
    private CalendarDay currentDay_D; //日视图日期
    private CalendarDay currentDay_W; //周视图日期

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_table);
        ButterKnife.bind(this);
        initTitle();
        initView();
    }

    private void initView() {
        Calendar calendar = Calendar.getInstance();
        currentDay = CalendarDay.from(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        titleChanger = new TitleChanger(tv_change_date);
        titleChanger.setTitleFormatter(new DateFormatTitleFormatter(new SimpleDateFormat("yyyy年MM月")));
        titleChanger.setPreviousMonth(currentDay);
        titleChanger.change(currentDay);
        currentDay_D = currentDay;
        currentDay_W = currentDay;
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (index) {

            case 0:
                onChangeDate(currentDay_D);
                if (dayFragment_ycm == null) {
                    dayFragment_ycm = DayFragment_ycm.getInstance();
                    dayFragment_ycm.setOnChangeDateListener(this);
                    transaction.add(R.id.fl_calendarTab, dayFragment_ycm, "Homefragment");
                }
                transaction.show(dayFragment_ycm);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                onChangeDate(currentDay_W);
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
        if(selectedIndex == 0){
            currentDay_D = calendarDay;
        }else if(selectedIndex == 1){
            currentDay_W = calendarDay;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ORDER_REFRESH_REQUESTCODE) {
            if (dayFragment_ycm != null) {
                dayFragment_ycm.onActivityResult(requestCode, resultCode, data);
            }
            if (weekFragment != null) {
                weekFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

}
