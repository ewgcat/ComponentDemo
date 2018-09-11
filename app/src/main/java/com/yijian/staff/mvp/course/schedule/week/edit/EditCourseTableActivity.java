package com.yijian.staff.mvp.course.schedule.week.edit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.course.schedule.week.edit.list.CourseListFragment;
import com.yijian.staff.widget.NavigationBar;

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


    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_course_time_table;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        NavigationBar NavigationBar = findViewById(R.id.navigation_bar);
        NavigationBar.setTitle("编辑排课表");
        NavigationBar.hideLeftSecondIv();
        NavigationBar.setBackClickListener(this);
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


}
