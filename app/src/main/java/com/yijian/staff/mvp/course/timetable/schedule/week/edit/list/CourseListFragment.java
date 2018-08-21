package com.yijian.staff.mvp.course.timetable.schedule.week.edit.list;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.timetable.schedule.week.edit.list.CourseListAdapter;
import com.yijian.staff.mvp.course.timetable.schedule.week.edit.list.addstudent.step1.AddStudentCourseStepOneActivity;
import com.yijian.staff.widget.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseListFragment extends MvcBaseFragment {

    @BindView(R.id.rv)
    SwipeMenuRecyclerView rv;
    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> dataList = new ArrayList<>();
    private CourseListAdapter courseListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_student_list;
    }

    @Override
    public void initView() {
        courseListAdapter = new CourseListAdapter(getContext(), dataList);
        rv.addItemDecoration(new MyDividerItemDecoration());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(courseListAdapter);
        rv.setItemViewSwipeEnabled(true);
        rv.setOnItemMoveListener(new OnItemMoveListener() {
            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                return false;
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
                int position = srcHolder.getAdapterPosition();
                //删除本地数据
                // Item被侧滑删除时，删除数据，并更新adapter。
                dataList.remove(position);
                courseListAdapter.notifyItemRemoved(position);
                //TODO 向后台发送请求

            }
        });

    }


    public void updateUI(List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> dataList) {
        this.dataList = dataList;
        courseListAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.ll_add_student)
    public void onViewClicked() {

        Intent intent = new Intent(getContext(), AddStudentCourseStepOneActivity.class);
        startActivity(intent);
    }
}
