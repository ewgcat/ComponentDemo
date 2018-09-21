package com.yijian.clubmodule.ui.course.schedule;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.ui.course.schedule.day.ScheduleDayFragment;
import com.yijian.clubmodule.ui.course.schedule.week.ScheduleWeekFragment;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.util.ImageLoader;

@Route(path = "/test/32")
public class ScheduleCourseActivity extends MvcBaseActivity implements View.OnClickListener {


    TextView title_tv;
    ImageView iv_right;
    private ScheduleDayFragment scheduleDayFragment;
    private ScheduleWeekFragment scheduleWeekFragment;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_schedule_course;
    }

    private int index = 0;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        title_tv = findViewById(R.id.title_tv);
        iv_right = findViewById(R.id.iv_right);
        findViewById(R.id.ll_left).setOnClickListener(this);
        findViewById(R.id.ll_right).setOnClickListener(this);
        title_tv.setText("排课表");

        changeFragment(index);
    }


    private void changeFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllIndex(fragmentTransaction);
        switch (index) {
            case 0:
                if (scheduleWeekFragment == null) {
                    scheduleWeekFragment = new ScheduleWeekFragment();
                    fragmentTransaction.add(R.id.fl_content, scheduleWeekFragment);
                } else {
                    fragmentTransaction.show(scheduleWeekFragment);
                }
                ImageLoader.setImageResource(R.mipmap.weekview, this, iv_right);

                break;
            case 1:
                if (scheduleDayFragment == null) {
                    scheduleDayFragment = new ScheduleDayFragment();
                    fragmentTransaction.add(R.id.fl_content, scheduleDayFragment);
                } else {
                    fragmentTransaction.show(scheduleDayFragment);
                }
                ImageLoader.setImageResource(R.mipmap.dayview, this, iv_right);

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


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ll_left) {
            finish();
        } else if (i == R.id.ll_right) {
            if (index == 0) {
                index = 1;
                changeFragment(1);
            } else {
                index = 0;
                changeFragment(0);
            }

        }
    }
}
