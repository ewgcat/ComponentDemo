package com.yijian.staff.mvp.huiji.viperlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
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
import com.yijian.staff.mvp.huiji.bean.HuiJiVipeCardAdapter;
import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;
import com.yijian.staff.mvp.questionnaireresult.QuestionnaireResultActivity;
import com.yijian.staff.mvp.vip.detail.ViperDetailActivity;

import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 */

public class HuijiViperListAdapter extends RecyclerView.Adapter<HuijiViperListAdapter.ViewHolder> {

    private List<HuiJiViperBean> viperBeanList;
    private Context context;
    private Boolean isAllVipInfo; // true 全部会员，false  今日来访

    public HuijiViperListAdapter(Context context, List<HuiJiViperBean> viperBeanList, boolean isAllVipInfo) {
        this.context = context;
        this.viperBeanList = viperBeanList;
        this.isAllVipInfo = isAllVipInfo;
    }

    @Override
    public HuijiViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_vip_info, parent, false);
        HuijiViperListAdapter.ViewHolder holder = new HuijiViperListAdapter.ViewHolder(view);
        return holder;
    }

    public void update(List<HuiJiViperBean> viperBeanList) {
        this.viperBeanList = viperBeanList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(HuijiViperListAdapter.ViewHolder holder, int position) {
        HuiJiViperBean viperBean = viperBeanList.get(position);
        holder.rel_be_present_time.setVisibility(isAllVipInfo ? View.GONE : View.VISIBLE);
        holder.rel_be_departure_time.setVisibility(isAllVipInfo ? View.GONE : View.VISIBLE);

        holder.tv_name.setText(viperBean.getName());
        holder.iv_gender.setImageResource("0".equals(viperBean.getSex()) ? R.mipmap.lg_man : R.mipmap.lg_women);
        /*holder.tv_cardName.setText(viperBean.getCardName());
        holder.tv_card_type.setText(viperBean.getCardType());*/

        holder.rv_card.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_card.setAdapter(new HuiJiVipeCardAdapter(viperBean.getCardprodsBeans()));

        holder.rel_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rv_card.setVisibility((holder.rv_card.getVisibility()==View.GONE)?View.VISIBLE:View.GONE);
            }
        });

        holder.tv_private_coach.setText(viperBean.getPrivateCoach());
        holder.tv_like_lesson.setText(viperBean.getFavorCourse());
        holder.tv_like_teacher.setText(viperBean.getFavorTeacher());
        holder.tv_regist_time.setText(viperBean.getRegisterTime());
        holder.tv_contract_overTime.setText(viperBean.getContractDeadline());
        holder.tv_contract_balance.setText(viperBean.getContractBalance());
        holder.tv_buy_count.setText(viperBean.getPurchaseCount() + "");
        holder.tv_be_present_time.setText(viperBean.getVisitTime());
        holder.tv_be_departure_time.setText(viperBean.getLeaveTime());
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
        /* TextView tv_cardName;
         TextView tv_card_type;*/
        RelativeLayout rel_expand;
        RecyclerView rv_card;

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
            lin_content = view.findViewById(R.id.lin_content);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
          /*  tv_cardName = view.findViewById(R.id.tv_cardName);
            tv_card_type = view.findViewById(R.id.tv_card_type);*/
            rel_expand = view.findViewById(R.id.rel_expand);
            rv_card = view.findViewById(R.id.rv_card);
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
