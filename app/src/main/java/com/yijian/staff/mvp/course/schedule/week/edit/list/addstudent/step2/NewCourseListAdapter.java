package com.yijian.staff.mvp.course.schedule.week.edit.list.addstudent.step2;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.CoursePlanBean;
import com.yijian.staff.bean.CourseTimeBean;
import com.yijian.staff.mvp.base.BaseRvAdapter;
import com.yijian.staff.mvp.base.BaseViewHolder;

import java.util.List;

public class NewCourseListAdapter extends BaseRvAdapter<CoursePlanBean> {
    private Context context;

    public NewCourseListAdapter(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_plan_time;
    }

    @Override
    public void onBindItemHolder(BaseViewHolder holder, final int position) {
        TextView btnDelete = holder.getView(R.id.btnDelete);

        TextView tvWeekday = holder.getView(R.id.tv_week_day);
        RecyclerView rv = holder.getView(R.id.rv);
        //这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑

        CoursePlanBean coursePlanBean = getDataList().get(position);
        tvWeekday.setText(coursePlanBean.getWeekDay());
        List<CourseTimeBean> courseTimeBeanList = coursePlanBean.getCourseTimeBeanList();
        StringListAdapter adapter = new StringListAdapter(courseTimeBeanList);

        final GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        //隐藏控件

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    mOnSwipeListener.onDel(position);
                }
            }
        });



    }

    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);

    }

    private onSwipeListener mOnSwipeListener;

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

}

