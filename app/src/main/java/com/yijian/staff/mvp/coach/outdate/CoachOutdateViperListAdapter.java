package com.yijian.staff.mvp.coach.outdate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.invate.ExperienceClassInvateActivity;
import com.yijian.staff.mvp.coach.intent.CoachIntentViperDetailActivity;
import com.yijian.staff.mvp.huiji.bean.VipOutdateInfo;

import java.util.List;

/**
 * Created by yangk on 2018/3/26.
 */

public class CoachOutdateViperListAdapter extends RecyclerView.Adapter<CoachOutdateViperListAdapter.ViewHolder>  {

    private List<VipOutdateInfo> vipOutdateInfoList;
    private Context context;

    public CoachOutdateViperListAdapter(Context context, List<VipOutdateInfo> vipOutdateInfoList){
        this.context = context;
        this.vipOutdateInfoList = vipOutdateInfoList;
    }


    @Override
    public CoachOutdateViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oute_coach_date, parent, false);
        CoachOutdateViperListAdapter.ViewHolder holder = new CoachOutdateViperListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CoachOutdateViperListAdapter.ViewHolder holder, int position) {
        VipOutdateInfo vipOutdateInfo = vipOutdateInfoList.get(position);
        holder.tv_name.setText(vipOutdateInfo.getName());
        holder.iv_gender.setImageResource(vipOutdateInfo.getGender());

        holder.tv_history_lesson.setText(vipOutdateInfo.getHistoryLesson());
        holder.tv_outDate.setText(vipOutdateInfo.getOutDate());
        holder.tv_outDate_reason.setText(vipOutdateInfo.getOutDateReason());


        holder.ll_chakanxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,CoachIntentViperDetailActivity.class));
            }
        });


        holder.rl_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rv_card.setVisibility((holder.rv_card.getVisibility()==View.GONE)?View.VISIBLE:View.GONE);
            }
        });

        holder.lin_huifan.setOnClickListener(new View.OnClickListener() { //回访
            @Override
            public void onClick(View v) {

            }
        });

        holder.lin_yaoyue.setOnClickListener(new View.OnClickListener() { // 邀约
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,ExperienceClassInvateActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return vipOutdateInfoList==null?0:vipOutdateInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_chakanxiangqing;
        ImageView iv_header;
        TextView tv_name;
        ImageView iv_gender;

        RelativeLayout rl_expand;
        RecyclerView rv_card;

        TextView tv_history_lesson; //历史课程
        TextView tv_outDate; //过期时间
        TextView tv_outDate_reason; //过期原因
        LinearLayout lin_huifan;
        LinearLayout lin_yaoyue;


        public ViewHolder(View view) {
            super(view);
            ll_chakanxiangqing  =     view.findViewById(R.id.ll_chakanxiangqing);
            iv_header =  view.findViewById(R.id.iv_header);
            tv_name   = view.findViewById(R.id.tv_name);
            iv_gender =  view.findViewById(R.id.iv_gender);

            rl_expand =  view.findViewById(R.id.rl_expand);
            rv_card =  view.findViewById(R.id.rv_card);

            tv_history_lesson =     view.findViewById(R.id.tv_history_lesson);
            tv_outDate  =     view.findViewById(R.id.tv_outDate);
            tv_outDate_reason  =     view.findViewById(R.id.tv_outDate_reason);
            lin_huifan  =     view.findViewById(R.id.lin_huifan);
            lin_yaoyue  =     view.findViewById(R.id.lin_yaoyue);
        }
    }

}
