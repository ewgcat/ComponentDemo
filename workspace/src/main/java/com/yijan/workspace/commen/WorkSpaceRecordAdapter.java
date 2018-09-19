package com.yijan.workspace.commen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijan.workspace.R;
import com.yijan.workspace.bean.WorkSpaceRecordBean;
import com.yijan.workspace.bean.WorkSpaceVipBean;
import com.yijan.workspace.sport.SportTestActivity;
import com.yijan.workspace.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

public class WorkSpaceRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<WorkSpaceRecordBean> dataList = new ArrayList<>();

    public void resetDataList(List<WorkSpaceRecordBean> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workspace_record,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout rel_record;
        TextView tv_date;
        TextView tv_time;
        TextView tv_result_score;

        public ViewHolder(View itemView) {
            super(itemView);
            rel_record = itemView.findViewById(R.id.rel_record);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_result_score = itemView.findViewById(R.id.tv_result_score);
        }

        public void bind(WorkSpaceRecordBean workSpaceRecordBean){
            rel_record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityUtils.isShareJump){
                        ActivityUtils.moduleType = ActivityUtils.tempModule;
                        ActivityUtils.isShareJump = false;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("recordId",workSpaceRecordBean.getWdId());
                    ActivityUtils.startActivity(mContext,ShareTestActivity.class,bundle);
                }
            });
            tv_date.setText(workSpaceRecordBean.getDay());
            tv_time.setText(workSpaceRecordBean.getTime());
            tv_result_score.setText(workSpaceRecordBean.getGrade()+"分");
            if(workSpaceRecordBean.getType() == 4){ //动态评估
                tv_result_score.setVisibility(View.GONE);
            }else{
                tv_result_score.setVisibility(View.VISIBLE);
                tv_result_score.setText(workSpaceRecordBean.getGrade()+"分");
            }
        }

    }

}
