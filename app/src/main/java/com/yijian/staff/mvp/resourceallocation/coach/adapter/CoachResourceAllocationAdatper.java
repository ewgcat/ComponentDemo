package com.yijian.staff.mvp.resourceallocation.coach.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.coach.bean.CoachResourceAllocationInfo;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.ImageLoader;

import java.util.List;

/**
 * Created by yangk on 2018/3/12.
 * 这里的Adapter 是资源分配和历史分配以及不同角色的资源分配公用的
 * 通过 flag_type字段标识决定是哪个模块使用的
 */
public class CoachResourceAllocationAdatper extends RecyclerView.Adapter<CoachResourceAllocationAdatper.ViewHolder> {

    private List<CoachResourceAllocationInfo> list;
    private Context context;

    public CoachResourceAllocationAdatper(Context context, List<CoachResourceAllocationInfo> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public CoachResourceAllocationAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_resource_allocation, parent, false);
        CoachResourceAllocationAdatper.ViewHolder holder = new CoachResourceAllocationAdatper.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CoachResourceAllocationAdatper.ViewHolder holder, int position) {
        CoachResourceAllocationInfo coachResourceAllocationInfo = list.get(position);

        ImageLoader.setImageResource(coachResourceAllocationInfo.getHeadImg(), context, holder.iv_header);
        holder.tv_name.setText(coachResourceAllocationInfo.getMemberName());
        int resId = coachResourceAllocationInfo.getSex().equals("男") ? R.mipmap.lg_man : R.mipmap.lg_women;
        Glide.with(context).load(resId).into(holder.iv_gender);
        holder.tv_last_coach.setText(coachResourceAllocationInfo.getHistoryUser());
        holder.tv_coach.setText(coachResourceAllocationInfo.getDistributeUser());
        Long distributeTime = coachResourceAllocationInfo.getDistributeTime();
        if (distributeTime!=null&&distributeTime!=-1){
            String s = DateUtil.parseLongDateToTimeString(distributeTime);
            holder.tv_time.setText(s);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void update(List<CoachResourceAllocationInfo> resourceAllocationInfoList) {
        this.list = resourceAllocationInfoList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_coach; //教练
        TextView tv_last_coach; //历史教练
        TextView tv_time; //分配时间


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);

            tv_last_coach = view.findViewById(R.id.tv_last_coach);
            tv_coach = view.findViewById(R.id.tv_coach);
            tv_coach = view.findViewById(R.id.tv_coach);
            tv_time = view.findViewById(R.id.tv_time);
        }
    }

}
