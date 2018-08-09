package com.yijian.staff.mvp.course.timetable.schedule.day;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ScheduleDayFragment extends MvcBaseFragment {
    private static String TAG = ScheduleDayFragment.class.getSimpleName();




    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule_day;
    }

    @Override
    public void initView() {
    }




    @OnClick({R.id.ll1, R.id.ll2, R.id.ll3, R.id.ll4, R.id.ll5, R.id.ll6, R.id.ll7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll1:
                break;
            case R.id.ll2:
                break;
            case R.id.ll3:
                break;
            case R.id.ll4:
                break;
            case R.id.ll5:
                break;
            case R.id.ll6:
                break;
            case R.id.ll7:
                break;
        }
    }
}
