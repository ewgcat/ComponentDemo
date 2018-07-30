package com.yijian.staff.mvp.course.timetable;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.StudentBean;
import com.yijian.staff.widget.CircleProgressBar;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter {

    private List<StudentBean> dataList = new ArrayList<>();
    private Context mContext;

    public StudentListAdapter(Context context, List<StudentBean> dataList) {
        this.mContext=context;
        this.dataList=dataList;
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

        private TextView tv_courseName; //课程名称
        private TextView tv_memberName; //姓名
        private TextView tv_stockCourseCount; // 存课数量
        private TextView tv_totalCourseCount; // 存课总数量
        private Typeface typeFace; //字体类型


        public ViewHolder(View itemView) {
            super(itemView);
            tv_courseName = itemView.findViewById(R.id.tv_courseName);
            tv_memberName = itemView.findViewById(R.id.tv_memberName);
            tv_stockCourseCount = itemView.findViewById(R.id.tv_stockCourseCount);
            tv_totalCourseCount = itemView.findViewById(R.id.tv_totalCourseCount);

        }

        public void bindView(StudentBean studentBean) {

        }

    }

}
