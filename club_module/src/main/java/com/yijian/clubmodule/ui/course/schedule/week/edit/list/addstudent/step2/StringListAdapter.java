package com.yijian.clubmodule.ui.course.schedule.week.edit.list.addstudent.step2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.CourseTimeBean;

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
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
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
        private TextView tvStartTime;
        private TextView tvEndTime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvStartTime = itemView.findViewById(R.id.tv_start_time);
            tvEndTime = itemView.findViewById(R.id.tv_end_time);
        }

        public void bind(CourseTimeBean courseTimeBean) {
            tvStartTime.setText(courseTimeBean.getStartTime());
            tvEndTime.setText(courseTimeBean.getEndTime());
        }
    }
}
