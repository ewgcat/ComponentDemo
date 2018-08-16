package com.yijian.staff.mvp.course.curriculum;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.OnClick;

@Route(path = "/test/15")
public class CurriculumActivity extends MvcBaseActivity {


    private DayCurriculumFragment dayCurriculumFragment1;
    private DayCurriculumFragment dayCurriculumFragment2;
    private DayCurriculumFragment dayCurriculumFragment3;
    private DayCurriculumFragment dayCurriculumFragment4;
    private DayCurriculumFragment dayCurriculumFragment5;
    private DayCurriculumFragment dayCurriculumFragment6;
    private DayCurriculumFragment dayCurriculumFragment7;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_curriculum;
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
                if (dayCurriculumFragment1 == null) {
                     dayCurriculumFragment1 = new DayCurriculumFragment();
                    fragmentTransaction.add(R.id.fl_content, dayCurriculumFragment1);
                } else {
                    fragmentTransaction.show(dayCurriculumFragment1);
                }
                break;
            case 1:
                if (dayCurriculumFragment2 == null) {
                    dayCurriculumFragment2 = new DayCurriculumFragment();
                    fragmentTransaction.add(R.id.fl_content, dayCurriculumFragment2);
                } else {
                    fragmentTransaction.show(dayCurriculumFragment2);
                }
                break;
            case 2:
                if (dayCurriculumFragment3 == null) {
                    dayCurriculumFragment3 = new DayCurriculumFragment();
                    fragmentTransaction.add(R.id.fl_content, dayCurriculumFragment3);
                } else {
                    fragmentTransaction.show(dayCurriculumFragment3);
                }
                break;
            case 3:
                if (dayCurriculumFragment4 == null) {
                    dayCurriculumFragment4 = new DayCurriculumFragment();
                    fragmentTransaction.add(R.id.fl_content, dayCurriculumFragment4);
                } else {
                    fragmentTransaction.show(dayCurriculumFragment4);
                }
                break;
            case 4:
                if (dayCurriculumFragment5 == null) {
                    dayCurriculumFragment5 = new DayCurriculumFragment();
                    fragmentTransaction.add(R.id.fl_content, dayCurriculumFragment5);
                } else {
                    fragmentTransaction.show(dayCurriculumFragment5);
                }
                break;
            case 5:
                if (dayCurriculumFragment6 == null) {
                    dayCurriculumFragment6 = new DayCurriculumFragment();
                    fragmentTransaction.add(R.id.fl_content, dayCurriculumFragment6);
                } else {
                    fragmentTransaction.show(dayCurriculumFragment6);
                }
                break;
            case 6:
                if (dayCurriculumFragment7 == null) {
                    dayCurriculumFragment7 = new DayCurriculumFragment();
                    fragmentTransaction.add(R.id.fl_content, dayCurriculumFragment7);
                } else {
                    fragmentTransaction.show(dayCurriculumFragment7);
                }
                break;
        }
        fragmentTransaction.commit();

    }



    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {

        if (dayCurriculumFragment1 != null && dayCurriculumFragment1.isAdded()) {
            fragmentTransaction.hide(dayCurriculumFragment1);
        }
        if (dayCurriculumFragment2 != null && dayCurriculumFragment2.isAdded()) {
            fragmentTransaction.hide(dayCurriculumFragment2);
        }
        if (dayCurriculumFragment3 != null && dayCurriculumFragment3.isAdded()) {
            fragmentTransaction.hide(dayCurriculumFragment3);
        }
        if (dayCurriculumFragment4 != null && dayCurriculumFragment4.isAdded()) {
            fragmentTransaction.hide(dayCurriculumFragment4);
        }
        if (dayCurriculumFragment5 != null && dayCurriculumFragment5.isAdded()) {
            fragmentTransaction.hide(dayCurriculumFragment5);
        }
        if (dayCurriculumFragment6 != null && dayCurriculumFragment6.isAdded()) {
            fragmentTransaction.hide(dayCurriculumFragment6);
        }
        if (dayCurriculumFragment7 != null && dayCurriculumFragment7.isAdded()) {
            fragmentTransaction.hide(dayCurriculumFragment7);
        }
    }
}
