package com.yijian.staff.mvp.coach.huifang.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.huifang.bean.CoachHuiFangInfo;
import com.yijian.staff.util.Logger;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class CoachHuiFangHistoryAdapter extends RecyclerView.Adapter<CoachHuiFangHistoryAdapter.ViewHolder> {
    private List<CoachHuiFangInfo> mCoachHuiFangInfoList;
    private Context context;

    public CoachHuiFangHistoryAdapter(Context context, List<CoachHuiFangInfo> mCoachHuiFangInfoList) {
        this.mCoachHuiFangInfoList = mCoachHuiFangInfoList;
        this.context = context;
    }

    @Override
    public CoachHuiFangHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hui_fang_history, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void update(List<CoachHuiFangInfo> mCoachHuiFangInfoList){
        this.mCoachHuiFangInfoList = mCoachHuiFangInfoList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(CoachHuiFangHistoryAdapter.ViewHolder holder, int position) {
        Logger.i("CoachHuiFangTaskAdapter", "position: " + position);
        CoachHuiFangInfo coachHuiFangInfo = mCoachHuiFangInfoList.get(position);
        Glide.with(context).load(R.mipmap.wt_boysmall).into(holder.ivHead);
        holder.viperName.setText(coachHuiFangInfo.getName());
        Glide.with(context).load(R.mipmap.lg_man).into(holder.viperSex);
        holder.tvQuanyi.setText(coachHuiFangInfo.getQuanyi());
        holder.tvOutdateTime.setText(coachHuiFangInfo.getOutdateTime());
        holder.tvHuifangType.setText(coachHuiFangInfo.getHuifangType());
        holder.tvHuifangResult.setText(coachHuiFangInfo.getHuifangReason());
    }

    @Override
    public int getItemCount() {
        return mCoachHuiFangInfoList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivHead;
        TextView viperName;
        ImageView viperSex;
        TextView tvQuanyi;
        TextView tvOutdateTime;
        TextView tvOutdateReason;
        TextView tvHuifangType;
        TextView tvHuifangResult;

        public ViewHolder(View view) {
            super(view);
            ivHead = view.findViewById(R.id.iv_head);
            viperName = view.findViewById(R.id.tv_viper_name);
            viperSex = view.findViewById(R.id.iv_sex);
            tvQuanyi = view.findViewById(R.id.tv_quanyi);
            tvOutdateTime = view.findViewById(R.id.tv_outdate_time);
            tvOutdateReason = view.findViewById(R.id.tv_outdate_reason);
            tvHuifangType = view.findViewById(R.id.tv_huifang_type);
            tvHuifangResult = view.findViewById(R.id.tv_huifang_result);
        }
    }
}
