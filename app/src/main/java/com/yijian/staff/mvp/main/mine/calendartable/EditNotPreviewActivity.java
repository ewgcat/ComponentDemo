package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;
import com.yijian.staff.R;
import com.yijian.staff.mvp.questionnaire.detail.AllNoneSelectedDecorator;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResponseObserver;
import com.yijian.staff.net.response.ResultJSONIntegerObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultNullObserver;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yijian.staff.mvp.main.mine.calendartable.CalendarSettingActivity.REQUEST_CODE_SETTING_PREVIEW;
import static com.yijian.staff.mvp.main.mine.calendartable.CalendarSettingActivity.RESULT_CODE_SETTING_NO_PREVIEW;

public class EditNotPreviewActivity extends AppCompatActivity {

    @BindView(R.id.calendarView)
    MaterialCalendarView materialCalendarView;//布局内的控件

    //不可约
    private ArrayList<String> spaceTimeList = new ArrayList<>();
    //当日有课
    private ArrayList<String> courseTimeList = new ArrayList<>();
    private String strDate;

    List<CalendarDay> hasClassList; //当日有课日期集合
    List<CalendarDay> disableAppointmentList; //不可预约日期集合
    EventDecorator eventRedDecorator; //设置小红点
    BlueEventDecorator eventBlueDecorator; //设置小蓝点
    List<CalendarDay> calendarSelectDayList; //装载选中的日程集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_not_preview);
        ButterKnife.bind(this);
        strDate = getIntent().getStringExtra("date");
        initTitle();
        initData();
        initView();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar);
        navigationBar2.setTitle("选择不可约时间");
        navigationBar2.setmRightTvText("确定");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendarSelectDayList = materialCalendarView.getSelectedDates();
                for (CalendarDay calendarDay : calendarSelectDayList) {
                    Log.e("Test", "年份===" + calendarDay.getYear() + "  月份==" + (calendarDay.getMonth() + 1) + " 日==" + calendarDay.getDay());
                }
                postData();
            }
        });
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();

    }

    private void initData() {
        spaceTimeList = getIntent().getStringArrayListExtra("spaceTimeList");
        courseTimeList = getIntent().getStringArrayListExtra("courseTimeList");


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

    }

    private void initView() {
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE); //设置多选
        materialCalendarView.setSelectionColor(Color.parseColor("#ffe720"));
        //设置不可约小红点
        materialCalendarView.addDecorator(eventRedDecorator);
        //设置蓝色当日有课
        materialCalendarView.addDecorator(eventBlueDecorator);

        materialCalendarView.invalidateDecorators();
        //编辑日历属性
        materialCalendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2010, 4, 3))  //设置可以显示的最早时间
                .setMaximumDate(CalendarDay.from(2020, 5, 12))//设置可以显示的最晚时间
                .setCalendarDisplayMode(CalendarMode.MONTHS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                .commit();// 返回对象并保存
        strDate = getIntent().getStringExtra("date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            materialCalendarView.setCurrentDate(simpleDateFormat.parse(strDate));
            materialCalendarView.getCurrentDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * 提交不可约数据
     */
    private void postData() {
        Map<String, String> map = new HashMap<>();
        List<String> selectDateList = new ArrayList<>();
        for (CalendarDay calendarDay : calendarSelectDayList) {
            selectDateList.add(calendarDay.getYear() + "-" + (calendarDay.getMonth() + 1) + "-" + calendarDay.getDay());
        }
        if (selectDateList.size() > 0) {
            map.put("date", selectDateList.toString().substring(1, selectDateList.toString().length() - 1));
        }
        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_SETLEAVE_URL, map, new ResultJSONIntegerObserver() {
            @Override
            public void onSuccess(Integer result) {
                Toast.makeText(EditNotPreviewActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                setResult(RESULT_CODE_SETTING_NO_PREVIEW, intent);
                finish();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(EditNotPreviewActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
