package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.JsonArray;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.yijian.staff.R;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import butterknife.OnClick;

public class CalendarSettingActivity extends AppCompatActivity {

    @BindView(R.id.calendarView)
    MaterialCalendarView materialCalendarView;//布局内的控件

    List<CalendarDay> hasClassList; //当日有课日期集合
    List<CalendarDay> disableAppointmentList; //不可预约日期集合
    EventDecorator eventRedDecorator; //设置小红点
    BlueEventDecorator eventBlueDecorator; //设置小蓝点
    @BindView(R.id.tv_previewTime)
    TextView tv_previewTime;
    @BindView(R.id.tv_internal)
    TextView tv_internal;
    public static final int REQUEST_CODE_SETTING_PREVIEW = 100;
    public static final int RESULT_CODE_SETTING_INTERNAL = 101;
    public static final int RESULT_CODE_SETTING_NO_PREVIEW = 102;
    private String startTime = "";
    private String endTime = "";
    private String intervalTime = "";
    private ArrayList<String> spaceTimeList = new ArrayList<>();
    private ArrayList<String> courseTimeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_setting);
        ButterKnife.bind(this);
        initTitle();
        initView();
        initData();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar2.setTitle("日程表设置");
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
    }

    private void initData() {

        hasClassList = new ArrayList<CalendarDay>();
        disableAppointmentList = new ArrayList<CalendarDay>();
    }

    private void initView() {
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE); //设置多选
        materialCalendarView.setSelectionColor(getResources().getColor(R.color.blue));
        materialCalendarView.addDecorator(new AllNoneSelectedDecorator());
        materialCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                loadData(date.getYear() + "-" + (date.getMonth() + 1));
            }
        });
        //编辑日历属性
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                .setMinimumDate(CalendarDay.from(2010, 4, 3))  //设置可以显示的最早时间
                .setMaximumDate(CalendarDay.from(2020, 5, 12))//设置可以显示的最晚时间
                .setCalendarDisplayMode(CalendarMode.MONTHS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                .commit();// 返回对象并保存

    }


    private void loadData(String strDate) {
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String newStrDate = "";
        try {
            Date date = simpleDateFormat.parse(strDate);
            newStrDate = simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        map.put("date", strDate);
        //教练查询当月是否有课或者休息
        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_GETLIST_TIME_URL + "?date=" + newStrDate, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    //不可约
                    JSONArray spaceTimeJsonArray = result.getJSONArray("spaceTime");
                    for (int i = 0; i < spaceTimeJsonArray.length(); i++) {
                        String spaceTime = (String) spaceTimeJsonArray.get(i);
                        spaceTimeList.add(spaceTime);
                    }

                    //当日有课
                    JSONArray courseTimeJsonArray = result.getJSONArray("courseTime");
                    for (int i = 0; i < courseTimeJsonArray.length(); i++) {
                        String courseTime = (String) courseTimeJsonArray.get(i);
                        courseTimeList.add(courseTime);
                    }

                    hasClassList = new ArrayList<CalendarDay>();
                    for (int i = 0; i < spaceTimeList.size(); i++) {
                        String[] spaceArray = spaceTimeList.get(i).split("-");
                        CalendarDay day = CalendarDay.from(Integer.parseInt(spaceArray[0]), Integer.parseInt(spaceArray[1]) - 1, Integer.parseInt(spaceArray[2]));
                        hasClassList.add(day);
                    }

                    disableAppointmentList = new ArrayList<CalendarDay>();
                    for (int i = 0; i < courseTimeList.size(); i++) {
                        String[] courseArray = courseTimeList.get(i).split("-");
                        CalendarDay day = CalendarDay.from(Integer.parseInt(courseArray[0]), Integer.parseInt(courseArray[1]) - 1, Integer.parseInt(courseArray[2]));
                        disableAppointmentList.add(day);
                    }

                    eventRedDecorator = new EventDecorator(Color.RED, hasClassList);
                    eventBlueDecorator = new BlueEventDecorator(Color.parseColor("#1997f8"), disableAppointmentList);
                    //设置多个不可选日期的颜色
                    materialCalendarView.addDecorator(eventRedDecorator);
                    //设置蓝色当日有课
                    materialCalendarView.addDecorator(eventBlueDecorator);
                    materialCalendarView.invalidateDecorators();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(CalendarSettingActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        // 查询工作时间与间隔时间
        HttpManager.postHasHeaderNoParam(HttpManager.COACH_PRIVATE_COURSE_GET_TIME_URL, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                if(result != null && result.length() > 0){
                    try {
                        if (!CommonUtil.isEmpty(result.getString("startTime")))
                            startTime = result.getString("startTime").substring(0, result.getString("startTime").lastIndexOf(":"));
                        if (!CommonUtil.isEmpty(result.getString("endTime")))
                            endTime = result.getString("endTime").substring(0, result.getString("endTime").lastIndexOf(":"));
                        intervalTime = result.getInt("intervalTime") + "";
                        tv_internal.setText(intervalTime);
                        if (!CommonUtil.isEmpty(result.getString("startTime")) || !CommonUtil.isEmpty(result.getString("endTime"))) {
                            tv_previewTime.setText(startTime + "-" + endTime);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(CalendarSettingActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick({R.id.rel_disable_date, R.id.rel_enable_preview, R.id.rel_internal})
    public void click(View v) {

        switch (v.getId()) {
            case R.id.rel_disable_date: //设置不可约日期
                CalendarDay calendarDay = materialCalendarView.getCurrentDate();
                String strDate = calendarDay.getYear() + "-" + (calendarDay.getMonth() + 1);
                Intent intent1 = new Intent(this, EditNotPreviewActivity.class);
                intent1.putExtra("date", strDate);
                intent1.putStringArrayListExtra("spaceTimeList", spaceTimeList);
                intent1.putStringArrayListExtra("courseTimeList", courseTimeList);
                startActivityForResult(intent1, RESULT_CODE_SETTING_NO_PREVIEW);
                break;
            case R.id.rel_enable_preview: //可约时间段
                Intent intent = new Intent(this, PreviewTimeActivity.class);
                intent.putExtra("startTime", startTime);
                intent.putExtra("endTime", endTime);
                startActivityForResult(intent, REQUEST_CODE_SETTING_PREVIEW);
                break;
            case R.id.rel_internal: //课程间隔时间
                Intent intent2 = new Intent(this, CourseInterActivity.class);
                intent2.putExtra("internal", tv_internal.getText());
                startActivityForResult(intent2, RESULT_CODE_SETTING_INTERNAL);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_CODE_SETTING_NO_PREVIEW == requestCode && resultCode == RESULT_CODE_SETTING_NO_PREVIEW) {
            String strDate = data.getStringExtra("date");
            loadData(strDate);
        } else if (REQUEST_CODE_SETTING_PREVIEW == requestCode && resultCode == REQUEST_CODE_SETTING_PREVIEW) {
            startTime = data.getStringExtra("startTime");
            endTime = data.getStringExtra("endTime");
            tv_previewTime.setText(startTime + "-" + endTime);
        } else if (RESULT_CODE_SETTING_INTERNAL == requestCode && resultCode == RESULT_CODE_SETTING_INTERNAL) {
            tv_internal.setText(data.getStringExtra("internal"));
        }
    }
}
