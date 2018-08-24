package com.yijian.staff.mvp.huifang.student.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.HuiFangInfo;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.util.Logger;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class HuiFangHistoryAdapter extends RecyclerView.Adapter<HuiFangHistoryAdapter.ViewHolder> {
    private List<HuiFangInfo> mHuiFangInfoList;
    private Context context;

    public HuiFangHistoryAdapter(Context context, List<HuiFangInfo> mHuiFangInfoList) {
        this.mHuiFangInfoList = mHuiFangInfoList;
        this.context = context;
    }


    public void update(List<HuiFangInfo> mHuiFangInfoList) {
        this.mHuiFangInfoList = mHuiFangInfoList;
        notifyDataSetChanged();
    }

    @Override
    public HuiFangHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hui_fang_history, parent, false);
        HuiFangHistoryAdapter.ViewHolder holder = new HuiFangHistoryAdapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HuiFangInfo huiFangInfo = mHuiFangInfoList.get(position);
        resetView(holder);

        holder.bindView(context, huiFangInfo);
    }

    @Override
    public int getItemCount() {
        return mHuiFangInfoList == null ? 0 : mHuiFangInfoList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHead;
        ImageView iv_rank;
        ImageView ivSex;
        TextView tvViperName;
        TextView tvShentiZhuangtai;
        TextView tvJianshenAihao;
        TextView tvXingquAihao;

        LinearLayout llOutdateTime;
        LinearLayout llHetongYuEr;
        LinearLayout llHetongDaoQiRi;
        LinearLayout llPreVisitDate;
        LinearLayout llFuFangReason;
        LinearLayout llCardName;
        LinearLayout llCardType;
        LinearLayout llCardYuEr;
        LinearLayout llZuijinJianshen;
        LinearLayout llChenMoTianShu;
        LinearLayout llBirthdayType;
        LinearLayout llBirthday;
        LinearLayout llDaoFangDate;
        LinearLayout llShangKeTime;
        LinearLayout llCourseName;
        LinearLayout llHuifangJilu;
        LinearLayout llYaoyueJilu;
        LinearLayout llNextVisitTime;

        TextView tvCourseName;
        TextView tvOutdateTime;
        TextView tvHetongDaoQiRi;
        TextView tvCardName;
        TextView tvHetongYuEr;
        TextView tvCardType;
        TextView tvZuijinJianshen;
        TextView tvChenMoTianShu;
        TextView tvPreVisitDate;
        TextView tvFuFangReason;
        TextView tvBirthdayType;
        TextView tvBirthday;
        TextView tvDaoFangDate;
        TextView tvShangKeTime;
        TextView tvHuifangJilu;
        TextView tvYaoyueJilu;
        TextView tvNextVisitTime;


        TextView tvHuifangType;


        public ViewHolder(View view) {
            super(view);
            ivHead = view.findViewById(R.id.iv_head);
            iv_rank = view.findViewById(R.id.iv_rank);
            tvViperName = view.findViewById(R.id.tv_viper_name);
            ivSex = view.findViewById(R.id.iv_sex);
            tvShentiZhuangtai = view.findViewById(R.id.tv_shenti_zhuangtai);
            tvJianshenAihao = view.findViewById(R.id.tv_jianshen_aihao);
            tvXingquAihao = view.findViewById(R.id.tv_xingqu_aihao);
            llCardName = view.findViewById(R.id.ll_card_name);


            llBirthday = view.findViewById(R.id.ll_birthday);
            tvBirthday = view.findViewById(R.id.tv_birthday);

            llCourseName = view.findViewById(R.id.ll_course_name);
            tvCourseName = view.findViewById(R.id.tv_course_name);


            llBirthdayType = view.findViewById(R.id.ll_birthday_type);
            tvBirthdayType = view.findViewById(R.id.tv_birthday_type);

            llOutdateTime = view.findViewById(R.id.ll_outdate_time);
            tvOutdateTime = view.findViewById(R.id.tv_outdate_time);


            llHetongDaoQiRi = view.findViewById(R.id.ll_hetong_dao_qi_ri);
            tvHetongDaoQiRi = view.findViewById(R.id.tv_hetong_dao_qi_ri);


            tvHetongYuEr = view.findViewById(R.id.tv_hetong_yu_er);
            llHetongYuEr = view.findViewById(R.id.ll_hetong_yu_er);

            tvCardName = view.findViewById(R.id.tv_card_name);
            llCardName = view.findViewById(R.id.ll_card_name);
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


            tvDaoFangDate = view.findViewById(R.id.tv_dao_fang_date);
            llDaoFangDate = view.findViewById(R.id.ll_dao_fang_date);


            tvShangKeTime = view.findViewById(R.id.tv_shang_ke_time);
            llShangKeTime = view.findViewById(R.id.ll_shang_ke_time);

            tvHuifangJilu = view.findViewById(R.id.tv_huifang_jilu);
            llHuifangJilu = view.findViewById(R.id.ll_huifang_jilu);
            tvYaoyueJilu = view.findViewById(R.id.tv_yaoyue_jilu);
            llYaoyueJilu = view.findViewById(R.id.ll_yaoyue_jilu);
            tvNextVisitTime= view.findViewById(R.id.tv_next_visit_time);
            llNextVisitTime = view.findViewById(R.id.ll_next_visit_time);

            tvHuifangType = view.findViewById(R.id.tv_huifang_type);

        }

        public void bindView(Context context, HuiFangInfo huiFangInfo) {
            //公共部分


            ImageLoader.setHeadImageResource(BuildConfig.FILE_HOST+huiFangInfo.getHeadUrl(), context, ivHead);
            tvViperName.setText(huiFangInfo.getName());
            int resId = huiFangInfo.getGender() == 1 ? R.mipmap.lg_man : R.mipmap.lg_women;
            Glide.with(context).load(resId).into(ivSex);

            int medalType = huiFangInfo.getMemberMedalType();
            if (medalType==0){

            }else if (medalType==1){
                ImageLoader.setImageResource(R.mipmap.member_gray, context, iv_rank);
            }else if (medalType==2){
                ImageLoader.setImageResource(R.mipmap.member_gold, context, iv_rank);
            }


            tvShentiZhuangtai.setText(huiFangInfo.getHealthStatus());
            tvJianshenAihao.setText(huiFangInfo.getFitnessHobby());
            tvXingquAihao.setText(huiFangInfo.getHobby());
            tvHuifangType.setText(huiFangInfo.getInterviewName());
            String reviewReason = huiFangInfo.getReviewReason();
            int status = huiFangInfo.getStatus();
            if (status == 3) {
                if (!TextUtils.isEmpty(reviewReason)) {
                    llPreVisitDate.setVisibility(View.VISIBLE);
                    llFuFangReason.setVisibility(View.VISIBLE);
                    tvPreVisitDate.setText(huiFangInfo.getLastInterviewTime());
                    tvFuFangReason.setText(reviewReason);
                    tvHuifangType.setText(huiFangInfo.getInterviewName() + " ( 复访 ）");
                }
            }
            int invite = huiFangInfo.getInvite();// 是否邀约, 0未邀约, 1已邀约 ,

            if (invite == 0) {
                llHuifangJilu.setVisibility(View.VISIBLE);
                String result = huiFangInfo.getResult();
                Logger.i("HuiFangHistoryAdapter","result="+result);
                tvHuifangJilu.setText(result);
            } else if (invite == 1) {
                llYaoyueJilu.setVisibility(View.VISIBLE);
                llNextVisitTime.setVisibility(View.VISIBLE);
                String inviteContent = huiFangInfo.getInviteContent();
                tvYaoyueJilu.setText(inviteContent);
                String inviteVisitTime = huiFangInfo.getInviteVisitTime();
                tvNextVisitTime.setText(inviteVisitTime);
            }


            //会员生日回访
            HuiFangInfo.MemberBirthdayInterviewBean memberBirthdayInterview = huiFangInfo.getMemberBirthdayInterview();
            if (memberBirthdayInterview != null) {
                llBirthday.setVisibility(View.VISIBLE);
                llBirthdayType.setVisibility(View.VISIBLE);
                tvBirthday.setText(memberBirthdayInterview.getBirthday());
                tvBirthdayType.setText(memberBirthdayInterview.getBirthdayTypeName());
            }

            //会员过期回访
            HuiFangInfo.MemberPastDueInterviewBean memberPastDueInterview = huiFangInfo.getMemberPastDueInterview();
            if (memberPastDueInterview != null) {
                llCardName.setVisibility(View.VISIBLE);
                llOutdateTime.setVisibility(View.VISIBLE);
                tvCardName.setText(memberPastDueInterview.getCardprodName());
                tvOutdateTime.setText(memberPastDueInterview.getExpireDate());
            }

            //沉寂会员回访
            HuiFangInfo.MemberQuietInterviewBean memberQuietInterview = huiFangInfo.getMemberQuietInterview();
            if (memberQuietInterview != null) {
                llChenMoTianShu.setVisibility(View.VISIBLE);
                llZuijinJianshen.setVisibility(View.VISIBLE);
                String lastTime = memberQuietInterview.getLastTime();
                tvZuijinJianshen.setText(lastTime);
                int intervalDay = memberQuietInterview.getIntervalDay();
                tvChenMoTianShu.setText("" + intervalDay);
            }
            //快到期会员回访
            HuiFangInfo.MemberWillExpireInterviewBean memberWillExpireInterview = huiFangInfo.getMemberWillExpireInterview();
            if (memberWillExpireInterview != null) {
                llHetongDaoQiRi.setVisibility(View.VISIBLE);
                llHetongYuEr.setVisibility(View.VISIBLE);
                llCardName.setVisibility(View.VISIBLE);
                tvCardName.setText(memberWillExpireInterview.getCardprodName());
                String endTime = memberWillExpireInterview.getEndTime();
                tvHetongDaoQiRi.setText(endTime);
                int amount = memberWillExpireInterview.getAmount();
                tvHetongYuEr.setText("" + amount);
            }

            //昨日开卡回访
            HuiFangInfo.MemberYesterdayBuyCardInterviewBean memberYesterdayBuyCardInterview = huiFangInfo.getMemberYesterdayBuyCardInterview();
            if (memberYesterdayBuyCardInterview != null) {
                llCardName.setVisibility(View.VISIBLE);
                llCardType.setVisibility(View.VISIBLE);
                tvCardName.setText(memberYesterdayBuyCardInterview.getCardprodName());
                tvCardType.setText(memberYesterdayBuyCardInterview.getCardTypeName());
            }

            //昨日到访回访
            HuiFangInfo.MemberYesterdayVisitInterviewBean memberYesterdayVisitInterview = huiFangInfo.getMemberYesterdayVisitInterview();
            if (memberYesterdayVisitInterview != null) {
                llDaoFangDate.setVisibility(View.VISIBLE);
                tvDaoFangDate.setText(memberYesterdayVisitInterview.getYesterdayVisitTime());
            }


            //学员生日来访
            HuiFangInfo.StudentBirthdayInterviewBean studentBirthdayInterview = huiFangInfo.getStudentBirthdayInterview();
            if (studentBirthdayInterview != null) {
                llBirthday.setVisibility(View.VISIBLE);
                llBirthdayType.setVisibility(View.VISIBLE);
                tvBirthday.setText(studentBirthdayInterview.getBirthday());
                tvBirthdayType.setText(studentBirthdayInterview.getBirthdayTypeName());
            }

            //昨日上课
            HuiFangInfo.StudentYesterdayInCourseInterviewBean studentYesterdayInCourseInterview = huiFangInfo.getStudentYesterdayInCourseInterview();
            if (studentYesterdayInCourseInterview != null) {
                llShangKeTime.setVisibility(View.VISIBLE);
                tvShangKeTime.setText(studentYesterdayInCourseInterview.getInviteTime());
            }

            //学员到期回访
            HuiFangInfo.StudentPrivateCoursePastDueInterviewBean studentPrivateCoursePastDueInterview = huiFangInfo.getStudentPrivateCoursePastDueInterview();
            if (studentPrivateCoursePastDueInterview != null) {
                llCourseName.setVisibility(View.VISIBLE);
                llOutdateTime.setVisibility(View.VISIBLE);
                tvCourseName.setText(studentPrivateCoursePastDueInterview.getCourseName());
                tvOutdateTime.setText(studentPrivateCoursePastDueInterview.getEndTime());
            }

            //快到期学员回访
            HuiFangInfo.StudentPrivateCourseWillExpireInterviewBean studentPrivateCourseWillExpireInterview = huiFangInfo.getStudentPrivateCourseWillExpireInterview();
            if (studentPrivateCourseWillExpireInterview != null) {
                llCourseName.setVisibility(View.VISIBLE);
                llHetongDaoQiRi.setVisibility(View.VISIBLE);
                tvCourseName.setText(studentPrivateCourseWillExpireInterview.getCourseName());
                tvHetongDaoQiRi.setText(studentPrivateCourseWillExpireInterview.getEndTime());
            }

            //昨日买课回访
            HuiFangInfo.StudentYesterdayBuyCourseInterviewBean studentYesterdayBuyCourseInterview = huiFangInfo.getStudentYesterdayBuyCourseInterview();
            if (studentYesterdayBuyCourseInterview != null) {
                llCourseName.setVisibility(View.VISIBLE);
                tvCourseName.setText(studentYesterdayBuyCourseInterview.getCourseName());
            }


        }

    }

    private void resetView(ViewHolder holder) {
        holder.llBirthday.setVisibility(View.GONE);
        holder.llBirthdayType.setVisibility(View.GONE);
        holder.llHetongDaoQiRi.setVisibility(View.GONE);
        holder.llCardName.setVisibility(View.GONE);
        holder.llCardType.setVisibility(View.GONE);
        holder.llCardYuEr.setVisibility(View.GONE);
        holder.llHetongYuEr.setVisibility(View.GONE);
        holder.llZuijinJianshen.setVisibility(View.GONE);
        holder.llChenMoTianShu.setVisibility(View.GONE);


        holder.llFuFangReason.setVisibility(View.GONE);
        holder.llCourseName.setVisibility(View.GONE);
        holder.llOutdateTime.setVisibility(View.GONE);
        holder.llPreVisitDate.setVisibility(View.GONE);

        holder.llDaoFangDate.setVisibility(View.GONE);
        holder.llHuifangJilu.setVisibility(View.GONE);
        holder.llYaoyueJilu.setVisibility(View.GONE);
        holder.llNextVisitTime.setVisibility(View.GONE);
        holder.llShangKeTime.setVisibility(View.GONE);
    }
}
