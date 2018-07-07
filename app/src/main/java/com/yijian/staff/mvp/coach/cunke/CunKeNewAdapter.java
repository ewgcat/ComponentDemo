package com.yijian.staff.mvp.coach.cunke;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.cunke.bean.TypeOfCunKeBody;
import com.yijian.staff.widget.CircleProgressBar;

import java.util.ArrayList;
import java.util.List;

public class CunKeNewAdapter extends RecyclerView.Adapter {

    private List<TypeOfCunKeBody> dataList = new ArrayList<>();

    public void resetDataList(List<TypeOfCunKeBody> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cunke_new,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bindView(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private CircleProgressBar cunkeProgress;
        private TextView tv_courseName; //课程名称
        private TextView tv_memberName; //姓名
        private TextView tv_stockCourseCount; // 存课数量
        private TextView tv_totalCourseCount; // 存课总数量


        public ViewHolder(View itemView) {
            super(itemView);
            cunkeProgress = itemView.findViewById(R.id.cunkeProgress);
            tv_courseName = itemView.findViewById(R.id.tv_courseName);
            tv_memberName = itemView.findViewById(R.id.tv_memberName);
            tv_stockCourseCount = itemView.findViewById(R.id.tv_stockCourseCount);
            tv_totalCourseCount = itemView.findViewById(R.id.tv_totalCourseCount);
        }

        public void bindView(TypeOfCunKeBody typeOfCunKeBody){
            tv_courseName.setText(typeOfCunKeBody.getCourseName());
            tv_memberName.setText(typeOfCunKeBody.getMemberName());
            tv_stockCourseCount.setText(typeOfCunKeBody.getStockCourseCount());
            tv_totalCourseCount.setText(typeOfCunKeBody.getTotalCourseCount());
            int stockCourseCount = Integer.valueOf(typeOfCunKeBody.getStockCourseCount());
            int totalCourseCount = Integer.valueOf(typeOfCunKeBody.getTotalCourseCount());
            cunkeProgress.setProgress((totalCourseCount-stockCourseCount)/totalCourseCount*100);
            cunkeProgress.setCunkeViewTextColor(tv_stockCourseCount);
        }

    }

}
