package com.yijian.staff.mvp.huiji.outdate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.vip.bean.VipOutdateInfo;

import java.util.List;

/**
 * Created by yangk on 2018/3/26.
 */

public class VipOutDateAdapter extends RecyclerView.Adapter<VipOutDateAdapter.ViewHolder>  {

    private List<VipOutdateInfo> vipOutdateInfoList;
    private Context context;

    public VipOutDateAdapter(Context context, List<VipOutdateInfo> vipOutdateInfoList){
        this.context = context;
        this.vipOutdateInfoList = vipOutdateInfoList;
    }


    @Override
    public VipOutDateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oute_huiji_date, parent, false);
        VipOutDateAdapter.ViewHolder holder = new VipOutDateAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VipOutDateAdapter.ViewHolder holder, int position) {
        VipOutdateInfo vipOutdateInfo = vipOutdateInfoList.get(position);
        holder.tv_name.setText(vipOutdateInfo.getName());
        holder.iv_gender.setImageResource(vipOutdateInfo.getGender());
        holder.tv_cardName.setText(vipOutdateInfo.getCardName());
        holder.tv_cardType.setText(vipOutdateInfo.getCardType());
        holder.tv_privateCoach.setText(vipOutdateInfo.getPrivateCoach());
        holder.tv_likeLesson.setText(vipOutdateInfo.getLikeLesson());
        holder.tv_likeTeacher.setText(vipOutdateInfo.getLikeTeacher());
        holder.tv_registTime.setText(vipOutdateInfo.getRegistTime());
        holder.tv_contractOutDate.setText(vipOutdateInfo.getContractOutDate());
        holder.tv_outDateDay.setText(vipOutdateInfo.getOutDateDay());
        holder.lin_quey_contract.setOnClickListener(new View.OnClickListener() { //查看合同
            @Override
            public void onClick(View v) {

            }
        });
        holder.lin_quey_question.setOnClickListener(new View.OnClickListener() { //查看问卷
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return vipOutdateInfoList==null?0:vipOutdateInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        TextView tv_name;
        ImageView iv_gender;
        TextView tv_cardName;
        TextView tv_cardType;
        TextView tv_privateCoach;
        TextView tv_likeLesson;
        TextView tv_likeTeacher;
        TextView tv_registTime;
        TextView tv_contractOutDate;
        TextView tv_outDateDay;
        LinearLayout lin_quey_contract;
        LinearLayout lin_quey_question;



        public ViewHolder(View view) {
            super(view);
            iv_header =  view.findViewById(R.id.iv_header);
            tv_name   = view.findViewById(R.id.tv_name);
            iv_gender =  view.findViewById(R.id.iv_gender);
            tv_cardName   = view.findViewById(R.id.tv_cardName);
            tv_cardType =     view.findViewById(R.id.tv_cardType);
            tv_privateCoach =     view.findViewById(R.id.tv_privateCoach);
            tv_likeLesson  =     view.findViewById(R.id.tv_likeLesson);
            tv_likeTeacher  =     view.findViewById(R.id.tv_likeTeacher);
            tv_registTime  =     view.findViewById(R.id.tv_registTime);
            tv_contractOutDate  =     view.findViewById(R.id.tv_contractOutDate);
            tv_outDateDay  =     view.findViewById(R.id.tv_outDateDay);
            lin_quey_contract  =     view.findViewById(R.id.lin_quey_contract);
            lin_quey_question  =     view.findViewById(R.id.lin_quey_question);
        }
    }

}
