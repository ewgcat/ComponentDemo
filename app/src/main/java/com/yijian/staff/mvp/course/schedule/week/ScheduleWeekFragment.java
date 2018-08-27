package com.yijian.staff.mvp.course.schedule.week;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.schedule.week.edit.EditCourseTableActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.MyScollView;
import com.yijian.staff.widget.WeekLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.yijian.staff.application.CustomApplication.SCREEN_WIDTH;


public class ScheduleWeekFragment extends MvcBaseFragment {

    @BindView(R.id.stub)
    View stub;
    @BindView(R.id.week_layout)
    WeekLayout weekLayout;
    @BindView(R.id.week_course_view)
    WeekCourseView weekCourseView;
    @BindView(R.id.scoll_view)
    MyScollView scollView;
    private static String TAG = ScheduleWeekFragment.class.getSimpleName();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule_week;
    }

    @Override
    public void initView() {

        int width = ((SCREEN_WIDTH - CommonUtil.dp2px(getContext(), 40))) / 7;
        stub.setLayoutParams(new LinearLayout.LayoutParams((CommonUtil.dp2px(getContext(), 40)), width ));
        weekLayout.setTimeItemWidthAndHeight(width, width);

        weekCourseView.setItemParams(width,width,48);
    }


    @OnClick(R.id.ll_edit)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), EditCourseTableActivity.class));
    }

}

