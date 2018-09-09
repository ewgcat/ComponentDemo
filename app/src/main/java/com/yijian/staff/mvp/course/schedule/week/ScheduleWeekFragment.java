package com.yijian.staff.mvp.course.schedule.week;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.schedule.week.edit.EditCourseTableActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.requestbody.course.SaveCourseRequestBody;
import com.yijian.staff.net.response.Response2Observer;
import com.yijian.staff.net.response.ResponseObserver;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.MyScollView;
import com.yijian.staff.widget.ScrollViewListener;
import com.yijian.staff.widget.WeekLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.yijian.staff.application.CustomApplication.SCREEN_WIDTH;


public class ScheduleWeekFragment extends MvcBaseFragment {

    @BindView(R.id.content)
    RelativeLayout content;
    @BindView(R.id.week_layout)
    WeekLayout weekLayout;
    @BindView(R.id.week_course_view)
    WeekCourseView weekCourseView;
    @BindView(R.id.scoll_view)
    MyScollView scollView;
    private static String TAG = ScheduleWeekFragment.class.getSimpleName();
    private ViewTreeObserver.OnGlobalLayoutListener listener;
    private int width;
    private int size = 48;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule_week;
    }

    @Override
    public void initView() {

        width = ((SCREEN_WIDTH - CommonUtil.dp2px(getContext(), 40))) / 7;
        weekLayout.setTimeItemWidthAndHeight(width, width);

        //下边界 屏幕底部
        weekCourseView.setItemParams(width, width, 48);
        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                RectF contentRectF = CommonUtil.calcViewScreenLocation(content);
                RectF rectF = CommonUtil.calcViewScreenLocation(weekLayout);

                //上边界 在屏幕中的
                float topLimitAbsY = rectF.bottom;
                float bottomLimitAbsY = contentRectF.bottom;
                weekCourseView.setLimit(topLimitAbsY, bottomLimitAbsY);
                scollToCurrentTime();
            }
        };
        getActivity().getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(listener);

        scollView.setOnScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ViewGroup viewGroup, int x, int y, int oldx, int oldy) {
                weekCourseView.onScollYPosition(y);
            }
        });
        weekCourseView.setOnDragEndListener(new WeekCourseView.OnDragEndListener() {
            @Override
            public void onDragEnd(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean) {
                postSaveCourse(courseBean);
            }
        });
        initData();
    }

    private CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean orignCourse;
    public void postSaveCourse(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean) {


        List<SaveCourseRequestBody.PrivateCoachCAPDTOsBean> privateCoachCAPDTOs = new ArrayList<>();

        if (courseBean != null) {
            Boolean hasCourse = false;
            Boolean isSelf = false;
            int istartTime = Integer.parseInt(courseBean.getSTime().replace(":", ""));
            int iendTime = Integer.parseInt(courseBean.getETime().replace(":", ""));


            List<CourseStudentBean> courseStudentBeans = DBManager.getInstance().queryCourseStudentBeans();
            if (courseStudentBeans != null && courseStudentBeans.size() > 0) {
                for (int i = 0; i < courseStudentBeans.size(); i++) {
                    CourseStudentBean courseStudentBean = courseStudentBeans.get(i);
                    if (courseStudentBean.getWeekCode() == courseBean.getWeek()) {
                        List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> privateCoachCurriculumArrangementPlanVOS = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                        if (privateCoachCurriculumArrangementPlanVOS != null && privateCoachCurriculumArrangementPlanVOS.size() > 0) {
                            for (int j = 0; j < privateCoachCurriculumArrangementPlanVOS.size(); j++) {
                                CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean privateCoachCurriculumArrangementPlanVOSBean = privateCoachCurriculumArrangementPlanVOS.get(j);
                                if (!privateCoachCurriculumArrangementPlanVOSBean.getId().equals(courseBean.getId())) {
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
                                } else {
                                    isSelf = true;
                                    orignCourse = privateCoachCurriculumArrangementPlanVOSBean;
                                }

                            }
                        }

                    }
                }

                if (isSelf) {
                    if (orignCourse != null) {
                        if (courseBean.getWeek() == orignCourse.getWeek()
                                && courseBean.getSTime().equals(orignCourse.getSTime())
                                && courseBean.getETime().equals(orignCourse.getETime())) {
                            weekCourseView.clearView();
                            for (int i = 0; i < courseStudentBeans.size(); i++) {
                                CourseStudentBean courseStudentBean = courseStudentBeans.get(i);
                                List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                                int weekCode = courseStudentBean.getWeekCode();
                                for (int j = 0; j < list.size(); j++) {
                                    weekCourseView.addItem(list.get(j), weekCode);
                                }
                            }
                            return;
                        }
                    }
                }
                if (hasCourse) {
                    if (courseStudentBeans != null) {
                        showToast("该时间段已有课程安排！");
                        weekCourseView.clearView();
                        for (int i = 0; i < courseStudentBeans.size(); i++) {
                            CourseStudentBean courseStudentBean = courseStudentBeans.get(i);
                            List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                            int weekCode = courseStudentBean.getWeekCode();
                            for (int j = 0; j < list.size(); j++) {
                                weekCourseView.addItem(list.get(j), weekCode);
                            }
                        }
                    }
                } else {
                    CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVO = courseBean.getPrivateCoachCourseVO();
                    CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVO = courseBean.getPrivateCourseMemberVO();

                    SaveCourseRequestBody.PrivateCoachCAPDTOsBean privateCoachCAPDTOsBean = new SaveCourseRequestBody.PrivateCoachCAPDTOsBean();
                    privateCoachCAPDTOsBean.setDataType(1);
                    privateCoachCAPDTOsBean.setMemberId(privateCourseMemberVO.getMemberId());
                    privateCoachCAPDTOsBean.setMemberCourseId(privateCoachCourseVO.getMemberCourseId());
                    privateCoachCAPDTOsBean.setCoachId(SharePreferenceUtil.getUserId());
                    privateCoachCAPDTOsBean.setCapId(courseBean.getId());

                    privateCoachCAPDTOsBean.setWeek(courseBean.getWeek());
                    privateCoachCAPDTOsBean.setSTime(courseBean.getSTime());
                    privateCoachCAPDTOsBean.setETime(courseBean.getETime());
                    privateCoachCAPDTOs.add(privateCoachCAPDTOsBean);
                    SaveCourseRequestBody saveCourseRequestBody = new SaveCourseRequestBody();
                    saveCourseRequestBody.setPrivateCoachCAPDTOs(privateCoachCAPDTOs);
                    showLoading();

                    HttpManager.postSaveCourse(saveCourseRequestBody, new Response2Observer(getLifecycle()) {
                        @Override
                        public void onSuccess(JSONObject jsonObject) {
                            String msg = JsonUtil.getString(jsonObject, "msg");
                            if (TextUtils.isEmpty(msg)){
                            }else {
                                showToast(msg);
                            }
                            JSONArray data = JsonUtil.getJsonArray(jsonObject, "data");;

                            weekCourseView.clearView();
                            List<CourseStudentBean> courseStudentBeanList = com.alibaba.fastjson.JSONArray.parseArray(data.toString(), CourseStudentBean.class);

                            if (courseStudentBeanList != null) {
                                DBManager.getInstance().insertCourseStudentBeans(courseStudentBeanList);
                                for (int i = 0; i < courseStudentBeanList.size(); i++) {
                                    CourseStudentBean courseStudentBean = courseStudentBeanList.get(i);
                                    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                                    int weekCode = courseStudentBean.getWeekCode();
                                    for (int j = 0; j < list.size(); j++) {
                                        weekCourseView.addItem(list.get(j), weekCode);
                                    }
                                }
                            }
                            hideLoading();
                        }

                        @Override
                        public void onFail(String msg) {
                            hideLoading();
                            showToast(msg);
                            if (courseStudentBeans != null) {
                                weekCourseView.clearView();
                                for (int i = 0; i < courseStudentBeans.size(); i++) {
                                    CourseStudentBean courseStudentBean = courseStudentBeans.get(i);
                                    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                                    int weekCode = courseStudentBean.getWeekCode();
                                    for (int j = 0; j < list.size(); j++) {
                                        weekCourseView.addItem(list.get(j), weekCode);
                                    }
                                }
                            }
                        }
                    });
                }


            }


        }

    }

    /**
     * 从日视图切换回来刷新数据
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            List<CourseStudentBean> courseStudentBeanList = DBManager.getInstance().queryCourseStudentBeans();
            if (courseStudentBeanList != null) {
                weekCourseView.clearView();
                for (int i = 0; i < courseStudentBeanList.size(); i++) {
                    CourseStudentBean courseStudentBean = courseStudentBeanList.get(i);
                    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                    int weekCode = courseStudentBean.getWeekCode();
                    for (int j = 0; j < list.size(); j++) {
                        weekCourseView.addItem(list.get(j), weekCode);
                    }
                }
            }
        }
    }

    /**
     * 从编辑排课表功能返回刷新数据
     */
    @Override
    public void onResume() {
        super.onResume();
        List<CourseStudentBean> courseStudentBeanList = DBManager.getInstance().queryCourseStudentBeans();
        if (courseStudentBeanList != null) {
            weekCourseView.clearView();
            for (int i = 0; i < courseStudentBeanList.size(); i++) {
                CourseStudentBean courseStudentBean = courseStudentBeanList.get(i);
                List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                int weekCode = courseStudentBean.getWeekCode();
                for (int j = 0; j < list.size(); j++) {
                    weekCourseView.addItem(list.get(j), weekCode);
                }
            }
        }
    }

    public void scollToCurrentTime() {
        long l = System.currentTimeMillis();
        long currentDate = DateUtil.getStringToDate(DateUtil.getCurrentDate(), "yyyy-MM-dd");
        long l1 = 86400000;
        long l2 = l - currentDate;
        long top = width * size * l2 / l1 + weekCourseView.getPaddingTop();
        int screenHeight = CustomApplication.SCREEN_HEIGHT;
        if (top > screenHeight) {
            long l3 = top - screenHeight / 2;
            scollView.scrollTo(0, (int) l3);
        }
        getActivity().getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }

    private void initData() {
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("version", "1.3");

        HttpManager.getHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_WEEK_PLAN_URL, map, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {
                weekCourseView.clearView();
                List<CourseStudentBean> courseStudentBeanList = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), CourseStudentBean.class);

                if (courseStudentBeanList != null) {
                    DBManager.getInstance().insertCourseStudentBeans(courseStudentBeanList);

                    for (int i = 0; i < courseStudentBeanList.size(); i++) {
                        CourseStudentBean courseStudentBean = courseStudentBeanList.get(i);
                        List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                        int weekCode = courseStudentBean.getWeekCode();
                        for (int j = 0; j < list.size(); j++) {
                            weekCourseView.addItem(list.get(j), weekCode);
                        }
                    }
                }
                hideLoading();
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                showToast(msg);
            }
        });
    }

    @OnClick(R.id.ll_edit)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), EditCourseTableActivity.class));
    }

}

