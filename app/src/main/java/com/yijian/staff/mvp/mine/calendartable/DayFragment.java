package com.yijian.staff.mvp.mine.calendartable;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.complaint.handling.HandTaskAdapter;
import com.yijian.staff.mvp.complaint.handling.HandTaskInfo;
import com.yijian.staff.mvp.complaint.list.ComplaintListActivity;
import com.yijian.staff.mvp.invitation.InvitationInfo;
import com.yijian.staff.mvp.invitation.InvitationRecordFragment;
import com.yijian.staff.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

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
    RecyclerView rv_day;
    private List<DayCanlendarInfo> dayCanlendarInfoList=new ArrayList<>();

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
        rv_day = view.findViewById(R.id.rv_day);
        lin_expand.setOnClickListener(this);
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
        initDayCanlendarInfoList();
    }

    private void initDayCanlendarInfoList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("className", "张三三");
            jsonObject.put("venue", "场馆1");
            jsonObject.put("intervalTime", "20分钟");
            jsonObject.put("startOrderTime", "09:00");
            jsonObject.put("endOrderTime", "11:00");
            jsonObject.put("status", "1");
            JSONArray stuArray = new JSONArray();
            stuArray.put("张三");
            stuArray.put("李四");
            jsonObject.put("stuList", stuArray);
            for (int i = 0; i < 10; i++) {
                DayCanlendarInfo dayCanlendarInfo = new DayCanlendarInfo(jsonObject);
                dayCanlendarInfoList.add(dayCanlendarInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
            //设置RecyclerView 布局
            rv_day.setLayoutManager(layoutmanager);
            DayCanlendarAdapter dayCanlendarAdapter = new DayCanlendarAdapter(getActivity(), dayCanlendarInfoList);
            rv_day.setAdapter(dayCanlendarAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }

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
