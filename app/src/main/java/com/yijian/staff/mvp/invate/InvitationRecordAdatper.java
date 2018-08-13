package com.yijian.staff.mvp.invate;

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
import com.yijian.staff.bean.InvitationRecordBean;
import com.yijian.staff.util.ImageLoader;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/10 16:31:23
 */
public class InvitationRecordAdatper extends RecyclerView.Adapter<InvitationRecordAdatper.ViewHolder> {

    private List<InvitationRecordBean> invitationRecordBeanList;
    private Context context;

    public InvitationRecordAdatper(Context context, List<InvitationRecordBean> invitationRecordBeanList) {
        this.context = context;
        this.invitationRecordBeanList = invitationRecordBeanList;
    }

    @Override
    public InvitationRecordAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitation_record, parent, false);
        InvitationRecordAdatper.ViewHolder holder = new InvitationRecordAdatper.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(InvitationRecordAdatper.ViewHolder holder, int position) {
        InvitationRecordBean invitationRecordBean = invitationRecordBeanList.get(position);
        ImageLoader.setHeadImageResource(invitationRecordBean.getHeadPath(), context, holder.iv_header);
        int resId = "男".equals(invitationRecordBean.getGender()) ? R.mipmap.lg_man : R.mipmap.lg_women;
        Glide.with(context).load(resId).into(holder.iv_gender);
        holder.tv_name.setText(invitationRecordBean.getMemberName());
        holder.tv_birthday.setText(invitationRecordBean.getBirthday());
        holder.tv_birthday_type.setText(invitationRecordBean.getBirthdayTypeName());
        holder.tv_shenti_zhuangtai.setText(invitationRecordBean.getHealthStatusName());
        holder.tv_jianshen_aihao.setText(invitationRecordBean.getSportHobbyName());
        holder.tv_jianshen_mudi.setText(invitationRecordBean.getExercisePartName());
        holder.tv_invitation_content.setText(invitationRecordBean.getContent());
        holder.tv_invitation_time.setText(invitationRecordBean.getVisitTime());

        holder.tv_member_type.setText(invitationRecordBean.getMemberTypeName());

    }

    @Override
    public int getItemCount() {
        return invitationRecordBeanList == null ? 0 : invitationRecordBeanList.size();
    }

    public void update(List<InvitationRecordBean> invitationRecordBeanList) {
        this.invitationRecordBeanList = invitationRecordBeanList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        ImageView iv_status;
        TextView tv_name;
        TextView tv_birthday;
        TextView tv_birthday_type;
        TextView tv_shenti_zhuangtai;
        TextView tv_jianshen_aihao;
        TextView tv_jianshen_mudi;
        TextView tv_member_type;
        TextView tv_invitation_content;
        TextView tv_invitation_time;


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            iv_status = view.findViewById(R.id.iv_status);
            tv_name = view.findViewById(R.id.tv_name);
            tv_birthday = view.findViewById(R.id.tv_birthday);
            tv_birthday_type = view.findViewById(R.id.tv_birthday_type);
            tv_shenti_zhuangtai = view.findViewById(R.id.tv_shenti_zhuangtai);
            tv_jianshen_aihao = view.findViewById(R.id.tv_jianshen_aihao);
            tv_jianshen_mudi = view.findViewById(R.id.tv_jianshen_mudi);
            tv_member_type = view.findViewById(R.id.tv_member_type);
            tv_invitation_time = view.findViewById(R.id.tv_invitation_time);
            tv_invitation_content = view.findViewById(R.id.tv_invitation_content);
        }
    }

}
