package com.yijian.staff.mvp.resourceallocation.huiji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.huiji.bean.HuiJiResourceAllocationInfo;
import com.yijian.staff.util.ImageLoader;

import java.util.List;

/**
 * Created by yangk on 2018/3/12.
 * 这里的Adapter 是资源分配和历史分配以及不同角色的资源分配公用的
 * 通过 flag_type字段标识决定是哪个模块使用的
 */
public class HuiJiResourceAllocationAdatper extends RecyclerView.Adapter<HuiJiResourceAllocationAdatper.ViewHolder> {

    private List<HuiJiResourceAllocationInfo> list;
    private Context context;

    public HuiJiResourceAllocationAdatper(Context context, List<HuiJiResourceAllocationInfo> list) {
        this.list = list;
    }


    @Override
    public HuiJiResourceAllocationAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_resource_allocation, parent, false);
        HuiJiResourceAllocationAdatper.ViewHolder holder = new HuiJiResourceAllocationAdatper.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HuiJiResourceAllocationAdatper.ViewHolder holder, int position) {
        HuiJiResourceAllocationInfo huijiResourceAllocationInfo = list.get(position);
        holder.bind(huijiResourceAllocationInfo,context);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void update(List<HuiJiResourceAllocationInfo> resourceAllocationInfoList) {
        this.list=resourceAllocationInfoList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_select_huiji; //分配会籍
        TextView tv_history_huiji; //历史会籍
        TextView tv_time; //分配时间


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);

            tv_select_huiji = view.findViewById(R.id.tv_select_huiji);
            tv_history_huiji = view.findViewById(R.id.tv_history_huiji);
            tv_time = view.findViewById(R.id.tv_time);
        }

        public void bind(HuiJiResourceAllocationInfo huijiResourceAllocationInfo,Context context){
            ImageLoader.setImageResource(huijiResourceAllocationInfo.getHeadImg(), context, iv_header);
            iv_gender.setImageResource(huijiResourceAllocationInfo.getGenderImg());
            tv_name.setText(huijiResourceAllocationInfo.getMemberName());
            tv_select_huiji.setText(huijiResourceAllocationInfo.getDistributeUser());
            tv_history_huiji.setText(huijiResourceAllocationInfo.getHistoryUser());
            tv_time.setText(huijiResourceAllocationInfo.getDistributeTime());
        }

    }

}
