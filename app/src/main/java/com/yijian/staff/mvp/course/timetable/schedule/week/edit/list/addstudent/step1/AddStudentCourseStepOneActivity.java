package com.yijian.staff.mvp.course.timetable.schedule.week.edit.list.addstudent.step1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.GroupedStudentBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.course.timetable.schedule.week.edit.list.addstudent.step2.AddStudentCourseStepTwoActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.MyDividerItemDecoration;
import com.yijian.staff.util.SystemUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import drawthink.expandablerecyclerview.listener.OnRecyclerViewListener;

public class AddStudentCourseStepOneActivity extends MvcBaseActivity {


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<RecyclerViewData> datas = new ArrayList<>();
    private GroupedStudentBean selectGroupedStudentBean;
    private GroupedStudentBean.PrivateCoachCourseVOSBean course;
    private StudentCourseListAdapter studentCourseListAdapter;

    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量

    @Override
    protected int getLayoutID() {
        return R.layout.activity_add_student_course_step_one;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initNavigationBar();

        initComponent();
        etSearch.setHintTextColor(Color.parseColor("#666666"));
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        clearEditTextFocus();
                        refresh();
                        break;
                }
                return true;
            }
        });
        refresh();
    }

    private void initNavigationBar() {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle("添加学员");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setmRightTvColor(Color.parseColor("#1997f8"));
        navigationBar2.setmRightTvText("下一步");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectGroupedStudentBean != null) {
                    Intent intent = new Intent(AddStudentCourseStepOneActivity.this, AddStudentCourseStepTwoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("selectGroupedStudentBean", selectGroupedStudentBean);
                    int selectedChildIndex = studentCourseListAdapter.getSelectedChildIndex();
                    course = selectGroupedStudentBean.getPrivateCoachCourseVOS().get(selectedChildIndex);
                    bundle.putSerializable("course", course);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    showToast("请先选择学员!");
                }
            }
        });
    }


    public void initComponent() {
        studentCourseListAdapter = new StudentCourseListAdapter(this, datas);
        studentCourseListAdapter.setOnItemClickListener(new OnRecyclerViewListener.OnItemClickListener() {
            @Override
            public void onGroupItemClick(int position, int groupPosition, View view) {
                RecyclerViewData recyclerViewData = datas.get(groupPosition);
                boolean expand = recyclerViewData.getGroupItem().isExpand();
                if (!expand) {
                    selectGroupedStudentBean = (GroupedStudentBean) recyclerViewData.getGroupData();
                } else {
                    selectGroupedStudentBean = null;
                    course = null;
                }
                studentCourseListAdapter.selectParent(groupPosition);

            }

            @Override
            public void onChildItemClick(int position, int groupPosition, int childPosition, View view) {
                RecyclerViewData recyclerViewData = datas.get(groupPosition);
                List childDatas = recyclerViewData.getGroupItem().getChildDatas();
                course = (GroupedStudentBean.PrivateCoachCourseVOSBean) childDatas.get(childPosition);
                studentCourseListAdapter.selectChild(groupPosition, childPosition);

            }
        });
        rv.addItemDecoration(new MyDividerItemDecoration());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(studentCourseListAdapter);
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

    private void refresh() {
        pageNum = 1;
        pageSize = 10;
        datas.clear();
        showLoading();
        String name = etSearch.getText().toString().trim();
        HashMap<String, String> map = new HashMap<>();
        map.put("version", "1.3.0");
        map.put("pageNum", "" + pageNum);
        map.put("pageSize", "" + pageSize);
        map.put("memberName",name);

        HttpManager.getHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_WEEK_PLAN_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                refreshLayout.finishRefresh(2000, true);
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                List<GroupedStudentBean> list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), GroupedStudentBean.class);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        GroupedStudentBean groupedStudentBean = list.get(i);
                        List<GroupedStudentBean.PrivateCoachCourseVOSBean> courses = groupedStudentBean.getPrivateCoachCourseVOS();
                        RecyclerViewData recyclerViewData = new RecyclerViewData(groupedStudentBean, courses, false);
                        datas.add(recyclerViewData);
                    }
                }
                studentCourseListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);
                showToast(msg);
                hideLoading();
            }
        });
    }

    public void loadMore() {
        showLoading();
        String name = etSearch.getText().toString().trim();
        HashMap<String, String> map = new HashMap<>();
        map.put("version", "1.3.0");
        map.put("pageNum", "" + pageNum);
        map.put("pageSize", "" + pageSize);
        map.put("memberName",name);

        HttpManager.getHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_WEEK_PLAN_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                List<GroupedStudentBean> list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), GroupedStudentBean.class);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        GroupedStudentBean groupedStudentBean = list.get(i);
                        List<GroupedStudentBean.PrivateCoachCourseVOSBean> courses = groupedStudentBean.getPrivateCoachCourseVOS();
                        RecyclerViewData recyclerViewData = new RecyclerViewData(groupedStudentBean, courses, false);
                        datas.add(recyclerViewData);
                    }
                }
                studentCourseListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                showToast(msg);
                hideLoading();
            }
        });

    }

    public void clearEditTextFocus() {
        etSearch.clearFocus();
        rv.requestFocus();
        SystemUtil.hideKeyBoard(etSearch, this);
    }

}
