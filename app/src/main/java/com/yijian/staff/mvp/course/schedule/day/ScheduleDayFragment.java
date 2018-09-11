package com.yijian.staff.mvp.course.schedule.day;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.bean.DateBean;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class ScheduleDayFragment extends MvcBaseFragment {
    private static String TAG = ScheduleDayFragment.class.getSimpleName();

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.course_view)
    DayCourseView dayCourseView;
    @BindView(R.id.scoll_view)
    MyScollView scollView;

    private List<DateBean> dateBeanList = new ArrayList<>();
    private int index = 0;
    private int height, size;
    private ViewTreeObserver.OnGlobalLayoutListener listener;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule_day;
    }

    @Override
    public void initView() {
        initLeftDate();
        height = CommonUtil.dp2px(getContext(), 44);
        size = 48;
        dayCourseView.setHeightAndSize(height, size);
        dayCourseView.setOnSelectFlagListener(new DayCourseView.OnSelectFlagListener() {
            @Override
            public void OnSelectFlag(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean, String color) {
                postSaveCourse(courseBean, color);
            }
        });
        dayCourseView.setActivity(getActivity());
        dayCourseView.setOnSelectLockTimeListener(new DayCourseView.OnSelectLockTimeListener() {
            @Override
            public void onSelectLockTime(String startTime, String endTime) {
                postLockTime(startTime, endTime);
            }

            @Override
            public void onUnSelectLockTime(View view, String id) {
                DBManager.getInstance().deletePrivateCoachCurriculumArrangementPlanVOSBeanById(id);
                deleteLockTime(view, id);
            }
        });


        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        getActivity().registerReceiver(broadcastReceiver, filter);
        scollView.setOnScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ViewGroup viewGroup, int x, int y, int oldx, int oldy) {
                dayCourseView.dismiss();
                dayCourseView.onScollYPosition(y);
            }
        });
        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scollToCurrentTime();
            }
        };
        getActivity().getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(listener);

    }

    /**
     *
     */
    @Override
    public void onResume() {
        super.onResume();
        request();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            request();
        }
    }

    private void deleteLockTime(View view, String id) {
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("capId", id);
        HttpManager.postHasHeaderHasParam(CourseUrls.DELETE_PRIVATE_COURSE_PLAN_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                dayCourseView.removeLockView(view);
                hideLoading();
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                showToast(msg);
            }
        });
    }


    public void scollToCurrentTime() {
        long l = System.currentTimeMillis();
        long currentDate = DateUtil.getStringToDate(DateUtil.getCurrentDate(), "yyyy-MM-dd");
        long l1 = 86400000;
        long l2 = l - currentDate;
        long top = height * size * l2 / l1 + dayCourseView.getPaddingTop();
        int screenHeight = CustomApplication.SCREEN_HEIGHT;
        if (top > screenHeight) {
            long l3 = top - screenHeight / 2;
            scollView.scrollTo(0, (int) l3);
        }
        getActivity().getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }

    private void initLeftDate() {
        for (int i = 0; i < 7; i++) {
            Date date = new Date(System.currentTimeMillis() + i * 86400000);
            String s = transferDate(date);
            String weekOfDate = DateUtil.getWeekOfDate(date);
            DateBean dateBean = new DateBean();
            dateBean.setDate(s);
            dateBean.setWeekDay(weekOfDate);
            dateBeanList.add(dateBean);
        }
        LeftDateListAdapter adapter = new LeftDateListAdapter(getContext(), CustomApplication.SCREEN_HEIGHT / 9, dateBeanList);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        adapter.setItemClickListener(new LeftDateListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                adapter.selectDate(position);
                index = position;
                request();
            }
        });
        rv.scrollToPosition(0);
        adapter.selectDate(0);
    }

    public String transferDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    private void updateUi(List<CourseStudentBean> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                CourseStudentBean courseStudentBean = list.get(i);
                int weekCode = courseStudentBean.getWeekCode();
                if (weekCode == getWeek()) {
                    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> courseBeanList = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
                    if (courseBeanList != null && courseBeanList.size() > 0) {
                        for (int j = 0; j < courseBeanList.size(); j++) {
                            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean privateCoachCurriculumArrangementPlanVOSBean = courseBeanList.get(j);
                            dayCourseView.addItem(privateCoachCurriculumArrangementPlanVOSBean);
                        }
                    }

                }
            }
        }

    }

    private void request() {
        dayCourseView.clearView();
        showLoading();
        int week = getWeek();
        HashMap<String, String> map = new HashMap<>();
        map.put("week", week + "");
        HttpManager.getHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_DAY_PLAN_URL, map, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {
                List<CourseStudentBean> list = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), CourseStudentBean.class);
                if (list != null) {
                    updateUi(list);
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

    private int getWeek() {
        DateBean dateBean = dateBeanList.get(index);
        String weekDay = dateBean.getWeekDay();
        int week = 0;
        switch (weekDay) {
            case "周日":
                week = 0;
                break;
            case "周一":
                week = 1;
                break;
            case "周二":
                week = 2;
                break;
            case "周三":
                week = 3;
                break;
            case "周四":
                week = 4;
                break;
            case "周五":
                week = 5;
                break;
            case "周六":
                week = 6;
                break;
        }
        return week;
    }

    private void post() {
        showLoading();
        DateBean dateBean = dateBeanList.get(index);
        String weekDay = dateBean.getWeekDay();
        int week = 0;
        switch (weekDay) {
            case "周日":
                week = 0;
                break;
            case "周一":
                week = 1;
                break;
            case "周二":
                week = 2;
                break;
            case "周三":
                week = 3;
                break;
            case "周四":
                week = 4;
                break;
            case "周五":
                week = 5;
                break;
            case "周六":
                week = 6;
                break;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("week", week + "");
        HttpManager.postAbortAppointCourseTable(CourseUrls.ABORT_APPOINT_COURSE_TABLE_URL, map, new ResponseObserver<JSONObject>(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                hideLoading();
                String msg = JsonUtil.getString(jsonObject, "msg");
                if (TextUtils.isEmpty(msg)){
                    showToast("排课计划生成约课表成功！");
                }else {
                    showToast(msg);
                }
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                showToast(msg);
            }
        });
    }


    public void postSaveCourse(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean, String color) {
        List<SaveCourseRequestBody.PrivateCoachCAPDTOsBean> privateCoachCAPDTOs = new ArrayList<>();
        if (courseBean != null) {
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVO = courseBean.getPrivateCoachCourseVO();
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVO = courseBean.getPrivateCourseMemberVO();
            SaveCourseRequestBody.PrivateCoachCAPDTOsBean privateCoachCAPDTOsBean = new SaveCourseRequestBody.PrivateCoachCAPDTOsBean();
            privateCoachCAPDTOsBean.setDataType(1);
            privateCoachCAPDTOsBean.setMemberId(privateCourseMemberVO.getMemberId());
            privateCoachCAPDTOsBean.setMemberCourseId(privateCoachCourseVO.getMemberCourseId());
            privateCoachCAPDTOsBean.setCoachId(SharePreferenceUtil.getUserId());
            privateCoachCAPDTOsBean.setCapId(courseBean.getId());
            privateCoachCAPDTOsBean.setWeek(getWeek());
            privateCoachCAPDTOsBean.setColour(color);
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
                    dayCourseView.dismiss();
                    List<CourseStudentBean> courseStudentBeanList = com.alibaba.fastjson.JSONArray.parseArray(data.toString(), CourseStudentBean.class);
                    if (courseStudentBeanList != null) {
                        DBManager.getInstance().insertCourseStudentBeans(courseStudentBeanList);
                    }
                    hideLoading();
                    request();
                }

                @Override
                public void onFail(String msg) {
                    hideLoading();
                    dayCourseView.dismiss();
                    showToast(msg);
                }
            });

        }

    }

    private void postLockTime(String startTime, String endTime) {



        Boolean hasCourse = false;
        int istartTime = Integer.parseInt(startTime.replace(":", ""));
        int iendTime = Integer.parseInt(endTime.replace(":", ""));


        List<CourseStudentBean> courseStudentBeans = DBManager.getInstance().queryCourseStudentBeans();
        if (courseStudentBeans != null && courseStudentBeans.size() > 0) {
            for (int i = 0; i < courseStudentBeans.size(); i++) {
                CourseStudentBean courseStudentBean = courseStudentBeans.get(i);
                if (courseStudentBean.getWeekCode() == getWeek()) {
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
        }
        if (hasCourse) {
            if (courseStudentBeans != null) {
                showToast("该时间段已有课程安排！");
            }
        } else {
            SaveCourseRequestBody.PrivateCoachCAPDTOsBean privateCoachCAPDTOsBean = new SaveCourseRequestBody.PrivateCoachCAPDTOsBean();
            privateCoachCAPDTOsBean.setDataType(0);
            privateCoachCAPDTOsBean.setCoachId(SharePreferenceUtil.getUserId());
            privateCoachCAPDTOsBean.setWeek(getWeek());
            privateCoachCAPDTOsBean.setSTime(startTime);
            privateCoachCAPDTOsBean.setETime(endTime);
            showLoading();
            HttpManager.postLockTime(privateCoachCAPDTOsBean, new ResponseObserver<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean>(getLifecycle()) {
                @Override
                public void onSuccess(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean bean) {
                    hideLoading();
                    String id = bean.getId();
                    DBManager.getInstance().insertPrivateCoachCurriculumArrangementPlanVOSBean(bean);
                    dayCourseView.addLockView(startTime, endTime, id);
                }

                @Override
                public void onFail(String msg) {
                    hideLoading();
                    dayCourseView.dismiss();
                    showToast(msg);
                }
            });
        }
    }


    @OnClick({R.id.iv_edit})
    public void onViewClicked(View view) {
        post();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                dayCourseView.invalidate();
            }
        }
    };

}
