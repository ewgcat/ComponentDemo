package com.yijian.staff.mvp.course.schedule.week;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.widget.MyScollView;
import com.yijian.staff.mvp.course.schedule.week.edit.EditCourseTableActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.ScrollViewListener;
import com.yijian.staff.widget.TimeLayout;
import com.yijian.staff.widget.WeekLayout;

import butterknife.OnClick;

import static com.yijian.staff.application.CustomApplication.SCREEN_HEIGHT;
import static com.yijian.staff.application.CustomApplication.SCREEN_WIDTH;


public class ScheduleWeekFragment extends MvcBaseFragment implements ScrollViewListener {

    private View stub;
    private MyScollView scoll_view;
    private TimeLayout timeLayout;
    private WeekLayout week_layout;
    private static String TAG = ScheduleWeekFragment.class.getSimpleName();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule_week;
    }

    @Override
    public void initView() {

        scoll_view = rootView.findViewById(R.id.scoll_view);
        stub = rootView.findViewById(R.id.stub);
        int width = ((SCREEN_WIDTH - CommonUtil.dp2px(getContext(), 40))) / 7;
        timeLayout = rootView.findViewById(R.id.time_layout);
        week_layout = rootView.findViewById(R.id.week_layout);
        week_layout.setTimeItemWidthAndHeight(width, width);
        stub.setLayoutParams(new LinearLayout.LayoutParams((CommonUtil.dp2px(getContext(), 40)), width - CommonUtil.dp2px(getContext(), 10)));
        int height = SCREEN_HEIGHT / 9;
        timeLayout.setTimeItemWidthAndHeight(CommonUtil.dp2px(getContext(), 40), height);


        timeLayout.setOnScrollViewListener(this);
    }



    @Override
    public void onScrollChanged(ViewGroup viewGroup, int x, int y, int oldx, int oldy) {
        if (viewGroup == scoll_view) {
            timeLayout.scrollTo(x, y);
        } else if (viewGroup == timeLayout) {
            scoll_view.scrollTo(x, y);

        }
    }






    @OnClick(R.id.ll_edit)
    public void onViewClicked() {
        startActivity(new Intent(getContext(),EditCourseTableActivity.class));
    }
}

