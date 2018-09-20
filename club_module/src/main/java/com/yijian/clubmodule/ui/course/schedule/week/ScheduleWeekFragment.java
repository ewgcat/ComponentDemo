package com.yijian.clubmodule.ui.course.schedule.week;

import android.content.Intent;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.yijian.clubmodule.R;

import com.yijian.clubmodule.bean.CourseStudentBean;
import com.yijian.clubmodule.db.ClubDBManager;
import com.yijian.clubmodule.ui.course.schedule.week.edit.EditCourseTableActivity;
import com.yijian.commonlib.base.mvc.MvcBaseFragment;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.clubmodule.net.httpmanager.url.CourseUrls;
import com.yijian.clubmodule.net.requestbody.course.SaveCourseRequestBody;
import com.yijian.commonlib.net.response.Response2Observer;
import com.yijian.commonlib.net.response.ResultJSONArrayObserver;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.util.CommonUtil;
import com.yijian.commonlib.util.DateUtil;
import com.yijian.commonlib.util.JsonUtil;
import com.yijian.commonlib.util.Logger;
import com.yijian.commonlib.util.system.ScreenUtil;
import com.yijian.commonlib.widget.MyScollView;
import com.yijian.commonlib.widget.ScrollViewListener;
import com.yijian.commonlib.widget.WeekLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





public class ScheduleWeekFragment extends MvcBaseFragment implements View.OnClickListener {

    RelativeLayout content;
    WeekLayout week_layout;
    WeekCourseView week_course_view;
    MyScollView scoll_view;
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
        View rootView = getRootView();
        scoll_view = rootView.findViewById(R.id.scoll_view);
        content = rootView.findViewById(R.id.content);
        week_layout = rootView.findViewById(R.id.week_layout);
        week_course_view = rootView.findViewById(R.id.week_course_view);

        rootView.findViewById(R.id.ll_edit).setOnClickListener(this);

        int screen_width = ScreenUtil.getScreenWidth(getContext());
        Logger.i("TEST","screen_width="+screen_width);
        width = ((screen_width - CommonUtil.dp2px(getContext(), 40))) / 7;
        week_layout.setTimeItemWidthAndHeight(width, width);

        //下边界 屏幕底部
        week_course_view.setItemParams(width, width, 48);
        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                RectF contentRectF = CommonUtil.calcViewScreenLocation(content);
                RectF rectF = CommonUtil.calcViewScreenLocation(week_layout);

                //上边界 在屏幕中的
                float topLimitAbsY = rectF.bottom;
                float bottomLimitAbsY = contentRectF.bottom;
                week_course_view.setLimit(topLimitAbsY,bottomLimitAbsY);
                scollToCurrentTime();
            }
        };
        getActivity().getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(listener);

        scoll_view.setOnScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ViewGroup viewGroup, int x, int y, int oldx, int oldy) {
                week_course_view.onScollYPosition(y);
            }
        });
        week_course_view.setOnDragEndListener(new WeekCourseView.OnDragEndListener() {
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


            List<CourseStudentBean> courseStudentBeans = ClubDBManager.getInstance().queryCourseStudentBeans();
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
                            week_course_view.clearView();
                            for (int i = 0; i < courseStudentBeans.size(); i++) {
                                CourseStudentBean courseStudentBean = courseStudentBeans.get(i);
                                List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                                int weekCode = courseStudentBean.getWeekCode();
                                for (int j = 0; j < list.size(); j++) {
                                    week_course_view.addItem(list.get(j), weekCode);
                                }
                            }
                            return;
                        }
                    }
                }
                if (hasCourse) {
                    if (courseStudentBeans != null) {
                        showToast("该时间段已有课程安排！");
                        week_course_view.clearView();
                        for (int i = 0; i < courseStudentBeans.size(); i++) {
                            CourseStudentBean courseStudentBean = courseStudentBeans.get(i);
                            List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                            int weekCode = courseStudentBean.getWeekCode();
                            for (int j = 0; j < list.size(); j++) {
                                week_course_view.addItem(list.get(j), weekCode);
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

                            week_course_view.clearView();
                            List<CourseStudentBean> courseStudentBeanList = com.alibaba.fastjson.JSONArray.parseArray(data.toString(), CourseStudentBean.class);

                            if (courseStudentBeanList != null) {
                                ClubDBManager.getInstance().insertCourseStudentBeans(courseStudentBeanList);
                                for (int i = 0; i < courseStudentBeanList.size(); i++) {
                                    CourseStudentBean courseStudentBean = courseStudentBeanList.get(i);
                                    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                                    int weekCode = courseStudentBean.getWeekCode();
                                    for (int j = 0; j < list.size(); j++) {
                                        week_course_view.addItem(list.get(j), weekCode);
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
                                week_course_view.clearView();
                                for (int i = 0; i < courseStudentBeans.size(); i++) {
                                    CourseStudentBean courseStudentBean = courseStudentBeans.get(i);
                                    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                                    int weekCode = courseStudentBean.getWeekCode();
                                    for (int j = 0; j < list.size(); j++) {
                                        week_course_view.addItem(list.get(j), weekCode);
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
            List<CourseStudentBean> courseStudentBeanList = ClubDBManager.getInstance().queryCourseStudentBeans();
            if (courseStudentBeanList != null) {
                week_course_view.clearView();
                for (int i = 0; i < courseStudentBeanList.size(); i++) {
                    CourseStudentBean courseStudentBean = courseStudentBeanList.get(i);
                    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                    int weekCode = courseStudentBean.getWeekCode();
                    for (int j = 0; j < list.size(); j++) {
                        week_course_view.addItem(list.get(j), weekCode);
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
        List<CourseStudentBean> courseStudentBeanList = ClubDBManager.getInstance().queryCourseStudentBeans();
        if (courseStudentBeanList != null) {
            week_course_view.clearView();
            for (int i = 0; i < courseStudentBeanList.size(); i++) {
                CourseStudentBean courseStudentBean = courseStudentBeanList.get(i);
                List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                int weekCode = courseStudentBean.getWeekCode();
                for (int j = 0; j < list.size(); j++) {
                    week_course_view.addItem(list.get(j), weekCode);
                }
            }
        }
    }

    public void scollToCurrentTime() {
        long l = System.currentTimeMillis();
        long currentDate = DateUtil.getStringToDate(DateUtil.getCurrentDate(), "yyyy-MM-dd");
        long l1 = 86400000;
        long l2 = l - currentDate;
        long top = width * size * l2 / l1 + week_course_view.getPaddingTop();
        int screenHeight =ScreenUtil.getScreenHeight(getContext());
        if (top > screenHeight) {
            long l3 = top - screenHeight / 2;
            scoll_view.scrollTo(0, (int) l3);
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
                week_course_view.clearView();
                List<CourseStudentBean> courseStudentBeanList = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), CourseStudentBean.class);

                if (courseStudentBeanList != null) {
                    ClubDBManager.getInstance().insertCourseStudentBeans(courseStudentBeanList);

                    for (int i = 0; i < courseStudentBeanList.size(); i++) {
                        CourseStudentBean courseStudentBean = courseStudentBeanList.get(i);
                        List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                        int weekCode = courseStudentBean.getWeekCode();
                        for (int j = 0; j < list.size(); j++) {
                            week_course_view.addItem(list.get(j), weekCode);
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



    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), EditCourseTableActivity.class));

    }
}

