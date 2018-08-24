package com.yijian.staff.mvp.course.appointcourse;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.bean.DateBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class AppointCourseTableActivity extends MvcBaseActivity {


    private static String TAG = AppointCourseTableActivity.class.getSimpleName();

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.course_view)
    AppointCourseView courseView;
    @BindView(R.id.scoll_view)
    NestedScrollView scollView;

    private List<DateBean> dateBeanList = new ArrayList<>();
    private int height, size;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_appoint_course_table;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("约课表");

        initLeftDate();

        height = CommonUtil.dp2px(this, 35);
        size = 48;
        courseView.setHeightAndSize(height, size);
        AppointCourseBean appointCourseBean =new AppointCourseBean();
        appointCourseBean.setStartDate("2018-08-24");
        appointCourseBean.setStartTime("18:00");
        appointCourseBean.setEndTime("18:30");
        appointCourseBean.setHeadPath("");
        appointCourseBean.setMemberCourseName("测试课");
        appointCourseBean.setMemberName("测试");
        courseView.addItem(appointCourseBean);

        AppointCourseBean appointCourseBean1 =new AppointCourseBean();
        appointCourseBean1.setStartDate("2018-08-24");
        appointCourseBean1.setStartTime("18:30");
        appointCourseBean1.setEndTime("19:30");
        appointCourseBean1.setHeadPath("");
        appointCourseBean1.setMemberCourseName("测试课");
        appointCourseBean1.setMemberName("测试");
        courseView.addItem(appointCourseBean1);
        String date = DateUtil.getCurrentDate();
        request(date);

        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scollToCurrentTime();
            }
        });
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        registerReceiver(broadcastReceiver, filter);
        //广播的注册，其中Intent.ACTION_TIME_CHANGED代表时间设置变化的时候会发出该广播

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                courseView.invalidate();
            }
        }
    };


    public void scollToCurrentTime() {
        long l = System.currentTimeMillis();
        long currentDate = DateUtil.getStringToDate(DateUtil.getCurrentDate(), "yyyy-MM-dd");
        long l1 = 86400000;
        long l2 = l - currentDate;
        long top = height * size * l2 / l1 + courseView.getPaddingTop();
        int screenHeight = CustomApplication.SCREEN_HEIGHT;
        if (top > screenHeight) {
            long l3 = top - screenHeight / 2;
            scollView.scrollTo(0, (int) l3);
        }
    }

    private void initLeftDate() {

        for (int i = 0; i < 90; i++) {
            Date date = new Date(System.currentTimeMillis() + (i-83) * 86400000);
            String s = transferDate(date);
            String weekOfDate = DateUtil.getWeekOfDate(date);
            DateBean dateBean = new DateBean();
            dateBean.setDate(s);
            dateBean.setWeekDay(weekOfDate);
            dateBeanList.add(dateBean);
        }
        DateListAdapter dateListAdapter = new DateListAdapter(this, dateBeanList);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(dateListAdapter);

        dateListAdapter.setItemClickListener(new DateListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                dateListAdapter.selectDate(position);
                String date = dateBeanList.get(position).getDate();
                request(date);
            }
        });
        rv.scrollToPosition(83);
        dateListAdapter.selectDate(83);
    }

    public String transferDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public void request(String date) {

        HashMap<String, String> map = new HashMap<>();
        map.put("mmddmmdd", date);
        HttpManager.postHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_DAY_TABLE_URL, map, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {
//                List<AppointCourseBean> list = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), AppointCourseBean.class);
//                for (int i = 0; i < list.size(); i++) {
//                    AppointCourseBean appointCourseBean = list.get(i);
//                    courseView.addItem(appointCourseBean);
//                }


            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
    }


}
