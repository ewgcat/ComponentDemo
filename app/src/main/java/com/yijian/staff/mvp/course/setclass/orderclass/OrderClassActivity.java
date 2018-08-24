package com.yijian.staff.mvp.course.setclass.orderclass;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.yijian.staff.R;
import com.yijian.staff.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.main.mine.calendartable.OnChangeDateListener;
import com.yijian.staff.mvp.main.mine.calendartable.TitleChanger;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/test/15")
public class OrderClassActivity extends MvcBaseActivity implements OnChangeDateListener {

    /**
     * Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
     */
    private static final String[] FRAGMENT_TAG = {"DayFragment", "WeekFragment"};
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRESELECTEDINDEX = "PREV_SELECTED_INDEX";
    private static final String TAG = OrderClassActivity.class.getSimpleName();

    //    private DayFragment dayFragment;  //日视图Fragment
    private OrderClassDayFragment dayFragment;  //日视图Fragment
    //    private WeekFragment weekFragment;  //周视图Fragment
    private OrderClassWeekFragment weekFragment;  //周视图Fragment

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

    public static int ORDER_REFRESH_REQUESTCODE = 110;

    private String pushDate = "";
    private CalendarDay currentDay_D; //日视图日期
    private CalendarDay currentDay_W; //周视图日期
    private int currentIndex = 0; //记录现在tab的位置



    @Override
    protected int getLayoutID() {
        return R.layout.activity_order_class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitle();
        initView();
    }

    private void initView() {
        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_course_appoint_info",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        pushDate = getIntent().getStringExtra("date");
        Calendar calendar = Calendar.getInstance();
        currentDay = CalendarDay.from(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        titleChanger = new TitleChanger(tv_change_date);
        titleChanger.setTitleFormatter(new DateFormatTitleFormatter(new SimpleDateFormat("yyyy年MM月")));
        titleChanger.setPreviousMonth(currentDay);
        titleChanger.change(currentDay);
        currentDay_D = currentDay;
        currentDay_W = currentDay;
        selectTab(0);
        SharePreferenceUtil.setHasNewYueKePush(false);

    }


    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_activity_navigation_bar2);
        navigationBar2.setTitle("排课信息");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
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
        currentIndex = index;
        setBotoomStyle(index);
        hideFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (index) {

            case 0:
                onChangeDate(currentDay_D);
                if (dayFragment == null) {
                    dayFragment = OrderClassDayFragment.getInstance();
                    Bundle dayBundle = new Bundle();
                    dayBundle.putString("date",pushDate);
                    dayFragment.setArguments(dayBundle);
                    dayFragment.setOnChangeDateListener(this);
                    transaction.add(R.id.fl_calendarTab, dayFragment, "Homefragment");

                }
                transaction.show(dayFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                onChangeDate(currentDay_W);
                if (weekFragment == null) {
                    weekFragment = OrderClassWeekFragment.getInstance();
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
        if (dayFragment != null) {
            ft.hide(dayFragment);
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
        if(currentIndex == 0){
           currentDay_D = calendarDay;
        }else if(currentIndex == 1){
            currentDay_W = calendarDay;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ORDER_REFRESH_REQUESTCODE) {
            if (dayFragment != null) {
                dayFragment.onActivityResult(requestCode, resultCode, data);
            }
            if (weekFragment != null) {
                weekFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
            Logger.i(TAG,"push_message");

    }
    
}
