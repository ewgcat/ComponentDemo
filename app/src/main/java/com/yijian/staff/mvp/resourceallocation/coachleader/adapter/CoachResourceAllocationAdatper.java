package com.yijian.staff.mvp.resourceallocation.coachleader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.coachleader.bean.CoachHistoryResourceAllocationInfo;
import com.yijian.staff.mvp.resourceallocation.coachleader.selectcoach.SelectCoachActivity;

import java.util.List;

/**
 * Created by yangk on 2018/3/12.
 * 这里的Adapter 是资源分配和历史分配以及不同角色的资源分配公用的
 * 通过 flag_type字段标识决定是哪个模块使用的
 */
public class CoachResourceAllocationAdatper extends RecyclerView.Adapter<CoachResourceAllocationAdatper.ViewHolder> {

    private List<CoachHistoryResourceAllocationInfo> historyResourceAllocationInfoList;
    private Context context;
    private int flag_type;
    public static int RESOURCE_TYPE = 0;//资源分配
    public static int HISTORY_TYPE = 1;//历史分配
    public static int ROLE_RESOURCE_TYPE = 2;//不同角色的资源分配

    public CoachResourceAllocationAdatper(Context context, List<CoachHistoryResourceAllocationInfo> historyResourceAllocationInfoList, int flag_type){
        this.context = context;
        this.historyResourceAllocationInfoList = historyResourceAllocationInfoList;
        this.flag_type = flag_type;
    }

    public void setFlag_type(int flag_type) {
        this.flag_type = flag_type;
    }

    @Override
    public CoachResourceAllocationAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_history_resource_allocation, parent, false);
        CoachResourceAllocationAdatper.ViewHolder holder = new CoachResourceAllocationAdatper.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CoachResourceAllocationAdatper.ViewHolder holder, int position) {
        CoachHistoryResourceAllocationInfo historyResourceAllocationInfo = historyResourceAllocationInfoList.get(position);
        holder.tv_name.setText(historyResourceAllocationInfo.getName());
        holder.iv_gender.setImageResource(historyResourceAllocationInfo.getGender());
        holder.tv_birthDay.setText(historyResourceAllocationInfo.getBirthDay());
        holder.tv_wxIdentification.setText(historyResourceAllocationInfo.getWxIdentification());
        holder.tv_email.setText(historyResourceAllocationInfo.getEmail());
        holder.tv_service_huiji.setText(historyResourceAllocationInfo.getServiceHuiJi());
        holder.tv_service_coach.setText(historyResourceAllocationInfo.getServiceCoach());

        switch (flag_type){
            case 0: //资源分配
                holder.lin_history_resource_allocation.setVisibility(View.VISIBLE);
                holder.rel_receive_coach.setVisibility(View.VISIBLE);
                holder.rel_service_coach.setVisibility(View.GONE);
                break;
            case 1: //历史分配
                holder.lin_history_resource_allocation.setVisibility(View.GONE);
                holder.rel_receive_coach.setVisibility(View.GONE);
                holder.rel_service_coach.setVisibility(View.VISIBLE);
                holder.tv_status.setVisibility(View.VISIBLE);

                break;
            case 2: //不同角色的资源分配
                holder.lin_history_resource_allocation.setVisibility(View.GONE);
                holder.tv_status.setVisibility(View.GONE);
                holder.rel_receive_coach.setVisibility(View.GONE);
                holder.rel_service_coach.setVisibility(View.VISIBLE);
                break;
        }

        holder.lin_allocation_coach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectCoachActivity.startToActivity("选择教练",context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyResourceAllocationInfoList==null?0:historyResourceAllocationInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_status;
        TextView tv_birthDay; //生日
        TextView tv_wxIdentification; //微信号
        TextView tv_email; //邮箱
        TextView tv_service_huiji; //服务会籍
        TextView tv_service_coach; //服务教练
        RelativeLayout rel_service_coach; //服务教练布局
        RelativeLayout rel_receive_coach; //接待教练布局
//        LinearLayout lin_allocation_huiji; //分配会籍
        LinearLayout lin_allocation_coach; //分配教练
        RelativeLayout lin_history_resource_allocation; //分配会籍和分配教练的布局


        public ViewHolder(View view) {
            super(view);
            iv_header =  view.findViewById(R.id.iv_header);
            iv_gender =  view.findViewById(R.id.iv_gender);
            tv_name   = view.findViewById(R.id.tv_name);
            tv_status   = view.findViewById(R.id.tv_status);
            tv_birthDay   = view.findViewById(R.id.tv_birthDay);
            tv_wxIdentification =     view.findViewById(R.id.tv_wxIdentification);
            tv_email =     view.findViewById(R.id.tv_email);
            tv_service_huiji  =     view.findViewById(R.id.tv_service_huiji);
            tv_service_coach  =     view.findViewById(R.id.tv_service_coach);
            rel_service_coach  =     view.findViewById(R.id.rel_service_coach);
            rel_receive_coach  =     view.findViewById(R.id.rel_receive_coach);
//            lin_allocation_huiji  =     view.findViewById(R.id.lin_allocation_huiji);
            lin_allocation_coach  =     view.findViewById(R.id.lin_allocation_coach);
            lin_history_resource_allocation  =     view.findViewById(R.id.lin_history_resource_allocation);
        }
    }

}
