package com.yijian.staff.mvp.huiji.outdate;

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
import com.yijian.staff.mvp.huiji.bean.HuiJiVipCardAdapter;
import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;
import com.yijian.staff.mvp.questionnaireresult.QuestionnaireResultActivity;

import java.util.List;

/**
 * Created by yangk on 2018/3/26.
 */

public class HuijiOutdateViperListAdapter extends RecyclerView.Adapter<HuijiOutdateViperListAdapter.ViewHolder>  {

    private List<HuiJiViperBean> vipOutdateInfoList;
    private Context context;

    public HuijiOutdateViperListAdapter(Context context, List<HuiJiViperBean> vipOutdateInfoList){
        this.context = context;
        this.vipOutdateInfoList = vipOutdateInfoList;
    }


    @Override
    public HuijiOutdateViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oute_huiji_date, parent, false);
        HuijiOutdateViperListAdapter.ViewHolder holder = new HuijiOutdateViperListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HuijiOutdateViperListAdapter.ViewHolder holder, int position) {
        HuiJiViperBean vipOutdateInfo = vipOutdateInfoList.get(position);
        holder.tv_name.setText(vipOutdateInfo.getName());
        holder.iv_gender.setImageResource(vipOutdateInfo.getSex());
       /* holder.tv_cardName.setText(vipOutdateInfo.getCardName());
        holder.tv_cardType.setText(vipOutdateInfo.getCardType());*/

        holder.rv_card.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_card.setAdapter(new HuiJiVipCardAdapter(vipOutdateInfo.getCardprodsBeans()));

        holder.rel_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rv_card.setVisibility((holder.rv_card.getVisibility()==View.GONE)?View.VISIBLE:View.GONE);
                holder.tv_opration_label.setText((holder.rv_card.getVisibility()==View.GONE)?"收起":"展开");
                holder.iv_opration_arrow.setImageResource((holder.rv_card.getVisibility()==View.GONE)?R.mipmap.fp_shang:R.mipmap.fp_xia);
            }
        });

        holder.tv_privateCoach.setText(vipOutdateInfo.getPrivateCoach());
        holder.tv_likeLesson.setText(vipOutdateInfo.getFavorCourse());
        holder.tv_likeTeacher.setText(vipOutdateInfo.getFavorTeacher());
        holder.tv_registTime.setText(vipOutdateInfo.getRegisterTime());
        holder.tv_contractOutDate.setText(vipOutdateInfo.getContractDeadline());
        holder.tv_outDateDay.setText(vipOutdateInfo.getExpiredDay());

        holder.lin_quey_contract.setOnClickListener(new View.OnClickListener() { //查看合同
            @Override
            public void onClick(View v) {

            }
        });
        holder.lin_quey_question.setOnClickListener(new View.OnClickListener() { //查看问卷
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, QuestionnaireResultActivity.class));
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
       /* TextView tv_cardName;
        TextView tv_cardType;*/

        RelativeLayout rel_expand;
        RecyclerView rv_card;
        TextView tv_opration_label;
        ImageView iv_opration_arrow;

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
           /* tv_cardName   = view.findViewById(R.id.tv_cardName);
            tv_cardType =     view.findViewById(R.id.tv_cardType);*/

            rel_expand = view.findViewById(R.id.rel_expand);
            rv_card = view.findViewById(R.id.rv_card);
            tv_opration_label = view.findViewById(R.id.tv_opration_label);
            iv_opration_arrow = view.findViewById(R.id.iv_opration_arrow);

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
