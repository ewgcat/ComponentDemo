package com.yijian.staff.mvp.course.setclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExperienceRecorderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int type;
    private List<Map<String, String>> recordList = new ArrayList<>();

    public ExperienceRecorderAdapter(List<Map<String, String>> recordList, int type) {
        this.recordList = recordList;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        if (type == 1) { //无器械
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experience_record_1, parent, false);
            viewHolder = new ViewHolder1(view);
        } else if (type == 2) { //有氧器械
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experience_record_2, parent, false);
            viewHolder = new ViewHolder2(view);
        } else if (type == 3) { //力量器械
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experience_record_3, parent, false);
            viewHolder = new ViewHolder3(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (type == 1) {
            ((ViewHolder1) holder).bind(recordList.get(position), position);
        } else if (type == 2) {
            ((ViewHolder2) holder).bind(recordList.get(position), position);
        } else if (type == 3) {
            ((ViewHolder3) holder).bind(recordList.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return recordList != null ? recordList.size() : 0;
    }

    private class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView tv_rank;
        private TextView tv_project_label;
        private TextView tv_project;
        private TextView tv_group_label;
        private TextView tv_group;
        private TextView tv_time_label;
        private TextView tv_time;
        private View view_line;

        public ViewHolder1(View itemView) {
            super(itemView);
            tv_rank = itemView.findViewById(R.id.tv_rank);
            tv_project_label = itemView.findViewById(R.id.tv_project_label);
            tv_project = itemView.findViewById(R.id.tv_project);
            tv_group_label = itemView.findViewById(R.id.tv_group_label);
            tv_group = itemView.findViewById(R.id.tv_group);
            tv_time_label = itemView.findViewById(R.id.tv_time_label);
            tv_time = itemView.findViewById(R.id.tv_time);
            view_line = itemView.findViewById(R.id.view_line);
        }

        public void bind(Map<String, String> map, int position) {
            tv_rank.setText((position + 1) + "");
            tv_project_label.setText(map.get("projectlabel"));
            tv_project.setText(map.get("project"));
            tv_group_label.setText(map.get("grouplabel"));
            tv_group.setText(map.get("group"));
            tv_time_label.setText(map.get("timelabel"));
            tv_time.setText(map.get("time"));
            if (position == recordList.size()) {
                view_line.setVisibility(View.GONE);
            }
        }

    }


    private class ViewHolder2 extends RecyclerView.ViewHolder {

        private TextView tv_rank;
        private TextView tv_project_label;
        private TextView tv_project;
        private TextView tv_group_label;
        private TextView tv_group;
        private TextView tv_time_label;
        private TextView tv_time;
        private View view_line;

        public ViewHolder2(View itemView) {
            super(itemView);
            tv_rank = itemView.findViewById(R.id.tv_rank);
            tv_project_label = itemView.findViewById(R.id.tv_project_label);
            tv_project = itemView.findViewById(R.id.tv_project);
            tv_group_label = itemView.findViewById(R.id.tv_group_label);
            tv_group = itemView.findViewById(R.id.tv_group);
            tv_time_label = itemView.findViewById(R.id.tv_time_label);
            tv_time = itemView.findViewById(R.id.tv_time);
            view_line = itemView.findViewById(R.id.view_line);
        }

        public void bind(Map<String, String> map, int position) {
            tv_rank.setText((position + 1) + "");
            tv_project_label.setText(map.get("projectlabel"));
            tv_group_label.setText(map.get("grouplabel"));
            tv_group.setText(map.get("group"));
            tv_time_label.setText(map.get("timelabel"));
            tv_time.setText(map.get("time"));
            if (position == recordList.size()) {
                view_line.setVisibility(View.GONE);
            }
        }

    }

    private class ViewHolder3 extends RecyclerView.ViewHolder {

        private TextView tv_rank;
        private TextView tv_project_label;
        private TextView tv_project;
        private TextView tv_weight_label;
        private TextView tv_weight;
        private TextView tv_group_label;
        private TextView tv_group;
        private TextView tv_time_label;
        private TextView tv_time;
        private View view_line;

        public ViewHolder3(View itemView) {
            super(itemView);
            tv_rank = itemView.findViewById(R.id.tv_rank);
            tv_project_label = itemView.findViewById(R.id.tv_project_label);
            tv_project = itemView.findViewById(R.id.tv_project);
            tv_weight_label = itemView.findViewById(R.id.tv_weight_label);
            tv_weight = itemView.findViewById(R.id.tv_weight);
            tv_group_label = itemView.findViewById(R.id.tv_group_label);
            tv_group = itemView.findViewById(R.id.tv_group);
            tv_time_label = itemView.findViewById(R.id.tv_time_label);
            tv_time = itemView.findViewById(R.id.tv_time);
            view_line = itemView.findViewById(R.id.view_line);
        }

        public void bind(Map<String, String> map, int position) {
            tv_rank.setText((position + 1) + "");
            tv_project_label.setText(map.get("projectlabel"));
            tv_project.setText(map.get("project"));
            tv_weight_label.setText(map.get("weightlabel"));
            tv_weight.setText(map.get("weight"));
            tv_group_label.setText(map.get("grouplabel"));
            tv_group.setText(map.get("group"));
            tv_time_label.setText(map.get("timelabel"));
            tv_time.setText(map.get("time"));
            if (position == recordList.size()) {
                view_line.setVisibility(View.GONE);
            }
        }

    }


}
