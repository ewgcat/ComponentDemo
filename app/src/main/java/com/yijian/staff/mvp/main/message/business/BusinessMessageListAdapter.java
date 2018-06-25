package com.yijian.staff.mvp.main.message.business;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.util.ImageLoader;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class BusinessMessageListAdapter extends RecyclerView.Adapter<BusinessMessageListAdapter.ViewHolder> {

    private List<BusinessMessageBean> businessMessageBeans;
    private Context context;
    private int type;

    public BusinessMessageListAdapter(Context context, List<BusinessMessageBean> businessMessageBeans) {
        this.context = context;
        this.type = type;
        this.businessMessageBeans = businessMessageBeans;
    }

    public void update(List<BusinessMessageBean> businessMessageBeans) {
        this.businessMessageBeans = businessMessageBeans;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_message, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BusinessMessageBean businessMessageBean = businessMessageBeans.get(position);
        holder.tv_bussiness_msg_name.setText(businessMessageBean.getMemberName());
        holder.tv_business_msg_type.setText(businessMessageBean.getTypeName());
        holder.tv_business_msg_content.setText(businessMessageBean.getName());
        holder.tv_business_msg_time.setText(businessMessageBean.getCreateTime());


        ImageLoader.setHeadImageResource(businessMessageBean.getMemberHeadPortrait(), context, holder.iv_bussiness_msg_header);

    }

    @Override
    public int getItemCount() {
        return businessMessageBeans == null ? 0 : businessMessageBeans.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        /***  业务消息  **/
        ImageView iv_bussiness_msg_header; //业务消息发送者头像
        TextView tv_bussiness_msg_name; //业务消息发送者姓名
        TextView tv_business_msg_type; //业务消息的处理状态
        TextView tv_business_msg_content; //业务消息内容
        TextView tv_business_msg_time; //业务消息发送的时间

        View ll_line;

        public ViewHolder(View view) {
            super(view);


            /***  业务消息  **/
            iv_bussiness_msg_header = view.findViewById(R.id.iv_bussiness_msg_header);
            tv_bussiness_msg_name = view.findViewById(R.id.tv_bussiness_msg_name);
            tv_business_msg_type = view.findViewById(R.id.tv_business_msg_type);
            tv_business_msg_content = view.findViewById(R.id.tv_business_msg_content);
            tv_business_msg_time = view.findViewById(R.id.tv_business_msg_time);
            ll_line = view.findViewById(R.id.ll_line);

        }
    }
}
