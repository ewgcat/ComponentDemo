package com.yijian.staff.mvp.coach.intent;

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
import com.yijian.staff.bean.ViperBean;
import com.yijian.staff.mvp.experienceclass.invate.ExperienceClassInvateActivity;

import java.util.List;

/**
 * Created by yangk on 2018/3/5.
 */

public class CoachIntentViperListAdapter extends RecyclerView.Adapter<CoachIntentViperListAdapter.ViewHolder> {

    private List<ViperBean> viperBeanList;
    private Context context;

    public CoachIntentViperListAdapter(Context context, List<ViperBean> viperBeanList){
        this.context = context;
        this.viperBeanList = viperBeanList;
    }

    @Override
    public CoachIntentViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_vip_intention_info, parent, false);
        CoachIntentViperListAdapter.ViewHolder holder = new CoachIntentViperListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CoachIntentViperListAdapter.ViewHolder holder, int position) {
        ViperBean viperBean = viperBeanList.get(position);
        holder.tv_name.setText(viperBean.getName());
        holder.tv_birth.setText(viperBean.getBirthday());
        holder.tv_birth_type.setText(viperBean.getBirthdayType());
        holder.tv_bodybuildingHobby.setText(viperBean.getBodybuildingHobby());
        holder.tv_bodyStatus.setText(viperBean.getBodyStatus());
        holder.tv_interestHobby.setText(viperBean.getInterestHobby());
        holder.iv_gender.setImageResource("女".equals(viperBean.getSex())?R.mipmap.lg_women:R.mipmap.lg_man);
        holder.tv_useCar.setText(viperBean.getUseCar());



        holder.lin_baojia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,CoachIntentViperDetailActivity.class));
            }
        });



        //TODO 保护7天状态不可电话回访，非保护七天状态可电话回访
        holder.lin_protect_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.lin_invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,ExperienceClassInvateActivity.class));
            }
        });

        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,CoachIntentViperDetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return viperBeanList==null?0:viperBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_birth;
        TextView tv_birth_type;
        TextView tv_bodyStatus;
        TextView tv_bodybuildingHobby;
        TextView tv_interestHobby;
        TextView tv_useCar;
        LinearLayout ll_content; //真个Item条目

        LinearLayout lin_baojia; //报价
        LinearLayout lin_protect_seven; //保护7天/回访
        LinearLayout lin_invitation; //邀请


        public ViewHolder(View view) {
            super(view);
            iv_header =  view.findViewById(R.id.iv_header);
            iv_gender =  view.findViewById(R.id.iv_gender);
            tv_name   = view.findViewById(R.id.tv_name);
            tv_birth  =     view.findViewById(R.id.tv_birth);
            tv_birth_type =     view.findViewById(R.id.tv_birth_type);
            tv_bodyStatus =     view.findViewById(R.id.tv_bodyStatus);
            tv_bodybuildingHobby =     view.findViewById(R.id.tv_bodybuildingHobby);
            tv_interestHobby =     view.findViewById(R.id.tv_interestHobby);
            tv_useCar  =     view.findViewById(R.id.tv_useCar);

            ll_content =     view.findViewById(R.id.ll_content);
            lin_baojia =     view.findViewById(R.id.lin_baojia);
            lin_protect_seven =     view.findViewById(R.id.lin_protect_seven);
            lin_invitation =     view.findViewById(R.id.lin_invitation);
        }
    }

}
