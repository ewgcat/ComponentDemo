package com.yijian.staff.mvp.huiji.huifang.history;

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
import com.yijian.staff.mvp.huiji.huifang.bean.HuiFangInfo;
import com.yijian.staff.mvp.huiji.huifang.bean.HuiFangTypeBean;
import com.yijian.staff.mvp.huiji.huifang.task.adapter.HuiFangTaskAdapter;
import com.yijian.staff.mvp.huiji.huifang.tianxieresult.HuijiTianXieHuiFangResultActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.GlideCircleTransform;
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
    public void onBindViewHolder(HuiFangHistoryAdapter.ViewHolder holder, int position) {

        resetView(holder);


        HuiFangInfo huiFangInfo = mHuiFangInfoList.get(position);

        String headImg = huiFangInfo.getHeadImg();

        holder.tvViperName.setText(huiFangInfo.getName());
        String sex = huiFangInfo.getSex();
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

        String carBrand = huiFangInfo.getCarBrand();
        holder.tvCarName.setText(carBrand);

        String healthStatus = huiFangInfo.getHealthStatus();
        holder.tvShentiZhuangtai.setText(healthStatus);

        String fitnessHobby = huiFangInfo.getFitnessHobby();
        holder.tvJianshenAihao.setText(fitnessHobby);

        String hobby = huiFangInfo.getHobby();
        holder.tvXingquAihao.setText(hobby);


        String interviewType = huiFangInfo.getInterviewType();
        holder.tvHuifangType.setText(interviewType);
        String interviewResult = huiFangInfo.getInterviewResult();
        holder.tv_huifang_result.setText(interviewResult);

        /**
         *  mTitleList.add("全部");0
         mTitleList.add("生日");1
         mTitleList.add("昨日到访");2
         mTitleList.add("昨日开卡");3
         mTitleList.add("潜在会员");4
         mTitleList.add("沉寂会员");5
         mTitleList.add("恢复健身");6
         mTitleList.add("复访");7
         mTitleList.add("过期");8
         mTitleList.add("快到期");9
         mTitleList.add("易健平台");10
         mTitleList.add("体验课");11
         mTitleList.add("意向会员");16
         */
        String subclassName = huiFangInfo.getSubclassName();
        switch (subclassName) {

            case "BirthdayVO"://生日回访
                holder.llBirthday.setVisibility(View.VISIBLE);
                holder.llBirthdayType.setVisibility(View.VISIBLE);
                Long birthday = huiFangInfo.getBirthday();
                if (birthday != null && birthday != -1) {
                    String s = DateUtil.parseLongDateToDateString(birthday);
                    holder.tvBirthday.setText(s);
                }
                String birthdayType = huiFangInfo.getBirthdayType();
                if (!TextUtils.isEmpty(birthdayType)) {
                    holder.tvBirthdayType.setText(birthdayType);
                }
                break;
            case "ExpireVO"://过期回访
                holder.llOutdateTime.setVisibility(View.VISIBLE);
                holder.llOutdateReason.setVisibility(View.VISIBLE);
                String expiryReason = huiFangInfo.getExpiryReason();
                if (!TextUtils.isEmpty(expiryReason)) {
                    holder.tvOutdateReason.setText(expiryReason);
                }
                Long deadline = huiFangInfo.getDeadline();
                if (deadline != null && deadline != -1) {
                    String s = DateUtil.parseLongDateToDateString(deadline);
                    holder.tvOutdateTime.setText(s);
                }
                break;
            case "ReVO"://复访
                holder.llPreVisitDate.setVisibility(View.VISIBLE);
                holder.llFuFangReason.setVisibility(View.VISIBLE);
                Long lastVisitTime = huiFangInfo.getLastVisitTime();
                if (lastVisitTime != null && lastVisitTime != -1) {
                    String s = DateUtil.parseLongDateToDateString(lastVisitTime);
                    holder.tvPreVisitDate.setText(s);
                }
                String reinterviewReason = huiFangInfo.getReinterviewReason();
                holder.tvFuFangReason.setText(reinterviewReason);
                break;
            case "NearExpireVO"://快到期回访
                holder.llHetongDaoQiRi.setVisibility(View.VISIBLE);
                holder.llHetongYuEr.setVisibility(View.VISIBLE);

                holder.tvHetongYuEr.setText(huiFangInfo.getContractBalance());
                Long time = huiFangInfo.getDeadline();
                if (time != null && time != -1) {
                    String s = DateUtil.parseLongDateToDateString(time);
                    holder.tvHetongDaoQiRi.setText(s);
                }
                break;

            case "YesterdayVisitVO"://昨日到访
                holder.ll_dao_fang_date.setVisibility(View.VISIBLE);
                Long visitTime = huiFangInfo.getVisitTime();
                if (visitTime != null && visitTime != -1) {
                    String s = DateUtil.parseLongDateToDateString(visitTime);
                    holder.tv_dao_fang_date.setText(s);
                }
                break;
            case "ReFitVO":
                holder.llPreJianShenDate.setVisibility(View.VISIBLE);
                holder.llWeiJianShenTime.setVisibility(View.VISIBLE);
                String lastFitTime = huiFangInfo.getLastFitTime();
                holder.tvPreJianShenDate.setText(lastFitTime);
                Long breakDay = huiFangInfo.getBreakDay();
                if (breakDay!=null&&breakDay!=-1){
                    holder.tvWeiJianShenTime.setText(breakDay+"");
                }
                break;
            case "PotentialVO"://潜在会员
                break;
            case "EjoyVO":
                holder.llChenMoTianShu.setVisibility(View.VISIBLE);
                Long sinkDay = huiFangInfo.getSinkDay();
                if (sinkDay!=null&&sinkDay!=-1){
                    holder.tvChenMoTianShu.setText(sinkDay+"");
                }
                break;

            case "YesterdayOpenVO":
//                holder.llHetongDaoQiRi.setVisibility(View.VISIBLE);
                holder.llCardName.setVisibility(View.VISIBLE);
                holder.llCardType.setVisibility(View.VISIBLE);
                holder.tvCardName.setText(huiFangInfo.getCardName());
                holder.tvCardType.setText(huiFangInfo.getCardType());
                break;
            case "QuietVO":
                holder.llKaiKaDate.setVisibility(View.VISIBLE);
                holder.llCardName.setVisibility(View.VISIBLE);
                holder.llCardType.setVisibility(View.VISIBLE);
                holder.llZuijinJianshen.setVisibility(View.VISIBLE);
                holder.llChenMoTianShu.setVisibility(View.VISIBLE);
                holder.tvCardName.setText(huiFangInfo.getCardName());
                holder.tvCardType.setText(huiFangInfo.getCardType());
                Long openCardTime = huiFangInfo.getOpenCardTime();
                if (openCardTime!=null&&openCardTime!=-1){
                    String s = DateUtil.parseLongDateToDateString(openCardTime);
                    holder.tvKaiKaDate.setText(s);
                }

                Long recentlyFitTime = huiFangInfo.getRecentlyFitTime();
                if (recentlyFitTime!=null&&recentlyFitTime!=-1){
                    String s = DateUtil.parseLongDateToDateString(recentlyFitTime);
                    holder.tvZuijinJianshen.setText(s);
                }

                Long sinkDay1 = huiFangInfo.getSinkDay();
                if (sinkDay1!=null&&sinkDay1!=-1){
                    holder.tvChenMoTianShu.setText(sinkDay1+"");
                }
                break;
        }



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

