package com.yijian.staff.mvp.coach.intent;

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
import com.yijian.staff.mvp.coach.bean.CoachViperBean;
import com.yijian.staff.mvp.coach.classbaojia.NoSearchBarCoachClassBaojiaActivity;
import com.yijian.staff.mvp.coach.experienceclass.invate.ExperienceClassInvateActivity;

import java.util.List;

/**
 * Created by yangk on 2018/3/5.
 */

public class CoachIntentViperListAdapter extends RecyclerView.Adapter<CoachIntentViperListAdapter.ViewHolder> {

    private List<CoachViperBean> coachViperBeanList;
    private Context context;

    public CoachIntentViperListAdapter(Context context, List<CoachViperBean> coachViperBeanList){
        this.context = context;
        this.coachViperBeanList = coachViperBeanList;
    }

    @Override
    public CoachIntentViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_vip_intention_info, parent, false);
        CoachIntentViperListAdapter.ViewHolder holder = new CoachIntentViperListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CoachIntentViperListAdapter.ViewHolder holder, int position) {
        CoachViperBean coachViperBean = coachViperBeanList.get(position);
        holder.tv_name.setText(coachViperBean.getName());



        holder.rl_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rv_card.setVisibility((holder.rv_card.getVisibility()==View.GONE)?View.VISIBLE:View.GONE);
            }
        });

        holder.ll_chakanxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,CoachIntentViperDetailActivity.class));
            }
        });

        holder.lin_baojia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,NoSearchBarCoachClassBaojiaActivity.class));
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



    }

    @Override
    public int getItemCount() {
        return coachViperBeanList ==null?0: coachViperBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        LinearLayout ll_chakanxiangqing; //报价

        RelativeLayout rl_expand;
        RecyclerView rv_card;
        TextView tv_fuwu_huiji;
        TextView tv_ti_yan_ke_ci_shu;
        TextView tv_first_class_record;
        TextView tv_second_class_record;
        TextView tv_like_class;
        TextView tv_like_teacher;
        TextView tv_regist_time;
        TextView tv_contract_overTime;
        TextView tv_contract_balance;
        TextView tv_buy_count;




        LinearLayout lin_baojia; //报价
        LinearLayout lin_protect_seven; //保护7天/回访
        LinearLayout lin_invitation; //邀请


        public ViewHolder(View view) {
            super(view);
            iv_header =  view.findViewById(R.id.iv_header);
            iv_gender =  view.findViewById(R.id.iv_gender);
            tv_name   = view.findViewById(R.id.tv_name);
            ll_chakanxiangqing   = view.findViewById(R.id.ll_chakanxiangqing);

            rl_expand   = view.findViewById(R.id.rl_expand);
            rv_card   = view.findViewById(R.id.rv_card);

            tv_fuwu_huiji  =     view.findViewById(R.id.tv_fuwu_huiji);

            tv_ti_yan_ke_ci_shu =     view.findViewById(R.id.tv_ti_yan_ke_ci_shu);

            tv_first_class_record =     view.findViewById(R.id.tv_first_class_record);
            tv_second_class_record =     view.findViewById(R.id.tv_second_class_record);
            tv_like_class =     view.findViewById(R.id.tv_like_class);
            tv_like_teacher  =     view.findViewById(R.id.tv_like_teacher);
            tv_regist_time  =     view.findViewById(R.id.tv_regist_time);
            tv_contract_overTime  =     view.findViewById(R.id.tv_contract_overTime);
            tv_contract_balance  =     view.findViewById(R.id.tv_contract_balance);
            tv_buy_count  =     view.findViewById(R.id.tv_buy_count);

            lin_baojia =     view.findViewById(R.id.lin_baojia);
            lin_protect_seven =     view.findViewById(R.id.lin_protect_seven);
            lin_invitation =     view.findViewById(R.id.lin_invitation);
        }
    }

}
