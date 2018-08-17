package com.yijian.staff.mvp.course.timetable.schedule.week.addstudent.step2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.GroupedStudentBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/17 09:57:56
 */
public class AddStudentCourseStepTwoActivity extends MvcBaseActivity {

    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.tv_course)
    TextView tvCourse;
    @BindView(R.id.tv_course_time_status)
    TextView tvCourseTimeStatus;
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
    @BindView(R.id.wheelview1)
    WheelView wheelView1;
    @BindView(R.id.wheelview2)
    WheelView wheelView2;
    @BindView(R.id.rv)
    RecyclerView rv;

    private int weekday = 0;
    private String hours = "00";
    private String minutes = "00";
    private String consumingMinute;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_add_sudent_course_step_two;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("选择时间");
        navigationBar2.setmRightTvColor(Color.parseColor("#1997f8"));
        navigationBar2.setmRightTvText("确定");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });
        selectWeekDay(weekday);

        init();
    }

    private void init() {
        GroupedStudentBean selectGroupedStudentBean = (GroupedStudentBean) getIntent().getSerializableExtra("selectGroupedStudentBean");
        GroupedStudentBean.PrivateCoachCourseVOSBean course = (GroupedStudentBean.PrivateCoachCourseVOSBean) getIntent().getSerializableExtra("course");
        if (selectGroupedStudentBean != null) {
            ImageLoader.setImageResource(BuildConfig.FILE_HOST + selectGroupedStudentBean.getHeadPath(), this, ivHeader);
            int resId = selectGroupedStudentBean.getMemberSex() == 0 ? R.mipmap.lg_man : R.mipmap.lg_women;
            ImageLoader.setImageResource(resId, this, ivSex);
            tvName.setText(selectGroupedStudentBean.getMemberName());
        }
        if (course != null) {
            consumingMinute = course.getConsumingMinute()+"";
            tvCourse.setText(course.getMemberCourseName() + "（" + consumingMinute + ")");
        }
        initSetTime();
    }

    private void initSetTime() {
        wheelView1.setCyclic(false);

        final List<String> mOptionsItems1 = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                mOptionsItems1.add("0" + i);
            } else {
                mOptionsItems1.add("" + i);
            }
        }

        wheelView1.setAdapter(new ArrayWheelAdapter(mOptionsItems1));
        wheelView1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                hours = mOptionsItems1.get(index);
                checkoutScheduleTime();
            }
        });

        wheelView2.setCyclic(false);

        final List<String> mOptionsItems2 = new ArrayList<>();

        mOptionsItems2.add("00");
        mOptionsItems2.add("30");

        wheelView2.setAdapter(new ArrayWheelAdapter(mOptionsItems2));
        wheelView2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                minutes = mOptionsItems2.get(index);
                checkoutScheduleTime();
            }
        });
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
            case R.id.cancel:
                break;
            case R.id.add_time:
                break;
        }
    }

    public void post() {


    }

    public void checkoutScheduleTime() {
        HashMap<String,String> map=new HashMap<>();
        map.put("version","1.3.0");
        map.put("schooltime",hours+":"+minutes);
        map.put("week",weekday+"");
        map.put("classHour",consumingMinute);
        HttpManager.postHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_PLAN_IS_ABLE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                tvCourseTimeStatus.setVisibility(View.GONE);
            }

            @Override
            public void onFail(String msg) {
                tvCourseTimeStatus.setVisibility(View.VISIBLE);
                tvCourseTimeStatus.setText("(选中时间段已有安排)");
            }
        });

    }

    public void selectWeekDay(int index) {
        resetAllWeekDay();
        weekday = index;
        switch (index) {
            case 0:
                tvSeven.setTextSize(16);
                tvSeven.setTextColor(Color.parseColor("#1997f8"));
                line7.setVisibility(View.VISIBLE);
                break;
            case 1:
                tvOne.setTextSize(16);

                tvOne.setTextColor(Color.parseColor("#1997f8"));
                line1.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvTwo.setTextSize(16);
                tvTwo.setTextColor(Color.parseColor("#1997f8"));
                line2.setVisibility(View.VISIBLE);
                break;
            case 3:
                tvThree.setTextSize(16);
                tvThree.setTextColor(Color.parseColor("#1997f8"));
                line3.setVisibility(View.VISIBLE);
                break;
            case 4:
                tvFour.setTextSize(16);
                tvFour.setTextColor(Color.parseColor("#1997f8"));
                line4.setVisibility(View.VISIBLE);
                break;
            case 5:
                tvFive.setTextSize(16);
                tvFive.setTextColor(Color.parseColor("#1997f8"));
                line5.setVisibility(View.VISIBLE);
                break;
            case 6:
                tvSix.setTextSize(16);
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

        tvSeven.setTextSize(12);
        tvOne.setTextSize(12);
        tvTwo.setTextSize(12);
        tvThree.setTextSize(12);
        tvFour.setTextSize(12);
        tvFive.setTextSize(12);
        tvSix.setTextSize(12);
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);
        line5.setVisibility(View.INVISIBLE);
        line6.setVisibility(View.INVISIBLE);
        line7.setVisibility(View.INVISIBLE);
    }


}
