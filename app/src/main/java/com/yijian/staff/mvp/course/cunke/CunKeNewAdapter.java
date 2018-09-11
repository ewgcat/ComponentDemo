package com.yijian.staff.mvp.course.cunke;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.TypeOfCunKeBody;
import com.yijian.staff.widget.CircleProgressBar;

import java.util.ArrayList;
import java.util.List;

public class CunKeNewAdapter extends RecyclerView.Adapter {

    private List<TypeOfCunKeBody> dataList = new ArrayList<>();
    private Context mContext;

    public void resetDataList(List<TypeOfCunKeBody> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
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
        private Typeface typeFace; //字体类型


        public ViewHolder(View itemView) {
            super(itemView);
            cunkeProgress = itemView.findViewById(R.id.cunkeProgress);
            tv_courseName = itemView.findViewById(R.id.tv_courseName);
            tv_memberName = itemView.findViewById(R.id.tv_memberName);
            tv_stockCourseCount = itemView.findViewById(R.id.tv_stockCourseCount);
            tv_totalCourseCount = itemView.findViewById(R.id.tv_totalCourseCount);
            typeFace =Typeface.createFromAsset(mContext.getAssets(),"fonts/DINCond-Black.otf");
            tv_stockCourseCount.setTypeface(typeFace);
            tv_totalCourseCount.setTypeface(typeFace);
            cunkeProgress.setTypeFace_DIY(typeFace);
        }

        public void bindView(TypeOfCunKeBody typeOfCunKeBody){
            tv_courseName.setText(typeOfCunKeBody.getCourseName());
            tv_memberName.setText(typeOfCunKeBody.getMemberName());
            tv_stockCourseCount.setText(typeOfCunKeBody.getStockCourseCount());
            tv_totalCourseCount.setText(typeOfCunKeBody.getTotalCourseCount());
            float stockCourseCount = Integer.valueOf(typeOfCunKeBody.getStockCourseCount());
            float totalCourseCount = Integer.valueOf(typeOfCunKeBody.getTotalCourseCount());
            float v =( totalCourseCount - stockCourseCount)/ totalCourseCount * 100;
            if (v>=1.0&&v<100){
                cunkeProgress.setProgress((int)v);
            }else if (v>0&&v<1){
                cunkeProgress.setProgress(1);
            }else if (v==0){
                cunkeProgress.setProgress(0);
            }else if (v==100){
                cunkeProgress.setProgress(100);
            }

            cunkeProgress.setCunkeProgressTextView(tv_stockCourseCount);
        }

    }

}
