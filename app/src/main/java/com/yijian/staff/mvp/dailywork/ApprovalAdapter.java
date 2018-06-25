package com.yijian.staff.mvp.dailywork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;

import java.util.List;

/**
 * Created by yangk on 2018/3/7.
 */

public class ApprovalAdapter extends RecyclerView.Adapter<ApprovalAdapter.ViewHolder> {

    private List<ApprovalInfo> approvalInfoList;
    private Context context;
    private Boolean isAllVipInfo; // true 全部会员，false  今日来访

    public ApprovalAdapter(Context context, List<ApprovalInfo> approvalInfoList, boolean isAllVipInfo) {
        this.context = context;
        this.approvalInfoList = approvalInfoList;
        this.isAllVipInfo = isAllVipInfo;
    }

    @Override
    public ApprovalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_vip_info, parent, false);
        ApprovalAdapter.ViewHolder holder = new ApprovalAdapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ApprovalAdapter.ViewHolder holder, int position) {
        ApprovalInfo vipPeopleInfo = approvalInfoList.get(position);

          /*  holder.tv_name.setText(vipPeopleInfo.getName());
            holder.iv_gender.setHeadImageResource("0".equals(vipPeopleInfo.getGender())?R.mipmap.lg_women:R.mipmap.lg_man);
            holder.tv_cardName.setText(vipPeopleInfo.getCardName());
            holder.tv_card_type.setText(vipPeopleInfo.getCardType());
            holder.tv_private_coach.setText(vipPeopleInfo.getPrivateCoach());
            holder.tv_like_lesson.setText(vipPeopleInfo.getLikeLesson());
            holder.tv_like_teacher.setText(vipPeopleInfo.getLikeTeacher());
            holder.tv_regist_time.setText(vipPeopleInfo.getRegistTime());
            holder.tv_contract_overTime.setText(vipPeopleInfo.getContractOverTime());
            holder.tv_contract_balance.setText(vipPeopleInfo.getContractBalance());
            holder.tv_buy_count.setText(vipPeopleInfo.getBuyCount());
            holder.tv_be_present_time.setText(vipPeopleInfo.getBePresentTime());
            holder.tv_be_departure_time.setText(vipPeopleInfo.getDepartureTime());
*/

    }

    @Override
    public int getItemCount() {
        return approvalInfoList == null ? 0 : approvalInfoList.size();
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


        public ViewHolder(View view) {
            super(view);
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
