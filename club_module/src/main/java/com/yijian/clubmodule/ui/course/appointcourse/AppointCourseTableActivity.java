package com.yijian.clubmodule.ui.course.appointcourse;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.clubmodule.net.httpmanager.url.CourseUrls;
import com.yijian.commonlib.util.CommonUtil;
import com.yijian.commonlib.util.DateUtil;
import com.yijian.commonlib.util.system.ScreenUtil;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.AppointCourseBean;
import com.yijian.clubmodule.bean.DateBean;
import com.yijian.clubmodule.bean.P2mToBCappVOSBean;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.net.response.ResponseObserver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;




@Route(path = "/test/31")
public class AppointCourseTableActivity extends MvcBaseActivity {


    private static String TAG = AppointCourseTableActivity.class.getSimpleName();

    RecyclerView rv;
    AppointCourseView courseView;
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

        rv=findViewById(R.id.rv);
        courseView=findViewById(R.id.course_view);
        scollView=findViewById(R.id.scoll_view);
        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar .setBackClickListener(this);
        navigationBar.setTitle("约课表");

        initLeftDate();
        height = CommonUtil.dp2px(this, 44);
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
        date= DateUtil.getCurrentDate();
    }




    public void scollToCurrentTime() {
        long l = System.currentTimeMillis();
        long currentDate = DateUtil.getStringToDate(DateUtil.getCurrentDate(), "yyyy-MM-dd");
        long l1 = 86400000;
        long l2 = l - currentDate;
        long top = height * size * l2 / l1 + courseView.getPaddingTop();
        int screenHeight =  ScreenUtil.getScreenHeight(this);
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
            DateBean dateBean = new DateBean(weekOfDate,s);
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
        HttpManager.postHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_DAY_TABLE_URL, map, new ResponseObserver<AppointCourseBean>(getLifecycle()) {
            @Override
            public void onSuccess(AppointCourseBean appointCourseBean) {

                String p2mParentId = appointCourseBean.getP2mParentId();
                List<P2mToBCappVOSBean> list = appointCourseBean.getP2mToBCappVOS();
               if(TextUtils.isEmpty(p2mParentId)){
                   showToast(date+" 未生成约课表！");
               }else {
                   if (list.size()>0){
                       for (int i = 0; i < list.size(); i++) {
                          P2mToBCappVOSBean p2mToBCappVOSBean = list.get(i);
                           courseView.addItem(p2mToBCappVOSBean);
                       }
                   }
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
