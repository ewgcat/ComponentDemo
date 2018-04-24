package com.yijian.staff.mvp.coach.huifang.task.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.mvp.coach.huifang.bean.CoachHuiFangInfo;
import com.yijian.staff.mvp.coach.huifang.bean.CoachHuiFangTypeBean;
import com.yijian.staff.mvp.coach.huifang.tianxieresult.CoachTianXieHuiFangResultActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.GlideCircleTransform;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class CoachHuiFangTaskAdapter extends RecyclerView.Adapter<CoachHuiFangTaskAdapter.ViewHolder> {

    private List<CoachHuiFangInfo> mCoachHuiFangInfoList;
    private Context context;
    private int type;

    public CoachHuiFangTaskAdapter(Context context, List<CoachHuiFangInfo> mCoachHuiFangInfoList, int type) {
        this.mCoachHuiFangInfoList = mCoachHuiFangInfoList;
        this.context = context;
        this.type = type;
    }

    public void update(List<CoachHuiFangInfo> mCoachHuiFangInfoList) {
        this.mCoachHuiFangInfoList = mCoachHuiFangInfoList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_hui_fang_task, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        resetView(holder);

        CoachHuiFangInfo coachHuiFangInfo = mCoachHuiFangInfoList.get(position);
        String headImg = coachHuiFangInfo.getHeadImg();

        holder.tvViperName.setText(coachHuiFangInfo.getName());
        String sex = coachHuiFangInfo.getSex();
        if ("男".equals(sex)) {
            Glide.with(context).load(R.mipmap.lg_man).into(holder.ivSex);
            if (TextUtils.isEmpty(headImg)) {
                Glide.with(context).load(R.mipmap.wt_boysmall).into(holder.ivHead);
            } else {
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.wt_boysmall)
                        .error(R.mipmap.wt_boysmall)
                        .transform(new GlideCircleTransform())
                        .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(context).load(headImg).apply(options).into(holder.ivHead);
            }
        } else {
            Glide.with(context).load(R.mipmap.lg_women).into(holder.ivSex);
            if (TextUtils.isEmpty(headImg)) {
                Glide.with(context).load(R.mipmap.wt_girlsmall).into(holder.ivHead);
            } else {
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.wt_girlsmall)
                        .error(R.mipmap.wt_girlsmall)
                        .transform(new GlideCircleTransform())
                        .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(context).load(headImg).apply(options).into(holder.ivHead);
            }
        }

        String carBrand = coachHuiFangInfo.getCarBrand();
        holder.tvCarName.setText(carBrand);

        String healthStatus = coachHuiFangInfo.getHealthStatus();
        holder.tvShentiZhuangtai.setText(healthStatus);

        String fitnessHobby = coachHuiFangInfo.getFitnessHobby();
        holder.tvJianshenAihao.setText(fitnessHobby);

        String hobby = coachHuiFangInfo.getHobby();
        holder.tvXingquAihao.setText(hobby);



        String huifangType = coachHuiFangInfo.getInterviewType();
        holder.tvHuifangType.setText(huifangType);

        /**
         【0:全部，1:生日，2:昨日到访，
         3:昨日开卡，4:潜在会员，5:沉寂会员，
         6:恢复健身，7:复访，8:过期，9:快到期，
         10:易建平台，11:体验课，12:昨日上课，
         13:定时体测，14:私课上完，15:昨日买课】
         */
        String subclassName = coachHuiFangInfo.getSubclassName();
        switch (subclassName) {

            case "BirthdayVO"://生日回访 1
                holder.llBirthday.setVisibility(View.VISIBLE);
                holder.llBirthdayType.setVisibility(View.VISIBLE);
                Long birthday = coachHuiFangInfo.getBirthday();
                if (birthday != null && birthday != -1) {
                    String s = DateUtil.parseLongDateToDateString(birthday);
                    holder.tvBirthday.setText(s);
                }
                String birthdayType = coachHuiFangInfo.getBirthdayType();
                if (!TextUtils.isEmpty(birthdayType)) {
                    holder.tvBirthdayType.setText(birthdayType);
                }
                break;
            case "YesterdayCourseVO"://昨日上课 14
                holder.llZuoRiYueKeTime.setVisibility(View.VISIBLE);
                Long inviteCourseTime = coachHuiFangInfo.getInviteCourseTime();
                if (inviteCourseTime != null && inviteCourseTime != -1) {
                    String s = DateUtil.parseLongDateToTimeString(inviteCourseTime);
                    holder.tv_last_yue_ke_time.setText(s);
                }
                break;
            case "YesterdayOpenVO"://昨日开卡  3
                holder.llHetongDaoQiRi.setVisibility(View.VISIBLE);
                holder.llCardName.setVisibility(View.VISIBLE);
                holder.llCardType.setVisibility(View.VISIBLE);
                break;
            case "NearExpireVO"://快到期回访 9
                holder.llHetongDaoQiRi.setVisibility(View.VISIBLE);
                holder.llHetongYuEr.setVisibility(View.VISIBLE);

                holder.tvHetongYuEr.setText(coachHuiFangInfo.getContractBalance());
                Long time = coachHuiFangInfo.getDeadline();
                if (time != null && time != -1) {
                    String s = DateUtil.parseLongDateToDateString(time);
                    holder.tvHetongDaoQiRi.setText(s);
                }
                break;
            case "TimingPhysicalTestVO"://定时体测  13
                holder.llLastTiCeTime.setVisibility(View.VISIBLE);
                Long lastTestTime = coachHuiFangInfo.getLastTestTime();
                if (lastTestTime != null && lastTestTime != -1) {
                    String s = DateUtil.parseLongDateToTimeString(lastTestTime);
                    holder.tvLastTiCeTime.setText(s);
                }
                break;
            case "ReVO"://复访  7
                holder.llPreVisitDate.setVisibility(View.VISIBLE);
                holder.llFuFangReason.setVisibility(View.VISIBLE);
                Long lastVisitTime = coachHuiFangInfo.getLastVisitTime();
                if (lastVisitTime != null && lastVisitTime != -1) {
                    String s = DateUtil.parseLongDateToDateString(lastVisitTime);
                    holder.tvPreVisitDate.setText(s);
                }
                String reinterviewReason = coachHuiFangInfo.getReinterviewReason();
                holder.tvFuFangReason.setText(reinterviewReason);
                break;
            case "ExpireVO"://过期回访  8

                holder.llOutdateTime.setVisibility(View.VISIBLE);
                holder.llOutdateReason.setVisibility(View.VISIBLE);
                String expiryReason = coachHuiFangInfo.getExpiryReason();
                if (!TextUtils.isEmpty(expiryReason)) {
                    holder.tvOutdateReason.setText(expiryReason);
                }
                Long deadline = coachHuiFangInfo.getDeadline();
                if (deadline != null && deadline != -1) {
                    String s = DateUtil.parseLongDateToDateString(deadline);
                    holder.tvOutdateTime.setText(s);
                }
                break;

            case "PrivateCourseFinishedVO"://私课上完 14
                break;
            case "YesterdayBuyCourseVO"://昨日买课 15
                holder.llGouMaiKeCheng.setVisibility(View.VISIBLE);
                holder.tv_gou_mai_ke_cheng.setText(coachHuiFangInfo.getCourseName());
                break;

        }


        holder.llBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = coachHuiFangInfo.getMobile();
                if (!TextUtils.isEmpty(mobile)) {
                    if (CommonUtil.isPhoneFormat(mobile)) {
                        CoachHuiFangTypeBean coachHuiFangTypeBean = DBManager.getInstance().queryCoachHuiFangTypeBean("15");
                        Intent i = new Intent(context, CoachTianXieHuiFangResultActivity.class);
                        i.putExtra("interviewRecordId", coachHuiFangInfo.getInterviewRecordId());
                        i.putExtra("memberId", coachHuiFangInfo.getId());
                        context.startActivity(i);
                        CommonUtil.callPhone(context, mobile);

                    } else {
                        Toast.makeText(context, "返回的手机号不正确！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "未录入手机号！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCoachHuiFangInfoList == null ? 0 : mCoachHuiFangInfoList.size();
    }

    private void resetView(ViewHolder holder) {
        holder.llOutdateTime.setVisibility(View.GONE);
        holder.llOutdateReason.setVisibility(View.GONE);
        holder.llHetongYuEr.setVisibility(View.GONE);
        holder.llHetongDaoQiRi.setVisibility(View.GONE);
        holder.llKaiKaDate.setVisibility(View.GONE);
        holder.llPreVisitDate.setVisibility(View.GONE);
        holder.llFuFangReason.setVisibility(View.GONE);
        holder.llCardName.setVisibility(View.GONE);
        holder.llCardType.setVisibility(View.GONE);
        holder.llCardYuEr.setVisibility(View.GONE);
        holder.llPreJianShenDate.setVisibility(View.GONE);
        holder.llZuijinJianshen.setVisibility(View.GONE);
        holder.llChenMoTianShu.setVisibility(View.GONE);
        holder.llWeiJianShenTime.setVisibility(View.GONE);
        holder.llBirthday.setVisibility(View.GONE);
        holder.llBirthdayType.setVisibility(View.GONE);
        holder.llLastTiCeTime.setVisibility(View.GONE);
        holder.llZuoRiYueKeTime.setVisibility(View.GONE);
        holder.llGouMaiKeCheng.setVisibility(View.GONE);


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHead;
        TextView tvViperName;
        ImageView ivSex;
        TextView tvShentiZhuangtai;
        TextView tvJianshenAihao;
        TextView tvXingquAihao;
        TextView tvCarName;

        LinearLayout llOutdateTime;
        LinearLayout llOutdateReason;
        LinearLayout llHetongYuEr;
        LinearLayout llHetongDaoQiRi;
        LinearLayout llKaiKaDate;
        LinearLayout llPreVisitDate;
        LinearLayout llFuFangReason;
        LinearLayout llCardName;
        LinearLayout llCardType;
        LinearLayout llCardYuEr;
        LinearLayout llPreJianShenDate;
        LinearLayout llZuijinJianshen;
        LinearLayout llChenMoTianShu;
        LinearLayout llWeiJianShenTime;
        LinearLayout llBirthdayType;
        LinearLayout llBirthday;
        LinearLayout llLastTiCeTime;
        LinearLayout llZuoRiYueKeTime;
        LinearLayout llGouMaiKeCheng;

        TextView tvOutdateTime;
        TextView tvOutdateReason;
        TextView tvHetongDaoQiRi;
        TextView tvCardName;
        TextView tvHetongYuEr;
        TextView tvKaiKaDate;
        TextView tvCardYuEr;
        TextView tvCardType;
        TextView tvZuijinJianshen;
        TextView tvChenMoTianShu;
        TextView tvPreVisitDate;
        TextView tvFuFangReason;
        TextView tvPreJianShenDate;
        TextView tvWeiJianShenTime;
        TextView tvBirthdayType;
        TextView tvBirthday;
        TextView tvLastTiCeTime;
        TextView tv_last_yue_ke_time;
        TextView tv_gou_mai_ke_cheng;


        TextView tvHuifangType;
        ImageView iv;
        TextView tv;
        LinearLayout llBt;


        public ViewHolder(View view) {
            super(view);
            ivHead = view.findViewById(R.id.iv_head);
            tvViperName = view.findViewById(R.id.tv_viper_name);
            ivSex = view.findViewById(R.id.iv_sex);
            tvShentiZhuangtai = view.findViewById(R.id.tv_shenti_zhuangtai);
            tvJianshenAihao = view.findViewById(R.id.tv_jianshen_aihao);
            tvXingquAihao = view.findViewById(R.id.tv_xingqu_aihao);
            tvCarName = view.findViewById(R.id.tv_car_name);
            llCardName = view.findViewById(R.id.ll_card_name);


            llBirthday = view.findViewById(R.id.ll_birthday);
            tvBirthday = view.findViewById(R.id.tv_birthday);

            llBirthdayType = view.findViewById(R.id.ll_birthday_type);
            tvBirthdayType = view.findViewById(R.id.tv_birthday_type);

            llOutdateTime = view.findViewById(R.id.ll_outdate_time);
            tvOutdateTime = view.findViewById(R.id.tv_outdate_time);

            llOutdateReason = view.findViewById(R.id.ll_outdate_reason);
            tvOutdateReason = view.findViewById(R.id.tv_outdate_reason);

            llHetongDaoQiRi = view.findViewById(R.id.ll_hetong_dao_qi_ri);
            tvHetongDaoQiRi = view.findViewById(R.id.tv_hetong_dao_qi_ri);

            tvHetongYuEr = view.findViewById(R.id.tv_hetong_yu_er);
            llHetongYuEr = view.findViewById(R.id.ll_hetong_yu_er);

            tvKaiKaDate = view.findViewById(R.id.tv_kai_ka_date);
            llKaiKaDate = view.findViewById(R.id.ll_kai_ka_date);
            tvCardName = view.findViewById(R.id.tv_card_name);
            llCardName = view.findViewById(R.id.ll_card_name);
            tvCardYuEr = view.findViewById(R.id.tv_card_yu_er);
            llCardYuEr = view.findViewById(R.id.ll_card_yu_er);
            tvCardType = view.findViewById(R.id.tv_card_type);
            llCardType = view.findViewById(R.id.ll_card_type);
            tvZuijinJianshen = view.findViewById(R.id.tv_zuijin_jianshen);
            llZuijinJianshen = view.findViewById(R.id.ll_zuijin_jianshen);
            tvChenMoTianShu = view.findViewById(R.id.tv_chen_mo_tian_shu);
            llChenMoTianShu = view.findViewById(R.id.ll_chen_mo_tian_shu);
            tvPreVisitDate = view.findViewById(R.id.tv_pre_visit_date);
            llPreVisitDate = view.findViewById(R.id.ll_pre_visit_date);
            tvFuFangReason = view.findViewById(R.id.tv_fu_fang_reason);
            llFuFangReason = view.findViewById(R.id.ll_fu_fang_reason);
            tvPreJianShenDate = view.findViewById(R.id.tv_pre_jian_shen_date);
            llPreJianShenDate = view.findViewById(R.id.ll_pre_jian_shen_date);
            tvWeiJianShenTime = view.findViewById(R.id.tv_wei_jian_shen_time);
            llWeiJianShenTime = view.findViewById(R.id.ll_wei_jian_shen_time);

            tvLastTiCeTime = view.findViewById(R.id.tv_last_ti_ce_time);
            llLastTiCeTime = view.findViewById(R.id.ll_last_ti_ce_time);

            tv_last_yue_ke_time = view.findViewById(R.id.tv_last_yue_ke_time);
            llZuoRiYueKeTime = view.findViewById(R.id.ll_last_yue_ke_time);

            tv_gou_mai_ke_cheng = view.findViewById(R.id.tv_gou_mai_ke_cheng);
            llGouMaiKeCheng = view.findViewById(R.id.ll_gou_mai_ke_cheng);


            tvHuifangType = view.findViewById(R.id.tv_huifang_type);
            llBt = view.findViewById(R.id.ll_bt);
            iv = view.findViewById(R.id.iv);
            tv = view.findViewById(R.id.tv);
        }
    }
}
