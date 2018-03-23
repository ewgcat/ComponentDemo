package com.yijian.staff.mvp.vip.info;

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
import com.yijian.staff.bean.ViperBean;
import com.yijian.staff.mvp.contract.ContractActivity;
import com.yijian.staff.mvp.questionnaireresult.QuestionnaireResultActivity;
import com.yijian.staff.mvp.vip.detail.ViperDetailActivity;
import com.yijian.staff.mvp.vip.model.VipPeopleInfo;

import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 */

public class VipPeopleInfoAdapter extends RecyclerView.Adapter<VipPeopleInfoAdapter.ViewHolder> {

    private List<ViperBean> viperBeanList;
    private Context context;
    private Boolean isAllVipInfo; // true 全部会员，false  今日来访

    public VipPeopleInfoAdapter(Context context, List<ViperBean> viperBeanList, boolean isAllVipInfo) {
        this.context = context;
        this.viperBeanList = viperBeanList;
        this.isAllVipInfo = isAllVipInfo;
    }

    @Override
    public VipPeopleInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vip_people_info, parent, false);
        VipPeopleInfoAdapter.ViewHolder holder = new VipPeopleInfoAdapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(VipPeopleInfoAdapter.ViewHolder holder, int position) {
        ViperBean viperBean = viperBeanList.get(position);
        holder.rel_be_present_time.setVisibility(isAllVipInfo ? View.GONE : View.VISIBLE);
        holder.rel_be_departure_time.setVisibility(isAllVipInfo ? View.GONE : View.VISIBLE);

        holder.tv_name.setText(viperBean.getName());
        holder.iv_gender.setImageResource("男".equals(viperBean.getSex()) ? R.mipmap.lg_man : R.mipmap.lg_women);
        holder.tv_cardName.setText(viperBean.getCardName());
        holder.tv_card_type.setText(viperBean.getCardType());
        holder.tv_private_coach.setText(viperBean.getPrivateCoach());
        holder.tv_like_lesson.setText(viperBean.getFavorCourse());
        holder.tv_like_teacher.setText(viperBean.getFavorTeacher());
        holder.tv_regist_time.setText(viperBean.getRegisterTime());
        holder.tv_contract_overTime.setText(viperBean.getContractDeadline());
        holder.tv_contract_balance.setText(viperBean.getContractBalance());
        holder.tv_buy_count.setText(viperBean.getPurchaseCount());
        holder.tv_be_present_time.setText(viperBean.getBePresentTime());
        holder.tv_be_departure_time.setText(viperBean.getDepartureTime());
        holder.lin_query_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ContractActivity.class));
            }
        });
        holder.lin_query_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, QuestionnaireResultActivity.class));
            }
        });
        holder.lin_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ViperDetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return viperBeanList == null ? 0 : viperBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_cardName;
        TextView tv_card_type;
        TextView tv_private_coach;
        TextView tv_like_lesson;
        TextView tv_like_teacher;
        TextView tv_regist_time;
        TextView tv_contract_overTime;
        TextView tv_contract_balance;
        TextView tv_buy_count;
        TextView tv_be_present_time;
        TextView tv_be_departure_time;
        RelativeLayout rel_be_present_time;
        RelativeLayout rel_be_departure_time;
        LinearLayout lin_query_contract;
        LinearLayout lin_query_question;
        LinearLayout lin_content;

        public ViewHolder(View view) {
            super(view);
            lin_content=view.findViewById(R.id.lin_content);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_cardName = view.findViewById(R.id.tv_cardName);
            tv_card_type = view.findViewById(R.id.tv_card_type);
            tv_private_coach = view.findViewById(R.id.tv_private_coach);
            tv_like_lesson = view.findViewById(R.id.tv_like_lesson);
            tv_like_teacher = view.findViewById(R.id.tv_like_teacher);
            tv_regist_time = view.findViewById(R.id.tv_regist_time);
            tv_contract_overTime = view.findViewById(R.id.tv_contract_overTime);
            tv_contract_balance = view.findViewById(R.id.tv_contract_balance);
            tv_buy_count = view.findViewById(R.id.tv_buy_count);
            tv_be_present_time = view.findViewById(R.id.tv_be_present_time);
            tv_be_departure_time = view.findViewById(R.id.tv_be_departure_time);
            rel_be_present_time = view.findViewById(R.id.rel_be_present_time);
            rel_be_departure_time = view.findViewById(R.id.rel_be_departure_time);
            lin_query_contract = view.findViewById(R.id.lin_query_contract);
            lin_query_question = view.findViewById(R.id.lin_query_question);
        }
    }

}
