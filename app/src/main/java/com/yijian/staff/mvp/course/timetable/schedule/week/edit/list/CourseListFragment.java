package com.yijian.staff.mvp.course.timetable.schedule.week.edit.list;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.timetable.schedule.week.edit.list.addstudent.step1.AddStudentCourseStepOneActivity;
import com.yijian.staff.mvp.vipermanage.viper.viperlist.filter.HuijiViperFilterBean;
import com.yijian.staff.rx.RxBus;
import com.yijian.staff.widget.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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

                mDataAdapter.getDataList().remove(pos);
                mDataAdapter.notifyItemRemoved(pos);//推荐用这个

                if(pos != (mDataAdapter.getDataList().size())){ // 如果移除的是最后一个，忽略 注意：这里的mDataAdapter.getDataList()不需要-1，因为上面已经-1了
                    mDataAdapter.notifyItemRangeChanged(pos, mDataAdapter.getDataList().size() - pos);
                }

                //TODO 发送删除请求
            }

        });

        rv.setAdapter(mDataAdapter);


        Disposable disposable = RxBus.getDefault().toDefaultFlowable(CourseStudentBean.class, new Consumer<CourseStudentBean>() {
            @Override
            public void accept(CourseStudentBean courseStudentBean) throws Exception {

                updateUI(courseStudentBean);
            }
        });


    }


    public void updateUI( CourseStudentBean courseStudentBean) {
        List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();

        this.dataList.clear();
        this.dataList .addAll(list) ;
        mDataAdapter.setDataList(dataList);
    }


    @OnClick(R.id.ll_add_student)
    public void onViewClicked() {

        Intent intent = new Intent(getContext(), AddStudentCourseStepOneActivity.class);
        startActivity(intent);
    }
}
