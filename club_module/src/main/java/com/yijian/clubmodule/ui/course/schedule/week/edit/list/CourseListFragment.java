package com.yijian.clubmodule.ui.course.schedule.week.edit.list;


import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.CourseStudentBean;
import com.yijian.clubmodule.db.ClubDBManager;
import com.yijian.clubmodule.ui.course.schedule.week.edit.list.addstudent.step1.AddStudentCourseStepOneActivity;
import com.yijian.commonlib.base.mvc.MvcBaseFragment;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.clubmodule.net.httpmanager.url.CourseUrls;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.widget.MyDividerItemDecoration;
import com.yijian.commonlib.widget.NoScrollRecycleView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




@SuppressLint("ValidFragment")
public class CourseListFragment extends MvcBaseFragment implements View.OnClickListener {

    NoScrollRecycleView rv;
    List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> dataList = new ArrayList<>();
    private CourseListAdapter mDataAdapter;

    private int type = -1;//0 -6周日 周一 周二 周六

    public CourseListFragment(int type) {
        this.type = type;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_student_list;
    }

    @Override
    public void initView() {

        rv=getRootView().findViewById(R.id.rv);
       getRootView().findViewById(R.id.ll_add_student).setOnClickListener(this);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new MyDividerItemDecoration());

        mDataAdapter = new CourseListAdapter(getContext());
        mDataAdapter.setDataList(dataList);
        mDataAdapter.setOnDelListener(new CourseListAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                delete(pos);
            }

        });

        rv.setAdapter(mDataAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<CourseStudentBean> courseStudentBeans = ClubDBManager.getInstance().queryCourseStudentBeans();
        updateUi(courseStudentBeans);
    }

    private void delete(int pos) {
        showLoading();
        CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean privateCoachCurriculumArrangementPlanVOSBean = mDataAdapter.getDataList().get(pos);
        HashMap<String, String> map = new HashMap<>();
        map.put("capId", privateCoachCurriculumArrangementPlanVOSBean.getId());
        HttpManager.postHasHeaderHasParam(CourseUrls.DELETE_PRIVATE_COURSE_PLAN_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                String id = mDataAdapter.getDataList().get(pos).getId();
                ClubDBManager.getInstance().deletePrivateCoachCurriculumArrangementPlanVOSBeanById(id);
                mDataAdapter.getDataList().remove(pos);
                mDataAdapter.notifyItemRemoved(pos);//推荐用这个
                if (pos != (mDataAdapter.getDataList().size())) { // 如果移除的是最后一个，忽略 注意：这里的mDataAdapter.getDataList()不需要-1，因为上面已经-1了
                    mDataAdapter.notifyItemRangeChanged(pos, mDataAdapter.getDataList().size() - pos);
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


    private void updateUi(List<CourseStudentBean> courseStudentBeanList) {
        for (int i = 0; i < courseStudentBeanList.size(); i++) {
            CourseStudentBean courseStudentBean = courseStudentBeanList.get(i);
            List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
            int weekCode = courseStudentBean.getWeekCode();
            if (weekCode == type) {
                this.dataList.clear();
                for (int j = 0; j < list.size(); j++) {
                    CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean privateCoachCurriculumArrangementPlanVOSBean = list.get(j);
                    if (privateCoachCurriculumArrangementPlanVOSBean.getDataType()==1) {
                        this.dataList.add(privateCoachCurriculumArrangementPlanVOSBean);
                    }
                }

                mDataAdapter.setDataList(dataList);
            }
        }
    }





    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), AddStudentCourseStepOneActivity.class);
        intent.putExtra("weekday",type);
        startActivity(intent);
    }
}
