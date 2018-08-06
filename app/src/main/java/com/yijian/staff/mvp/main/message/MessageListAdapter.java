package com.yijian.staff.mvp.main.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.MessageBean;
import com.yijian.staff.util.ImageLoader;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    private List<MessageBean> messageBeans;
    private Context context;
    private int type;

    public MessageListAdapter(Context context, List<MessageBean> messageBeans) {
        this.context = context;
        this.type = type;
        this.messageBeans = messageBeans;
    }

    public void update(List<MessageBean> messageBeans) {
        this.messageBeans = messageBeans;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MessageBean messageBean = messageBeans.get(position);
        holder.tv_member_name.setText(messageBean.getMemberName());
        holder.tv_content.setText(messageBean.getContent());
        holder.tv_create_time.setText(messageBean.getCreateTime());


        ImageLoader.setHeadImageResource(messageBean.getMemberHeadPortrait(), context, holder.iv_member_head);

    }

    @Override
    public int getItemCount() {
        return messageBeans == null ? 0 : messageBeans.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView iv_member_head; //业务消息发送者头像
        ImageView iv_sex; //业务消息发送者头像
        TextView tv_member_name; //业务消息发送者姓名
        TextView tv_cost; //业务消息的处理状态
        TextView tv_content; //业务消息的处理状态
        TextView tv_create_time; //业务消息的处理状态


        public ViewHolder(View view) {
            super(view);


            iv_member_head = view.findViewById(R.id.iv_member_head);
            tv_member_name = view.findViewById(R.id.tv_member_name);
            iv_sex = view.findViewById(R.id.iv_sex);
            tv_cost = view.findViewById(R.id.tv_cost);
            tv_content = view.findViewById(R.id.tv_content);
            tv_create_time = view.findViewById(R.id.tv_create_time);

        }
    }
}
