package com.yijian.staff.mvp.course.setclass;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.course.setclass.bean.TypeOfActionItem;
import com.yijian.staff.widget.MDividerItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by The_P on 2018/3/22.
 * 约课
 */
//@Route(path = "/test/15")
public class ActivityOpenLesson extends AppCompatActivity {
    private static final String TAG = "ActivityOpenLesson";
    private RecyclerView recyclerView;
    private AdapterLesson adapterLesson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openlesson);
        initView();
    }

    private Handler handler = new Handler();

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //添加Android自带的分割线
        MDividerItemDecoration decor = new MDividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        decor.setDrawable(getDrawable(R.drawable.divider_recyclerview));
        recyclerView.addItemDecoration(decor);
        recyclerView.setNestedScrollingEnabled(false);
        adapterLesson = new AdapterLesson(this);
        recyclerView.setAdapter(adapterLesson);


        mockData();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapterLesson.resetActionList(typeOfActionItems);
            }
        }, 2000);


        Chronometer chronometer = findViewById(R.id.chronometer);
//        chronometer.setFormat("H:MM:SS");
        Calendar mCalendar = Calendar.getInstance();
        int hour = mCalendar.get(Calendar.HOUR);
        int minute = mCalendar.get(Calendar.MINUTE);
        int second = mCalendar.get(Calendar.SECOND);

        int time = hour * 60 * 60 + minute * 60 + second;
        chronometer.setBase(SystemClock.elapsedRealtime() - time * 1000);//计时器清零
        chronometer.start();
    }

    private List<Object> typeOfActionItems = new ArrayList<>();

    private void mockData() {
        TypeOfActionItem typeOfActionItem = new TypeOfActionItem();
        typeOfActionItem.setActionName("心肺功能测试罚款浪费的说法");
//        typeOfActionItem.setActionForm(Arrays.asList("心肺一","心肺二","心肺三"));
//        typeOfActionItem.setActionIntensity(Arrays.asList("强","中","弱"));
        typeOfActionItems.add(typeOfActionItem);


        TypeOfActionItem typeOfActionItem1 = new TypeOfActionItem();
        typeOfActionItem1.setActionName("肌耐力测试");
//        typeOfActionItem1.setActionForm(Arrays.asList("肌耐力一","肌耐力二","肌耐力三"));
//        typeOfActionItem1.setActionIntensity(Arrays.asList("强","中","弱"));
        typeOfActionItems.add(typeOfActionItem1);

        TypeOfActionItem typeOfActionItem2 = new TypeOfActionItem();
        typeOfActionItem2.setActionName("下肢力量测试");
//        typeOfActionItem2.setActionForm(Arrays.asList("下肢力量一","下肢力量二","下肢力量三"));
//        typeOfActionItem2.setActionIntensity(Arrays.asList("强","中","弱"));
        typeOfActionItems.add(typeOfActionItem2);

        typeOfActionItems.add(typeOfActionItem);
        typeOfActionItems.add(typeOfActionItem1);
        typeOfActionItems.add(typeOfActionItem2);
        typeOfActionItems.add(typeOfActionItem2);
        typeOfActionItems.add(typeOfActionItem);
        typeOfActionItems.add(typeOfActionItem1);
    }

    private BottomSheetDialogFragmentLesson bottomSheetDialogFragmentLesson;

    //打开秒表
    public void showClockView(int position) {
        if (bottomSheetDialogFragmentLesson == null) {
            bottomSheetDialogFragmentLesson = new BottomSheetDialogFragmentLesson();
        }
        bottomSheetDialogFragmentLesson.show(getSupportFragmentManager(), "BottomSheetDialogFragmentLesson");
        bottomSheetDialogFragmentLesson.setResultChronometerListener(new BottomSheetDialogFragmentLesson.ResultChronometerListener() {
            @Override
            public void getTimes(long time) {


                Log.e(TAG, "getTimes: time=" + time);
                Object o = typeOfActionItems.get(position);
                if (o instanceof TypeOfActionItem) {
                    ((TypeOfActionItem) o).setActionTime("" + time / 1000.00f + "s");
                    ((TypeOfActionItem) o).setStartTime(SystemClock.elapsedRealtime() - time);//记录开始时间
                    ((TypeOfActionItem) o).setEndTime(SystemClock.elapsedRealtime());//记录结束时间

                    if (position != 0) {//计算间隔时间
                        Long startTime = ((TypeOfActionItem) o).getStartTime();
                        Long endTime = ((TypeOfActionItem) typeOfActionItems.get(position - 1)).getEndTime();//上一item的结束时间
                        ((TypeOfActionItem) o).setActionTimeInterval("" + ((startTime - endTime) / 1000) + "s");
                    }

                    adapterLesson.notifyItemChanged(position + 1);//加上标题item

                    if (adapterLesson.getIndex_point() == position + 1) {
                        adapterLesson.setIndex_point(position + 1 + 1);//可以点击的位置+1
                    }
                }
            }
        });
    }

}
