package com.yijian.staff.mvp.coach.intent;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.bean.CoachViperBean;
import com.yijian.staff.mvp.coach.card.CoachVipCardListAdapter;
import com.yijian.staff.mvp.coach.classbaojia.NoSearchBarCoachClassBaojiaActivity;
import com.yijian.staff.mvp.coach.detail.CoachViperDetailActivity;
import com.yijian.staff.mvp.setclass.ExperienceClassRecordActivity;
import com.yijian.staff.mvp.setclass.OpenLessonNewActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;

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

        int resId;
        if (coachViperBean.getSex().equals("1")){
            resId =  R.mipmap.lg_man ;
        } else if (coachViperBean.getSex().equals("2")){
            resId =  R.mipmap.lg_women ;
        }else {
            resId =  R.mipmap.lg_man ;

        }
        holder.iv_gender.setImageResource(resId);


        holder.rv_card.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_card.setAdapter(new CoachVipCardListAdapter(coachViperBean.getCardprodsBeans()));

        holder.rel_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCardView(holder);
            }
        });

        holder. tv_fuwu_huiji.setText(coachViperBean.getSeller());
        int experienceClassTimes = coachViperBean.getExperienceClassTimes();
        String text = experienceClassTimes + "";
        if (TextUtils.isEmpty(text)){
            holder. rl_ti_yan_ke_ci_shu.setVisibility(View.GONE);
            holder. rl_class_record.setVisibility(View.GONE);

        }else {
            if (experienceClassTimes==0){
                holder. rl_ti_yan_ke_ci_shu.setVisibility(View.GONE);
                holder. rl_class_record.setVisibility(View.GONE);
            }else  if (experienceClassTimes==1){
                holder. rl_ti_yan_ke_ci_shu.setVisibility(View.VISIBLE);
                holder. tv_first_class_record.setVisibility(View.VISIBLE);

                holder.tv_second_class_record.setVisibility(View.GONE);
                holder.tv_first_class_record.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO 跳转到第一次体验课上课表
                        if (coachViperBean.getFirstType()==0){
                            Intent intent = new Intent(context, ExperienceClassRecordActivity.class);
                            intent.putExtra("privateApplyId",coachViperBean.getFiirstId());
                            context.startActivity(intent);
                        }else {
                            Intent intent = new Intent(context, OpenLessonNewActivity.class);
                            intent.putExtra("privateApplyId",coachViperBean.getFiirstId());
                            context.startActivity(intent);
                        }

                    }
                });
                holder. rl_class_record.setVisibility(View.VISIBLE);
            }else  if (experienceClassTimes==2){
                holder. rl_ti_yan_ke_ci_shu.setVisibility(View.VISIBLE);
                holder. rl_class_record.setVisibility(View.VISIBLE);
                holder. tv_first_class_record.setVisibility(View.VISIBLE);
                holder. tv_second_class_record.setVisibility(View.VISIBLE);
                holder.tv_first_class_record.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (coachViperBean.getFirstType()==0){
                            Intent intent = new Intent(context, ExperienceClassRecordActivity.class);
                            intent.putExtra("privateApplyId",coachViperBean.getFiirstId());
                            context.startActivity(intent);
                        }else {
                            Intent intent = new Intent(context, OpenLessonNewActivity.class);
                            intent.putExtra("privateApplyId",coachViperBean.getFiirstId());
                            context.startActivity(intent);
                        }
                    }
                });
                holder.tv_second_class_record.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (coachViperBean.getSecondType()==0){
                            Intent intent = new Intent(context, ExperienceClassRecordActivity.class);
                            intent.putExtra("privateApplyId",coachViperBean.getSecondId());
                            context.startActivity(intent);
                        }else {
                            Intent intent = new Intent(context, OpenLessonNewActivity.class);
                            intent.putExtra("privateApplyId",coachViperBean.getSecondId());
                            context.startActivity(intent);
                        }
                    }
                });
            }
        }


        holder. tv_like_class.setText(coachViperBean.getFavorCourse());
        holder. tv_like_teacher.setText(coachViperBean.getFavorTeacher());

        long registerTime = coachViperBean.getRegisterTime();
        if (registerTime!=0){
            String s = DateUtil.parseLongDateToDateString(registerTime);
            holder.tv_regist_time.setText(s);
        }else {
            holder.tv_regist_time.setText("");
        }

        long contractDeadline = coachViperBean.getContractDeadline();
        if (contractDeadline!=0){
            String s = DateUtil.parseLongDateToDateString(contractDeadline);
            holder.tv_contract_overTime.setText(s);
        }else {
            holder.tv_contract_overTime.setText("");
        }

        holder. tv_contract_balance.setText(coachViperBean.getContractBalance());
        holder. tv_buy_count.setText(coachViperBean.getPurchaseCount()+"");


        holder.ll_chakanxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoachViperDetailActivity.class);
                intent.putExtra("vipType",0);
                intent.putExtra("coachViperBean",coachViperBean);
                context.startActivity(intent);
            }
        });

        holder.lin_baojia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,NoSearchBarCoachClassBaojiaActivity.class));
            }
        });

        Boolean isProtected = coachViperBean.getProtected();
        if (isProtected){
            holder.tv_huifang.setText("保护7天");
        }else {
            holder.tv_huifang.setText("回访");
            String mobile = coachViperBean.getMobile();

            holder.lin_protect_seven.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(mobile)){
                        CommonUtil.callPhone(context,mobile);
                    } else {
                        Toast.makeText(context,"未录入手机号,无法进行电话回访",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return coachViperBeanList ==null?0: coachViperBeanList.size();
    }



    private void toggleCardView(CoachIntentViperListAdapter.ViewHolder holder) {
        int visibility = holder.rv_card.getVisibility();
        if (visibility == View.GONE) {
            holder.rv_card.setVisibility(View.VISIBLE);
            holder.tv_zhankai_status.setText("收起");
            Drawable drawable = context.getDrawable(R.mipmap.fp_shang);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            holder.tv_zhankai_status.setCompoundDrawables(null,null,drawable,null);
        } else {
            holder.rv_card.setVisibility(View.GONE);
            holder.tv_zhankai_status.setText("展开");
            Drawable drawable = context.getDrawable(R.mipmap.lg_xiala);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            holder.tv_zhankai_status.setCompoundDrawables(null,null,drawable,null);
        }

    }
    public void update(List<CoachViperBean> coachViperBeanList) {
        this.coachViperBeanList=coachViperBeanList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        LinearLayout ll_chakanxiangqing; //报价

        TextView tv_zhankai_status;
        RelativeLayout rel_expand;
        RecyclerView rv_card;

        TextView tv_fuwu_huiji;

        RelativeLayout rl_ti_yan_ke_ci_shu;
        TextView tv_ti_yan_ke_ci_shu;

        RelativeLayout rl_class_record;
        TextView tv_first_class_record;
        TextView tv_second_class_record;

        TextView tv_like_class;
        TextView tv_like_teacher;
        TextView tv_regist_time;
        TextView tv_contract_overTime;
        TextView tv_contract_balance;
        TextView tv_buy_count;
        TextView tv_huifang;




        LinearLayout lin_baojia; //报价
        LinearLayout lin_protect_seven; //保护7天/回访


        public ViewHolder(View view) {
            super(view);
            iv_header =  view.findViewById(R.id.iv_header);
            iv_gender =  view.findViewById(R.id.iv_gender);
            tv_name   = view.findViewById(R.id.tv_name);
            ll_chakanxiangqing   = view.findViewById(R.id.ll_chakanxiangqing);

            tv_zhankai_status = view.findViewById(R.id.tv_zhankai_status);
            rel_expand   = view.findViewById(R.id.rel_expand);
            rv_card   = view.findViewById(R.id.rv_card);

            tv_fuwu_huiji  =     view.findViewById(R.id.tv_fuwu_huiji);

            rl_ti_yan_ke_ci_shu =     view.findViewById(R.id.rl_ti_yan_ke_ci_shu);
            tv_ti_yan_ke_ci_shu =     view.findViewById(R.id.tv_ti_yan_ke_ci_shu);

            rl_class_record =     view.findViewById(R.id.rl_class_record);
            tv_first_class_record =     view.findViewById(R.id.tv_first_class_record);
            tv_second_class_record =     view.findViewById(R.id.tv_second_class_record);


            tv_like_class =     view.findViewById(R.id.tv_like_class);
            tv_like_teacher  =     view.findViewById(R.id.tv_like_teacher);
            tv_regist_time  =     view.findViewById(R.id.tv_regist_time);
            tv_contract_overTime  =     view.findViewById(R.id.tv_contract_overTime);
            tv_contract_balance  =     view.findViewById(R.id.tv_contract_balance);
            tv_buy_count  =     view.findViewById(R.id.tv_buy_count);
            tv_huifang  =     view.findViewById(R.id.tv_huifang);

            lin_baojia =     view.findViewById(R.id.lin_baojia);
            lin_protect_seven =     view.findViewById(R.id.lin_protect_seven);
        }
    }

}
