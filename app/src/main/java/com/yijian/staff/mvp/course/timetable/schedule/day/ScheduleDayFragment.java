package com.yijian.staff.mvp.course.timetable.schedule.day;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.bean.DateBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.appointcourse.AppointCourseBean;
import com.yijian.staff.mvp.course.appointcourse.AppointCourseTableActivity;
import com.yijian.staff.mvp.course.appointcourse.DateListAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.JsonUtil;

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


    private List<DateBean> dateBeanList = new ArrayList<>();
    private int index = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule_day;
    }

    @Override
    public void initView() {
        initLeftDate();
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


    }

    private void request() {
        showLoading();
        DateBean dateBean = dateBeanList.get(index);
        String weekDay = dateBean.getWeekDay();
        int week=0;
        switch (weekDay) {
            case "周日":
                week=0;
                break;
            case "周一":
                week=1;
                break;
            case "周二":
                week=2;
                break;
            case "周三":
                week=3;
                break;
            case "周四":
                week=4;
                break;
            case "周五":
                week=5;
                break;
            case "周六":
                week=6;
                break;
        }
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

    private void post() {
        showLoading();
        DateBean dateBean = dateBeanList.get(index);
        String weekDay = dateBean.getWeekDay();
        int week=0;
        switch (weekDay) {
            case "周日":
                week=0;
                break;
            case "周一":
                week=1;
                break;
            case "周二":
                week=2;
                break;
            case "周三":
                week=3;
                break;
            case "周四":
                week=4;
                break;
            case "周五":
                week=5;
                break;
            case "周六":
                week=6;
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
}
