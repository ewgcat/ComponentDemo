package com.yijian.staff.mvp.huiji.huifang.task.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;

import com.yijian.staff.mvp.coach.huifang.tianxieresult.CoachTianXieHuiFangResultActivity;
import com.yijian.staff.mvp.huiji.huifang.bean.HuiFangInfo;
import com.yijian.staff.util.CommonUtil;

import java.util.List;

import butterknife.BindView;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class HuiFangTaskAdapter extends RecyclerView.Adapter<HuiFangTaskAdapter.ViewHolder> {

    private List<HuiFangInfo> mHuiFangInfoList;
    private Context context;
    private int type;

    public HuiFangTaskAdapter(Context context, List<HuiFangInfo> mHuiFangInfoList, int type) {
        this.mHuiFangInfoList = mHuiFangInfoList;
        this.context = context;
        this.type = type;
    }

    public void update(List<HuiFangInfo> mHuiFangInfoList) {
        this.mHuiFangInfoList = mHuiFangInfoList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hui_fang_task, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        resetView(holder);

        HuiFangInfo huiFangInfo = mHuiFangInfoList.get(position);
        Glide.with(context).load(R.mipmap.wt_boysmall).into(holder.ivHead);
        holder.tvViperName.setText(huiFangInfo.getName());
        Glide.with(context).load(R.mipmap.lg_man).into(holder.ivSex);
        String huifangType = huiFangInfo.getHuifangType();
        switch (huifangType) {
            case "体验课":
                holder.ll_ti_yan_ke_ci_shu.setVisibility(View.VISIBLE);
                break;
            case "昨日到访":
                holder.ll_dao_fang_date.setVisibility(View.VISIBLE);
                break;
            case "潜在会员":
                holder.ll_jianshen_mudi.setVisibility(View.VISIBLE);
                holder.ll_income.setVisibility(View.VISIBLE);
                break;
            case "易健平台":
                holder.llChenMoTianShu.setVisibility(View.VISIBLE);
                break;
            case "生日回访":
                holder.llBirthday.setVisibility(View.VISIBLE);
                holder.llBirthdayType.setVisibility(View.VISIBLE);
                break;
            case "昨日开卡":
                holder.llHetongDaoQiRi.setVisibility(View.VISIBLE);
                holder.llCardName.setVisibility(View.VISIBLE);
                holder.llCardType.setVisibility(View.VISIBLE);
                break;
            case "沉寂会员":
                holder.llKaiKaDate.setVisibility(View.VISIBLE);
                holder.llCardName.setVisibility(View.VISIBLE);
                holder.llCardType.setVisibility(View.VISIBLE);
                holder.llZuijinJianshen.setVisibility(View.VISIBLE);
                holder.llChenMoTianShu.setVisibility(View.VISIBLE);
                break;
            case "恢复健身":
                holder.llPreJianShenDate.setVisibility(View.VISIBLE);
                holder.llWeiJianShenTime.setVisibility(View.VISIBLE);
                break;
            case "过期回访":
                holder.llQuanyi.setVisibility(View.VISIBLE);
                holder.llOutdateTime.setVisibility(View.VISIBLE);
                holder.llOutdateReason.setVisibility(View.VISIBLE);
                if (position == 0) {
                    holder.tv.setText("填写回访结果");
                    Glide.with(context).load(R.mipmap.wt_huifangjieguo).into(holder.iv);
                    holder.tvQuanyi.setText(huiFangInfo.getQuanyi());
                    holder.tvOutdateTime.setText(huiFangInfo.getOutdateTime());
                    holder.tvOutdateReason.setText(huiFangInfo.getOutdateReason());
                }
                break;
            case "复访":
                holder.llPreVisitDate.setVisibility(View.VISIBLE);
                holder.llFuFangReason.setVisibility(View.VISIBLE);
                break;
            case "快到期回访":
                holder.llHetongDaoQiRi.setVisibility(View.VISIBLE);
                holder.llHetongYuEr.setVisibility(View.VISIBLE);
                break;

        }
        holder.tvHuifangType.setText(huifangType);


        holder.llBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = holder.tv.getText().toString();
                if (text.equals("回访")) {
                    CommonUtil.callPhone(context, "13386170640");
                } else {
                    Intent i = new Intent(context, CoachTianXieHuiFangResultActivity.class);
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHuiFangInfoList.size();
    }

    private void resetView(ViewHolder holder) {
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
            llBt = view.findViewById(R.id.ll_bt);
            iv = view.findViewById(R.id.iv);
            tv = view.findViewById(R.id.tv);
        }
    }
}
