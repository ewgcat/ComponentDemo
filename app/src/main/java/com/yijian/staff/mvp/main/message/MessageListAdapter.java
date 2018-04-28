package com.yijian.staff.mvp.main.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.main.message.bean.MessageInfo;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    private List<MessageInfo> messageInfoList;
    private Context context;
    private int type;

    public MessageListAdapter(Context context, List<MessageInfo> messageInfoList, int type) {
        this.context = context;
        this.type = type;
        this.messageInfoList = messageInfoList;
    }

    public void update(List<MessageInfo> messageInfoList) {
        this.messageInfoList = messageInfoList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (type==0){
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_system_message, parent, false);
        }else if (type==1){
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_message, parent, false);
        }else {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club_message, parent, false);
        }
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 4;
//        return messageInfoList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        /**** 系统消息 ***/
        TextView tv_system_msg_content; //系统消息内容
        TextView tv_system_msg_time; //系统消息发送的时间

        /***  业务消息  **/
        ImageView iv_bussiness_msg_header; //业务消息发送者头像
        TextView tv_bussiness_msg_name; //业务消息发送者姓名
        TextView tv_business_msg_type; //业务消息的处理状态
        TextView tv_business_msg_content; //业务消息内容
        TextView tv_business_msg_time; //业务消息发送的时间

        public ViewHolder(View view) {
            super(view);
            /**** 系统消息 ***/
            tv_system_msg_content = view.findViewById(R.id.tv_system_msg_content);
            tv_system_msg_time = view.findViewById(R.id.tv_system_msg_time);

            /***  业务消息  **/
            iv_bussiness_msg_header = view.findViewById(R.id.iv_bussiness_msg_header);
            tv_bussiness_msg_name = view.findViewById(R.id.tv_bussiness_msg_name);
            tv_business_msg_type = view.findViewById(R.id.tv_business_msg_type);
            tv_business_msg_content = view.findViewById(R.id.tv_business_msg_content);
            tv_business_msg_time = view.findViewById(R.id.tv_business_msg_time);

        }
    }
}
