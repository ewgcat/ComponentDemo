package com.yijian.staff.mvp.course.timetable.schedule.week.edit.list;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.timetable.schedule.week.edit.list.addstudent.step1.AddStudentCourseStepOneActivity;
import com.yijian.staff.widget.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseListFragment extends MvcBaseFragment {

    @BindView(R.id.rv)
    RecyclerView rv;
    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> dataList = new ArrayList<>();
    private CourseListAdapter mDataAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_student_list;
    }

    @Override
    public void initView() {

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new MyDividerItemDecoration());

        mDataAdapter = new CourseListAdapter(getContext());
        mDataAdapter.setDataList(dataList);
        mDataAdapter.setOnDelListener(new CourseListAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                Toast.makeText(getContext(), "删除:" + pos, Toast.LENGTH_SHORT).show();

                //RecyclerView关于notifyItemRemoved的那点小事 参考：http://blog.csdn.net/jdsjlzx/article/details/52131528
                mDataAdapter.getDataList().remove(pos);
                mDataAdapter.notifyItemRemoved(pos);//推荐用这个

                if(pos != (mDataAdapter.getDataList().size())){ // 如果移除的是最后一个，忽略 注意：这里的mDataAdapter.getDataList()不需要-1，因为上面已经-1了
                    mDataAdapter.notifyItemRangeChanged(pos, mDataAdapter.getDataList().size() - pos);
                }
                //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
            }

        });

        rv.setAdapter(mDataAdapter);




    }


    public void updateUI(List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list) {
        this.dataList.clear();
        this.dataList .addAll(list) ;
        this.dataList.add(new CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean());
        this.dataList.add(new CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean());
        this.dataList.add(new CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean());
        mDataAdapter.setDataList(dataList);
    }


    @OnClick(R.id.ll_add_student)
    public void onViewClicked() {

        Intent intent = new Intent(getContext(), AddStudentCourseStepOneActivity.class);
        startActivity(intent);
    }
}
