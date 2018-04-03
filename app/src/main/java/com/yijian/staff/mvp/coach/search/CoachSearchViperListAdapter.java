package com.yijian.staff.mvp.coach.search;

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

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.card.CoachVipCardListAdapter;
import com.yijian.staff.mvp.coach.classbaojia.NoSearchBarCoachClassBaojiaActivity;
import com.yijian.staff.mvp.coach.experienceclass.invate.ExperienceClassInvateActivity;
import com.yijian.staff.mvp.coach.recordchart.RecordChartActivity;
import com.yijian.staff.mvp.huiji.invitation.index.InvateIndexActivity;
import com.yijian.staff.mvp.physical.PhysicalReportActivity;
import com.yijian.staff.util.DateUtil;

import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 */

public class CoachSearchViperListAdapter extends RecyclerView.Adapter<CoachSearchViperListAdapter.ViewHolder> {

    private static final String TAG = CoachSearchViperListAdapter.class.getSimpleName();
    private List<CoachSearchViperBean> viperBeanList;
    private Context context;

    public CoachSearchViperListAdapter(Context context, List<CoachSearchViperBean> viperBeanList) {
        this.context = context;
        this.viperBeanList = viperBeanList;
    }

    @Override
    public CoachSearchViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_search_vip_info, parent, false);
        CoachSearchViperListAdapter.ViewHolder holder = new CoachSearchViperListAdapter.ViewHolder(view);
        return holder;
    }

    public void update(List<CoachSearchViperBean> viperBeanList) {
        this.viperBeanList = viperBeanList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(CoachSearchViperListAdapter.ViewHolder holder, int position) {
        CoachSearchViperBean viperBean = viperBeanList.get(position);


        holder.tv_name.setText(viperBean.getName());
        holder.iv_gender.setImageResource("1".equals(viperBean.getSex()) ? R.mipmap.lg_man : R.mipmap.lg_women);
        Glide.with(context).load(viperBean.getHeadImg()).into(holder.iv_header);
        String viperRole = viperBean.getViperRole();
        if (TextUtils.isEmpty(viperRole)) {
            holder.ll_zhengshi_viper.setVisibility(View.GONE);
            holder.ll_guoqi_viper.setVisibility(View.GONE);
            holder.ll_qianzai_viper.setVisibility(View.GONE);
            holder.ll_yixiang_viper.setVisibility(View.GONE);
        } else {
            holder.tv_viper_role.setText(viperBean.getViperRole());
            String subclassName = viperBean.getSubclassName();
            if (subclassName.equals("CoachInfoVO")) {
                holder.ll_zhengshi_viper.setVisibility(View.VISIBLE);
                holder.ll_guoqi_viper.setVisibility(View.GONE);
                holder.ll_qianzai_viper.setVisibility(View.GONE);
                holder.ll_yixiang_viper.setVisibility(View.GONE);

                holder.zhengshi_rel_expand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleZhengSiCardView(holder);
                    }


                });


                holder.zhengshi_rv_card.setLayoutManager(new LinearLayoutManager(context));
                holder.zhengshi_rv_card.setAdapter(new CoachVipCardListAdapter(viperBean.getCardprodsBeans()));
                holder.zhengshi_tv_private_class.setText(viperBean.getPrivateCourse());
                holder.zhengshi_tv_like_lesson.setText(viperBean.getFavorCourse());
                holder.zhengshi_tv_like_teacher.setText(viperBean.getFavorTeacher());

                long registerTime = viperBean.getRegisterTime();
                if (registerTime!=0){
                    String s = DateUtil.parseLongDateToString(registerTime);
                    holder.zhengshi_tv_regist_time.setText(s);
                }else {
                    holder.zhengshi_tv_regist_time.setText("");
                }


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
            } else if (subclassName.equals("PotentialVO")) {
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

                //TODO 电话回访
                holder.qianzai_lin_visit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                //邀约
                holder.qianzai_lin_invitation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, InvateIndexActivity.class));

                    }
                });
            } else if (subclassName.equals("CoachIntentionVO")) {
                holder.ll_yixiang_viper.setVisibility(View.VISIBLE);
                holder.ll_zhengshi_viper.setVisibility(View.GONE);
                holder.ll_guoqi_viper.setVisibility(View.GONE);
                holder.ll_qianzai_viper.setVisibility(View.GONE);

                holder.yixiang_tv_birth.setText(viperBean.getBirthday());
                holder.yixiang_tv_birth_type.setText(viperBean.getBirthdayType());
                holder.yixiang_tv_bodyStatus.setText(viperBean.getHealthStatus());
                holder.yixiang_tv_bodybuildingHobby.setText(viperBean.getFitnessHobby());
                holder.yixiang_tv_interestHobby.setText(viperBean.getHobby());
                holder.yixiang_tv_useCar.setText(viperBean.getUseCar());
                holder.yixiang_lin_baojia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, NoSearchBarCoachClassBaojiaActivity.class));
                    }
                });
                //TODO 电话回访
                holder.yixiang_lin_protect_seven.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                holder.yixiang_lin_invitation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, InvateIndexActivity.class));
                    }
                });


            } else if (subclassName.equals("CoachExpireVO")) {
                holder.ll_guoqi_viper.setVisibility(View.VISIBLE);
                holder.ll_zhengshi_viper.setVisibility(View.GONE);
                holder.ll_qianzai_viper.setVisibility(View.GONE);
                holder.ll_yixiang_viper.setVisibility(View.GONE);
                holder.guoqi_rel_expand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleGuoQiCardView(holder);

                    }
                });


                holder.guoqi_rv_card.setLayoutManager(new LinearLayoutManager(context));
                holder.guoqi_rv_card.setAdapter(new CoachVipCardListAdapter(viperBean.getCardprodsBeans()));
                holder.guoqi_tv_history_lesson.setText(viperBean.getHistoryCourse());

                long contractDeadline = viperBean.getDeadline();
                if (contractDeadline!=0){
                    String s = DateUtil.parseLongDateToString(contractDeadline);
                    holder.guoqi_tv_outDate.setText(s);
                }else {
                    holder.guoqi_tv_outDate.setText("");
                }

                holder.guoqi_tv_outDate_reason.setText(viperBean.getExpiryReason());
                //TODO 电话回访
                holder.guoqi_lin_huifan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                holder.guoqi_lin_yaoyue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, ExperienceClassInvateActivity.class));
                    }
                });
            }
        }


    }


    private void toggleZhengSiCardView(CoachSearchViperListAdapter.ViewHolder holder) {
        int visibility = holder.zhengshi_rv_card.getVisibility();
        if (visibility == View.GONE) {
            holder.zhengshi_rv_card.setVisibility(View.VISIBLE);
            holder.zhengshi_tv_zhankai_status.setText("收起");
            Drawable drawable = context.getDrawable(R.mipmap.fp_shang);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            holder.zhengshi_tv_zhankai_status.setCompoundDrawables(null,null,drawable,null);
        } else {
            holder.zhengshi_rv_card.setVisibility(View.GONE);
            holder.zhengshi_tv_zhankai_status.setText("展开");
            Drawable drawable = context.getDrawable(R.mipmap.lg_xiala);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            holder.zhengshi_tv_zhankai_status.setCompoundDrawables(null,null,drawable,null);
        }

    }
    private void toggleGuoQiCardView(CoachSearchViperListAdapter.ViewHolder holder) {

        int visibility = holder.guoqi_rv_card.getVisibility();
        if (visibility == View.GONE) {
            holder.guoqi_rv_card.setVisibility(View.VISIBLE);
            holder.guoqi_tv_zhankai_status.setText("收起");
            Drawable drawable = context.getDrawable(R.mipmap.fp_shang);
            holder.guoqi_tv_zhankai_status.setCompoundDrawables(null,null,drawable,null);
        } else {
            holder.guoqi_rv_card.setVisibility(View.GONE);
            holder.guoqi_tv_zhankai_status.setText("展开");
            Drawable drawable = context.getDrawable(R.mipmap.lg_xiala);
            holder.guoqi_tv_zhankai_status.setCompoundDrawables(null,null,drawable,null);
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
        RelativeLayout zhengshi_rel_expand;
        TextView zhengshi_tv_zhankai_status;
        RecyclerView zhengshi_rv_card;
        TextView zhengshi_tv_private_class;
        TextView zhengshi_tv_like_lesson;
        TextView zhengshi_tv_like_teacher;
        TextView zhengshi_tv_regist_time;
        TextView zhengshi_tv_buy_count;
        LinearLayout zhengshi_lin_shangke_recordchart;
        LinearLayout zhengshi_lin_ti_ce_shu_ju;

        //过期会员
        LinearLayout ll_guoqi_viper;
        RelativeLayout guoqi_rel_expand;
        TextView guoqi_tv_zhankai_status;
        RecyclerView guoqi_rv_card;
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
            zhengshi_rel_expand = view.findViewById(R.id.zhengshi_rel_expand);
            zhengshi_rv_card = view.findViewById(R.id.zhengshi_rv_card);
            zhengshi_tv_zhankai_status = view.findViewById(R.id.zhengshi_tv_zhankai_status);
            zhengshi_tv_private_class = view.findViewById(R.id.zhengshi_tv_private_class);
            zhengshi_tv_like_lesson = view.findViewById(R.id.zhengshi_tv_like_lesson);
            zhengshi_tv_like_teacher = view.findViewById(R.id.zhengshi_tv_like_teacher);
            zhengshi_tv_regist_time = view.findViewById(R.id.zhengshi_tv_regist_time);
            zhengshi_tv_buy_count = view.findViewById(R.id.zhengshi_tv_buy_count);
            zhengshi_lin_shangke_recordchart = view.findViewById(R.id.zhengshi_lin_shangke_recordchart);
            zhengshi_lin_ti_ce_shu_ju = view.findViewById(R.id.zhengshi_lin_ti_ce_shu_ju);

            //过期
            ll_guoqi_viper = view.findViewById(R.id.ll_guoqi_viper);
            guoqi_rel_expand = view.findViewById(R.id.guoqi_rel_expand);
            guoqi_tv_zhankai_status = view.findViewById(R.id.guoqi_tv_zhankai_status);
            guoqi_rv_card = view.findViewById(R.id.guoqi_rv_card);
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
