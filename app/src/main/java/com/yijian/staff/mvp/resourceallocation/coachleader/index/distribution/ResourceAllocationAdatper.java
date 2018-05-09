package com.yijian.staff.mvp.resourceallocation.coachleader.index.distribution;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.coachleader.bean.ResourceInfo;
import com.yijian.staff.mvp.resourceallocation.coachleader.detail.ResourceDetailActivity;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.util.Logger;

import java.util.List;


public class ResourceAllocationAdatper extends RecyclerView.Adapter<ResourceAllocationAdatper.ViewHolder> {

    private List<ResourceInfo> resourceInfoList;
    private Context context;


    public ResourceAllocationAdatper(Context context, List<ResourceInfo> resourceInfoList) {
        this.context = context;
        this.resourceInfoList = resourceInfoList;
    }

    public void update(List<ResourceInfo> resourceInfoList) {
        this.resourceInfoList = resourceInfoList;
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

        ImageLoader.setImageResource(resourceInfo.getHeadImg(), context, holder.iv_header);
        holder.tv_name.setText(resourceInfo.getName());
        int resId = resourceInfo.getSex() == 1 ? R.mipmap.lg_man : R.mipmap.lg_women;
        Glide.with(context).load(resId).into(holder.iv_gender);
        holder.tv_viper_type.setText(resourceInfo.getViperRole());
        String subclassName = resourceInfo.getSubclassName();
        String memberId = resourceInfo.getMemberId();
        Logger.i("ResourceAllocationAdatper","memberId="+memberId);

        /**
         * CoachExpireVO：教练过期
         * CoachInfoVO ：教练正式
         * CoachIntentionVO：教练意向
         * CoachTodayVisitVO：教练今日来访
         * CustomerInfoVO：会籍正式
         * CustomerTodayVisitVO：会籍今日来访
         * CustomerExpireVO：会籍过期
         * CustomerIntentionVO：会籍意向
         * PotentialVO：潜在（会籍教练共用）
         */
        int type;
        switch (subclassName) {
            case "CoachInfoVO"://教练正式
                type = 1;
                break;
            case "CoachIntentionVO"://教练意向
                type = 2;
                break;
            case "PotentialVO"://潜在
                type = 3;
                break;
            default:
                type = 1;
                break;

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ResourceDetailActivity.class);
                intent.putExtra("type", type);
                intent.putExtra("subclassName", subclassName);
                intent.putExtra("memberId", resourceInfo.getMemberId());
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
