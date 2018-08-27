package com.yijian.staff.mvp.course.schedule.week;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.schedule.week.edit.EditCourseTableActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.widget.MyScollView;
import com.yijian.staff.widget.WeekLayout;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.yijian.staff.application.CustomApplication.SCREEN_WIDTH;


public class ScheduleWeekFragment extends MvcBaseFragment {

    @BindView(R.id.week_layout)
    WeekLayout weekLayout;
    @BindView(R.id.week_course_view)
    WeekCourseView weekCourseView;
    @BindView(R.id.scoll_view)
    MyScollView scollView;
    private static String TAG = ScheduleWeekFragment.class.getSimpleName();
    private ViewTreeObserver.OnGlobalLayoutListener listener;
    private int width;
    private int size=48;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule_week;
    }

    @Override
    public void initView() {

        width = ((SCREEN_WIDTH - CommonUtil.dp2px(getContext(), 40))) / 7;
        weekLayout.setTimeItemWidthAndHeight(width, width);

        weekCourseView.setItemParams(width, width,48);
        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scollToCurrentTime();
            }
        };
        getActivity().getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(listener);


    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void scollToCurrentTime() {
        long l = System.currentTimeMillis();
        long currentDate = DateUtil.getStringToDate(DateUtil.getCurrentDate(), "yyyy-MM-dd");
        long l1 = 86400000;
        long l2 = l - currentDate;
        long top = width * size * l2 / l1 + weekCourseView.getPaddingTop();
        int screenHeight = CustomApplication.SCREEN_HEIGHT;
        if (top > screenHeight) {
            long l3 = top - screenHeight / 2;
            scollView.scrollTo(0, (int) l3);
        }
        getActivity().getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }

    private void initData() {
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("version", "1.3");

        HttpManager.getHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_WEEK_PLAN_URL, map, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {

                List<CourseStudentBean> courseStudentBeanList = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), CourseStudentBean.class);
                if (courseStudentBeanList != null) {
                    for (int i = 0; i < courseStudentBeanList.size(); i++) {
                        CourseStudentBean courseStudentBean = courseStudentBeanList.get(i);
                        List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                        int weekCode = courseStudentBean.getWeekCode();
                        for (int j = 0; j < list.size(); j++) {
                            weekCourseView.addItem(list.get(j),weekCode);
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

    @OnClick(R.id.ll_edit)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), EditCourseTableActivity.class));
    }

}

