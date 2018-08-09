package com.yijian.staff.mvp.course.timetable.edit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.course.timetable.StudentListFragment;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.OnClick;

public class EditCourseTableActivity extends MvcBaseActivity {


    @BindView(R.id.tv_seven)
    TextView tvSeven;
    @BindView(R.id.line7)
    View line7;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.tv_five)
    TextView tvFive;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.tv_six)
    TextView tvSix;
    @BindView(R.id.line6)
    View line6;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    private StudentListFragment fragment0, fragment1, fragment2, fragment3, fragment4, fragment5, fragment6;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_course_time_table;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle( "编辑排课表");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        changeFragment(0);
        selectWeekDay(0);
    }


    private void changeFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllIndex(fragmentTransaction);
        switch (index) {
            case 0:
                if (fragment0 == null) {
                    fragment0 = new StudentListFragment();
                    fragmentTransaction.add(R.id.fl_content, fragment0);
                } else {
                    fragmentTransaction.show(fragment0);
                }
                break;
            case 1:
                if (fragment1 == null) {
                    fragment1 = new StudentListFragment();
                    fragmentTransaction.add(R.id.fl_content, fragment1);
                } else {
                    fragmentTransaction.show(fragment1);
                }
                break;
            case 2:
                if (fragment2 == null) {
                    fragment2 = new StudentListFragment();
                    fragmentTransaction.add(R.id.fl_content, fragment2);
                } else {
                    fragmentTransaction.show(fragment2);
                }
                break;
            case 3:
                if (fragment3 == null) {
                    fragment3 = new StudentListFragment();
                    fragmentTransaction.add(R.id.fl_content, fragment3);
                } else {
                    fragmentTransaction.show(fragment3);
                }
                break;
            case 4:
                if (fragment4 == null) {
                    fragment4 = new StudentListFragment();
                    fragmentTransaction.add(R.id.fl_content, fragment4);
                } else {
                    fragmentTransaction.show(fragment4);
                }
                break;
            case 5:
                if (fragment5 == null) {
                    fragment5 = new StudentListFragment();
                    fragmentTransaction.add(R.id.fl_content, fragment5);
                } else {
                    fragmentTransaction.show(fragment5);
                }
                break;
            case 6:
                if (fragment6 == null) {
                    fragment6 = new StudentListFragment();
                    fragmentTransaction.add(R.id.fl_content, fragment6);
                } else {
                    fragmentTransaction.show(fragment6);
                }
                break;
        }

        fragmentTransaction.commit();
    }

    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {

        if (fragment0 != null && fragment0.isAdded()) {
            fragmentTransaction.hide(fragment0);
        }
        if (fragment1 != null && fragment1.isAdded()) {
            fragmentTransaction.hide(fragment1);
        }
        if (fragment2 != null && fragment2.isAdded()) {
            fragmentTransaction.hide(fragment2);
        }
        if (fragment3 != null && fragment3.isAdded()) {
            fragmentTransaction.hide(fragment3);
        }
        if (fragment4 != null && fragment4.isAdded()) {
            fragmentTransaction.hide(fragment4);
        }
        if (fragment5 != null && fragment5.isAdded()) {
            fragmentTransaction.hide(fragment5);
        }
        if (fragment6 != null && fragment6.isAdded()) {
            fragmentTransaction.hide(fragment6);
        }


    }


    @OnClick({R.id.ll_week_sunday, R.id.ll_week_one, R.id.ll_week_two, R.id.ll_week_three, R.id.ll_week_four, R.id.ll_week_five, R.id.ll_week_six})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.ll_week_sunday:
                selectWeekDay(0);
                break;
            case R.id.ll_week_one:
                selectWeekDay(1);
                break;
            case R.id.ll_week_two:
                selectWeekDay(2);
                break;
            case R.id.ll_week_three:
                selectWeekDay(3);
                break;
            case R.id.ll_week_four:
                selectWeekDay(4);

                break;
            case R.id.ll_week_five:
                selectWeekDay(5);
                break;
            case R.id.ll_week_six:
                selectWeekDay(6);
                break;
        }
    }

    public void selectWeekDay(int index) {
        resetAllWeekDay();

        switch (index) {
            case 0:
                tvSeven.setTextColor(Color.parseColor("#1997f8"));
                line7.setVisibility(View.VISIBLE);
                break;
            case 1:
                tvOne.setTextColor(Color.parseColor("#1997f8"));
                line1.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvTwo.setTextColor(Color.parseColor("#1997f8"));
                line2.setVisibility(View.VISIBLE);
                break;
            case 3:
                tvThree.setTextColor(Color.parseColor("#1997f8"));
                line3.setVisibility(View.VISIBLE);
                break;
            case 4:
                tvFour.setTextColor(Color.parseColor("#1997f8"));
                line4.setVisibility(View.VISIBLE);
                break;
            case 5:
                tvFive.setTextColor(Color.parseColor("#1997f8"));
                line5.setVisibility(View.VISIBLE);
                break;
            case 6:
                tvSix.setTextColor(Color.parseColor("#1997f8"));
                line6.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void resetAllWeekDay() {
        tvSeven.setTextColor(Color.parseColor("#333333"));
        tvOne.setTextColor(Color.parseColor("#333333"));
        tvTwo.setTextColor(Color.parseColor("#333333"));
        tvThree.setTextColor(Color.parseColor("#333333"));
        tvFour.setTextColor(Color.parseColor("#333333"));
        tvFive.setTextColor(Color.parseColor("#333333"));
        tvSix.setTextColor(Color.parseColor("#333333"));

        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);
        line5.setVisibility(View.INVISIBLE);
        line6.setVisibility(View.INVISIBLE);
        line7.setVisibility(View.INVISIBLE);
    }


}
