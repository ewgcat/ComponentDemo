package com.yijian.staff.mvp.resourceallocation.huijileader.index.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.huijileader.bean.HistoryResourceInfo;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.ImageLoader;

import java.util.List;

/**
 * Created by yangk on 2018/3/12.
 * 这里的Adapter 是资源分配和历史分配以及不同角色的资源分配公用的
 * 通过 flag_type字段标识决定是哪个模块使用的
 */
public class HistoryResourceListAdatper extends RecyclerView.Adapter<HistoryResourceListAdatper.ViewHolder> {

    private List<HistoryResourceInfo> list;
    private Context context;

    public HistoryResourceListAdatper(Context context, List<HistoryResourceInfo> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public HistoryResourceListAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_zongjian_history_resource, parent, false);
        HistoryResourceListAdatper.ViewHolder holder = new HistoryResourceListAdatper.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HistoryResourceListAdatper.ViewHolder holder, int position) {
        HistoryResourceInfo historyResourceInfo = list.get(position);
        ImageLoader.setHeadImageResource(historyResourceInfo.getHeadImg(), context, holder.iv_header);
        holder.tv_name.setText(historyResourceInfo.getMemberName());
        int resId = historyResourceInfo.getSex().equals("男") ? R.mipmap.lg_man : R.mipmap.lg_women;
        Glide.with(context).load(resId).into(holder.iv_gender);
        holder.tv_history_huiji.setText(historyResourceInfo.getHistoryUser());
        holder.tv_select_huiji.setText(historyResourceInfo.getDistributeUser());
        Long distributeTime = historyResourceInfo.getDistributeTime();
        if (distributeTime!=null&&distributeTime!=-1){
            String s = DateUtil.parseLongDateToTimeString(distributeTime);
            holder.tv_time.setText(s);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void update(List<HistoryResourceInfo> resourceAllocationInfoList) {
        this.list=resourceAllocationInfoList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_history_huiji; //历史教练
        TextView tv_select_huiji; //分配教练
        TextView tv_time; //分配时间


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);

            tv_history_huiji = view.findViewById(R.id.tv_history_huiji);
            tv_select_huiji = view.findViewById(R.id.tv_select_huiji);
            tv_time = view.findViewById(R.id.tv_time);
        }
    }

}
