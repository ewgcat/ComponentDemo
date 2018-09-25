package com.yijian.clubmodule.ui.reception;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.ReceptionRecordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class ReceptionHistoryAdapter extends RecyclerView.Adapter<ReceptionHistoryAdapter.ViewHolder> {
    private List<ReceptionRecordBean> list = new ArrayList<>();
    private Context context;

    public ReceptionHistoryAdapter(Context context,List<ReceptionRecordBean> list) {
        this.context = context;
        this.list = list;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reception, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvMemberName;
        private final TextView tvMemberPhone;
        private final ImageView ivMemberSex;
        private final TextView tvReceptionTime;
        private final TextView tvReceptionSale;
        private final TextView tvReceptionReferee;
        private final RelativeLayout rlReferee;
        private final TextView tvReceptionCoach;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMemberName = itemView.findViewById(R.id.tv_member_name);
            ivMemberSex = itemView.findViewById(R.id.iv_member_sex);
            tvMemberPhone = itemView.findViewById(R.id.tv_member_phone);

            tvReceptionTime = itemView.findViewById(R.id.tv_reception_time);
            tvReceptionSale = itemView.findViewById(R.id.tv_reception_sale);
            tvReceptionCoach = itemView.findViewById(R.id.tv_reception_coach);
            rlReferee = itemView.findViewById(R.id.rl_referee);
            tvReceptionReferee = itemView.findViewById(R.id.tv_reception_referee);
        }


        public void bindView(ReceptionRecordBean receptionRecordBean, int position) {
            if (receptionRecordBean == null) return;
            if (!TextUtils.isEmpty(receptionRecordBean.getMemberName()))
                tvMemberName.setText(receptionRecordBean.getMemberName());

            if (!TextUtils.isEmpty(receptionRecordBean.getMobile()))
                tvMemberPhone.setText(receptionRecordBean.getMobile());

            if (!TextUtils.isEmpty(receptionRecordBean.getVisitTime()))
                tvReceptionTime.setText(receptionRecordBean.getVisitTime());

            if (!TextUtils.isEmpty(receptionRecordBean.getSellerName()))
                tvReceptionSale.setText(receptionRecordBean.getSellerName());

            if (!TextUtils.isEmpty(receptionRecordBean.getCoachName()))
                tvReceptionCoach.setText(receptionRecordBean.getCoachName());

            if (!TextUtils.isEmpty(receptionRecordBean.getReferrerUserName())) {
                rlReferee.setVisibility(View.VISIBLE);
                tvReceptionReferee.setText(receptionRecordBean.getReferrerUserName());
            } else {
                rlReferee.setVisibility(View.GONE);
            }

            if (receptionRecordBean.getSex() == 2) {
                Glide.with(context).load(R.mipmap.wt_women).into(ivMemberSex);
            } else {
                Glide.with(context).load(R.mipmap.wt_man).into(ivMemberSex);
            }

        }
    }
}
