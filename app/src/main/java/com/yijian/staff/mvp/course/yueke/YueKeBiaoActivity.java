package com.yijian.staff.mvp.course.yueke;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.yijian.staff.R;
import com.yijian.staff.bean.DateBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.course.timetable.helper.MyScollView;
import com.yijian.staff.mvp.course.timetable.helper.NoScrollRecycleView;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.ScrollViewListener;
import com.yijian.staff.widget.TimeLayout;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YueKeBiaoActivity extends MvcBaseActivity {


    private static String TAG = YueKeBiaoActivity.class.getSimpleName();

    @BindView(R.id.rv)
    RecyclerView rv;


    @BindView(R.id.course_view)
    AppointCourseView courseView;

    private List<DateBean> dateBeanList = new ArrayList<>();


    @Override
    protected int getLayoutID() {
        return R.layout.activity_yue_ke_biao;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("约课表");

        initLeftDate();

        getScreenSize();
        int width = ((SCREEN_WIDTH - CommonUtil.dp2px(this, 160)));
        int height = SCREEN_HEIGHT / 9;


        Logger.i("TEST", "height=" + height);
        courseView.setWidthAndHeight(CommonUtil.dp2px(this, 35), 24);
        for (int i = 0; i < 24; i++) {
            if (i % 2 == 0) {

                courseView.addItem("" + i, i);
            }
        }


        SimpleDateFormat df = new SimpleDateFormat("HH");//设置日期格式
        String format = df.format(new Date());
        int i = Integer.parseInt(format);


    }


    private void initLeftDate() {

        for (int i = -82; i < 7; i++) {
            Date date = new Date(System.currentTimeMillis() + i * 86400000);
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
    }

    public String transferDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public void request(String date) {

        HashMap<String, String> map = new HashMap<>();
        map.put("mmddmmdd", date);
        HttpManager.postHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_DAY_TABLE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }


    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
        Log.i(TAG, "SCREEN_WIDTH=" + SCREEN_WIDTH);
        Log.i(TAG, "SCREEN_HEIGHT=" + SCREEN_HEIGHT);
    }


}
