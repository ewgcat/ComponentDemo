package com.yijian.clubmodule.ui.course.schedule.week.edit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.yijian.clubmodule.ui.course.schedule.week.edit.list.CourseListFragment;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;



public class EditCourseTableActivity extends MvcBaseActivity implements View.OnClickListener {


    TextView tvSeven;
    View line7;
    TextView tvOne;
    View line1;
    TextView tvTwo;
    View line2;
    TextView tvThree;
    View line3;
    TextView tvFour;
    View line4;
    TextView tvFive;
    View line5;
    TextView tvSix;
    View line6;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_course_time_table;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvSeven = findViewById(R.id.tv_seven);
        line7 = findViewById(R.id.line7);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        line5 = findViewById(R.id.line5);
        line6 = findViewById(R.id.line6);
        tvOne = findViewById(R.id.tv_one);
        tvTwo = findViewById(R.id.tv_two);
        tvThree = findViewById(R.id.tv_three);
        tvFour = findViewById(R.id.tv_four);
        tvFive = findViewById(R.id.tv_five);
        tvSix = findViewById(R.id.tv_six);
      findViewById(R.id.ll_week_sunday).setOnClickListener(this);
      findViewById(R.id.ll_week_one).setOnClickListener(this);
      findViewById(R.id.ll_week_two).setOnClickListener(this);
      findViewById(R.id.ll_week_three).setOnClickListener(this);
      findViewById(R.id.ll_week_four).setOnClickListener(this);
      findViewById(R.id.ll_week_five).setOnClickListener(this);
      findViewById(R.id.ll_week_six).setOnClickListener(this);

        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setTitle("编辑排课表");
        navigationBar.hideLeftSecondIv();
        navigationBar.setBackClickListener(this);
        selectWeekDay(0);
    }


    private void changeFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (index) {
            case 0:
                CourseListFragment fragment0 = new CourseListFragment(0);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment0).commit();

                break;
            case 1:
                CourseListFragment fragment1 = new CourseListFragment(1);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment1).commit();

                break;
            case 2:
                CourseListFragment fragment2 = new CourseListFragment(2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment2).commit();
                break;
            case 3:
                CourseListFragment fragment3 = new CourseListFragment(3);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment3).commit();
                break;
            case 4:
                CourseListFragment fragment4 = new CourseListFragment(4);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment4).commit();
                break;
            case 5:
                CourseListFragment fragment5 = new CourseListFragment(5);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment5).commit();

                break;
            case 6:
                CourseListFragment fragment6 = new CourseListFragment(6);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment6).commit();
                break;
        }

        fragmentTransaction.commit();
    }




    public void selectWeekDay(int index) {
        resetAllWeekDay();
        changeFragment(index);
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


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ll_week_sunday) {
            selectWeekDay(0);

        } else if (i == R.id.ll_week_one) {
            selectWeekDay(1);

        } else if (i == R.id.ll_week_two) {
            selectWeekDay(2);

        } else if (i == R.id.ll_week_three) {
            selectWeekDay(3);

        } else if (i == R.id.ll_week_four) {
            selectWeekDay(4);


        } else if (i == R.id.ll_week_five) {
            selectWeekDay(5);

        } else if (i == R.id.ll_week_six) {
            selectWeekDay(6);

        }
    }
}
