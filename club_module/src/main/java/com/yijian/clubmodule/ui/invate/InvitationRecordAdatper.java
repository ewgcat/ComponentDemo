package com.yijian.clubmodule.ui.invate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.InvitationRecordBean;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.util.DateUtil;
import com.yijian.commonlib.util.ImageLoader;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitation_record, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InvitationRecordBean invitationRecordBean = invitationRecordBeanList.get(position);
        ImageLoader.setHeadImageResource(SharePreferenceUtil.getImageUrl()+invitationRecordBean.getHeadPath(), context, holder.iv_header);
        int medalType = invitationRecordBean.getMedalType();
        if (medalType==0){

        }else if (medalType==1){
            ImageLoader.setImageResource(R.mipmap.member_gray, context, holder.iv_rank);
        }else if (medalType==2){
            ImageLoader.setImageResource(R.mipmap.member_gold, context, holder.iv_rank);
        }
        int resId = "1".equals(invitationRecordBean.getGender()) ? R.mipmap.lg_man : R.mipmap.lg_women;
        Glide.with(context).load(resId).into(holder.iv_gender);
        holder.tv_name.setText(invitationRecordBean.getMemberName());
        String birthday = invitationRecordBean.getBirthday();
        if (!TextUtils.isEmpty(birthday)){
            String s = DateUtil.parseLongDateToDateString(Long.parseLong(birthday));
            holder.tv_birthday.setText(s);
        }
        holder.tv_birthday_type.setText(invitationRecordBean.getBirthdayTypeName());
        holder.tv_shenti_zhuangtai.setText(invitationRecordBean.getHealthStatusName());
        holder.tv_jianshen_aihao.setText(invitationRecordBean.getSportHobbyName());
        holder.tv_jianshen_mudi.setText(invitationRecordBean.getFitnessGoalName());
        holder.tv_invitation_content.setText(invitationRecordBean.getContent());
        String visitTime = invitationRecordBean.getVisitTime();
        if (!TextUtils.isEmpty(visitTime)){
            String s = DateUtil.parseLongDateToDateString(Long.parseLong(visitTime));
            holder.tv_invitation_time.setText(s);

        }


        holder.tv_member_type.setText(invitationRecordBean.getMemberTypeName());
        int status = invitationRecordBean.getStatus();
        if (status == 0) {
            ImageLoader.setImageResource(R.mipmap.invite_blue, context, holder.iv_status);
        } else if (status == 1) {
            ImageLoader.setImageResource(R.mipmap.invite_green, context, holder.iv_status);
        } else if (status == 2) {
            ImageLoader.setImageResource(R.mipmap.invite_red, context, holder.iv_status);
        }

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
        ImageView iv_rank;
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
            iv_rank = view.findViewById(R.id.iv_rank);
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
