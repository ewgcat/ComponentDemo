package com.yijian.staff.mvp.course.schedule.week.edit.list.addstudent.step2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
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
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.bean.CourseTimeBean;
import com.yijian.staff.bean.GroupedStudentBean;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.requestbody.course.SaveCourseRequestBody;
import com.yijian.staff.net.response.Response2Observer;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.VibratorUtil;
import com.yijian.staff.widget.HorizontalWheelView;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NoScrollRecycleView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
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

    @BindView(R.id.wheelview1)
    WheelView wheelView1;
    @BindView(R.id.wheelview2)
    WheelView wheelView2;
    @BindView(R.id.rv)
    NoScrollRecycleView rv;
    @BindView(R.id.date_select_wheel_view)
    HorizontalWheelView dateSelectWheelView;


    private int weekday = 0;
    private ArrayList<String> weekdays = new ArrayList<>();
    private String hours = "00";
    private String minutes = "00";
    private int consumingMinute = 0;
    private GroupedStudentBean selectGroupedStudentBean;
    private GroupedStudentBean.PrivateCoachCourseVOSBean course;

    private List<CoursePlanBean> coursePlanBeanList = new ArrayList<>();
    private NewCourseListAdapter newCourseListAdapter;
    private String memberId;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_add_sudent_course_step_two;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar .hideLeftSecondIv();
        navigationBar .setBackClickListener(this);
        navigationBar.setTitle("选择时间");
        navigationBar.setmRightTvColor(Color.parseColor("#1997f8"));
        navigationBar.setmRightTvText("确定");
        navigationBar.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postSaveCourse();
            }
        });
        weekdays.add("周日");
        weekdays.add("周一");
        weekdays.add("周二");
        weekdays.add("周三");
        weekdays.add("周四");
        weekdays.add("周五");
        weekdays.add("周六");
        dateSelectWheelView.setData(weekdays);
        dateSelectWheelView.setOnSelectListener(new HorizontalWheelView.OnSelectListener() {
            @Override
            public void onSelected(String text, int position) {
                VibratorUtil.Vibrate(AddStudentCourseStepTwoActivity.this, 200);
                weekday = position;
                checkoutScheduleTime();
            }
        });
        weekday = getIntent().getIntExtra("weekday",0);
        dateSelectWheelView.setSelectedPosition(weekday);

        init();
    }

    private void init() {
        selectGroupedStudentBean = (GroupedStudentBean) getIntent().getSerializableExtra("selectGroupedStudentBean");
        course = (GroupedStudentBean.PrivateCoachCourseVOSBean) getIntent().getSerializableExtra("course");
        if (selectGroupedStudentBean != null) {
            ImageLoader.setHeadImageResource(BuildConfig.FILE_HOST + selectGroupedStudentBean.getHeadPath(), this, ivHeader);
            int resId = selectGroupedStudentBean.getMemberSex() == 1 ? R.mipmap.lg_man : R.mipmap.lg_women;
            ImageLoader.setImageResource(resId, this, ivSex);
            tvName.setText(selectGroupedStudentBean.getMemberName());
            memberId = selectGroupedStudentBean.getMemberId();
        }
        if (course != null) {
            consumingMinute = course.getConsumingMinute();
            tvCourse.setText(course.getMemberCourseName() + "（" + consumingMinute + "分钟)");

        }
        initSetTime();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        newCourseListAdapter = new NewCourseListAdapter(this);
        newCourseListAdapter.setDataList(coursePlanBeanList);
        newCourseListAdapter.setOnDelListener(new NewCourseListAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                coursePlanBeanList.remove(pos);
                newCourseListAdapter.setDataList(coursePlanBeanList);
//                newCourseListAdapter.getDataList().remove(pos);
//                newCourseListAdapter.notifyItemRemoved(pos);//推荐用这个
//                if (pos != (newCourseListAdapter.getDataList().size())) { // 如果移除的是最后一个，忽略 注意：这里的newCourseListAdapter.getDataList()不需要-1，因为上面已经-1了
//                    newCourseListAdapter.notifyItemRangeChanged(pos, newCourseListAdapter.getDataList().size() - pos);
//                }
            }
        });
        rv.setAdapter(newCourseListAdapter);

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


    @OnClick({R.id.add_time})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.add_time:
                addTime();
                break;
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
            int i2 = consumingMinute;
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

            CourseTimeBean courseTimeBean = new CourseTimeBean(startTime,endTime);


            if (coursePlanBeanList.size() == 0) {
                CoursePlanBean coursePlanBean = new CoursePlanBean();
                coursePlanBean.setWeekDay(day);
                ArrayList<CourseTimeBean> courseTimeBeans = new ArrayList<>();
                courseTimeBeans.add(courseTimeBean);
                coursePlanBean.setCourseTimeBeanList(courseTimeBeans);
                coursePlanBeanList.add(coursePlanBean);
                Collections.sort(coursePlanBeanList);
                newCourseListAdapter.setDataList(coursePlanBeanList);

            } else {

                List<CourseRecordBean> repeatList = new ArrayList<>();
                for (int i = 0; i < coursePlanBeanList.size(); i++) {
                    CoursePlanBean coursePlanBean = coursePlanBeanList.get(i);
                    List<CourseTimeBean> courseTimeBeanList = coursePlanBean.getCourseTimeBeanList();
                    for (int j = 0; j < courseTimeBeanList.size(); j++) {
                        CourseTimeBean mcourseTimeBean = courseTimeBeanList.get(j);
                        String weekDay = coursePlanBean.getWeekDay();
                        if (weekDay.equals(day)) {
                            CourseRecordBean courseRecordBean = new CourseRecordBean();
                            courseRecordBean.setWeekDay(weekDay);
                            courseRecordBean.setCourseTimeBean(mcourseTimeBean);
                            repeatList.add(courseRecordBean);
                        }
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
                newCourseListAdapter.setDataList(coursePlanBeanList);
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
        List<SaveCourseRequestBody.PrivateCoachCAPDTOsBean> privateCoachCAPDTOs = new ArrayList<>();

        if (selectGroupedStudentBean != null && course != null) {
            if (coursePlanBeanList.size() > 0) {

                for (int i = 0; i < coursePlanBeanList.size(); i++) {
                    CoursePlanBean coursePlanBean = coursePlanBeanList.get(i);
                    String weekDay = coursePlanBean.getWeekDay();
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

                    List<CourseTimeBean> courseTimeBeanList = coursePlanBean.getCourseTimeBeanList();

                    for (int j = 0; j < courseTimeBeanList.size(); j++) {
                        CourseTimeBean courseTimeBean = courseTimeBeanList.get(j);
                        SaveCourseRequestBody.PrivateCoachCAPDTOsBean privateCoachCAPDTOsBean = new SaveCourseRequestBody.PrivateCoachCAPDTOsBean();
                        privateCoachCAPDTOsBean.setDataType(1);
                        privateCoachCAPDTOsBean.setMemberId(selectGroupedStudentBean.getMemberId());
                        privateCoachCAPDTOsBean.setMemberCourseId(course.getMemberCourseId());
                        privateCoachCAPDTOsBean.setCoachId(SharePreferenceUtil.getUserId());


                        privateCoachCAPDTOsBean.setWeek(week);
                        privateCoachCAPDTOsBean.setSTime(courseTimeBean.getStartTime());
                        privateCoachCAPDTOsBean.setETime(courseTimeBean.getEndTime());
                        privateCoachCAPDTOs.add(privateCoachCAPDTOsBean);
                    }

                }
                SaveCourseRequestBody saveCourseRequestBody = new SaveCourseRequestBody();
                saveCourseRequestBody.setPrivateCoachCAPDTOs(privateCoachCAPDTOs);
                showLoading();
                HttpManager.postSaveCourse(saveCourseRequestBody,

                        new Response2Observer(getLifecycle()) {
                            @Override
                            public void onSuccess(JSONObject jsonObject) {
                                hideLoading();
                                String msg = JsonUtil.getString(jsonObject, "msg");
                                if (TextUtils.isEmpty(msg)){
                                    showToast("新增成功！");
                                }else {
                                    showToast(msg);
                                }
                                JSONArray data = JsonUtil.getJsonArray(jsonObject, "data");
                                List<CourseStudentBean> list = com.alibaba.fastjson.JSONArray.parseArray(data.toString(), CourseStudentBean.class);
                                DBManager.getInstance().insertCourseStudentBeans(list);

                                setResult(4567);
                                finish();
                            }

                            @Override
                            public void onFail(String msg) {
                                hideLoading();
                                showToast(msg);
                            }
                        });

            } else {
                showToast("请先添加上课时间！");
            }

        } else {
            showToast("课程信息有误,请返回重新选择");
        }


    }

    public void checkoutScheduleTime() {
        boolean hasCourse = false;
        String startTime = hours + "" + minutes;
        int i1 = Integer.parseInt(minutes);
        int i2 = consumingMinute;
        int i3 = i1 + i2;
        int i4 = i3 / 60;
        int i5 = i3 % 60;

        int h1 = Integer.parseInt(hours);
        int h2 = h1 + i4;
        String endTime = "";
        if (h2 < 10) {
            endTime = "0" + h2;
        } else {
            endTime += h2;
        }
        if (i5 < 10) {
            endTime = endTime + "0" + i5;
        } else {
            endTime = endTime + i5;
        }
        int istartTime = Integer.parseInt(startTime.replace(":", ""));
        int iendTime = Integer.parseInt(endTime.replace(":", ""));


        List<CourseStudentBean> courseStudentBeans = DBManager.getInstance().queryCourseStudentBeans();
        if (courseStudentBeans != null && courseStudentBeans.size() > 0) {
            for (int i = 0; i < courseStudentBeans.size(); i++) {
                CourseStudentBean courseStudentBean = courseStudentBeans.get(i);
                if (courseStudentBean.getWeekCode() == weekday) {
                    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> privateCoachCurriculumArrangementPlanVOS = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                    if (privateCoachCurriculumArrangementPlanVOS != null && privateCoachCurriculumArrangementPlanVOS.size() > 0) {
                        for (int j = 0; j < privateCoachCurriculumArrangementPlanVOS.size(); j++) {
                            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean privateCoachCurriculumArrangementPlanVOSBean = privateCoachCurriculumArrangementPlanVOS.get(j);
                            String sTime = privateCoachCurriculumArrangementPlanVOSBean.getSTime();
                            String eTime = privateCoachCurriculumArrangementPlanVOSBean.getETime();
                            int isTime = Integer.parseInt(sTime.replace(":", ""));
                            int ieTime = Integer.parseInt(eTime.replace(":", ""));

                            if (istartTime <= isTime && iendTime >= ieTime) {
                                hasCourse = true;
                            } else if (istartTime < ieTime && iendTime >= ieTime) {
                                hasCourse = true;
                            } else if (istartTime >= isTime && iendTime < isTime) {
                                hasCourse = true;
                            } else if (istartTime < isTime & iendTime > ieTime) {
                                hasCourse = true;
                            }
                        }
                    }

                }
            }
            if (hasCourse) {
                tvCourseTimeStatus.setVisibility(View.VISIBLE);
                tvCourseTimeStatus.setText("(选中时间段已有安排)");
            } else {
                HashMap<String, String> map = new HashMap<>();
                map.put("schooltime", hours + ":" + minutes);
                map.put("week", weekday + "");
                map.put("classHour", consumingMinute + "");
                map.put("memberId", memberId);


                showLoading();
                HttpManager.postHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_PLAN_IS_ABLE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
                    @Override
                    public void onSuccess(JSONObject result) {
                        hideLoading();
                        tvCourseTimeStatus.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFail(String msg) {
                        hideLoading();
                        tvCourseTimeStatus.setVisibility(View.VISIBLE);
                        tvCourseTimeStatus.setText("(选中时间段已有安排)");
                        showToast(msg);
                    }
                });
            }
        }


    }


}
