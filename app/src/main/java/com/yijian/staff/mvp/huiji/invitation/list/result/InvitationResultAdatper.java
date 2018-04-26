package com.yijian.staff.mvp.huiji.invitation.list.result;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.invitation.list.bean.InvitationResultBean;

import java.util.List;

/**
 * Created by yangk on 2018/3/8.
 */

public class InvitationResultAdatper  extends RecyclerView.Adapter<InvitationResultAdatper.ViewHolder> {

    private List<InvitationResultBean> invitationRecordBeanList;
    private Context context;

    public InvitationResultAdatper(Context context, List<InvitationResultBean> invitationRecordBeanList){
        this.context = context;
        this.invitationRecordBeanList = invitationRecordBeanList;
    }

    @Override
    public InvitationResultAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitation_result, parent, false);
        InvitationResultAdatper.ViewHolder holder = new InvitationResultAdatper.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(InvitationResultAdatper.ViewHolder holder, int position) {
        InvitationResultBean invitationRecordBean = invitationRecordBeanList.get(position);
        holder.tv_name.setText(invitationRecordBean.getMemberName());
//        holder.tv_rightsandinterests.setText(invitationRecordBean.g());
//        holder.tv_invitation_over_time.setText(invitationRecordBean.getOverTime());
//        holder.tv_invitation_over_reason.setText(invitationRecordBean.getOverReason());
//        holder.tv_invitation_time.setText(invitationRecordBean.getInvitationTime());
//        holder.tv_invitation_content.setText(invitationRecordBean.getInvitationContent());
//        holder.tv_invitation_type.setText(invitationRecordBean.getInvitationType());
//        holder.tv_invitation_result.setText(invitationRecordBean.getInvitationResult());
    }

    @Override
    public int getItemCount() {
        return invitationRecordBeanList ==null?0: invitationRecordBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_rightsandinterests;
        TextView tv_invitation_over_time;
        TextView tv_invitation_over_reason;
        TextView tv_invitation_time;
        TextView tv_invitation_content;
        TextView tv_invitation_type;
        TextView tv_invitation_result;


        public ViewHolder(View view) {
            super(view);
            iv_header =  view.findViewById(R.id.iv_header);
            iv_gender =  view.findViewById(R.id.iv_gender);
            tv_name   = view.findViewById(R.id.tv_name);
            tv_rightsandinterests  =     view.findViewById(R.id.tv_rightsandinterests);
            tv_invitation_over_time =     view.findViewById(R.id.tv_invitation_over_time);
            tv_invitation_over_reason =     view.findViewById(R.id.tv_invitation_over_reason);
            tv_invitation_time =     view.findViewById(R.id.tv_invitation_time);
            tv_invitation_content =     view.findViewById(R.id.tv_invitation_content);
            tv_invitation_type  =     view.findViewById(R.id.tv_invitation_type);
            tv_invitation_result  =     view.findViewById(R.id.tv_invitation_result);
        }
    }

}
