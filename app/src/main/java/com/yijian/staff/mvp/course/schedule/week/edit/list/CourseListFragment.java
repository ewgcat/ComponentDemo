package com.yijian.staff.mvp.course.schedule.week.edit.list;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.schedule.week.edit.list.addstudent.step1.AddStudentCourseStepOneActivity;
import com.yijian.staff.mvp.vipermanage.viper.viperlist.filter.HuijiViperFilterBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.rx.RxBus;
import com.yijian.staff.widget.MyDividerItemDecoration;
import com.yijian.staff.widget.NoScrollRecycleView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

@SuppressLint("ValidFragment")
public class CourseListFragment extends MvcBaseFragment {

    @BindView(R.id.rv)
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

    private void delete(int pos) {
        showLoading();
        CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean privateCoachCurriculumArrangementPlanVOSBean = mDataAdapter.getDataList().get(pos);
        HashMap<String, String> map = new HashMap<>();
        map.put("capId", privateCoachCurriculumArrangementPlanVOSBean.getId());
        HttpManager.postHasHeaderHasParam(CourseUrls.DELETE_PRIVATE_COURSE_PLAN_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
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

    private void initData() {
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("version", "1.3");

        HttpManager.getHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_WEEK_PLAN_URL, map, new ResultJSONArrayObserver(getLifecycle()) {
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

    private void updateUi(List<CourseStudentBean> courseStudentBeanList) {
        for (int i = 0; i < courseStudentBeanList.size(); i++) {
            CourseStudentBean courseStudentBean = courseStudentBeanList.get(i);
            List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> list = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
            int weekCode = courseStudentBean.getWeekCode();
            if (weekCode == type) {
                this.dataList.clear();
                this.dataList.addAll(list);
                mDataAdapter.setDataList(dataList);
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }


    @OnClick(R.id.ll_add_student)
    public void onViewClicked() {

        Intent intent = new Intent(getContext(), AddStudentCourseStepOneActivity.class);
        startActivity(intent);
    }
}
