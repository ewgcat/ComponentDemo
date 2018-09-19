package com.yijian.clubmodule.ui.course.schedule.week.edit.list.addstudent.step2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.CoursePlanBean;
import com.yijian.clubmodule.bean.CourseTimeBean;

import java.util.ArrayList;
import java.util.List;


/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/31 11:37:17
 */
public class CoursePlanTimeAdapter extends RecyclerView.Adapter<CoursePlanTimeAdapter.ItemViewHolder> {
    private Context context;
    private List<CoursePlanBean> coursePlanBeanList = new ArrayList<>();

    public CoursePlanTimeAdapter(Context context, List<CoursePlanBean> coursePlanBeanList) {
        this.context = context;
        this.coursePlanBeanList = coursePlanBeanList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan_time, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(context,coursePlanBeanList.get(position));
    }

    @Override
    public int getItemCount() {
        return coursePlanBeanList == null ? 0 : coursePlanBeanList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvWeekday;
        private RecyclerView rv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvWeekday = itemView.findViewById(R.id.tv_week_day);
            rv = itemView.findViewById(R.id.rv);
        }

        public void bind(Context context,CoursePlanBean coursePlanBean) {
            tvWeekday.setText(coursePlanBean.getWeekDay());
            List<CourseTimeBean> courseTimeBeanList = coursePlanBean.getCourseTimeBeanList();
            StringListAdapter adapter = new StringListAdapter(courseTimeBeanList);

            final GridLayoutManager layoutManager = new GridLayoutManager(context,2);
            rv.setLayoutManager(layoutManager);
            rv.setAdapter(adapter);

        }
    }


}
