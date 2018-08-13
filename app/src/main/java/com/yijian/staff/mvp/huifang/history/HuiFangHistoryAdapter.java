package com.yijian.staff.mvp.huifang.history;

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
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.bean.HuiFangInfo;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.GlideCircleTransform;

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
    public void onBindViewHolder(HuiFangHistoryAdapter.ViewHolder holder, int position) {

        resetView(holder);


        HuiFangInfo huiFangInfo = mHuiFangInfoList.get(position);

        String headImg = huiFangInfo.getHeadUrl();

        holder.tvViperName.setText(huiFangInfo.getName());
        int sex = huiFangInfo.getGender();
        if (0==sex) {
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



        String healthStatus = huiFangInfo.getHealthStatus();
        holder.tvShentiZhuangtai.setText(healthStatus);
        String fitnessHobby = huiFangInfo.getFitnessHobby();
        holder.tvJianshenAihao.setText(fitnessHobby);
        String hobby = huiFangInfo.getHobby();
        holder.tvXingquAihao.setText(hobby);
        String interviewType = huiFangInfo.getInterviewName();
        holder.tvHuifangType.setText(interviewType);
        String interviewResult = huiFangInfo.getResult();
        holder.tv_huifang_result.setText(interviewResult);



    }

    @Override
    public int getItemCount() {
        return mHuiFangInfoList.size();
    }

    private void resetView(HuiFangHistoryAdapter.ViewHolder holder) {
        holder.llQuanyi.setVisibility(View.GONE);
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
        holder.ll_ti_yan_ke_ci_shu.setVisibility(View.GONE);
        holder.ll_dao_fang_date.setVisibility(View.GONE);
        holder.ll_income.setVisibility(View.GONE);
        holder.ll_jianshen_mudi.setVisibility(View.GONE);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHead;
        TextView tvViperName;
        ImageView ivSex;
        TextView tvShentiZhuangtai;
        TextView tvJianshenAihao;
        TextView tvXingquAihao;
        TextView tvCarName;

        LinearLayout llQuanyi;
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
        LinearLayout ll_ti_yan_ke_ci_shu;
        LinearLayout ll_dao_fang_date;
        LinearLayout ll_jianshen_mudi;
        LinearLayout ll_income;

        TextView tvQuanyi;
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
        TextView tv_ti_yan_ke_ci_shu;
        TextView tv_dao_fang_date;
        TextView tv_jianshen_mudi;
        TextView tv_income;


        TextView tvHuifangType;
        TextView tv_huifang_result;


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


            llQuanyi = view.findViewById(R.id.ll_quanyi);
            tvQuanyi = view.findViewById(R.id.tv_quanyi);

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

            tv_ti_yan_ke_ci_shu = view.findViewById(R.id.tv_ti_yan_ke_ci_shu);
            ll_ti_yan_ke_ci_shu = view.findViewById(R.id.ll_ti_yan_ke_ci_shu);

            tv_dao_fang_date = view.findViewById(R.id.tv_dao_fang_date);
            ll_dao_fang_date = view.findViewById(R.id.ll_dao_fang_date);

            tv_jianshen_mudi = view.findViewById(R.id.tv_jianshen_mudi);
            ll_jianshen_mudi = view.findViewById(R.id.ll_jianshen_mudi);
            tv_income = view.findViewById(R.id.tv_income);
            ll_income = view.findViewById(R.id.ll_income);

            tvHuifangType = view.findViewById(R.id.tv_huifang_type);

            tv_huifang_result = view.findViewById(R.id.tv_huifang_result);
        }
    }
}

