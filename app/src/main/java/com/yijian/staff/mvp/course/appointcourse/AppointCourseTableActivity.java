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

import com.alibaba.android.arouter.facade.annotation.Route;
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
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

@Route(path = "/test/31")
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
    private ViewTreeObserver.OnGlobalLayoutListener listener;
    private String date;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_appoint_course_table;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        NavigationBar NavigationBar = findViewById(R.id.navigation_bar);
        NavigationBar.getSecondLeftIv().setVisibility(View.GONE);
        NavigationBar.setBackClickListener(this);
        NavigationBar.setTitle("约课表");

        initLeftDate();
        height = CommonUtil.dp2px(this, 50);
        size = 48;
        courseView.setHeightAndSize(height, size);

        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                    scollToCurrentTime();
            }
        };
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(listener);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        registerReceiver(broadcastReceiver, filter);
        date=DateUtil.getCurrentDate();
    }




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
       getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(listener);

    }

    private void initLeftDate() {
        for (int i = 0; i < 90; i++) {
            Date date = new Date(System.currentTimeMillis() + (i - 83) * 86400000);
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
                date = dateBeanList.get(position).getDate();
                request();
            }
        });
        rv.scrollToPosition(83);
        dateListAdapter.selectDate(83);
    }

    public String transferDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }


    public void request() {
        courseView.clearView();
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("mmddmmdd", date);
        HttpManager.postHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_DAY_TABLE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                JSONArray p2mToBappVOs = JsonUtil.getJsonArray(result, "p2mToBCappVOS");
                List<AppointCourseBean.P2mToBCappVOSBean> list = com.alibaba.fastjson.JSONArray.parseArray(p2mToBappVOs.toString(), AppointCourseBean.P2mToBCappVOSBean.class);
                if (list!=null&&list.size()>0){
                    for (int i = 0; i < list.size(); i++) {
                        AppointCourseBean.P2mToBCappVOSBean appointCourseBean = list.get(i);
                        courseView.addItem(appointCourseBean);
                    }
                }else {
                    showToast(date+" 未生成约课表！");
                }


                hideLoading();
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                showToast(msg);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        request();
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

}
