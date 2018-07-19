package com.yijian.staff.mvp.main.message.business;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_message, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MessageBean messageBean = messageBeans.get(position);
        holder.tv_bussiness_msg_name.setText(messageBean.getMemberName());
        holder.tv_money.setText(messageBean.getMoney());
        holder.tv_business_msg_content.setText(messageBean.getName());
        holder.tv_business_msg_time.setText(messageBean.getCreateTime());


        ImageLoader.setHeadImageResource(messageBean.getMemberHeadPortrait(), context, holder.iv_bussiness_msg_header);

    }

    @Override
    public int getItemCount() {
        return messageBeans == null ? 0 : messageBeans.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        /***  业务消息  **/
        ImageView iv_bussiness_msg_header; //业务消息发送者头像
        TextView tv_bussiness_msg_name; //业务消息发送者姓名
        TextView tv_money; //业务消息的金钱
        TextView tv_business_msg_content; //业务消息内容
        TextView tv_business_msg_time; //业务消息发送的时间

        View ll_line;

        public ViewHolder(View view) {
            super(view);


            /***  业务消息  **/
            iv_bussiness_msg_header = view.findViewById(R.id.iv_bussiness_msg_header);
            tv_bussiness_msg_name = view.findViewById(R.id.tv_bussiness_msg_name);
            tv_money = view.findViewById(R.id.tv_money);
            tv_business_msg_content = view.findViewById(R.id.tv_business_msg_content);
            tv_business_msg_time = view.findViewById(R.id.tv_business_msg_time);
            ll_line = view.findViewById(R.id.ll_line);

        }
    }
}
