package com.yijian.staff.mvp.course.schedule.week.edit.list.edit;

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
import com.yijian.staff.db.DBManager;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.requestbody.course.SaveCourseRequestBody;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.util.Logger;
import com.yijian.staff.util.VibratorUtil;
import com.yijian.staff.widget.HorizontalWheelView;

import org.json.JSONArray;
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


    @BindView(R.id.wheelview1)
    WheelView wheelView1;
    @BindView(R.id.wheelview2)
    WheelView wheelView2;

    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.date_select_wheel_view)
    HorizontalWheelView dateSelectWheelView;
    private int weekday = 0;
    private ArrayList<String> weekdays = new ArrayList<>();
    private String hours = "00";
    private String minutes = "00";
    private int consumingMinute = 0;
    private CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseStudentBean;
    private String id;

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
        dateSelectWheelView.setData(weekdays);
        dateSelectWheelView.setOnSelectListener(new HorizontalWheelView.OnSelectListener() {
            @Override
            public void onSelected(String text,int position) {
                VibratorUtil.Vibrate(EditCourseTimeActivity.this,200);
                weekday=position;
                checkoutScheduleTime();
            }
        });

        courseStudentBean = (CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean) getIntent().getSerializableExtra("PrivateCoachCurriculumArrangementPlanVOSBean");
        if (courseStudentBean != null) {
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVO = courseStudentBean.getPrivateCourseMemberVO();
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVO = courseStudentBean.getPrivateCoachCourseVO();
            ImageLoader.setHeadImageResource(BuildConfig.FILE_HOST + privateCourseMemberVO.getHeadPath(), this, ivHeader);
            int resId = privateCourseMemberVO.getMemberSex() == 1 ? R.mipmap.lg_man : R.mipmap.lg_women;
            ImageLoader.setImageResource(resId, this, ivSex);
            tvName.setText(privateCourseMemberVO.getMemberName());
            tvCourse.setText(privateCoachCourseVO.getMemberCourseName() + "（" + privateCoachCourseVO.getConsumingMinute() + "分钟)");
            int week = courseStudentBean.getWeek();
            weekday = week;
            dateSelectWheelView.setSelectedPosition(weekday);
            id = courseStudentBean.getId();
            initSetTime();
            consumingMinute = privateCoachCourseVO.getConsumingMinute();
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
                    hours = mOptionsItems1.get(i);

                    int m = Integer.parseInt(split[1]);
                    if (m <= 30 && m > 0) {
                        wheelView2.setCurrentItem(1);
                        minutes = mOptionsItems2.get(1);
                    }
                    if (m < 60 && m > 30) {
                        wheelView2.setCurrentItem(0);
                        minutes = mOptionsItems2.get(0);

                        wheelView1.setCurrentItem(i + 1);
                        hours = mOptionsItems1.get(i + 1);
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
            List<SaveCourseRequestBody.PrivateCoachCAPDTOsBean> privateCoachCAPDTOs = new ArrayList<>();

            if (courseStudentBean != null) {

                CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVO = courseStudentBean.getPrivateCoachCourseVO();
                CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVO = courseStudentBean.getPrivateCourseMemberVO();

                SaveCourseRequestBody.PrivateCoachCAPDTOsBean privateCoachCAPDTOsBean = new SaveCourseRequestBody.PrivateCoachCAPDTOsBean();
                privateCoachCAPDTOsBean.setDataType(1);
                privateCoachCAPDTOsBean.setMemberId(privateCourseMemberVO.getMemberId());
                privateCoachCAPDTOsBean.setMemberCourseId(privateCoachCourseVO.getMemberCourseId());
                privateCoachCAPDTOsBean.setCoachId(SharePreferenceUtil.getUserId());
                privateCoachCAPDTOsBean.setCapId(id);


                privateCoachCAPDTOsBean.setWeek(weekday);

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


                privateCoachCAPDTOsBean.setSTime(startTime);
                privateCoachCAPDTOsBean.setETime(endTime);
                privateCoachCAPDTOs.add(privateCoachCAPDTOsBean);
                SaveCourseRequestBody saveCourseRequestBody = new SaveCourseRequestBody();
                saveCourseRequestBody.setPrivateCoachCAPDTOs(privateCoachCAPDTOs);
                HttpManager.postSaveCourse(saveCourseRequestBody, new ResultJSONArrayObserver(getLifecycle()) {
                    @Override
                    public void onSuccess(JSONArray result) {
                        showToast("修改成功！");

                        List<CourseStudentBean> list = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), CourseStudentBean.class);

                        DBManager.getInstance().insertCourseStudentBeans(list);
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

                            if (!privateCoachCurriculumArrangementPlanVOSBean.getId().equals(id)) {
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
            }

            if (hasCourse) {
                tvCourseTimeStatus.setVisibility(View.VISIBLE);
                tvCourseTimeStatus.setText("(选中时间段已有安排)");
            } else {
                HashMap<String, String> map = new HashMap<>();
                map.put("version", "1.3");
                String value = hours + ":" + minutes;
                Logger.i("TEST", "value=" + value);
                map.put("schooltime", value);
                map.put("week", weekday + "");
                map.put("classHour", consumingMinute + "");
                map.put("capId", id);
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


    @OnClick({R.id.left_tv, R.id.right_tv})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.left_tv:
                finish();
                break;
            case R.id.right_tv:
                postSaveCourse();
                break;

        }
    }



}
