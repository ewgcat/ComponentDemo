package com.yijian.staff.mvp.resourceallocation.coachleader.index.distribution;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.coachleader.bean.ResourceInfo;
import com.yijian.staff.mvp.resourceallocation.coachleader.detail.ResourceDetailActivity;

import java.util.List;


public class ResourceAllocationAdatper extends RecyclerView.Adapter<ResourceAllocationAdatper.ViewHolder> {

    private List<ResourceInfo> resourceInfoList;
    private Context context;


    public ResourceAllocationAdatper(Context context, List<ResourceInfo> resourceInfoList) {
        this.context = context;
        this.resourceInfoList = resourceInfoList;
    }
    public void update(List<ResourceInfo> resourceInfoList) {
        this.resourceInfoList=resourceInfoList;
        notifyDataSetChanged();
    }

    @Override
    public ResourceAllocationAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_zongjian_resource_list, parent, false);
        ResourceAllocationAdatper.ViewHolder holder = new ResourceAllocationAdatper.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ResourceAllocationAdatper.ViewHolder holder, int position) {
        ResourceInfo resourceInfo = resourceInfoList.get(position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ResourceDetailActivity.class);
                intent.putExtra("type",1);
                intent.putExtra("id",resourceInfo.getMemberId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resourceInfoList == null ? 0 : resourceInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_viper_type; //会员类型


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_viper_type = view.findViewById(R.id.tv_viper_type);
        }
    }

}
