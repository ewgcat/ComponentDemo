package com.yijian.staff.mvp.course.timetable.addstudent;

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
import com.yijian.staff.bean.StudentBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.course.timetable.MyDividerItemDecoration;
import com.yijian.staff.mvp.course.timetable.StudentListAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.SystemUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import drawthink.expandablerecyclerview.listener.OnRecyclerViewListener;

public class AddStudentActivity extends MvcBaseActivity {


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<RecyclerViewData> datas = new ArrayList<>();
    private GroupedStudentBean selectGroupedStudentBean;
    private GroupedStudentBean.CourseBean course;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_add_student;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle("添加学员");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setmRightTvColor(Color.parseColor("#1997f8"));
        navigationBar2.setmRightTvText("下一步");
        List<GroupedStudentBean.CourseBean> courses = new ArrayList<>();
        GroupedStudentBean.CourseBean courseBean1 = new GroupedStudentBean.CourseBean();
        courseBean1.setCourseName("瑜伽课");
        courseBean1.setCourseTime("60分钟");
        courses.add(courseBean1);
        GroupedStudentBean.CourseBean courseBean2 = new GroupedStudentBean.CourseBean();

        courseBean2.setCourseName("增肌课");
        courseBean2.setCourseTime("30分钟");
        courses.add(courseBean2);
        for (int i = 0; i < 10; i++) {
            GroupedStudentBean groupedStudentBean = new GroupedStudentBean();
            groupedStudentBean.setName("学员" + i);
            groupedStudentBean.setSex(i % 2);
            groupedStudentBean.setCourses(courses);
            RecyclerViewData recyclerViewData = new RecyclerViewData(groupedStudentBean, courses, false);
            datas.add(recyclerViewData);
        }
        GroupedListAdapter groupedListAdapter = new GroupedListAdapter(this, datas);
        groupedListAdapter.setOnItemClickListener(new OnRecyclerViewListener.OnItemClickListener() {
            @Override
            public void onGroupItemClick(int position, int groupPosition, View view) {
                RecyclerViewData recyclerViewData = datas.get(groupPosition);
                selectGroupedStudentBean = (GroupedStudentBean) recyclerViewData.getGroupData();
                groupedListAdapter.selectParent(groupPosition);

            }

            @Override
            public void onChildItemClick(int position, int groupPosition, int childPosition, View view) {
                RecyclerViewData recyclerViewData = datas.get(groupPosition);
                List childDatas = recyclerViewData.getGroupItem().getChildDatas();
                course = (GroupedStudentBean.CourseBean) childDatas.get(childPosition);
                groupedListAdapter.selectChild(groupPosition, childPosition);

            }
        });
        rv.addItemDecoration(new MyDividerItemDecoration());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(groupedListAdapter);
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
    }


    public void initComponent() {
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
        String name = etSearch.getText().toString().trim();

    }

    public void loadMore() {


    }

    public void clearEditTextFocus() {
        etSearch.clearFocus();
        rv.requestFocus();
        SystemUtil.hideKeyBoard(etSearch, this);
    }

}
