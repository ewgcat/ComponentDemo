package com.yijian.staff.mvp.huiji.invitation.list.record;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.invitation.detail.InvateDetailActivity;
import com.yijian.staff.mvp.huiji.invitation.list.bean.InvitationInfo;

import java.util.List;

/**
 * Created by yangk on 2018/3/8.
 */

public class InvitationRecordAdatper extends RecyclerView.Adapter<InvitationRecordAdatper.ViewHolder> {

    private List<InvitationInfo> invitationInfoList;
    private Context context;

    public InvitationRecordAdatper(Context context, List<InvitationInfo> invitationInfoList){
        this.context = context;
        this.invitationInfoList = invitationInfoList;
    }

    @Override
    public InvitationRecordAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitation_record, parent, false);
        InvitationRecordAdatper.ViewHolder holder = new InvitationRecordAdatper.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(InvitationRecordAdatper.ViewHolder holder, int position) {
        InvitationInfo invitationInfo = invitationInfoList.get(position);
        holder.tv_name.setText(invitationInfo.getName());
        holder.tv_cardName.setText(invitationInfo.getCardName());
        holder.tv_invitation_over_time.setText(invitationInfo.getOverTime());
        holder.tv_invitation_over_reason.setText(invitationInfo.getOverReason());
        holder.tv_invitation_type.setText(invitationInfo.getInvitationType());
        holder.lin_invate_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,InvateDetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return invitationInfoList==null?0:invitationInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_cardName;
        TextView tv_invitation_over_time;
        TextView tv_invitation_over_reason;
        TextView tv_invitation_type;
        LinearLayout lin_invate_detail;


        public ViewHolder(View view) {
            super(view);
            iv_header =  view.findViewById(R.id.iv_header);
            iv_gender =  view.findViewById(R.id.iv_gender);
            tv_name   = view.findViewById(R.id.tv_name);
            tv_cardName   = view.findViewById(R.id.tv_cardName);
            tv_invitation_over_time =     view.findViewById(R.id.tv_invitation_over_time);
            tv_invitation_over_reason =     view.findViewById(R.id.tv_invitation_over_reason);
            tv_invitation_type  =     view.findViewById(R.id.tv_invitation_type);
            lin_invate_detail  =     view.findViewById(R.id.lin_invate_detail);
        }
    }

}