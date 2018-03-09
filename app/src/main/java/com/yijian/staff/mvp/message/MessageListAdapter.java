package com.yijian.staff.mvp.message;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huifang.bean.HuiFangInfo;
import com.yijian.staff.mvp.huifang.tianxieresult.TianXieHuiFangResultActivity;
import com.yijian.staff.mvp.message.bean.MessageInfo;
import com.yijian.staff.util.CommonUtil;

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


        public ViewHolder(View view) {
            super(view);

        }
    }
}
