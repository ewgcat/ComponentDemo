package com.yijian.staff.mvp.course.timetable.schedule.day;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.bean.DateBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.appointcourse.AppointCourseBean;
import com.yijian.staff.mvp.course.appointcourse.AppointCourseTableActivity;
import com.yijian.staff.mvp.course.appointcourse.AppointCourseView;
import com.yijian.staff.mvp.course.appointcourse.DateListAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.MyScollView;
import com.yijian.staff.widget.ScrollViewListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ScheduleDayFragment extends MvcBaseFragment {
    private static String TAG = ScheduleDayFragment.class.getSimpleName();

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.course_view)
    CourseView courseView;
    @BindView(R.id.scoll_view)
    MyScollView scollView;

    private List<DateBean> dateBeanList = new ArrayList<>();
    private int index = 0;
    private int height, size;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule_day;
    }

    @Override
    public void initView() {
        initLeftDate();
        height = CommonUtil.dp2px(getContext(), 35);
        size = 48;
        courseView.setHeightAndSize(height,size);
        request();

        getActivity().getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scollToCurrentTime();
            }
        });
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        getActivity().  registerReceiver(broadcastReceiver, filter);
        scollView.setOnScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ViewGroup viewGroup, int x, int y, int oldx, int oldy) {
                courseView.onScrolled( );
            }
        });
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
    }

    private void initLeftDate() {
        for (int i = 0; i < 7; i++) {
            Date date = new Date(System.currentTimeMillis() + i * 86400000);
            String s = transferDate(date);
            String weekOfDate = DateUtil.getWeekOfDate(date);
            DateBean dateBean = new DateBean();
            dateBean.setDate(s);
            dateBean.setWeekDay(weekOfDate);
            dateBeanList.add(dateBean);
        }
        LeftDateListAdapter adapter = new LeftDateListAdapter(getContext(), CustomApplication.SCREEN_HEIGHT / 9, dateBeanList);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        adapter.setItemClickListener(new LeftDateListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                adapter.selectDate(position);
                index = position;
                request();
            }
        });
        rv.scrollToPosition(0);
        adapter.selectDate(0);
        request();
    }

    public String transferDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    private void updateUi(List<CourseStudentBean> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                CourseStudentBean courseStudentBean = list.get(i);
                int weekCode = courseStudentBean.getWeekCode();
                if (weekCode==getWeek()){
                    //TODO 更新界面
                    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> courseBeanList = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                    if (courseBeanList!=null&&courseBeanList.size()>0){
                        for (int j = 0; j <courseBeanList.size() ; j++) {
                            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean privateCoachCurriculumArrangementPlanVOSBean = courseBeanList.get(j);
                            courseView.addItem(privateCoachCurriculumArrangementPlanVOSBean);
                        }
                    }

                }
            }
        }

    }

    private void request() {
        courseView.clearView();
        showLoading();
        int week = getWeek();
        HashMap<String, String> map = new HashMap<>();
        map.put("week", week + "");
        HttpManager.getHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_DAY_PLAN_URL, map, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {
                List<CourseStudentBean> list = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), CourseStudentBean.class);
                if (list != null) {
                    updateUi(list);
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

    private int getWeek() {
        DateBean dateBean = dateBeanList.get(index);
        String weekDay = dateBean.getWeekDay();
        int week = 0;
        switch (weekDay) {
            case "周日":
                week = 0;
                break;
            case "周一":
                week = 1;
                break;
            case "周二":
                week = 2;
                break;
            case "周三":
                week = 3;
                break;
            case "周四":
                week = 4;
                break;
            case "周五":
                week = 5;
                break;
            case "周六":
                week = 6;
                break;
        }
        return week;
    }

    private void post() {
        showLoading();
        DateBean dateBean = dateBeanList.get(index);
        String weekDay = dateBean.getWeekDay();
        int week = 0;
        switch (weekDay) {
            case "周日":
                week = 0;
                break;
            case "周一":
                week = 1;
                break;
            case "周二":
                week = 2;
                break;
            case "周三":
                week = 3;
                break;
            case "周四":
                week = 4;
                break;
            case "周五":
                week = 5;
                break;
            case "周六":
                week = 6;
                break;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("week", week + "");
        HttpManager.postHasHeaderHasParam(CourseUrls.ABORT_APPOINT_COURSE_TABLE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                showToast(msg);
            }
        });
    }

    @OnClick({R.id.ll_edit})
    public void onViewClicked(View view) {
        post();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
       getActivity(). unregisterReceiver(broadcastReceiver);
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
