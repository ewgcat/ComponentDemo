package com.yijian.staff.mvp.course.yueke;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.course.timetable.schedule.day.ScheduleDayFragment;
import com.yijian.staff.mvp.course.timetable.schedule.week.ScheduleWeekFragment;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class YueKeBiaoActivity extends MvcBaseActivity {


    private YueKeBiaoFragment yueKeBiaoFragment1;
    private YueKeBiaoFragment yueKeBiaoFragment2;
    private YueKeBiaoFragment yueKeBiaoFragment3;
    private YueKeBiaoFragment yueKeBiaoFragment4;
    private YueKeBiaoFragment yueKeBiaoFragment5;
    private YueKeBiaoFragment yueKeBiaoFragment6;
    private YueKeBiaoFragment yueKeBiaoFragment7;

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
        changeFragment(0);

    }

    @OnClick({R.id.ll1, R.id.ll2, R.id.ll3, R.id.ll4, R.id.ll5, R.id.ll6, R.id.ll7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll1:
                changeFragment(0);
                break;
            case R.id.ll2:
                changeFragment(1);

                break;
            case R.id.ll3:
                changeFragment(2);

                break;
            case R.id.ll4:
                changeFragment(3);

                break;
            case R.id.ll5:
                changeFragment(4);

                break;
            case R.id.ll6:
                changeFragment(5);

                break;
            case R.id.ll7:
                changeFragment(6);

                break;
        }
    }

    private void changeFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllIndex(fragmentTransaction);
        switch (index) {
            case 0:
                if (yueKeBiaoFragment1 == null) {
                     yueKeBiaoFragment1 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment1);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment1);
                }
                break;
            case 1:
                if (yueKeBiaoFragment2 == null) {
                    yueKeBiaoFragment2 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment2);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment2);
                }
                break;
            case 2:
                if (yueKeBiaoFragment3== null) {
                    yueKeBiaoFragment3 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment3);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment3);
                }
                break;
            case 3:
                if (yueKeBiaoFragment4== null) {
                    yueKeBiaoFragment4= new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment4);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment4);
                }
                break;
            case 4:
                if (yueKeBiaoFragment5 == null) {
                    yueKeBiaoFragment5 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment5);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment5);
                }
                break;
            case 5:
                if (yueKeBiaoFragment6 == null) {
                    yueKeBiaoFragment6 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment6);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment6);
                }
                break;
            case 6:
                if (yueKeBiaoFragment7 == null) {
                    yueKeBiaoFragment7 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment7);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment7);
                }
                break;
        }
        fragmentTransaction.commit();

    }



    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {

        if (yueKeBiaoFragment1 != null && yueKeBiaoFragment1.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment1);
        }
        if (yueKeBiaoFragment2 != null && yueKeBiaoFragment2.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment2);
        }
        if (yueKeBiaoFragment3 != null && yueKeBiaoFragment3.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment3);
        }
        if (yueKeBiaoFragment4 != null && yueKeBiaoFragment4.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment4);
        }
        if (yueKeBiaoFragment5 != null && yueKeBiaoFragment5.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment5);
        }
        if (yueKeBiaoFragment6 != null && yueKeBiaoFragment6.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment6);
        }
        if (yueKeBiaoFragment7 != null && yueKeBiaoFragment7.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment7);
        }
    }
}
