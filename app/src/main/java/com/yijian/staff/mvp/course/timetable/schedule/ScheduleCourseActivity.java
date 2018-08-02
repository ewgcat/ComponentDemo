package com.yijian.staff.mvp.course.timetable.schedule;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScheduleCourseActivity extends MvcBaseActivity {


    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.bottom_line)
    View bottomLine;
    private ScheduleDayFragment scheduleDayFragment;
    private ScheduleWeekFragment scheduleWeekFragment;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_schedule_course;
    }

    private int index = 1;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        titleTv.setText("排课表");
        ImageLoader.setImageResource(R.mipmap.weekview, this, ivRight);

        changeFragment(index);
    }


    private void changeFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllIndex(fragmentTransaction);
        switch (index) {
            case 0:
                if (scheduleDayFragment == null) {
                    scheduleDayFragment = new ScheduleDayFragment();
                    fragmentTransaction.add(R.id.fl_content, scheduleDayFragment);
                } else {
                    fragmentTransaction.show(scheduleDayFragment);
                }
                break;
            case 1:
                if (scheduleWeekFragment == null) {
                    scheduleWeekFragment = new ScheduleWeekFragment();
                    fragmentTransaction.add(R.id.fl_content, scheduleWeekFragment);
                } else {
                    fragmentTransaction.show(scheduleWeekFragment);
                }
                break;
        }

        fragmentTransaction.commit();
    }

    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {

        if (scheduleDayFragment != null && scheduleDayFragment.isAdded()) {
            fragmentTransaction.hide(scheduleDayFragment);
        }
        if (scheduleWeekFragment != null && scheduleWeekFragment.isAdded()) {
            fragmentTransaction.hide(scheduleWeekFragment);
        }
    }



    @OnClick({R.id.ll_left, R.id.ll_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_left:
                finish();
                break;
            case R.id.ll_right:
                if (index == 0) {
                    index = 1;
                    ImageLoader.setImageResource(R.mipmap.weekview, this, ivRight);
                    changeFragment(1);
                } else {
                    index = 0;
                    ImageLoader.setImageResource(R.mipmap.dayview, this, ivRight);
                    changeFragment(0);
                }
                break;
        }
    }
}
