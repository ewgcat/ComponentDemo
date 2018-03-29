package com.yijian.staff.mvp.coach.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.bean.ViperBean;
import com.yijian.staff.mvp.coach.classbaojia.NoSearchBarCoachClassBaojiaActivity;
import com.yijian.staff.mvp.coach.experienceclass.invate.ExperienceClassInvateActivity;
import com.yijian.staff.mvp.coach.recordchart.RecordChartActivity;
import com.yijian.staff.mvp.physical.PhysicalReportActivity;
import com.yijian.staff.mvp.vip.detail.ViperDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 */

public class CoachSearchViperListAdapter extends RecyclerView.Adapter<CoachSearchViperListAdapter.ViewHolder> {

    private List<CoachSearchViperBean> viperBeanList = new ArrayList<CoachSearchViperBean>();
    private Context context;

    public CoachSearchViperListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CoachSearchViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_search_vip_info, parent, false);
        CoachSearchViperListAdapter.ViewHolder holder = new CoachSearchViperListAdapter.ViewHolder(view);
        return holder;
    }

    public void update(List<CoachSearchViperBean> viperBeanList) {
        this.viperBeanList.clear();
        this.viperBeanList.addAll(viperBeanList);
        notifyDataSetChanged();
        System.out.print("sdfsdfdsfsf");
    }


    @Override
    public void onBindViewHolder(CoachSearchViperListAdapter.ViewHolder holder, int position) {
        CoachSearchViperBean viperBean = viperBeanList.get(position);

        holder.tv_name.setText(viperBean.getName());
        holder.iv_gender.setImageResource("0".equals(viperBean.getSex()) ? R.mipmap.lg_man : R.mipmap.lg_women);
        Glide.with(context).load(viperBean.getHeadImg()).into(holder.iv_header);
        String viperRole = viperBean.getViperRole();
        if (TextUtils.isEmpty(viperRole)) {

        } else {
            if (viperRole.equals("普通会员")) {

                holder.ll_zhengshi_viper.setVisibility(View.VISIBLE);
                holder.ll_guoqi_viper.setVisibility(View.GONE);
                holder.ll_qianzai_viper.setVisibility(View.GONE);
                holder.ll_yixiang_viper.setVisibility(View.GONE);

                holder.tv_viper_role.setText(viperBean.getViperRole());
                holder.zhengshi_tv_cardName.setText(viperBean.getCardName());
                holder.zhengshi_tv_card_type.setText(viperBean.getCardType());
                holder.zhengshi_tv_private_class.setText(viperBean.getPrivateCourse());
                holder.zhengshi_tv_like_lesson.setText(viperBean.getFavorCourse());
                holder.zhengshi_tv_like_teacher.setText(viperBean.getFavorTeacher());
                holder.zhengshi_tv_regist_time.setText(viperBean.getRegisterTime());
                holder.zhengshi_tv_buy_count.setText(viperBean.getPurchaseCount() + "");
                //上课记录表
                holder.zhengshi_lin_shangke_recordchart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, RecordChartActivity.class));
                    }
                });
                //体测数据
                holder.zhengshi_lin_ti_ce_shu_ju.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, PhysicalReportActivity.class));
                    }
                });
            } else if (viperRole.equals("潜在会员")) {
                holder.ll_qianzai_viper.setVisibility(View.VISIBLE);
                holder.ll_zhengshi_viper.setVisibility(View.GONE);
                holder.ll_guoqi_viper.setVisibility(View.GONE);
                holder.ll_yixiang_viper.setVisibility(View.GONE);


                holder.qianzai_tv_birth.setText(viperBean.getBirthday());
                holder.qianzai_tv_birth_type.setText(viperBean.getBirthdayType());
                holder.qianzai_tv_bodybuildingHobby.setText(viperBean.getFitnessHobby());
                holder.qianzai_tv_bodyStatus.setText(viperBean.getHealthStatus());
                holder.qianzai_tv_interestHobby.setText(viperBean.getHobby());
                holder.qianzai_tv_useCar.setText(viperBean.getUseCar());

                //回访
                holder.qianzai_lin_visit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                //邀约
                holder.qianzai_lin_invitation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            } else if (viperRole.equals("意向会员")) {
                holder.ll_yixiang_viper.setVisibility(View.VISIBLE);
                holder.ll_zhengshi_viper.setVisibility(View.GONE);
                holder.ll_guoqi_viper.setVisibility(View.GONE);
                holder.ll_qianzai_viper.setVisibility(View.GONE);

                holder. yixiang_tv_birth.setText(viperBean.getBirthday());
                holder. yixiang_tv_birth_type.setText(viperBean.getBirthdayType());
                holder. yixiang_tv_bodyStatus.setText(viperBean.getHealthStatus());
                holder. yixiang_tv_bodybuildingHobby.setText(viperBean.getFitnessHobby());
                holder. yixiang_tv_interestHobby.setText(viperBean.getHobby());
                holder. yixiang_tv_useCar.setText(viperBean.getUseCar());
                holder. yixiang_lin_baojia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, NoSearchBarCoachClassBaojiaActivity.class));
                    }
                });
                //回访
                holder. yixiang_lin_protect_seven.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                holder. yixiang_lin_invitation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, ExperienceClassInvateActivity.class));
                    }
                });


            } else if (viperRole.equals("过期会员")) {
                holder.ll_guoqi_viper.setVisibility(View.VISIBLE);
                holder.ll_zhengshi_viper.setVisibility(View.GONE);
                holder.ll_qianzai_viper.setVisibility(View.GONE);
                holder.ll_yixiang_viper.setVisibility(View.GONE);

                holder.guoqi_tv_cardName.setText(viperBean.getCardName());
                holder.guoqi_tv_cardType.setText(viperBean.getCardType());
                holder.guoqi_tv_history_lesson.setText(viperBean.getHistoryCourse());
                holder.guoqi_tv_outDate.setText(viperBean.getDeadline());
                holder.guoqi_tv_outDate_reason.setText(viperBean.getExpiryReason());
                holder.guoqi_lin_huifan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                holder.guoqi_lin_yaoyue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context,ExperienceClassInvateActivity.class));
                    }
                });
            }
        }


    }

    @Override
    public int getItemCount() {
        return viperBeanList == null ? 0 : viperBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_viper_role;

        //正式会员
        LinearLayout ll_zhengshi_viper;
        TextView zhengshi_tv_cardName;
        TextView zhengshi_tv_card_type;
        TextView zhengshi_tv_private_class;
        TextView zhengshi_tv_like_lesson;
        TextView zhengshi_tv_like_teacher;
        TextView zhengshi_tv_regist_time;
        TextView zhengshi_tv_buy_count;
        LinearLayout zhengshi_lin_shangke_recordchart;
        LinearLayout zhengshi_lin_ti_ce_shu_ju;

        //过期会员
        LinearLayout ll_guoqi_viper;
        TextView guoqi_tv_cardName;
        TextView guoqi_tv_cardType;
        TextView guoqi_tv_history_lesson;
        TextView guoqi_tv_outDate;
        TextView guoqi_tv_outDate_reason;
        LinearLayout guoqi_lin_huifan;
        LinearLayout guoqi_lin_yaoyue;

        //意向会员
        LinearLayout ll_yixiang_viper;
        TextView yixiang_tv_birth;
        TextView yixiang_tv_birth_type;
        TextView yixiang_tv_bodyStatus;
        TextView yixiang_tv_bodybuildingHobby;
        TextView yixiang_tv_interestHobby;
        TextView yixiang_tv_useCar;
        LinearLayout yixiang_lin_baojia;
        LinearLayout yixiang_lin_protect_seven;
        LinearLayout yixiang_lin_invitation;

        //潜在会员
        LinearLayout ll_qianzai_viper;
        TextView qianzai_tv_birth;
        TextView qianzai_tv_birth_type;
        TextView qianzai_tv_bodyStatus;
        TextView qianzai_tv_bodybuildingHobby;
        TextView qianzai_tv_interestHobby;
        TextView qianzai_tv_useCar;
        LinearLayout qianzai_lin_visit;
        LinearLayout qianzai_lin_invitation;


        public ViewHolder(View view) {
            super(view);

            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_viper_role = view.findViewById(R.id.tv_viper_role);

            //正式
            ll_zhengshi_viper = view.findViewById(R.id.ll_zhengshi_viper);
            zhengshi_tv_cardName = view.findViewById(R.id.zhengshi_tv_cardName);
            zhengshi_tv_card_type = view.findViewById(R.id.zhengshi_tv_card_type);
            zhengshi_tv_private_class = view.findViewById(R.id.zhengshi_tv_private_class);
            zhengshi_tv_like_lesson = view.findViewById(R.id.zhengshi_tv_like_lesson);
            zhengshi_tv_like_teacher = view.findViewById(R.id.zhengshi_tv_like_teacher);
            zhengshi_tv_regist_time = view.findViewById(R.id.zhengshi_tv_regist_time);
            zhengshi_tv_buy_count = view.findViewById(R.id.zhengshi_tv_buy_count);
            zhengshi_lin_shangke_recordchart = view.findViewById(R.id.zhengshi_lin_shangke_recordchart);
            zhengshi_lin_ti_ce_shu_ju = view.findViewById(R.id.zhengshi_lin_ti_ce_shu_ju);

            //过期
            ll_guoqi_viper = view.findViewById(R.id.ll_guoqi_viper);
            guoqi_tv_cardName = view.findViewById(R.id.guoqi_tv_cardName);
            guoqi_tv_cardType = view.findViewById(R.id.guoqi_tv_cardType);
            guoqi_tv_history_lesson = view.findViewById(R.id.guoqi_tv_history_lesson);
            guoqi_tv_outDate = view.findViewById(R.id.guoqi_tv_outDate);
            guoqi_tv_outDate_reason = view.findViewById(R.id.guoqi_tv_outDate_reason);
            guoqi_lin_huifan = view.findViewById(R.id.guoqi_lin_huifan);
            guoqi_lin_yaoyue = view.findViewById(R.id.guoqi_lin_yaoyue);

            //潜在
            ll_qianzai_viper = view.findViewById(R.id.ll_qianzai_viper);
            qianzai_tv_birth = view.findViewById(R.id.qianzai_tv_birth);
            qianzai_tv_birth_type = view.findViewById(R.id.qianzai_tv_birth_type);
            qianzai_tv_bodyStatus = view.findViewById(R.id.qianzai_tv_bodyStatus);
            qianzai_tv_bodybuildingHobby = view.findViewById(R.id.qianzai_tv_bodybuildingHobby);
            qianzai_tv_interestHobby = view.findViewById(R.id.qianzai_tv_interestHobby);
            qianzai_tv_useCar = view.findViewById(R.id.qianzai_tv_useCar);
            qianzai_lin_visit = view.findViewById(R.id.qianzai_lin_visit);
            qianzai_lin_invitation = view.findViewById(R.id.qianzai_lin_invitation);

            //意向
            ll_yixiang_viper = view.findViewById(R.id.ll_yixiang_viper);
            yixiang_tv_birth = view.findViewById(R.id.yixiang_tv_birth);
            yixiang_tv_birth_type = view.findViewById(R.id.yixiang_tv_birth_type);
            yixiang_tv_bodyStatus = view.findViewById(R.id.yixiang_tv_bodyStatus);
            yixiang_tv_bodybuildingHobby = view.findViewById(R.id.yixiang_tv_bodybuildingHobby);
            yixiang_tv_interestHobby = view.findViewById(R.id.yixiang_tv_interestHobby);
            yixiang_tv_useCar = view.findViewById(R.id.yixiang_tv_useCar);
            yixiang_lin_protect_seven = view.findViewById(R.id.yixiang_lin_protect_seven);
            yixiang_lin_invitation = view.findViewById(R.id.yixiang_lin_invitation);
            yixiang_lin_baojia = view.findViewById(R.id.yixiang_lin_baojia);

        }
    }

}
