package com.yijian.staff.mvp.course.schedule.week.edit.list.addstudent.step2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.CourseTimeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/17 17:23:01
 */
public class StringListAdapter extends RecyclerView.Adapter<StringListAdapter.ItemViewHolder> {

    private List<CourseTimeBean> list = new ArrayList<>();

    public StringListAdapter(List<CourseTimeBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_string, parent, false);
        StringListAdapter.ItemViewHolder itemViewHolder = new StringListAdapter.ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;
        private TextView tv2;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
        }

        public void bind(CourseTimeBean courseTimeBean) {
            tv1.setText(courseTimeBean.getStartTime());
            tv2.setText(courseTimeBean.getEndTime());
        }
    }
}
