package com.yijian.staff.mvp.course.timetable.schedule.week.edit.list.edit;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.requestbody.course.SaveCourseRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditCourseTimeActivity extends MvcBaseActivity {

    @BindView(R.id.tv_course_time_status)
    TextView tvCourseTimeStatus;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.tv_course)
    TextView tvCourse;
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
    @BindView(R.id.ll_week_sunday)
    LinearLayout llWeekSunday;
    @BindView(R.id.ll_week_one)
    LinearLayout llWeekOne;
    @BindView(R.id.ll_week_two)
    LinearLayout llWeekTwo;
    @BindView(R.id.ll_week_three)
    LinearLayout llWeekThree;
    @BindView(R.id.ll_week_four)
    LinearLayout llWeekFour;
    @BindView(R.id.ll_week_five)
    LinearLayout llWeekFive;
    @BindView(R.id.ll_week_six)
    LinearLayout llWeekSix;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    private int weekday = 0;
    private ArrayList<String> weekdays = new ArrayList<>();
    private String hours = "00";
    private String minutes = "00";
    private String consumingMinute = "60";
    private List<SaveCourseRequestBody.PrivateCoachCAPDTOsBean> privateCoachCAPDTOs = new ArrayList<>();
    private CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseStudentBean;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_student_course_step_two;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        weekdays.add("周日");
        weekdays.add("周一");
        weekdays.add("周二");
        weekdays.add("周三");
        weekdays.add("周四");
        weekdays.add("周五");
        weekdays.add("周六");


        courseStudentBean = (CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean) getIntent().getSerializableExtra("PrivateCoachCurriculumArrangementPlanVOSBean");
        if (courseStudentBean != null) {
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVO = courseStudentBean.getPrivateCourseMemberVO();
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVO = courseStudentBean.getPrivateCoachCourseVO();
            ImageLoader.setImageResource(BuildConfig.FILE_HOST + privateCourseMemberVO.getHeadPath(), this, ivHeader);
            int resId = privateCourseMemberVO.getMemberSex() == 0 ? R.mipmap.lg_man : R.mipmap.lg_women;
            ImageLoader.setImageResource(resId, this, ivSex);
            tvName.setText(privateCourseMemberVO.getMemberName());
            tvCourse.setText(privateCoachCourseVO.getMemberCourseName() + "（" + privateCoachCourseVO.getConsumingMinute() + "分钟)");
            int week = courseStudentBean.getWeek();
            weekday = week;
            selectWeekDay(weekday);
            initSetTime();
        }


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

        if (courseStudentBean != null) {
            String sTime = courseStudentBean.getSTime();
            if (!TextUtils.isEmpty(sTime)) {
                String[] split = sTime.split(":");
                if (split.length == 2) {
                    int i = Integer.parseInt(split[0]);
                    wheelView1.setCurrentItem(i);
                    int m = Integer.parseInt(split[1]);
                    if (m <= 30 && m > 0) {
                        wheelView2.setCurrentItem(1);
                    }
                    if (m < 60 && m > 30) {
                        wheelView2.setCurrentItem(0);
                        wheelView1.setCurrentItem(i + 1);
                    }
                }
            }
        }
    }

    public void postSaveCourse() {
        int visibility = tvCourseTimeStatus.getVisibility();
        if (visibility == View.VISIBLE) {
            showToast("选中时间段已有安排,请选择其他时间！");
        } else {
            if (courseStudentBean != null) {

                CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVO = courseStudentBean.getPrivateCoachCourseVO();
                CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVO = courseStudentBean.getPrivateCourseMemberVO();

                SaveCourseRequestBody.PrivateCoachCAPDTOsBean privateCoachCAPDTOsBean = new SaveCourseRequestBody.PrivateCoachCAPDTOsBean();
                privateCoachCAPDTOsBean.setDataType(1);
                privateCoachCAPDTOsBean.setMemberId(privateCourseMemberVO.getMemberId());
                privateCoachCAPDTOsBean.setMemberCourseId(privateCoachCourseVO.getMemberCourseId());


                privateCoachCAPDTOsBean.setWeek(weekday);

                String startTime = hours + ":" + minutes;
                int i1 = Integer.parseInt(minutes);
                int i2 = Integer.parseInt(consumingMinute);
                int i3 = i1 + i2;
                int i4 = i3 / 60;
                int i5 = i3 % 60;

                int h1 = Integer.parseInt(hours);
                int h2 = h1 + i4;
                String endTime = "";
                if (h2 < 10) {
                    endTime = "0" + h2 + ":";
                } else {
                    endTime = h2 + ":";
                }
                if (i5 < 10) {
                    endTime = endTime + "0" + i5;
                } else {
                    endTime = endTime + i5;
                }


                privateCoachCAPDTOsBean.setSTime(startTime);
                privateCoachCAPDTOsBean.setETime(endTime);

                SaveCourseRequestBody saveCourseRequestBody = new SaveCourseRequestBody();
                saveCourseRequestBody.setPrivateCoachCAPDTOs(privateCoachCAPDTOs);
                HttpManager.postSaveCourse(saveCourseRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
                    @Override
                    public void onSuccess(JSONObject result) {
                        showToast("修改成功！");
                        finish();
                    }

                    @Override
                    public void onFail(String msg) {
                        showToast(msg);
                    }
                });
            } else {
                showToast("课程信息有误,请返回重新选择");
            }
        }


    }

    public void checkoutScheduleTime() {
        HashMap<String, String> map = new HashMap<>();
        map.put("version", "1.3");
        map.put("schooltime", hours + ":" + minutes);
        map.put("week", weekday + "");
        map.put("classHour", consumingMinute);
        HttpManager.postHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_PLAN_IS_ABLE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                tvCourseTimeStatus.setVisibility(View.GONE);
            }

            @Override
            public void onFail(String msg) {
                tvCourseTimeStatus.setVisibility(View.VISIBLE);
                tvCourseTimeStatus.setText("(选中时间段已有安排)");
                showToast(msg);
            }
        });

    }

    @OnClick({R.id.left_tv, R.id.right_tv,R.id.ll_week_sunday, R.id.ll_week_one, R.id.ll_week_two, R.id.ll_week_three, R.id.ll_week_four, R.id.ll_week_five, R.id.ll_week_six})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.left_tv:
                finish();
                break;
            case R.id.right_tv:
                postSaveCourse();
                break;
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
        weekday = index;
        checkoutScheduleTime();
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
