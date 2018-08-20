package com.yijian.staff.mvp.course.timetable.schedule.week.edit.list.addstudent.step2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.CoursePlanBean;
import com.yijian.staff.bean.CourseRecordBean;
import com.yijian.staff.bean.CourseTimeBean;
import com.yijian.staff.bean.GroupedStudentBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.requestbody.course.SaveCourseRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
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
    private ArrayList<String> weekdays = new ArrayList<>();
    private String hours = "00";
    private String minutes = "00";
    private String consumingMinute = "60";
    private List<SaveCourseRequestBody.PrivateCoachCAPDTOsBean> privateCoachCAPDTOs = new ArrayList<>();
    private GroupedStudentBean selectGroupedStudentBean;
    private GroupedStudentBean.PrivateCoachCourseVOSBean course;

    private List<CoursePlanBean> coursePlanBeanList = new ArrayList<>();
    private List<CourseRecordBean> list = new ArrayList<>();
    private CoursePlanTimeAdapter adapter;


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
                postSaveCourse();
            }
        });
        selectWeekDay(weekday);
        weekdays.add("周日");
        weekdays.add("周一");
        weekdays.add("周二");
        weekdays.add("周三");
        weekdays.add("周四");
        weekdays.add("周五");
        weekdays.add("周六");
        init();
    }

    private void init() {
        selectGroupedStudentBean = (GroupedStudentBean) getIntent().getSerializableExtra("selectGroupedStudentBean");
        course = (GroupedStudentBean.PrivateCoachCourseVOSBean) getIntent().getSerializableExtra("course");
        if (selectGroupedStudentBean != null) {
            ImageLoader.setImageResource(BuildConfig.FILE_HOST + selectGroupedStudentBean.getHeadPath(), this, ivHeader);
            int resId = selectGroupedStudentBean.getMemberSex() == 0 ? R.mipmap.lg_man : R.mipmap.lg_women;
            ImageLoader.setImageResource(resId, this, ivSex);
            tvName.setText(selectGroupedStudentBean.getMemberName());
        }
        if (course != null) {
            consumingMinute = course.getConsumingMinute() + "";
            tvCourse.setText(course.getMemberCourseName() + "（" + consumingMinute + "分钟)");
        }
        initSetTime();

        adapter = new CoursePlanTimeAdapter(this, coursePlanBeanList);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

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


    @OnClick({R.id.cancel, R.id.add_time, R.id.ll_week_sunday, R.id.ll_week_one, R.id.ll_week_two, R.id.ll_week_three, R.id.ll_week_four, R.id.ll_week_five, R.id.ll_week_six})
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
                cancel();
                break;
            case R.id.add_time:
                addTime();
                break;
        }
    }

    private void cancel() {
        if (list.size() > 0) {
            int index1 = 0, index2 = 0;
            CourseRecordBean courseRecordBean = list.get(list.size() - 1);
            String weekDay = courseRecordBean.getWeekDay();
            CourseTimeBean courseTimeBean = courseRecordBean.getCourseTimeBean();
            for (int i = 0; i < coursePlanBeanList.size(); i++) {
                CoursePlanBean coursePlanBean = coursePlanBeanList.get(i);
                String weekDay1 = coursePlanBean.getWeekDay();
                if (weekDay.equals(weekDay1)) {
                    index1 = i;
                    List<CourseTimeBean> courseTimeBeanList = coursePlanBean.getCourseTimeBeanList();
                    if (courseTimeBeanList != null) {
                        for (int j = 0; j < courseTimeBeanList.size(); j++) {
                            CourseTimeBean courseTimeBean1 = courseTimeBeanList.get(j);
                            if (courseTimeBean1.compareTo(courseTimeBean) == 0) {
                                index2 = j;
                                break;
                            }
                        }
                    }

                }

            }
            if (coursePlanBeanList.size() > index1) {
                List<CourseTimeBean> courseTimeBeanList = coursePlanBeanList.get(index1).getCourseTimeBeanList();
                if (courseTimeBeanList != null && courseTimeBeanList.size() > index2) {
                    coursePlanBeanList.get(index1).getCourseTimeBeanList().remove(index2);
                }
                List<CourseTimeBean> courseTimeBeanList1 = coursePlanBeanList.get(index1).getCourseTimeBeanList();
                if (courseTimeBeanList1 == null || courseTimeBeanList1.size() == 0) {
                    coursePlanBeanList.remove(index1);
                }
            }


            Collections.sort(coursePlanBeanList);
            adapter.notifyDataSetChanged();
            list.remove(list.size() - 1);
        }
    }

    private void addTime() {

        int visibility = tvCourseTimeStatus.getVisibility();
        if (visibility == View.VISIBLE) {
            showToast("选中时间段已有安排,请选择其他时间！");
        } else {
            String day = weekdays.get(weekday);
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

            CourseTimeBean courseTimeBean = new CourseTimeBean();
            courseTimeBean.setStartTime(startTime);

            courseTimeBean.setEndTime(endTime);

            if (list.size() == 0) {
                CoursePlanBean coursePlanBean = new CoursePlanBean();
                coursePlanBean.setWeekDay(day);
                ArrayList<CourseTimeBean> courseTimeBeans = new ArrayList<>();
                courseTimeBeans.add(courseTimeBean);
                coursePlanBean.setCourseTimeBeanList(courseTimeBeans);
                coursePlanBeanList.add(coursePlanBean);
                Collections.sort(coursePlanBeanList);
                adapter.notifyDataSetChanged();
                CourseRecordBean courseRecordBean = new CourseRecordBean();
                courseRecordBean.setWeekDay(day);
                courseRecordBean.setCourseTimeBean(courseTimeBean);
                list.add(courseRecordBean);
            } else {
                List<CourseRecordBean> repeatList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    CourseRecordBean courseRecordBean = list.get(i);
                    String weekDay = courseRecordBean.getWeekDay();
                    if (weekDay.equals(day)) {
                        repeatList.add(courseRecordBean);
                    }
                }

                for (int i = 0; i < repeatList.size(); i++) {
                    CourseRecordBean courseRecordBean = repeatList.get(i);
                    CourseTimeBean courseTimeBean1 = courseRecordBean.getCourseTimeBean();
                    boolean b = timeIsRepeated(courseTimeBean1, startTime, endTime);
                    if (b) {
                        showToast("添加失败,添加时间不得重复！");
                        return;
                    }
                }

                CourseRecordBean courseRecordBean = new CourseRecordBean();
                courseRecordBean.setWeekDay(day);
                courseRecordBean.setCourseTimeBean(courseTimeBean);
                list.add(courseRecordBean);

                if (repeatList.size() > 0) {
                    for (int i = 0; i < coursePlanBeanList.size(); i++) {
                        CoursePlanBean coursePlanBean = coursePlanBeanList.get(i);
                        if (coursePlanBean.getWeekDay().equals(day)) {
                            List<CourseTimeBean> courseTimeBeanList = coursePlanBean.getCourseTimeBeanList();
                            if (courseTimeBeanList != null) {
                                courseTimeBeanList.add(courseTimeBean);
                                Collections.sort(courseTimeBeanList);
                                coursePlanBean.setCourseTimeBeanList(courseTimeBeanList);
                            }
                        }
                    }
                } else {
                    CoursePlanBean coursePlanBean = new CoursePlanBean();
                    coursePlanBean.setWeekDay(day);
                    ArrayList<CourseTimeBean> courseTimeBeans = new ArrayList<>();
                    courseTimeBeans.add(courseTimeBean);
                    coursePlanBean.setCourseTimeBeanList(courseTimeBeans);
                    coursePlanBeanList.add(coursePlanBean);
                }
                Collections.sort(coursePlanBeanList);
                adapter.notifyDataSetChanged();
            }


        }

    }

    private boolean timeIsRepeated(CourseTimeBean courseTimeBean1, String startTime, String endTime) {
        boolean b = false;
        String startTime1 = courseTimeBean1.getStartTime();
        String endTime1 = courseTimeBean1.getEndTime();
        String s11 = startTime1.replaceAll(":", "");
        String s12 = endTime1.replaceAll(":", "");
        String s21 = startTime.replaceAll(":", "");
        String s22 = endTime.replaceAll(":", "");
        int i11 = Integer.parseInt(s11);
        int i12 = Integer.parseInt(s12);
        int i21 = Integer.parseInt(s21);
        int i22 = Integer.parseInt(s22);
        if (i11 >= i22 || i12 <= i21) {
            b = false;
        } else {
            b = true;
        }
        return b;

    }

    public void postSaveCourse() {

        if (selectGroupedStudentBean != null && course != null) {
            if (list.size() > 0) {

                for (int i = 0; i < list.size(); i++) {
                    SaveCourseRequestBody.PrivateCoachCAPDTOsBean privateCoachCAPDTOsBean = new SaveCourseRequestBody.PrivateCoachCAPDTOsBean();
                    privateCoachCAPDTOsBean.setDataType(1);
                    privateCoachCAPDTOsBean.setMemberId(selectGroupedStudentBean.getMemberId());
                    privateCoachCAPDTOsBean.setMemberCourseId(course.getMemberCourseId());
                    String weekDay = list.get(i).getWeekDay();
                    int week = 0;
                    if (weekDay.equals("周日")) {
                        week = 0;
                    } else if (weekDay.equals("周一")) {
                        week = 1;
                    } else if (weekDay.equals("周二")) {
                        week = 2;
                    } else if (weekDay.equals("周三")) {
                        week = 3;
                    } else if (weekDay.equals("周四")) {
                        week = 4;
                    } else if (weekDay.equals("周五")) {
                        week = 5;
                    } else if (weekDay.equals("周六")) {
                        week = 6;
                    }
                    privateCoachCAPDTOsBean.setWeek(week);
                    privateCoachCAPDTOsBean.setSTime(list.get(i).getCourseTimeBean().getStartTime());
                    privateCoachCAPDTOsBean.setETime(list.get(i).getCourseTimeBean().getEndTime());
                    privateCoachCAPDTOs.add(privateCoachCAPDTOsBean);
                }
                SaveCourseRequestBody saveCourseRequestBody = new SaveCourseRequestBody();
                saveCourseRequestBody.setPrivateCoachCAPDTOs(privateCoachCAPDTOs);
                HttpManager.postSaveCourse(saveCourseRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
                    @Override
                    public void onSuccess(JSONObject result) {
                        showToast("新增成功！");
                        setResult(4567);
                        finish();
                    }

                    @Override
                    public void onFail(String msg) {
                        showToast(msg);
                    }
                });
            }else {
                showToast("请先添加时间！");
            }

        } else {
            showToast("课程信息有误,请返回重新选择");
        }


    }

    public void checkoutScheduleTime() {
        HashMap<String, String> map = new HashMap<>();
        map.put("version", "1.3.0");
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
//                tvCourseTimeStatus.setVisibility(View.VISIBLE);
//                tvCourseTimeStatus.setText("(选中时间段已有安排)");
                showToast(msg);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
