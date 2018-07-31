package com.yijian.staff.mvp.course.timetable;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.StudentBean;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.CircleProgressBar;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter {

    private List<StudentBean> dataList = new ArrayList<>();
    private Context mContext;

    public StudentListAdapter(Context context, List<StudentBean> dataList) {
        this.mContext = context;
        this.dataList = dataList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime; //课程名称
        private ImageView ivHead; // 头像
        private TextView tvName; //姓名
        private TextView tvCourseName; //课程名称


        public ViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            ivHead = itemView.findViewById(R.id.iv_head);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);

        }

        public void bindView(StudentBean studentBean) {
//            tvTime.setText(studentBean.getTime()+"");
            tvName.setText(studentBean.getName());
            tvCourseName.setText(studentBean.getCourse());
            ImageLoader.setImageResource(studentBean.getHeadImg(),mContext,ivHead);
        }

    }

}
